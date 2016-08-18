package com.mhra.mcm.appian.steps.v1;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.springframework.context.annotation.Scope;

import com.mhra.mcm.appian.steps.common.CommonSteps;
import com.mhra.mcm.appian.utils.helpers.AssertUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by TPD_Auto on 12/07/2016.
 */
@Scope("cucumber-glue")
public class LoginPageSteps extends CommonSteps {

    @Given("^I am in appian page$")
    public void i_am_in_appian_page() throws Throwable {
        loginPage = loginPage.loadPage(baseUrl);
    }

    @When("^I login as user \"([^\"]*)\"$")
    public void i_login_as_user(String username) throws Throwable {
        try {
            mainNavigationBar = loginPage.login(username);
        }catch(Exception e) {
            mainNavigationBar = loginPage.reloginUsing(username);
        }
    }

    @When("^I am logged into appian as \"([^\"]*)\" user$")
    public void i_am_logged_into_appian_as_user(String username) throws Throwable {
        loginPage = loginPage.loadPage(baseUrl);
        try {
            mainNavigationBar = loginPage.login(username);
        }catch(Exception e) {
            mainNavigationBar = loginPage.reloginUsing(username);
        }
    }

    @Then("^I should see name of logged in user as \"([^\"]*)\"$")
    public void i_should_see_name_of_logged_in_user_as(String expectedName) throws Throwable {
        String actualName = loginPage.getLoggedInUserName();
        expectedName = AssertUtils.getExpectedName(expectedName);
        Assert.assertThat(actualName.toLowerCase(), Matchers.equalTo(expectedName));
    }

    @When("^I re login as user \"([^\"]*)\"$")
    public void i_re_login_as_user(String username) throws Throwable {
        mainNavigationBar = loginPage.reloginUsing(username);
    }


    @Given("^I login to appian as \"([^\"]*)\" user$")
    public void i_login_to_appian_as_user(String username) throws Throwable {
        loginPage = loginPage.loadPage(baseUrl);
        mainNavigationBar = loginPage.login(username);
    }

    @When("^I login as \"([^\"]*)\" and generate a standard invoice$")
    public void i_login_as_and_generate_a_standard_invoice(String username) throws Throwable {
        mainNavigationBar = loginPage.reloginUsing(username);
        actionsPage = mainNavigationBar.clickActions();
        //only FINANCE users should see this option
        actionsPage = actionsPage.generateStandardInvoices();
    }

    @When("^I generate a standard invoice$")
    public void i_generate_a_standard_invoice() throws Throwable {
        actionsPage = mainNavigationBar.clickActions();
        //only FINANCE users should see this option
        actionsPage = actionsPage.generateStandardInvoices();
    }
}
