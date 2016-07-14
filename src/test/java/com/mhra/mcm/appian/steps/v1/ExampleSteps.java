package com.mhra.mcm.appian.steps.v1;

import com.mhra.mcm.appian.steps.common.CommonSteps;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.ContextConfiguration;

import java.util.Properties;

/**
 * Created by TPD_Auto on 12/07/2016.
 */
@Scope("cucumber-glue")
public class ExampleSteps extends CommonSteps {

    @Given("^I am logged into MHRA application using$")
    public void i_am_logged_into_MHRA_application_using(DataTable users) throws Throwable {
        System.out.println("Test : " + users);
    }

    @Given("^I am in appian page$")
    public void i_am_in_appian_page() throws Throwable {
        driver.get(baseUrl);
        Properties properties = System.getProperties();
        System.out.println("" + baseUrl + "\n" + driver.getCurrentUrl());
    }
}
