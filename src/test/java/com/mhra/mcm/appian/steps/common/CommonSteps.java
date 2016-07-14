package com.mhra.mcm.appian.steps.common;

import com.mhra.mcm.appian.session.ScenarioSession;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import java.util.Properties;

import static org.codehaus.plexus.util.cli.CommandLineUtils.addShutdownHook;


@ContextConfiguration(locations = {"/cucumber.mhra.xml"})
public class CommonSteps {

    static boolean onlyOnce;

    @Value("${base.url}")
    public String baseUrl;

    @Autowired
    public WebDriver driver;

    @Autowired
    public ScenarioSession scenarioSession;

    public static final Logger log = LoggerFactory.getLogger(CommonSteps.class);

    /**
     * PageObjects: Main _Page objects, These page objects should create section objects
     */

    public static boolean oneDriverOnly = true;
    public CommonSteps() {
        String selectedProfile = System.getProperty("spring.profiles.active");
        // for prod we need to replace url with www
        if (selectedProfile.equals("mhra")) {
            baseUrl = baseUrl.replace("mhra.", "www.");
        } else {
            baseUrl = baseUrl;
        }

//        if(driver == null && oneDriverOnly){
//            oneDriverOnly = false;
//            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
//            driver = new InternetExplorerDriver(capabilities);
//        }
        //Add shutdown hook to close all the opened browser
        closeBrowserWhenFinished();
    }

    private void closeBrowserWhenFinished() {
        if (!onlyOnce) {
            onlyOnce = true;
            log.info("Close all browsers when testing done");

            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        if (driver != null) {
                            sleep(5000);
                            driver.quit();
                            //IE driver doesn't quit, so forced to try this
                            Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                            Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
                            log.info("All browsers closed after tests");
                        }
                    } catch (Exception e) {

                    }
                }
            });
        }
    }

    public void quit(){
        if(driver != null){
            driver.manage().deleteAllCookies();
        }
    }


    /**
     * Shutdown all the browsers after its done
     */ {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (driver != null) {
                        sleep(5000);
                        driver.quit();
                    }
                } catch (Exception e) {

                }
            }
        });
    }

}
