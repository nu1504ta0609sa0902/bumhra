package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.po.AppianHomePage;
import com.mhra.mcm.appian.steps.common.CommonSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.context.annotation.Scope;

import java.util.Properties;

/**
 * Created by TPD_Auto on 12/07/2016.
 */
@Scope("cucumber-glue")
public class HomePageSteps extends CommonSteps {

    @Given("^I am logged into MHRA application using$")
    public void i_am_logged_into_MHRA_application_using(DataTable users) throws Throwable {
        System.out.println("Test : " + users);
    }

    @Given("^I am in appian page$")
    public void i_am_in_appian_page() throws Throwable {
        appianHomePage = appianHomePage.loadPage(baseUrl);
    }

    @When("^I login as user \"([^\"]*)\"$")
    public void i_login_as_user(String username) throws Throwable {
        appianHomePage = appianHomePage.login(username);
    }

    @Then("^I should see name of logged in user as \"([^\"]*)\"$")
    public void i_should_see_name_of_logged_in_user_as(String expectedName) throws Throwable {
        String actualName = appianHomePage.getLoggedInUserName();
        Assert.assertThat(actualName.toLowerCase(), Matchers.equalTo(expectedName));
    }
}
