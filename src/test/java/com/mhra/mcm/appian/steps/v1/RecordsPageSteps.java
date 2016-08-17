package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.po.RecordsPage;
import com.mhra.mcm.appian.po.sections.filters.RecordsFilter;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Scope;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
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


    @Given("^I update status of an existing notification to \"([^\"]*)\"$")
    public void i_update_status_of_existing_notification_to(String updatedStatus) throws Throwable {
        //Select an existing notification from the page
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = recordsPage.getARandomNotificationWithStatusNotEqualTo(updatedStatus);
        log.info("ECID selected : " + ecid);
        scenarioSession.putData(SessionKey.ECID, ecid);

        //update notification
        notificationDetails = recordsPage.clickNotificationNumber(ecid);
        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.updateStatusTo(updatedStatus);
    }

    @Given("^I update status of stored notification to \"([^\"]*)\"$")
    public void i_update_status_of_stored_notification_to(String updatedStatus) throws Throwable {
        //Select an existing notification from the page
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);

        //update notification
        notificationDetails = recordsPage.clickNotificationNumber(ecid);
        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.updateStatusTo(updatedStatus);
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
    @Then("^I have a notification \"([^\"]*)\" generated$")
    public void i_should_see_the_notification_generated(String expectedNotificationID) throws Throwable {
        scenarioSession.putData(SessionKey.ECID, expectedNotificationID);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID);
        boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
        assertThat("Expected header to contains EC ID : " + expectedNotificationID , contains, is(equalTo(true)));

        //Add a toxicology report
        Notification random = NotificationUtils.updateDefaultNotification(null);
        random.getIngredient().ingredientName="SUPPA1";
        editNotification = notificationDetails.clickManageDocuments();
        notificationDetails = editNotification.addGenericToxicologyReportFromTempFolder("ToxicologyReport.pdf", random);
    }


    @Then("^I should see the stored notification$")
    public void i_should_see_the_stored_notification() throws Throwable {
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();

        String expectedNotificationID = (String) scenarioSession.getData(SessionKey.ECID);
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID);
        boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
        assertThat("Expected header to contains EC ID : " + expectedNotificationID , contains, is(equalTo(true)));
    }

    @Then("^I should see only (.*) notification$")
    public void i_should_see_only_single_notification(int countExpected) throws Throwable {
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);
        int count = recordsPage.getNotificationCount(ecid);
        String submitter = recordsPage.getSubmitterNameForEcid(ecid);
        scenarioSession.putData(SessionKey.submitter, submitter);
        assertThat("Expected to see 1 notification but was : " + count , count, is(equalTo(countExpected)));
    }


    @Then("^I should see (.*) or more notification$")
    public void i_should_see_X_number_OF_notification(int countExpected) throws Throwable {
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);
        int count = recordsPage.getNotificationCount(ecid);
        String submitter = recordsPage.getSubmitterNameForEcid(ecid);
        scenarioSession.putData(SessionKey.submitter, submitter);
        assertThat("Expected to see at least 1 notification but was : " + count , count, is(greaterThanOrEqualTo(countExpected)));
    }


    @Then("^I should see at least (.*) notification$")
    public void i_should_see_atleaset_X_number_OF_notification(int countExpected) throws Throwable {
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);
        int count = recordsPage.getNotificationCount(ecid);
        assertThat("Expected to see at least 1 notification but was : " + count , count, is(greaterThanOrEqualTo(countExpected)));
    }


    @Given("^I attach a toxicology report for \"([^\"]*)\"$")
    public void i_attach_a_toxicology_reporth_with_following_data(String ingredient) throws Throwable {
        Notification notification = (Notification) scenarioSession.getData(SessionKey.storedNotification);
        String ecId = notification.ecIDNumber;

        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(ecId);

        editNotification = notificationDetails.clickManageDocuments();
        notificationDetails = editNotification.addGenericToxicologyReportFromTempFolder("ToxicologyReport.pdf", notification);
    }


    @When("^I search for an existing notification by \"([^\"]*)\"$")
    public void i_search_for_an_existing_notification_by(String searchType) throws Throwable {

        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();

        if(searchType.trim().toLowerCase().equals("ecid")){
            //Select an existing notification from the page
            String ecid = recordsPage.getARandomNotificationECIDFromPosition(0);
            scenarioSession.putData(SessionKey.ECID, ecid);

            //Search for the ecid
            recordsPage = recordsPage.searchForECIDSubmitterOrOthers(ecid);
        }
    }

    @When("^I search for an existing notification by partial \"([^\"]*)\"$")
    public void i_search_for_an_existing_notification_by_partial(String searchType) throws Throwable {

        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();

        if(searchType.trim().toLowerCase().equals("ecid")){
            //Select an existing notification from the page
            String ecid = recordsPage.getARandomNotificationECIDFromPosition(0);
            if(ecid.contains("-"))
            ecid = ecid.substring(0, ecid.indexOf("-"));
            scenarioSession.putData(SessionKey.ECID, ecid);

            //Search for the ecid
            recordsPage = recordsPage.searchForECIDSubmitterOrOthers(ecid);
        }
    }

    @When("^I search for the stored submitter name$")
    public void i_search_for_the_stored_submitter_name() throws Throwable {
        //String menu = mainNavigationBar.getCurrentSelectedMenu();
        //if(menu==null || !menu.equals("Records")){
            recordsPage = mainNavigationBar.clickRecords();
            recordsPage = recordsPage.clickNotificationsLink();
        //}

        String submitter = (String) scenarioSession.getData(SessionKey.submitter);

        if(submitter!=null){
            //Search for the ecid
            recordsPage = recordsPage.searchForECIDSubmitterOrOthers(submitter);
        }
    }

    @When("^I go to the notifications page$")
    public void i_go_to_the_notifications_page() throws Throwable {
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
    }

    @When("^I filter by status \"([^\"]*)\"$")
    public void i_filter_by_status(String filterByStatus) throws Throwable {
        RecordsFilter filterSection = recordsPage.getFilterSection();
        //filterSection = filterSection.clearSelection();
        filterSection = filterSection.expand();
        //recordsPage = filterSection.filterByStatus(filterByStatus);
        recordsPage = filterSection.clickFilterText(filterByStatus);
    }

    @Then("^I should only see notifications where status is \"([^\"]*)\"$")
    public void i_should_only_see_notifications_in_the_selected_status(String filterBy) throws Throwable {
        recordsPage = new RecordsPage(driver);
        boolean allStatusSame = recordsPage.isAllNotificationStatusOfType(filterBy);
        assertThat("Expected to see notifications with status : " + filterBy , allStatusSame, is(equalTo(true)));
    }


    @When("^I clear the selected filter \"([^\"]*)\"$")
    public void i_go_to_the_notifications_page(String filter) throws Throwable {
        RecordsFilter filterSection = recordsPage.getFilterSection();
        filterSection.waitForOptionsToBeClickable();
        recordsPage = filterSection.clearSelection(filter);
    }

}
