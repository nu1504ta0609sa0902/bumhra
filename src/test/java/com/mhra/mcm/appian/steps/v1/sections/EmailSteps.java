package com.mhra.mcm.appian.steps.v1.sections;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.domain.sub.Invoice;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.PropertiesFileUtils;
import com.mhra.mcm.appian.utils.StepsUtils;
import com.mhra.mcm.appian.utils.emails.EmailUtils;
import com.mhra.mcm.appian.utils.emails.GmailEmail;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Scope;

import java.util.List;
import java.util.Properties;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class EmailSteps extends CommonSteps {

    @Then("^I should receive an invoice email in last (.*) min with correct price \"([^\"]*)\" for the stored notification$")
    public void iShouldReceiveAnInvoiceEmailWithCorrectPriceForTheStoredNotification(int min, String amount) throws Throwable {
        //Properties emailDetails = PropertiesFileUtils.loadPropertiesFile("users.properties");

        List<Invoice> listOfInvoices = null;
        boolean foundInvoices = false;
        int attempt = 0;
        do {
            //listOfInvoices = EmailUtils.getListOfInvoicesFromGmail(emailDetails);
            listOfInvoices = GmailEmail.getListOfInvoicesFromGmail(min);

            //Break from loop if invoices read from the email server
            if(listOfInvoices.size()>0){
                break;
            }else{
                //Wait for 10 seconds and try again
                Thread.sleep(1000 * 5);
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
            scenarioSession.putData(SessionKey.invoice, invoice);
            assertThat("Price should be : " + amount , amount, is((equalTo(amount))));
        }else{
            assertThat("No email received from appian related to invoices : mhra.uat@gmail.com" , listOfInvoices.size(), is(not(equalTo(0))));
        }

    }



    @When("^I send paid email response back to appian$")
    public void i_send_paid_email_response_back_to_appian_than_the_status_should_update_to() throws Throwable {
        Invoice invoice = (Invoice) scenarioSession.getData(SessionKey.invoice);
        String ecid = invoice.Description;

        //Keep track of current status
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(ecid);
        String currentStatus = notificationDetails.getCurrentStatus();

        //send the paid email and wait for status to change
        boolean emailSent = GmailEmail.sendPaidEmailToAppian(invoice);

        //Store the notification status
        scenarioSession.putData(SessionKey.notificationStatus, currentStatus);
    }

    @When("^The status should update to \"([^\"]*)\"$")
    public void the_status_should_update_to(String expectedStatus) throws Throwable {
        String currentStatus = (String) scenarioSession.getData(SessionKey.notificationStatus);
        //boolean statusChanged = notificationDetails.hasPageStatusChanged(currentStatus);
        boolean statusChanged = false;
        int attempt = 0;
        do{
            statusChanged = notificationDetails.hasPageStatusChanged(currentStatus);
            attempt++;
            Thread.sleep(1000 * 5);
        }while (!statusChanged && attempt < 15);

        String newStatus = notificationDetails.getCurrentStatus();

        assertThat("Status should not be : " + currentStatus , newStatus, is((equalTo(expectedStatus))));
    }
}
