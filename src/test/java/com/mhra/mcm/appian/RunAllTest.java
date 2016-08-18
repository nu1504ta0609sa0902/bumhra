package com.mhra.mcm.appian;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Created by TPD_Auto on 12/07/2016.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-html-report", "json:target/all_tests.json"}
        , monochrome = true, tags = {"~@ignore"}
)
public class RunAllTest {
}
