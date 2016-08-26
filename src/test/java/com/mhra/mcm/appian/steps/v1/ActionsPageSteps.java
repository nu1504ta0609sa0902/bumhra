package com.mhra.mcm.appian.steps.v1;

import java.util.List;
import java.util.Map;

import com.mhra.mcm.appian.domain.xmlPojo.EcigProductSubmission;
import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;
import org.springframework.context.annotation.Scope;

import com.mhra.mcm.appian.domain.webPagePojo.Notification;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.WaitUtils;
import com.mhra.mcm.appian.utils.helpers.page.NotificationUtils;

import cucumber.api.java.en.Given;

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
        actionsPage.isInCorrectPage();

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
        actionsPage.isInCorrectPage();

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
        actionsPage.isInCorrectPage();
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
                actionsPage.isInCorrectPage();
            }catch(Exception e){
                e.printStackTrace();
            }
            WaitUtils.nativeWait(5);
        }
    }



    @Given("^I create new xml notification with following data$")
    public void i_create_new_xml_notification_with_following_data(Map<String, String> dataValues) throws Throwable {


        EcigProductSubmission random = NotificationUtils.updateDefaultXMLNotification(dataValues, mapOfExcelDataAsMap);
        random.generateXml(random);
        String ecId = random.getEcIDNumber();
        log.info("Create Notification With ECID : " +  ecId);

        //UPLOAD NOTIFICATION
        //actionsPage = mainNavigationBar.clickActions();
        //createNotification = actionsPage.clickUploadSampleNotification();
        //actionsPage = createNotification.createRandomXMLNotification(random);
        //actionsPage.isInCorrectPage();

        //Stored ecId for future use
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, random);
        //log.debug("Notification Details : \n" + random);
        log.info("Created Notification With ECID : " +  ecId);
    }


}
