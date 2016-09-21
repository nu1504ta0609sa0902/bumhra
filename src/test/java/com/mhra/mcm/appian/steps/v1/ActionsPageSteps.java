package com.mhra.mcm.appian.steps.v1;

import java.util.Map;

import com.mhra.mcm.appian.domain.xmlPojo.EcigProductSubmission;
import com.mhra.mcm.appian.pageobjects.ActionsPage;
import com.mhra.mcm.appian.utils.helpers.others.FileUtils;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;
import com.mhra.mcm.appian.utils.helpers.page.AssertUtils;
import com.mhra.mcm.appian.utils.helpers.page.StepsUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.context.annotation.Scope;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;

import cucumber.api.java.en.Given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class ActionsPageSteps extends CommonSteps {


    @Given("^I create new notification$")
    public void i_create_a_new_notification() throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        if(actionsPage == null){
            actionsPage = mainNavigationBar.clickActions();
        }
        createNotification = actionsPage.clickUploadSampleNotification();
        if(createNotification == null){
            createNotification = actionsPage.clickUploadSampleNotification();
        }
        Notification random = new Notification(2, 2, null);
        log.info("Create Notification With ECID : " + random.ecIDNumber);
        actionsPage = createNotification.createRandomNotification(random);

        //Retry if fields are not correctly filled
        boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
        if (!isInCorrectPage) {
            int count = 1;
            do {
                actionsPage = createNotification.clickCancel();
                createNotification = actionsPage.clickUploadSampleNotification();
//                if(createNotification == null){
//                    createNotification = actionsPage.clickUploadSampleNotification();
//                }
                actionsPage = createNotification.createRandomNotification(random);
                isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                count++;
            } while (!isInCorrectPage && count <= 3);
        }

        //Stored ecId for future use
        String ecId = random.ecIDNumber;
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, random);
        log.info("Notification Details : \n" + random);
    }


    @Given("^I create new notification with following data$")
    public void i_create_new_notification_with_following_data(Map<String, String> dataValues) throws Throwable {

        Notification random = NotificationUtils.updateDefaultNotification(dataValues);
        random = NotificationUtils.updateOtherDetails(scenarioSession, random);
        String ecId = random.ecIDNumber;
        log.warn("Create Notification With ECID : " + random.ecIDNumber);

        //UPLOAD NOTIFICATION
        actionsPage = mainNavigationBar.clickActions();
        createNotification = actionsPage.clickUploadSampleNotification();
        actionsPage = createNotification.createRandomNotification(random);

        //Retry if fields are not correctly filled
        boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
        if (!isInCorrectPage) {
            int count = 1;
            do {
                actionsPage = createNotification.clickCancel();
                createNotification = actionsPage.clickUploadSampleNotification();
                actionsPage = createNotification.createRandomNotification(random);
                isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                count++;
            } while (!isInCorrectPage && count <= 3);
        }

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, random);
        //log.info("Notification Details : \n" + random);
        log.warn("Created Notification With ECID : " + random.ecIDNumber);
    }


    @Given("^I create new notification and attach a toxicology report with following data$")
    public void i_create_new_notification_and_attach_a_toxicology_reporth_with_following_data(Map<String, String> dataValues) throws Throwable {

        Notification random = NotificationUtils.updateDefaultNotification(dataValues);
        String ecId = random.ecIDNumber;
        log.info("Create Notification With ECID : " + ecId);

        //UPLOAD NOTIFICATION
        actionsPage = mainNavigationBar.clickActions();
        if(actionsPage == null){
            actionsPage = mainNavigationBar.clickActions();
        }
        createNotification = actionsPage.clickUploadSampleNotification();
        if(createNotification == null){
            createNotification = actionsPage.clickUploadSampleNotification();
        }
        actionsPage = createNotification.createRandomNotification(random);

        //Retry if fields are not correctly filled
        boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
        if (!isInCorrectPage) {
            int count = 1;
            do {
                actionsPage = createNotification.clickCancel();
                createNotification = actionsPage.clickUploadSampleNotification();
//                if(createNotification == null){
//                    createNotification = actionsPage.clickUploadSampleNotification();
//                }
                actionsPage = createNotification.createRandomNotification(random);
                isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                count++;
            } while (!isInCorrectPage && count <= 3);
        }

        log.info("Created Notification With ECID : " + ecId);

        //Add a toxicology report
        if (createNotification.ingredientAdded) {
            recordsPage = mainNavigationBar.clickRecords();
            if(recordsPage == null){
                recordsPage = mainNavigationBar.clickRecords();
            }
            recordsPage = recordsPage.clickNotificationsLink();
            notificationDetails = recordsPage.clickNotificationNumber(ecId, 5);

            editNotification = notificationDetails.clickManageDocuments();
            notificationDetails = editNotification.addGenericToxicologyReportFromTempFolder("ToxicologyReport.pdf", random);
        }

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, random);
    }

    @Given("^I create (.*) new notifications$")
    public void i_create_a_new_notification(int numberOfNotifications) throws Throwable {
        for (int x = 0; x < numberOfNotifications; x++) {
            try {
                actionsPage = mainNavigationBar.clickActions();
                if(actionsPage == null){
                    actionsPage = mainNavigationBar.clickActions();
                }
                createNotification = actionsPage.clickUploadSampleNotification();
                if(createNotification == null){
                    createNotification = actionsPage.clickUploadSampleNotification();
                }
                Notification random = new Notification(2, 2, null);
                actionsPage = createNotification.createRandomNotification(random);

                //Retry if fields are not correctly filled
                boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                if (!isInCorrectPage) {
                    int count = 1;
                    do {
                        actionsPage = createNotification.clickCancel();
                        createNotification = actionsPage.clickUploadSampleNotification();
//                        if(createNotification == null){
//                            createNotification = actionsPage.clickUploadSampleNotification();
//                        }
                        actionsPage = createNotification.createRandomNotification(random);
                        isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                        count++;
                    } while (!isInCorrectPage && count <= 3);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            WaitUtils.nativeWait(5);
        }
    }


    @Given("^I create new xml notification with following data$")
    public void i_create_new_xml_notification_with_following_data(Map<String, String> dataValues) throws Throwable {

        //Create and save XML file
        String xmlFileName = dataValues.get("saveXMLOutputAs");
        xmlFileName = FileUtils.getXMLNotificationDataFileName(xmlFileName);
        EcigProductSubmission xmlNotificationData = NotificationUtils.generateDefaultXMLNotificationDataSimple(dataValues, mapOfExcelDataAsMap);
        String xmlDataFileLocation = NotificationUtils.createXmlNotificationData(xmlNotificationData, xmlFileName);

        String ecId = xmlNotificationData.getEcIDNumber();
        log.info("Create Notification With ECID : " + ecId);
        log.info("XML Data File : " + xmlDataFileLocation);

        //UPLOAD XML NOTIFICATION Data
        //actionsPage = mainNavigationBar.clickActions();
        //createNotification = actionsPage.clickUploadSampleNotification();
        //actionsPage = createNotification.uploadXMLNotification(xmlDataFileLocation);
        //actionsPage.isInCorrectPage();

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, xmlNotificationData);
        //log.info("Notification Details : \n" + random);
        log.info("Created Notification With ECID : " + ecId);
    }

    @Given("^I create new xml notification with following data table$")
    public void i_create_new_xml_notification_with_following_data_table(DataTable dataTable) throws Throwable {
        Map<String, String> dataValues = StepsUtils.convertDataTableToMap(dataTable);

        //Create and save XML file
        String xmlFileName = dataValues.get("saveXMLOutputAs");
        xmlFileName = FileUtils.getXMLNotificationDataFileName(xmlFileName);
        EcigProductSubmission xmlNotificationData = NotificationUtils.generateDefaultXMLNotificationData(dataValues, mapOfExcelDataAsMap);
        String xmlDataFileLocation = NotificationUtils.createXmlNotificationData(xmlNotificationData, xmlFileName);

        String ecId = xmlNotificationData.getEcIDNumber();
        log.info("Create Notification With ECID : " + ecId);
        log.info("XML Data File : " + xmlDataFileLocation);

        //UPLOAD XML NOTIFICATION Data
        //actionsPage = mainNavigationBar.clickActions();
        //createNotification = actionsPage.clickUploadSampleNotification();
        //actionsPage = createNotification.uploadXMLNotification(xmlDataFileLocation);
        //actionsPage.isInCorrectPage();

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, xmlNotificationData);
        //log.info("Notification Details : \n" + random);
        log.info("Created Notification With ECID : " + ecId);
    }


    @Given("^I update qa percentage to \"([^\"]*)\"$")
    public void i_update_qa_percentage_to(String qaPercentage) throws Throwable {
        if(qaPercentage.equals("") || qaPercentage.equals("random")){
            qaPercentage = RandomDataUtils.getSimpleRandomNumberBetween(10,50);
        }

        actionsPage = mainNavigationBar.clickActions();
        updateQAPercentage = actionsPage.clickUpdateQAPercentage();
        updateQAPercentage = updateQAPercentage.setQAPercentage(qaPercentage);
        updateQAPercentage.acceptDialog(true);

        scenarioSession.putData(SessionKey.qaPercentageValue, qaPercentage);
    }


    @Then("^I should see \"([^\"]*)\" in the error message$")
    public void i_should_see_in_the_error_message(String expectedMessage) throws Throwable {
        boolean correct = updateQAPercentage.isErrorMessageCorrect(expectedMessage);
        assertThat("Error message should contains : " + expectedMessage, correct, is(true));
    }

    @Then("^I should see qa percentage updated to \"([^\"]*)\"$")
    public void i_should_see_qa_percentage_updated_to(String expectedQAPercentage) throws Throwable {
        if(expectedQAPercentage.equals("") || expectedQAPercentage.equals("random")){
            expectedQAPercentage = (String) scenarioSession.getData(SessionKey.qaPercentageValue);
        }

        actionsPage = new ActionsPage(driver);
        updateQAPercentage = actionsPage.clickUpdateQAPercentage();
        boolean correct = updateQAPercentage.isQAPercentageCorrect(expectedQAPercentage);
        assertThat("QA Percentage should be updated to : " + expectedQAPercentage, correct, is(true));
    }


    @When("^I go to manage substance page$")
    public void i_go_to_manage_substance_page() throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        if(actionsPage == null){
            actionsPage = mainNavigationBar.clickActions();
        }
        manageSubstances = actionsPage.clickManageSubstances();
    }

    @Then("^I should \"([^\"]*)\" able to add a new substance$")
    public void i_should_not_be_be_able_to_add_a_new_substance(String action) throws Throwable {
        if (action.equals("not be")) {
            assertThat("Manage substance page should not be displayed", manageSubstances, is(nullValue()));
        } else {
            assertThat("Manage substance page be displayed", manageSubstances, is(notNullValue()));
        }

        //Appian system can't handle the automation script requesting too many logins
        WaitUtils.nativeWait(2);
    }

