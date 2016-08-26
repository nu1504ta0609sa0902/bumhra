package com.mhra.mcm.appian.steps.common;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.mhra.mcm.appian.utils.helpers.others.datadriven.ExcelUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import com.mhra.mcm.appian.po.ActionsPage;
import com.mhra.mcm.appian.po.LoginPage;
import com.mhra.mcm.appian.po.NewsPage;
import com.mhra.mcm.appian.po.RecordsPage;
import com.mhra.mcm.appian.po.ReportsPage;
import com.mhra.mcm.appian.po.TasksPage;
import com.mhra.mcm.appian.po.sections.MainNavigationBar;
import com.mhra.mcm.appian.po.sections.contents.CreateNotification;
import com.mhra.mcm.appian.po.sections.contents.EditNotification;
import com.mhra.mcm.appian.po.sections.contents.Exceptions;
import com.mhra.mcm.appian.po.sections.contents.NotificationDetails;
import com.mhra.mcm.appian.session.ScenarioSession;


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
    public static Map<String, List> mapOfExcelData;
    public static Map<String, Map> mapOfExcelDataAsMap;

    /**
     * PageObjects: Main _Page objects, These page objects should create section objects
     */
    @Autowired
    public MainNavigationBar mainNavigationBar;
    @Autowired
    public LoginPage loginPage;
    @Autowired
    public NewsPage newsPage;
    @Autowired
    public RecordsPage recordsPage;
    @Autowired
    public ActionsPage actionsPage;
    @Autowired
    public TasksPage tasksPage;
    @Autowired
    public ReportsPage reportsPage;

    //SECTIONS
    @Autowired
    public NotificationDetails notificationDetails;
    @Autowired
    public EditNotification editNotification;
    @Autowired
    public CreateNotification createNotification;
    @Autowired
    public Exceptions exception;

    public static boolean oneDriverOnly = true;
    public CommonSteps() {
        String selectedProfile = System.getProperty("spring.profiles.active");
        // for prod we need to replace url with www
        if (selectedProfile.equals("mhra")) {
            baseUrl = baseUrl.replace("mhra.", "www.");
        }
        if(mapOfExcelData == null){
            //Load excel test data
            ExcelUtils excelUtils = new ExcelUtils();
            //mapOfExcelData = excelUtils.getAllData("configs/data/xmlTestData1.xlsx");
            mapOfExcelDataAsMap = excelUtils.getAllDataAsMap("configs/data/xmlTestData1.xlsx");
            log.info("TEST DATA LOADED FROM : configs/data/xmlTestData1.xlsx");
        }
        if(driver==null){
            if(!onlyOnce){
                onlyOnce=true;
                quit();
            }
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
                        log.info("Should generate the pretty report");
                        sleep(15000);
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
