package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.po.sections.contents.Exceptions;
import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import cucumber.api.java.en.Then;
import org.springframework.context.annotation.Scope;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class ReportsPageSteps extends CommonSteps {


    @Then("^I should see the notification displayed in exception page$")
    public void i_should_see_the_notification_displayed_in_exception_page() throws Throwable {
        reportsPage = mainNavigationBar.clickReports();
        Exceptions exception = reportsPage.gotoExceptionsPage();
        String ecId = (String) scenarioSession.getData(SessionKey.ECID);

        boolean isDisplayed = exception.isNotificationDisplayed(ecId);
        assertThat("Exception page should display a notification with EC ID : " + ecId , isDisplayed, is(equalTo(true)));
    }
}