//    @When("^I click on new substance$")
//    public void i_click_on_new_substance() throws Throwable {
//        manageSubstances = manageSubstances.clickOnAddNewSubstances();
//    }


    @When("^I add a substance \"([^\"]*)\" which \"([^\"]*)\" banned$")
    public void i_add_a_banned_substance_to_appian(String substance, String isBanned) throws Throwable {
        if (substance.equals("random")) {
            substance = RandomDataUtils.getRandomTestName("Substance");
        }

        //Add substance
        manageSubstances = manageSubstances.clickOnAddNewSubstances();

        if(isBanned.equals("is")) {
            actionsPage = manageSubstances.addNewSubstance(substance, true);
        }else{
            actionsPage = manageSubstances.addNewSubstance(substance, false);
        }

        log.info("Added new substance : " + substance);
        scenarioSession.putData(SessionKey.substance, substance);
        scenarioSession.putData(SessionKey.bannedTxt, isBanned);
    }

    @Then("^I should see the new substance in the manage substance page$")
    public void i_should_see_the_new_substance_in_the_manage_substance_page() throws Throwable {
        String substance = (String) scenarioSession.getData(SessionKey.substance);
        String isBanned = (String) scenarioSession.getData(SessionKey.bannedTxt);
        manageSubstances = actionsPage.clickManageSubstances();
        boolean substanceAdded = false;
        if(isBanned.equals("is")) {
            substanceAdded = manageSubstances.verifyNewSubstanceAdded(substance, true);
            assertThat("Expected to see new substance called : " + substance, substanceAdded, is(true));
        }else{
            substanceAdded = manageSubstances.verifyNewSubstanceAdded(substance, false);
            assertThat("Expected to see new substance called : " + substance, substanceAdded, is(true));
        }
    }


    @And("^I search for substance name containing \"([^\"]*)\" and \"([^\"]*)\" banned$")
    public void i_search_for_substance_in_the_manage_substance_page(String substance, String isBanned) throws Throwable {
        manageSubstances = actionsPage.clickManageSubstances();
        boolean substanceAdded = false;
        if(isBanned.equals("is")) {
            substanceAdded = manageSubstances.verifyNewSubstanceAdded(substance, true);
            assertThat("Expected to see new substance called : " + substance, substanceAdded, is(true));
        }else{
            substanceAdded = manageSubstances.verifyNewSubstanceAdded(substance, false);
            assertThat("Expected to see new substance called : " + substance, substanceAdded, is(true));
        }

        scenarioSession.putData(SessionKey.substance, substance);
        scenarioSession.putData(SessionKey.bannedTxt, isBanned);
    }


    @And("^I search for stored substance name which \"([^\"]*)\" banned$")
    public void i_search_for_stored_substance_which_is_banned_page(String isBanned) throws Throwable {
        //If its empty
        String substance = (String) scenarioSession.getData(SessionKey.substance);
        manageSubstances = actionsPage.clickManageSubstances();
        boolean substanceAdded = false;
        if(isBanned.equals("is")) {
            substanceAdded = manageSubstances.verifyNewSubstanceAdded(substance, true);
            assertThat("Expected to see new substance called : " + substance, substanceAdded, is(true));
        }else{
            substanceAdded = manageSubstances.verifyNewSubstanceAdded(substance, false);
            assertThat("Expected to see new substance called : " + substance, substanceAdded, is(true));
        }
    }

    @And("^I search for stored substance name$")
    public void i_search_for_stored_substance_name() throws Throwable {
        //If its empty
        String substance = (String) scenarioSession.getData(SessionKey.substance);
        //manageSubstances = actionsPage.clickManageSubstances();
        manageSubstances = manageSubstances.searchForSubstance(substance, true);
        boolean isItemDisplayed = manageSubstances.isAtleastOneItemDisplayed();
        if(!isItemDisplayed)
            manageSubstances = manageSubstances.searchForSubstance(substance, false);
    }

    @Then("^I should see substance \"([^\"]*)\" banned$")
    public void i_should_see_substance_banned(String isBanned) throws Throwable {
        String substance = (String) scenarioSession.getData(SessionKey.substance);
        boolean isSubstanceBanned = manageSubstances.isSubstanceBanned();
        if(isBanned.equals("is")) {
            assertThat("Expected substance to be banned", isSubstanceBanned, is(true));
        }else{
            assertThat("Expected substance not to be banned", isSubstanceBanned, is(false));
        }
    }

    @When("^I update a random substance name by appending \"([^\"]*)\"$")
    public void i_update_a_random_substance_name_by_appending(String appendText) throws Throwable {
        manageSubstances = manageSubstances.updateARandomSubstance(appendText);
    }

    @When("^I update a stored substance name by appending \"([^\"]*)\"$")
    public void i_update_a_stored_substance_name_by_appending(String appendText) throws Throwable {
        String substance = (String) scenarioSession.getData(SessionKey.substance);
        String isBanned = (String) scenarioSession.getData(SessionKey.bannedTxt);
        String substanceAppended = substance + appendText;

        if(manageSubstances == null){
            manageSubstances = actionsPage.clickManageSubstances();
        }

        //Search for substance
        if(isBanned.equals("is")){
            manageSubstances = manageSubstances.searchForSubstance(substance, true);
        }else{
            manageSubstances = manageSubstances.searchForSubstance(substance, false);
        }

        //Update the substance name
        manageSubstances = manageSubstances.viewSubstance(substance);
        actionsPage = manageSubstances.updateSubstance(substanceAppended);

        scenarioSession.putData(SessionKey.substance, substanceAppended);
        scenarioSession.putData(SessionKey.bannedTxt, isBanned);
    }



    @Then("^I should see stored substance$")
    public void i_should_see_stored_substance() throws Throwable {
        String substance = (String) scenarioSession.getData(SessionKey.substance);
        String isBanned = (String) scenarioSession.getData(SessionKey.bannedTxt);
        manageSubstances = actionsPage.clickManageSubstances();

        //Is item displayed
        boolean isItemDisplayed = false;
        if(isBanned.equals("is")){
            manageSubstances = manageSubstances.searchForSubstance(substance, true);
            isItemDisplayed = manageSubstances.isAtleastOneItemDisplayed();
        }else{
            manageSubstances = manageSubstances.searchForSubstance(substance, false);
            isItemDisplayed = manageSubstances.isAtleastOneItemDisplayed();
        }

        assertThat("Expected to see substance : " + substance, isItemDisplayed, is(true));
    }


    @When("^I add a substance \"([^\"]*)\" with following details \"([^\"]*)\"$")
    public void i_add_a__substance_with_following_details(String substance, String commanDelimitedDetails) throws Throwable {
        if (substance.equals("random")) {
            substance = RandomDataUtils.getRandomTestName("Substance");
        }

        //Add substance
        manageSubstances = manageSubstances.clickOnAddNewSubstances();

        String isBanned = commanDelimitedDetails.split(",")[0];

        if(isBanned.equals("banned=true")) {
            isBanned = "is";
        }else{
            isBanned = "is not";
        }
        actionsPage = manageSubstances.addNewSubstanceForDelimitedData(substance, commanDelimitedDetails);

        log.info("Added new substance : " + substance);
        scenarioSession.putData(SessionKey.substance, substance);
        scenarioSession.putData(SessionKey.bannedTxt, isBanned);
    }
}
