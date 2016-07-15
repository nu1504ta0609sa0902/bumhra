package com.mhra.mcm.appian.po;

import com.mhra.mcm.appian.steps.common.CommonSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created by TPD_Auto on 14/07/2016.
 */
@ContextConfiguration(locations = {"/cucumber.mhra.xml"})
public class _Page {


    public WebDriver driver;
    public final Logger log = LoggerFactory.getLogger(_Page.class);


    public _Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriver getDriver() {
        return driver;
    }
}
