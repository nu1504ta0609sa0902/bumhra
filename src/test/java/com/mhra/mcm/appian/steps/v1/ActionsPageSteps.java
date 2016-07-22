package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.po.sections.contents.CreateNotification;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import cucumber.api.java.en.Given;
import org.springframework.context.annotation.Scope;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class ActionsPageSteps extends CommonSteps {


    @Given("^I create a new notification$")
    public void i_create_a_new_notification() throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        CreateNotification createNotification = actionsPage.clickUploadSampleNotification();
        createNotification.createRandomNotification(new Notification(10,10));
    }

}
