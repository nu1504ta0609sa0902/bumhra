package com.mhra.mcm.appian.steps.v1.sections;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.domain.sub.Invoice;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.PropertiesFileUtils;
import com.mhra.mcm.appian.utils.StepsUtils;
import com.mhra.mcm.appian.utils.emails.EmailUtils;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
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

    @Then("^I should receive an invoice email with correct price \"([^\"]*)\" for the stored notification$")
    public void iShouldReceiveAnInvoiceEmailWithCorrectPriceForTheStoredNotification(String amount) throws Throwable {
        Properties emailDetails = PropertiesFileUtils.loadPropertiesFile("users.properties");

        List<Invoice> listOfInvoices = null;
        boolean foundInvoices = false;
        int attempt = 0;
        do {
            listOfInvoices = EmailUtils.getListOfInvoicesFromGmail(emailDetails);

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
            Invoice invoice = StepsUtils.getInvoiceForNotification(listOfInvoices, notification);

            //Verify details
            scenarioSession.putData(SessionKey.invoice, invoice);
            assertThat("Price should be : " + amount , amount, is(not(equalTo(amount))));
        }else{
            assertThat("No email received from appian related to invoices : mhra.uat@gmail.com" , listOfInvoices.size(), is(not(equalTo(0))));
        }
        System.out.println("Done");
    }
}
