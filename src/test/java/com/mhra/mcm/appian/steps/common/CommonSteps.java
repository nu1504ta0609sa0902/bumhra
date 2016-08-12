package com.mhra.mcm.appian.steps.common;

import com.mhra.mcm.appian.po.*;
import com.mhra.mcm.appian.po.sections.MainNavigationBar;
import com.mhra.mcm.appian.po.sections.contents.EditNotification;
import com.mhra.mcm.appian.po.sections.contents.NotificationDetails;
import com.mhra.mcm.appian.session.ScenarioSession;
import com.mhra.mcm.appian.utils.reporter.CreatePrettyReport;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import java.util.concurrent.TimeUnit;


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
    public static final String PRETTY_REPORT = "PrettyReport";
    public static CreatePrettyReport pr;

    /**
     * PageObjects: Main _Page objects, These page objects should create section objects
     */
    @Autowired
    public LoginPage loginPage;
    @Autowired
    public MainNavigationBar mainNavigationBar;
    @Autowired
    public NewsPage newsPage;
    @Autowired
    public RecordsPage recordsPage;
    @Autowired
    public EditNotification notificationPage;
    @Autowired
    public ActionsPage actionsPage;
    @Autowired
    public NotificationDetails notificationDetails;
    @Autowired
    public TasksPage tasksPage;

    public static boolean oneDriverOnly = true;
    public CommonSteps() {
        String selectedProfile = System.getProperty("spring.profiles.active");
        // for prod we need to replace url with www
        if (selectedProfile.equals("mhra")) {
            baseUrl = baseUrl.replace("mhra.", "www.");
        }
        if(driver==null){
            quit();
        }else{
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }


    /**
     * Shutdown all the browsers after its done
     */
    public void quit(){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    if (driver != null) {
                        sleep(10000);
                        driver.quit();
                        //IE driver doesn't quit, so forced to try this
                        //Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
                        //Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
                        log.info("All browsers closed after tests");
                    }
                } catch (Exception e) {

                }
            }
        });
    }


}
