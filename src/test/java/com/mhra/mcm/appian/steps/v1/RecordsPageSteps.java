package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.po.RecordsPage;
import com.mhra.mcm.appian.po.sections.contents.NotificationDetails;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.springframework.context.annotation.Scope;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class RecordsPageSteps extends CommonSteps {

    @And("^I have notifications$")
    public void i_have_notifications() throws Throwable {
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        boolean hasNotifications = recordsPage.hasNotifications();
        assertThat(hasNotifications, is(equalTo(true)));
    }

    @Then("^I should be able to edit notification$")
    public void i_should_see_be_able_to_edit_notification() throws Throwable {
        recordsPage = recordsPage.clickNotificationNumber(1);
        boolean edited = recordsPage.isNotificationEditable();
        assertThat(edited, is(equalTo(true)));
    }

    @Then("^I should not be able to edit notification$")
    public void i_should_not_be_able_to_edit_notification() throws Throwable {
        recordsPage = recordsPage.clickNotificationNumber(1);
        boolean edited = recordsPage.isNotificationEditable();
        assertThat(edited, is(equalTo(false)));
    }


    @Given("^I make change to submitter name by appending \"([^\"]*)\"$")
    public void i_make_change_to_submitter_name_by_appending(String append) throws Throwable {
        recordsPage = recordsPage.clickNotificationNumber(1);
        notificationPage = recordsPage.clickManageNotification();
        String existingName = notificationPage.appendTextToSubmitterName(append);
        recordsPage = notificationPage.submitChanges();
        scenarioSession.putData(SessionKey.storedValue, existingName);
    }

    @Then("^I should see the submitter name containing \"([^\"]*)\"$")
    public void i_should_see_the_submitter_name_showing(String append) throws Throwable {
        String previousName = (String) scenarioSession.getData(SessionKey.storedValue);
        String expectedName = previousName + append;

        //verfiy page contains the updated information
        boolean contains = recordsPage.notificationsPageContainsText(expectedName);
        assertThat(contains, is(equalTo(true)));
    }

    /***
     * remove this : only created so we don't have 100s of notifications
     * @param expectedNotificationID
     * @throws Throwable
     */
    @Then("^I should see the notification \"([^\"]*)\" generated$")
    public void i_should_see_the_notification_generated(String expectedNotificationID) throws Throwable {
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID);
        boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
        assertThat("Expected header to contains EC ID : " + expectedNotificationID , contains, is(equalTo(true)));
    }
}
