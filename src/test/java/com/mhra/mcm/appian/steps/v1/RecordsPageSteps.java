package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.domain.webPagePojo.sub.Address;
import com.mhra.mcm.appian.domain.xmlPojo.EcigProductSubmission;
import com.mhra.mcm.appian.pageobjects.RecordsPage;
import com.mhra.mcm.appian.pageobjects.sections.MainNavigationBar;
import com.mhra.mcm.appian.pageobjects.sections.contents.Documents;
import com.mhra.mcm.appian.pageobjects.sections.filters.RecordsFilter;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.others.FileUtils;
import com.mhra.mcm.appian.utils.helpers.others.GenericUtils;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Scope;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class RecordsPageSteps extends CommonSteps {


    @And("^I have notifications$")
    public void i_have_notifications() throws Throwable {
        mainNavigationBar = new MainNavigationBar(driver);
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
        WaitUtils.nativeWait(2);
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
        editNotification = recordsPage.clickManageNotification();
        String existingName = editNotification.appendTextToSubmitterName(append);
        notificationDetails = editNotification.submitChanges();
        scenarioSession.putData(SessionKey.storedValue, existingName);
    }


    @Given("^I goto notifications page and update status of an existing notification to \"([^\"]*)\"$")
    public void i_goto_notifications_page_and_update_status_of_existing_notification_to(String updatedStatus) throws Throwable {
        //Select an existing notification from the page
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = recordsPage.getARandomNotificationWithStatusNotEqualTo(updatedStatus, 10);
        log.info("ECID selected : " + ecid);
        scenarioSession.putData(SessionKey.ECID, ecid);

        //update notification
        notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
        editNotification = notificationDetails.clickManageNotification();
        //notificationDetails = editNotification.updateStatusTo(updatedStatus);

        //Update with new ecid
        String newECID = GenericUtils.generateECIDBySpecifiedNumber(1, ecid);
        notificationDetails = editNotification.updateNotificationStatus(updatedStatus, ecid, newECID);
        log.info("Previous ECID : " + ecid);
        log.info("New ECID : " + newECID);
        scenarioSession.putData(SessionKey.ECID, newECID);
    }


    @Given("^Set a new ecid for stored notification$")
    public void set_a_new_ecid_for_stored_notifications() throws Throwable {
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);
        String newECID = GenericUtils.generateECIDBySpecifiedNumber(1, ecid);
        log.info("Previous ECID : " + ecid);
        log.info("New ECID : " + newECID);
        scenarioSession.putData(SessionKey.ECID, newECID);

        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.updateNotificationStatus(null, ecid, newECID);
    }

    @Given("^I update status of an existing notification to \"([^\"]*)\"$")
    public void i_update_status_of_existing_notification_to(String updatedStatus) throws Throwable {
        //Select an existing notification from the page
        String ecid = recordsPage.getARandomNotificationWithStatusNotEqualTo(updatedStatus, 10);
        log.info("ECID selected : " + ecid);
        scenarioSession.putData(SessionKey.ECID, ecid);

        //update notification
        notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.updateStatusTo(updatedStatus);
    }


    @Given("^I select notification with status \"([^\"]*)\" and update status to \"([^\"]*)\"$")
    public void i_update_status_of_existing_notification_to(String status, String updatedStatus) throws Throwable {
        //Select an existing notification from the page
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();

        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = recordsPage.getARandomNotificationWithStatusEqualTo(status, 50);
        log.info("ECID selected : " + ecid);
        scenarioSession.putData(SessionKey.ECID, ecid);

        //Click on selected notificatioins
        notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
        boolean isCorrectPage = notificationDetails.isCorrectPage();
        if (!isCorrectPage) {
            int count = 1;
            do {
//                recordsPage = mainNavigationBar.clickRecords();
//                recordsPage = recordsPage.clickNotificationsLink();
                notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
                isCorrectPage = notificationDetails.isCorrectPage();
                count++;
            } while (!isCorrectPage && count <= 3);
        }

        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.updateStatusTo(updatedStatus);
    }

    @Given("^I update status of stored notification to \"([^\"]*)\"$")
    public void i_update_status_of_stored_notification_to(String updatedStatus) throws Throwable {
        //Select an existing notification from the page
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);

        //update notification
        notificationDetails = recordsPage.clickNotificationNumber(ecid, 10);
        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.updateStatusTo(updatedStatus);
    }

    @Then("^I should see the submitter name containing \"([^\"]*)\"$")
    public void i_should_see_the_submitter_name_showing(String append) throws Throwable {
        String previousName = (String) scenarioSession.getData(SessionKey.storedValue);
        String expectedName = previousName + append;

        //verfiy page contains the updated information
        boolean contains = recordsPage.notificationsPageContainsText(expectedName, true);
        assertThat(contains, is(equalTo(true)));
    }

    /***
     * remove this : only created so we don't have 100s of notifications
     *
     * @param expectedNotificationID
     * @throws Throwable
     */
    @Then("^I have a notification \"([^\"]*)\" generated$")
    public void i_should_see_the_notification_generated(String expectedNotificationID) throws Throwable {
        scenarioSession.putData(SessionKey.ECID, expectedNotificationID);
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();

        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID, 5);
        boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
        assertThat("Expected header to contains EC ID : " + expectedNotificationID, contains, is(equalTo(true)));
        log.warn("Notification verified with ECID : " + expectedNotificationID);

        //Add a toxicology report
        Notification random = NotificationUtils.updateDefaultNotification(null);
        random.getIngredient().ingredientName = "SUPPA1";
        editNotification = notificationDetails.clickManageDocuments();
        notificationDetails = editNotification.addGenericToxicologyReportFromTempFolder("ToxicologyReport.pdf", random);
    }


    @Then("^I select a notification with ecid \"([^\"]*)\"")
    public void i_select_see_the_notification_generated(String expectedNotificationID) throws Throwable {
        scenarioSession.putData(SessionKey.ECID, expectedNotificationID);
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();

        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID, 5);
        boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
        assertThat("Expected header to contains EC ID : " + expectedNotificationID, contains, is(equalTo(true)));
        log.warn("Notification verified with ECID : " + expectedNotificationID);
    }


    @Then("^I should see the stored notification$")
    public void i_should_see_the_stored_notification() throws Throwable {
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();

        recordsPage = recordsPage.clickNotificationsLink();

        String expectedNotificationID = (String) scenarioSession.getData(SessionKey.ECID);
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID, 5);
        boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
        assertThat("Expected header to contains EC ID : " + expectedNotificationID, contains, is(equalTo(true)));
        log.warn("Notification verified with ECID : " + expectedNotificationID);
    }

    @Then("^I should see only (.*) notification$")
    public void i_should_see_only_single_notification(int countExpected) throws Throwable {
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);
        int count = recordsPage.getNotificationCount(ecid);
        String submitter = recordsPage.getSubmitterNameForEcid(ecid);
        scenarioSession.putData(SessionKey.submitter, submitter);
        assertThat("Expected to see 1 notification but was : " + count, count, is(equalTo(countExpected)));
    }

    @Then("^The showing search results text is correct$")
    public void search_by_text_should_be_correct(){
        String seachTerm = (String) scenarioSession.getData(SessionKey.searchTerm);
        boolean searchResultsForTextIsCorrect = recordsPage.isSearchResultsFor(seachTerm);
        assertThat("Showing search results for : " + seachTerm, searchResultsForTextIsCorrect, is(equalTo(true)));
    }


    @Then("^I should see (.*) or more notification$")
    public void i_should_see_X_number_OF_notification(int countExpected) throws Throwable {
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);
        int count = recordsPage.getNotificationCount(ecid);
        String submitter = recordsPage.getSubmitterNameForEcid(ecid);
        scenarioSession.putData(SessionKey.submitter, submitter);
        assertThat("Expected to see at least 1 notification but was : " + count, count, is(greaterThanOrEqualTo(countExpected)));
    }


    @Then("^I should see at least (.*) notification$")
    public void i_should_see_atleaset_X_number_OF_notification(int countExpected) throws Throwable {
        String ecid = (String) scenarioSession.getData(SessionKey.ECID);
        int count = recordsPage.getNotificationCount(ecid);
        assertThat("Expected to see at least 1 notification but was : " + count, count, is(greaterThanOrEqualTo(countExpected)));
    }


    @Given("^I attach a toxicology report for \"([^\"]*)\"$")
    public void i_attach_a_toxicology_reporth_with_following_data(String ingredient) throws Throwable {
        if(ingredient!=null && !ingredient.equals("")) {
            Notification notification = (Notification) scenarioSession.getData(SessionKey.storedNotification);
            String ecId = notification.ecIDNumber;

            mainNavigationBar = new MainNavigationBar(driver);
            recordsPage = mainNavigationBar.clickRecords();
            recordsPage = recordsPage.clickNotificationsLink();
            notificationDetails = recordsPage.clickNotificationNumber(ecId, 5);

            editNotification = notificationDetails.clickManageDocuments();
            String reportName = "ToxicologyReport.pdf";
            notificationDetails = editNotification.addGenericToxicologyReportFromTempFolder(reportName, notification);

            scenarioSession.putData(SessionKey.reportName, reportName);
        }
    }


    @Given("^I attach a \"([^\"]*)\" report for \"([^\"]*)\"$")
    public void i_attach_other_reports_with_following_data(String documentType, String ingredient) throws Throwable {
        Notification notification = (Notification) scenarioSession.getData(SessionKey.storedNotification);
        String ecId = (String) scenarioSession.getData(SessionKey.ECID);
        if(notification!=null) {
            ecId = notification.ecIDNumber;
        }

        if(!PageUtils.isCorrectPage(driver, ecId)) {
            mainNavigationBar = new MainNavigationBar(driver);
            recordsPage = mainNavigationBar.clickRecords();
            recordsPage = recordsPage.clickNotificationsLink();
            notificationDetails = recordsPage.clickNotificationNumber(ecId, 5);
        }

        editNotification = notificationDetails.clickManageDocuments();
        int countOfDocsAttached = editNotification.getNumberOfDocsAttached();

        notificationDetails = editNotification.addOtherReportFromTempFolder(countOfDocsAttached+1 ,documentType, "ToxicologyReport.pdf", "Other Docs : " + documentType, false, false, ingredient);
    }


    @When("^I search for an existing notification by \"([^\"]*)\" for text \"([^\"]*)\"$")
    public void i_search_for_an_existing_notification_by(String searchType, String searchTextTerm) throws Throwable {

        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();

        if (searchType.trim().toLowerCase().equals("ecid")) {

            if(searchTextTerm.equals("random")) {
                //Select an existing notification from the page
                //String ecid = recordsPage.getARandomNotificationECIDFromPosition(0, 50);
                String ecid = recordsPage.getARandomNotification(5);
                scenarioSession.putData(SessionKey.ECID, ecid);
                scenarioSession.putData(SessionKey.searchTerm, ecid);

                //Search for the ecid
                recordsPage = recordsPage.searchForECIDSubmitterOrOthers(ecid);
            }

        }else if(searchType.trim().toLowerCase().equals("date")){

        }
    }

    @When("^I re search for previously searched notification$")
    public void i_research_for_an_existing_notification_by_partial_searchTerm() throws Throwable {
        String searchTerm = (String) scenarioSession.getData(SessionKey.searchTerm);
        String subSearchString = searchTerm.substring(0,5);
        recordsPage = recordsPage.searchForECIDSubmitterOrOthers(subSearchString);
        scenarioSession.putData(SessionKey.searchTerm, subSearchString);

        //Show all the notifications
//        recordsPage = recordsPage.searchForECIDSubmitterOrOthers("");
//
//        //ASSUMES WE ARE IN THE SEARCH PAGE
//        if (searchType.trim().toLowerCase().equals("ecid")) {
//
//            if(searchTextTerm.equals("random")) {
//                String ecid = recordsPage.getARandomNotification(5);
//                scenarioSession.putData(SessionKey.ECID, ecid);
//                scenarioSession.putData(SessionKey.searchTerm, ecid);
//
//                //Search for the ecid
//                recordsPage = recordsPage.searchForECIDSubmitterOrOthers(ecid);
//            }
//
//        }else if(searchType.trim().toLowerCase().equals("date")){
//
//        }
    }

    @When("^I search for an existing notification by partial ecid \"([^\"]*)\"$")
    public void i_search_for_an_existing_notification_by_partial(String searchType) throws Throwable {

        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();

        recordsPage = recordsPage.clickNotificationsLink();

        if (searchType.trim().toLowerCase().equals("ecid")) {
            //Select an existing notification from the page
            String ecid = recordsPage.getARandomNotificationECIDFromPosition(0, 10);
            if (ecid.contains("-"))
                ecid = ecid.substring(0, ecid.indexOf("-"));
            scenarioSession.putData(SessionKey.ECID, ecid);

            //Search for the ecid
            recordsPage = recordsPage.searchForECIDSubmitterOrOthers(ecid);
        }
    }

    @When("^I search for the stored submitter name$")
    public void i_search_for_the_stored_submitter_name() throws Throwable {
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();

        String submitter = (String) scenarioSession.getData(SessionKey.submitter);

        if (submitter != null) {
            //Search for the ecid
            recordsPage = recordsPage.searchForECIDSubmitterOrOthers(submitter);
        }
    }

    @When("^I go to the notifications page$")
    public void i_go_to_the_notifications_page() throws Throwable {
        mainNavigationBar = new MainNavigationBar(driver);
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
        boolean isFitered = filterSection.isFiteredBy(filterByStatus);
        if (!isFitered) {
            recordsPage = filterSection.clickFilterText(filterByStatus);
        }
        int count = recordsPage.getTotalNotificationCount();
        log.info("Number of " + filterByStatus + " notifications is : " + count);
        scenarioSession.putData(SessionKey.notificationCount, count);
        scenarioSession.putData(SessionKey.notificationStatus, filterByStatus);
    }


    @When("^I store count of notifications by status \"([^\"]*)\"$")
    public void i_store_count_of_notificaitons_by_status(String filterByStatus) throws Throwable {
        RecordsFilter filterSection = recordsPage.getFilterSection();
        //filterSection = filterSection.clearSelection();
        filterSection = filterSection.expand();
        //recordsPage = filterSection.filterByStatus(filterByStatus);
        recordsPage = filterSection.clickFilterText(filterByStatus);
        boolean isFitered = filterSection.isFiteredBy(filterByStatus);
        if (!isFitered) {
            recordsPage = filterSection.clickFilterText(filterByStatus);
        }
        int count = recordsPage.getTotalNotificationCount();
        log.info("Number of " + filterByStatus + " notifications is : " + count);
        scenarioSession.putData(SessionKey.notificationCount, count);
        scenarioSession.putData(SessionKey.notificationStatus, filterByStatus);
    }


    @When("^I count the number of notifications in \"([^\"]*)\" status$")
    public void i_filter_by_statuses(String filterByStatuses) throws Throwable {
        String[] statuses = filterByStatuses.split(",");
        int totalCount = 0;
        for (String filterByStatus : statuses) {
            RecordsFilter filterSection = recordsPage.getFilterSection();
            filterSection = filterSection.expand();
            recordsPage = filterSection.clickFilterText(filterByStatus);
            boolean isFitered = filterSection.isFiteredBy(filterByStatus);
            if (!isFitered) {
                recordsPage = filterSection.clickFilterText(filterByStatus);
            }
            int count = recordsPage.getTotalNotificationCount();
            totalCount = totalCount + count;

            if (statuses.length > 1) {
                //Return to page
                filterSection = filterSection.clearSelection();
            }
        }
        log.info("Number of notifications in following statuses " + filterByStatuses + " count : " + totalCount);
        scenarioSession.putData(SessionKey.notificationCount, totalCount);
    }

    @Then("^I should only see notifications where status is \"([^\"]*)\"$")
    public void i_should_only_see_notifications_in_the_selected_status(String filterBy) throws Throwable {
        recordsPage = new RecordsPage(driver);
        boolean allStatusSame = recordsPage.isAllNotificationStatusOfType(filterBy);
        assertThat("Expected to see notifications with status : " + filterBy, allStatusSame, is(equalTo(true)));
    }


    @When("^I clear the selected filter \"([^\"]*)\"$")
    public void i_go_to_the_notifications_page(String filter) throws Throwable {
        RecordsFilter filterSection = recordsPage.getFilterSection();
        filterSection.waitForOptionsToBeClickable();
        recordsPage = filterSection.clearSelection(filter);
    }

    @And("^Update the status of stored notification to \"([^\"]*)\"$")
    public void updateTheStatusOfStoredNotificationTo(String updatedStatus) throws Throwable {
        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.updateStatusTo(updatedStatus);
        scenarioSession.putData(SessionKey.notificationStatus, updatedStatus);
    }

    @When("^I expect the notification status should be \"([^\"]*)\"$")
    public void the_status_should_update_to(String expectedStatus) throws Throwable {
        String currentStatus = (String) scenarioSession.getData(SessionKey.notificationStatus);
        boolean statusMatched = false;
        int attempt = 0;
        do {
            statusMatched = notificationDetails.expectedStatusToBe(expectedStatus);
            if (statusMatched)
                break;
            attempt++;
        } while (!statusMatched && attempt < 15);

        String newStatus = notificationDetails.getCurrentStatus();

        assertThat("Status should be : " + currentStatus, newStatus, is(isOneOf(expectedStatus, "Quality Assurance")));
        scenarioSession.putData(SessionKey.notificationStatus, newStatus);
    }


