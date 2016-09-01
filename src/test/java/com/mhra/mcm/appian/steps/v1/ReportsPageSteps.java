package com.mhra.mcm.appian.steps.v1;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import cucumber.api.java.en.When;
import org.springframework.context.annotation.Scope;

import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.steps.common.CommonSteps;

import cucumber.api.java.en.Then;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Scope("cucumber-glue")
public class ReportsPageSteps extends CommonSteps {


    @Then("^I should see the notification displayed in exception page$")
    public void i_should_see_the_notification_displayed_in_exception_page() throws Throwable {
        reportsPage = mainNavigationBar.clickReports();
        exception = reportsPage.gotoExceptionsPage();
        String ecId = (String) scenarioSession.getData(SessionKey.ECID);

        boolean isDisplayed = exception.isNotificationDisplayed(ecId);
        assertThat("Exception page should display a notification with EC ID : " + ecId , isDisplayed, is(equalTo(true)));
    }


    @When("^I view a notification displayed in exception page$")
    public void i_select_a_notification_displayed_in_exception_page() throws Throwable {
        reportsPage = mainNavigationBar.clickReports();
        exception = reportsPage.gotoExceptionsPage();

        String ecid = exception.selectARandomException();
        scenarioSession.putData(SessionKey.ECID, ecid);

        notificationDetails = exception.clickOnNotificationWithEcid(ecid);
        log.info("Clicked notification with ecid : " + ecid);
    }


    @When("^I view an existing notification with ecid  \"([^\"]*)\" in exception page$")
    public void i_view_an_existing_notification_displayed_in_exception_page(String ecid) throws Throwable {
        reportsPage = mainNavigationBar.clickReports();
        exception = reportsPage.gotoExceptionsPage();

        scenarioSession.putData(SessionKey.ECID, ecid);

        notificationDetails = exception.clickOnNotificationWithEcid(ecid);
        log.info("Clicked notification with ecid : " + ecid);
    }
}
