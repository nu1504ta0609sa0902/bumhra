package com.mhra.mcm.appian.steps.v1;

import java.util.Map;

import com.mhra.mcm.appian.domain.xmlPojo.EcigProductSubmission;
import com.mhra.mcm.appian.pageobjects.sections.contents.UpdateQAPercentage;
import com.mhra.mcm.appian.utils.helpers.FileUtils;
import com.mhra.mcm.appian.utils.helpers.StepsUtils;
import cucumber.api.DataTable;
import cucumber.api.java.en.Then;
import org.springframework.context.annotation.Scope;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;

import cucumber.api.java.en.Given;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class ActionsPageSteps extends CommonSteps {


    @Given("^I create new notification$")
    public void i_create_a_new_notification() throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        createNotification = actionsPage.clickUploadSampleNotification();
        Notification random = new Notification(2, 2, null);
        log.info("Create Notification With ECID : " +  random.ecIDNumber);
        actionsPage = createNotification.createRandomNotification(random);

        //Retry if fields are not correctly filled
        boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
        if(!isInCorrectPage){
            int count = 1;
            do {
                actionsPage = createNotification.clickCancel();
                createNotification = actionsPage.clickUploadSampleNotification();
                actionsPage = createNotification.createRandomNotification(random);
                isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                count++;
            }while (!isInCorrectPage && count <= 3 );
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
        String ecId = random.ecIDNumber;
        log.info("Create Notification With ECID : " +  random.ecIDNumber);

        //UPLOAD NOTIFICATION
        actionsPage = mainNavigationBar.clickActions();
        createNotification = actionsPage.clickUploadSampleNotification();
        actionsPage = createNotification.createRandomNotification(random);

        //Retry if fields are not correctly filled
        boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
        if(!isInCorrectPage){
            int count = 1;
            do {
                actionsPage = createNotification.clickCancel();
                createNotification = actionsPage.clickUploadSampleNotification();
                actionsPage = createNotification.createRandomNotification(random);
                isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                count++;
            }while (!isInCorrectPage && count <= 3 );
        }

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, random);
        //log.debug("Notification Details : \n" + random);
        log.info("Created Notification With ECID : " +  random.ecIDNumber);
    }



    @Given("^I create new notification and attach a toxicology report with following data$")
    public void i_create_new_notification_and_attach_a_toxicology_reporth_with_following_data(Map<String, String> dataValues) throws Throwable {

        Notification random = NotificationUtils.updateDefaultNotification(dataValues);
        String ecId = random.ecIDNumber;
        log.info("Create Notification With ECID : " +  ecId);

        //UPLOAD NOTIFICATION
        actionsPage = mainNavigationBar.clickActions();
        createNotification = actionsPage.clickUploadSampleNotification();
        actionsPage = createNotification.createRandomNotification(random);

        //Retry if fields are not correctly filled
        boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
        if(!isInCorrectPage){
            int count = 1;
            do {
                actionsPage = createNotification.clickCancel();
                createNotification = actionsPage.clickUploadSampleNotification();
                actionsPage = createNotification.createRandomNotification(random);
                isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                count++;
            }while (!isInCorrectPage && count <= 3 );
        }

        log.info("Created Notification With ECID : " +  ecId);

        //Add a toxicology report
        if(createNotification.ingredientAdded) {
            recordsPage = mainNavigationBar.clickRecords();
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
        for(int x = 0; x < numberOfNotifications; x++){
            try {
                actionsPage = mainNavigationBar.clickActions();
                createNotification = actionsPage.clickUploadSampleNotification();
                Notification random = new Notification(2, 2, null);
                actionsPage = createNotification.createRandomNotification(random);

                //Retry if fields are not correctly filled
                boolean isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                if(!isInCorrectPage){
                    int count = 1;
                    do {
                        actionsPage = createNotification.clickCancel();
                        createNotification = actionsPage.clickUploadSampleNotification();
                        actionsPage = createNotification.createRandomNotification(random);
                        isInCorrectPage = actionsPage.isNotificationGeneratedSuccessfully();
                        count++;
                    }while (!isInCorrectPage && count <= 3 );
                }

            }catch(Exception e){
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
        String xmlDataFileLocation = NotificationUtils.createXmlNotificationData(xmlNotificationData,xmlFileName);

        String ecId = xmlNotificationData.getEcIDNumber();
        log.info("Create Notification With ECID : " +  ecId);
        log.info("XML Data File : " + xmlDataFileLocation);

        //UPLOAD XML NOTIFICATION Data
        //actionsPage = mainNavigationBar.clickActions();
        //createNotification = actionsPage.clickUploadSampleNotification();
        //actionsPage = createNotification.uploadXMLNotification(xmlDataFileLocation);
        //actionsPage.isInCorrectPage();

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, xmlNotificationData);
        //log.debug("Notification Details : \n" + random);
        log.info("Created Notification With ECID : " +  ecId);
    }

    @Given("^I create new xml notification with following data table$")
    public void i_create_new_xml_notification_with_following_data_table(DataTable dataTable) throws Throwable {
        Map<String, String> dataValues = StepsUtils.convertDataTableToMap(dataTable);

        //Create and save XML file
        String xmlFileName = dataValues.get("saveXMLOutputAs");
        xmlFileName = FileUtils.getXMLNotificationDataFileName(xmlFileName);
        EcigProductSubmission xmlNotificationData = NotificationUtils.generateDefaultXMLNotificationData(dataValues, mapOfExcelDataAsMap);
        String xmlDataFileLocation = NotificationUtils.createXmlNotificationData(xmlNotificationData,xmlFileName);

        String ecId = xmlNotificationData.getEcIDNumber();
        log.info("Create Notification With ECID : " +  ecId);
        log.info("XML Data File : " + xmlDataFileLocation);

        //UPLOAD XML NOTIFICATION Data
        //actionsPage = mainNavigationBar.clickActions();
        //createNotification = actionsPage.clickUploadSampleNotification();
        //actionsPage = createNotification.uploadXMLNotification(xmlDataFileLocation);
        //actionsPage.isInCorrectPage();

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, xmlNotificationData);
        //log.debug("Notification Details : \n" + random);
        log.info("Created Notification With ECID : " +  ecId);
    }


    @Given("^I update qa percentage to \"([^\"]*)\"$")
    public void i_update_qa_percentage_to(String qaPercentage) throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        updateQAPercentage = actionsPage.clickUpdateQAPercentage();
        updateQAPercentage = updateQAPercentage.setQAPercentage(qaPercentage);
        updateQAPercentage.acceptDialog(true);
    }


    @Then("^I should see \"([^\"]*)\" in the error message$")
    public void i_should_see_in_the_error_message(String expectedMessage) throws Throwable {
        boolean correct = updateQAPercentage.isErrorMessageCorrect(expectedMessage);
        assertThat("Error message should contains : " + expectedMessage , correct, is(true));
    }

    @Then("^I should see qa percentage updated to \"([^\"]*)\"$")
    public void i_should_see_qa_percentage_updated_to(String expectedQAPercentage) throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        updateQAPercentage = actionsPage.clickUpdateQAPercentage();
        boolean correct = updateQAPercentage.isQAPercentageCorrect(expectedQAPercentage);
        assertThat("QA Percentage should be updated to : " + expectedQAPercentage , correct, is(true));
    }
}