//
//    @When("^I view an existing notification with ecid  \"([^\"]*)\" in exception page$")
//    public void i_view_an_existing_notification_displayed_in_exception_page(String ecid) throws Throwable {
//        reportsPage = mainNavigationBar.clickReports();
//        exception = reportsPage.gotoExceptionsPage();
//
//        scenarioSession.putData(SessionKey.ECID, ecid);
//
//        notificationDetails = exception.clickOnNotificationWithEcid(ecid);
//        log.info("Clicked notification with ecid : " + ecid);
//    }


    @Then("^I view an random notification with status \"([^\"]*)\"$")
    public void i_view_random_notification(String status) throws Throwable {
        String ecid = recordsPage.getARandomNotificationWithStatusEqualTo(status, 50);
        log.info("View notification with ecid : " + ecid);
        scenarioSession.putData(SessionKey.ECID, ecid);

        //View notifications
        notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
    }


    @Then("^I view a random notification$")
    public void i_view_random_notification() throws Throwable {

        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = recordsPage.getARandomNotification(50);
        log.info("View notification with ecid : " + ecid);

        //View notifications
        notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
    }

    @Then("^I select a previous notification with ecid \"([^\"]*)\"$")
    public void i_view_random_notification_with_ecid(String prevID) throws Throwable {

        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = prevID;
        if (!prevID.equals("") && prevID.equals("random")) {
            ecid = recordsPage.getARandomNotification(50);
            log.info("View notification with ecid : " + ecid);
        }

        //View notifications
        //notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
        scenarioSession.putData(SessionKey.previousECID, ecid);
    }

    @Then("^I select a previous notification with ecid \"([^\"]*)\" and status \"([^\"]*)\"$")
    public void i_view_random_notification(String prevID, String status) throws Throwable {

        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        String ecid = prevID;
        if (!prevID.equals("") && prevID.equals("random")) {
            ecid = recordsPage.getARandomNotificationWithStatusEqualTo(status, 50);
            log.info("View notification with ecid : " + ecid);
        }

        //View notifications
        //notificationDetails = recordsPage.clickNotificationNumber(ecid, 5);
        scenarioSession.putData(SessionKey.previousECID, ecid);
    }


    @Then("^Verify audit log details \"([^\"]*)\" are correct$")
    public void i_verify_uploaded_details_are_correct(String status) throws Throwable {
        auditHistory = notificationDetails.clickAuditHistory();
        boolean isCorrect = auditHistory.isUploadedDataCorrect(status);
        assertThat("Uploaded user details should be related to RDT users", isCorrect, is(equalTo(true)));
    }

    @Then("^Verify audit log details \"([^\"]*)\"$")
    public void i_verify_status_details_are_correct(String details) throws Throwable {
        auditHistory = notificationDetails.clickAuditHistory();
        String[] data = details.split(",");
        String status = data[0];
        String action = data[1].split("=")[1];
        String user = data[2].split("=")[1];
        String comment = data[3].split("=")[1];
        String timestamp = data[4].split("=")[1];

        boolean isCorrect = auditHistory.isDataCorrect(status, action, user, comment, timestamp);
        assertThat("Audit details should be : " + details, isCorrect, is(equalTo(true)));
    }

    @Then("^Audit log displays correct status \"([^\"]*)\" user name \"([^\"]*)\" and comment$")
    public void audit_log_displays_correct_user_name_and_comment(String status, String userNameOrEmail) throws Throwable {
        auditHistory = notificationDetails.clickAuditHistory();
        boolean statusIsPaid = auditHistory.isStatus(status);
        boolean userNameCorrect = true;
        if (userNameOrEmail != null && !userNameOrEmail.trim().equals(""))
            userNameCorrect = auditHistory.isUserNameEqualTo(userNameOrEmail);

        assertThat("Status should be : " + status, statusIsPaid, is(equalTo(true)));
        assertThat("User should be : " + userNameOrEmail, userNameCorrect, is(equalTo(true)));
    }

    @When("^I add comment \"([^\"]*)\" to selected notification$")
    public void i_add_comment_to_selected_notification(String commentTxt) throws Throwable {
        commentSection = notificationDetails.clickCommentsLink();
        if (commentTxt.equals("random")) {
            commentTxt = "Test Comment " + RandomDataUtils.getSimpleRandomNumberBetween(1000, 1000000);
        }
        scenarioSession.putData(SessionKey.comment, commentTxt);
        commentSection = commentSection.clickOnAddNewCommentButton(commentTxt);
        commentSection.addNewComment(commentTxt);
    }

    @Then("^I should see comment \"([^\"]*)\" displayed in notification for user \"([^\"]*)\"$")
    public void i_should_see_comment_displayed_in_notification(String commentTxt, String username) throws Throwable {
        if (commentTxt == null || commentTxt.trim().equals("") || commentTxt.equals("random")) {
            commentTxt = (String) scenarioSession.getData(SessionKey.comment);
        }
        boolean isCommentDisplayed = commentSection.isCommentDisplayed(commentTxt, username);
        assertThat("Expected to see comments : " + commentTxt, isCommentDisplayed, is(equalTo(true)));
    }

    @Then("^I should be able to manage documents$")
    public void i_should_be_able_to_manage_documents() throws Throwable {
        editNotification = notificationDetails.clickManageDocuments();
        boolean isCorrectPage = editNotification.isCorrectPage();
        assertThat("Expected to be on edit notification page", isCorrectPage, is(equalTo(true)));
        WaitUtils.nativeWait(1);
    }


    @Then("^I should be able to view documents page$")
    public void i_should_be_able_to_view_documents_page() throws Throwable {
        documents = notificationDetails.clickDocuments();
        boolean isVisisble = documents.isCorrectPage();
        assertThat("Expected to be on notifications documents page", isVisisble, is(equalTo(true)));
    }


    @Then("^There should be at least \"([^\"]*)\" or more documents$")
    public void there_should_be_at_least_or_more_documents(int count) throws Throwable {
        boolean countMatched = documents.isNumberOfDocumentsDisplayedCorrect(count);
        assertThat("Expected to see at least " + count + " documents", countMatched, is(equalTo(true)));
    }

    @Then("^There should be at least a document for stored report name$")
    public void there_should_be_at_least_a_document_for_ingredient() throws Throwable {
        String reportName = (String) scenarioSession.getData(SessionKey.reportName);
        boolean countMatched = documents.isDocumentsDisplayedFor(reportName);
        assertThat("Expected to see a document for : " + reportName, countMatched, is(equalTo(true)));
    }


    @When("^The notification status should update to \"([^\"]*)\"$")
    public void the_notification_status_should_update_to(String expectedStatus) throws Throwable {
        String currentStatus = (String) scenarioSession.getData(SessionKey.notificationStatus);
        //boolean statusChanged = notificationDetails.hasPageStatusChangedTo(currentStatus);
        boolean statusChanged = false;
        int attempt = 0;
        do {
            statusChanged = notificationDetails.hasPageStatusChangedTo(currentStatus);
            if (statusChanged)
                break;
            attempt++;
        } while (!statusChanged && attempt < 15);

        String newStatus = notificationDetails.getCurrentStatus();

        assertThat("Status should not be : " + currentStatus, newStatus, is(isOneOf(expectedStatus, "Quality Assurance")));
        scenarioSession.putData(SessionKey.notificationStatus, newStatus);
    }


    @Given("^I should see the stored notification with status set to \"([^\"]*)\"$")
    public void i_should_see_new_task_generated_for_the_submitter(String expectedStatus) throws Throwable {

        if(expectedStatus!=null && !expectedStatus.equals("")) {
            //Stored data to verify
            Notification data = (Notification) scenarioSession.getData(SessionKey.storedNotification);
            String expectedNotificationID = data.ecIDNumber;

            //Verify notification generated
            mainNavigationBar = new MainNavigationBar(driver);
            recordsPage = mainNavigationBar.clickRecords();
            recordsPage = recordsPage.clickNotificationsLink();
            notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID, 5);
            boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
            assertThat("Expected header to contains EC ID : " + expectedNotificationID, contains, is(equalTo(true)));

            //Verify notification is loaded in page
            long start = System.currentTimeMillis();
            boolean statusMatched = false;
            int attempt = 0;
            do {
                statusMatched = notificationDetails.expectedStatusToBe(expectedStatus);
                if (statusMatched)
                    break;
                attempt++;
            } while (!statusMatched && attempt < 15);

            long end = System.currentTimeMillis();
            long diff = (end-start)/1000;
            log.warn("Time taken for status to change for ecid : " + expectedNotificationID + ", in seconds : " + diff);

            String newStatus = notificationDetails.getCurrentStatus();
            assertThat("Status should be : " + expectedStatus, newStatus, is((equalTo(expectedStatus))));

            String zipFile = (String) scenarioSession.getData(SessionKey.zipFileLocation);
            if(zipFile!=null){
                //Assumes notification loaded, therefore delete file
                FileUtils.deleteFile(zipFile);
            }
        }
    }


    @Given("^I should see the uploaded zip file notification with status set to \"([^\"]*)\"$")
    public void i_should_see_uploaded_zip_file_notification_with_status(String expectedStatus) throws Throwable {

        if(expectedStatus!=null && !expectedStatus.equals("")) {
            //Stored data to verify
            EcigProductSubmission data = (EcigProductSubmission) scenarioSession.getData(SessionKey.storedNotification);
            String expectedNotificationID = data.getEcIDNumber();

            //Verify notification generated
            mainNavigationBar = new MainNavigationBar(driver);
            recordsPage = mainNavigationBar.clickRecords();
            recordsPage = recordsPage.clickNotificationsLink();
            notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID, 5);
            boolean contains = notificationDetails.headerContainsID(expectedNotificationID);
            assertThat("Expected header to contains EC ID : " + expectedNotificationID, contains, is(equalTo(true)));

            //Verify notification is loaded in page
            long start = System.currentTimeMillis();
            boolean statusMatched = false;
            int attempt = 0;
            do {
                statusMatched = notificationDetails.expectedStatusToBe(expectedStatus);
                if (statusMatched)
                    break;
                attempt++;
                WaitUtils.nativeWait(5);
            } while (!statusMatched && attempt < 15);

            long end = System.currentTimeMillis();
            long diff = (end-start)/1000;
            log.warn("Time taken for status to change for ecid : " + expectedNotificationID + ", in seconds : " + diff);

            String newStatus = notificationDetails.getCurrentStatus();
            assertThat("Status should be : " + expectedStatus, newStatus, is((equalTo(expectedStatus))));

            String zipFile = (String) scenarioSession.getData(SessionKey.zipFileLocation);
            String xmlFileToDelete = (String) scenarioSession.getData(SessionKey.xmlDataFileLocation);
            if(zipFile!=null){
                //Assumes notification loaded, therefore delete file
                FileUtils.deleteFile(zipFile);
                //FileUtils.deleteFile(xmlFileToDelete);
            }
        }
    }


    /**
     * This is to help with entering submitter address
     *
     * Otherwise we cannot pay : Workflow broken because the XML data doesn't contain submitter name
     * @throws Throwable
     */
    @Then("^I enter a submitter address to stored notification$")
    public void i_enter_a_submitter_address_for_stored_notifications() throws Throwable {

        EcigProductSubmission data = (EcigProductSubmission) scenarioSession.getData(SessionKey.storedNotification);
        String expectedNotificationID = data.getEcIDNumber();

        //Verify notification generated
        mainNavigationBar = new MainNavigationBar(driver);
        recordsPage = mainNavigationBar.clickRecords();
        recordsPage = recordsPage.clickNotificationsLink();
        notificationDetails = recordsPage.clickNotificationNumber(expectedNotificationID, 5);

        //Enter date
        editNotification = notificationDetails.clickManageNotification();
        notificationDetails = editNotification.enterDate();

        WaitUtils.nativeWait(2);
        //Add a random submitter address
        driver.navigate().refresh();
        editNotification = notificationDetails.clickManageNotification();
        editNotification = editNotification.clickAddAddress();
        notificationDetails = editNotification.addSubmitterAddress(new Address(), data);
    }

}
