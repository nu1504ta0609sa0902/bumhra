package com.mhra.mcm.appian.steps.v1.sections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isOneOf;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

import java.util.List;

import com.mhra.mcm.appian.utils.helpers.GenericUtils;
import org.springframework.context.annotation.Scope;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.domain.webPagePojo.sub.Invoice;
import com.mhra.mcm.appian.pageobjects.sections.MainNavigationBar;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.emails.GmailEmail;
import com.mhra.mcm.appian.utils.helpers.page.StepsUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class EmailSteps extends CommonSteps {

    @Then("^I should receive an invoice email with heading \"([^\"]*)\" from appian in next (.*) min with correct price \"([^\"]*)\" for the stored notification$")
    public void iShouldReceiveAnInvoiceEmailWithCorrectPriceForTheStoredNotification(String heading, int min, String amount) throws Throwable {
        //Properties emailDetails = FileUtils.loadPropertiesFile("users.properties");

        List<Invoice> listOfInvoices = null;
        boolean foundInvoices = false;
        int attempt = 0;
        do {
            String ecID = (String) scenarioSession.getData(SessionKey.ECID);
            listOfInvoices = GmailEmail.getListOfInvoicesFromGmail(min, ecID, heading);

            //Break from loop if invoices read from the email server
            if(listOfInvoices.size()>0){
                break;
            }else{
                //Wait for 10 seconds and try again, Thread.sleep required because this is checking email
                WaitUtils.nativeWait(8);
            }
            attempt++;
        }while(!foundInvoices && attempt < 12);

        //verify the price and other details
        if(listOfInvoices!=null && listOfInvoices.size() > 0) {
            Notification notification = (Notification) scenarioSession.getData(SessionKey.storedNotification);
            String ecID = (String) scenarioSession.getData(SessionKey.ECID);
            if(notification!=null){
                ecID = notification.ecIDNumber;
            }
            Invoice invoice = StepsUtils.getInvoiceForNotification(listOfInvoices, ecID);

            //Verify details
            if(amount!=null && !amount.equals("")) {
                String netAmount = invoice.Net_Amount;
                assertThat("Price should be : " + amount, amount, is((equalTo(netAmount))));
            }

            //Store data in map
            scenarioSession.putData(SessionKey.invoice, invoice);
            scenarioSession.putData(SessionKey.listOfInvoices, listOfInvoices);
        }else{
            assertThat("No email received from appian related to invoices : mhra.uat@gmail.com" , listOfInvoices.size(), is(not(equalTo(0))));
        }

    }


    @Then("^If I receive an invoice email with heading \"([^\"]*)\" from appian in next (.*) min than the invoice should not contain my notification$")
    public void iShouldReceiveAnInvoiceEmailButItShouldNotContainMyNotification(String heading, int min) throws Throwable {

        String ecID = (String) scenarioSession.getData(SessionKey.ECID);
        List<Invoice> listOfInvoices = null;
        boolean foundInvoices = false;
        int attempt = 0;
        do {
            listOfInvoices = GmailEmail.getListOfInvoicesFromGmail(min, ecID, heading);

            //Break from loop if invoices read from the email server
            if(listOfInvoices.size()>0){
                break;
            }else{
                //Wait for 10 seconds and try again, Thread.sleep required because this is checking email
                WaitUtils.nativeWait(1);
            }
            attempt++;
        }while(!foundInvoices && attempt < 12);

        if(listOfInvoices.size() > 0){
            Invoice invoice = StepsUtils.getInvoiceForNotification(listOfInvoices, ecID);
            assertThat("We should not receive any invoice for notification with ecid : " + ecID + " because it has no TCANumber check email : mhra.uat@gmail.com", invoice, nullValue());
        }else {
            //verify the price and other details
            assertThat("We should not receive any invoice for notification with ecid : " + ecID + " because it has no TCANumber check email : mhra.uat@gmail.com", listOfInvoices.size(), is((equalTo(0))));
        }
    }


    @When("^I send paid email response back to appian$")
    public void i_send_paid_email_response_back_to_appian_than_the_status_should_update_to() throws Throwable {
        Invoice invoice = (Invoice) scenarioSession.getData(SessionKey.invoice);
        String ecid = invoice.Description;

        //Keep track of current status
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
        String currentStatus = notificationDetails.getCurrentStatus();

        //send the paid email and wait for status to change
        boolean emailSent = GmailEmail.sendPaidEmailToAppian(invoice);

        //Store the notification status
        scenarioSession.putData(SessionKey.notificationStatus, currentStatus);
    }

    @When("^The notification status should update to \"([^\"]*)\"$")
    public void the_status_should_update_to(String expectedStatus) throws Throwable {
        String currentStatus = (String) scenarioSession.getData(SessionKey.notificationStatus);
        //boolean statusChanged = notificationDetails.hasPageStatusChangedTo(currentStatus);
        boolean statusChanged = false;
        int attempt = 0;
        do{
            statusChanged = notificationDetails.hasPageStatusChangedTo(currentStatus);
            if(statusChanged)
                break;
            attempt++;
        }while (!statusChanged && attempt < 15);

        String newStatus = notificationDetails.getCurrentStatus();

        assertThat("Status should not be : " + currentStatus , newStatus, is(isOneOf(expectedStatus,"Quality Assurance")));
        scenarioSession.putData(SessionKey.notificationStatus, newStatus);
    }


    @Given("^I should see the stored notification with status set to \"([^\"]*)\"$")
    public void i_should_see_new_task_generated_for_the_submitter(String expectedStatus) throws Throwable {
        //Stored data to verify
        Notification data = (Notification) scenarioSession.getData(SessionKey.storedNotification);
        String expectedNotificationID = data.ecIDNumber;

        //Verify notification generated
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID, 5);
        boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
        assertThat("Expected header to contains EC ID : " + expectedNotificationID , contains, is(equalTo(true)));

        //Verify status
        boolean statusMatched = false;
        int attempt = 0;
        do{
            statusMatched = notificationDetails.expectedStatusToBe(expectedStatus);
            if(statusMatched)
                break;
            attempt++;
        }while (!statusMatched && attempt < 15);

        String newStatus = notificationDetails.getCurrentStatus();

        assertThat("Status should be : " + expectedStatus , newStatus, is((equalTo(expectedStatus))));
    }


    @Then("^The invoice should contain correct code \"([^\"]*)\" and other details$")
    public void the_invoice_should_contain_correct_code_and_other_details(String expectedGlcode) throws Throwable {
        Invoice invoice = (Invoice) scenarioSession.getData(SessionKey.invoice);
        String expectedNotificationID = (String) scenarioSession.getData(SessionKey.ECID);
        //Notification data = (Notification) scenarioSession.getData(SessionKey.storedNotification);
        //String expectedNotificationID = data.ecIDNumber;
        String glCode = invoice.Natural_Account;
        String description = invoice.Description;
        assertThat("Expected GLCode : " + expectedGlcode , glCode, is((equalTo(expectedGlcode))));
        assertThat("Description should contain : " + expectedNotificationID , description.contains(expectedNotificationID), is((equalTo(true))));
    }


    @Then("^The invoices should be unique by invoice id$")
    public void the_invoices_should_be_unique_by_invoice_id() throws Throwable {
        List<Invoice> loi = (List<Invoice>) scenarioSession.getData(SessionKey.listOfInvoices);
        boolean isUniqueInvoiceIds = GenericUtils.isInvoiceUnique(loi);

        assertThat("Expected unique invoice ids "  , isUniqueInvoiceIds, is((equalTo(true))));
    }
}
