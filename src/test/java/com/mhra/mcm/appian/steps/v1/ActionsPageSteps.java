package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.po.sections.contents.CreateNotification;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.NotificationUtils;
import cucumber.api.java.en.Given;
import org.springframework.context.annotation.Scope;

import java.util.Map;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class ActionsPageSteps extends CommonSteps {


    @Given("^I create new notification$")
    public void i_create_a_new_notification() throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        CreateNotification createNotification = actionsPage.clickUploadSampleNotification();
        Notification random = new Notification(2, 2);
        log.info("Create Notification With ECID : " +  random.ecIDNumber);
        actionsPage = createNotification.createRandomNotification(random);

        //Stored ecId for future use
        String ecId = random.ecIDNumber;
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, random);
        log.info("Notification Details : \n" + random);
    }


    @Given("^I create new notification with following data$")
    public void i_create_new_notification_with_following_data(Map<String, String> dataValues) throws Throwable {

        Notification random = NotificationUtils.updateDefaultNotification(dataValues);
        log.info("Create Notification With ECID : " +  random.ecIDNumber);

        //UPLOAD NOTIFICATION
        actionsPage = mainNavigationBar.clickActions();
        CreateNotification createNotification = actionsPage.clickUploadSampleNotification();
        actionsPage = createNotification.createRandomNotification(random);

        //Stored ecId for future use
        String ecId = random.ecIDNumber;
        scenarioSession.putData(SessionKey.ECID, ecId);
        scenarioSession.putData(SessionKey.storedNotification, random);
        log.debug("Notification Details : \n" + random);
        log.info("Created Notification With ECID : " +  random.ecIDNumber);
    }


    @Given("^I create (.*) new notifications$")
    public void i_create_a_new_notification(int numberOfNotifications) throws Throwable {
        for(int x = 0; x < numberOfNotifications; x++){
            try {
                actionsPage = mainNavigationBar.clickActions();
                CreateNotification createNotification = actionsPage.clickUploadSampleNotification();
                Notification random = new Notification(2, 2);
                actionsPage = createNotification.createRandomNotification(random);
            }catch(Exception e){
                e.printStackTrace();
            }
            Thread.sleep(1000*5);
        }
    }

}
