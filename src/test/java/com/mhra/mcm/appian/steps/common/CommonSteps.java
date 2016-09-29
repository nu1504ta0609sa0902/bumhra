package com.mhra.mcm.appian.steps.common;

import com.mhra.mcm.appian.pageobjects.*;
import com.mhra.mcm.appian.pageobjects.sections.MainNavigationBar;
import com.mhra.mcm.appian.pageobjects.sections.contents.*;
import com.mhra.mcm.appian.session.ScenarioSession;
import com.mhra.mcm.appian.utils.helpers.datadriven.ExcelUtils;
import com.mhra.mcm.appian.utils.helpers.others.NetworkUtils;
import com.mhra.mcm.appian.utils.helpers.page.PageUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;


@ContextConfiguration(locations = {"/cucumber.mhra.xml"})
public class CommonSteps {

    static boolean onlyOnce;

    @Value("${base.url}")
    public String baseUrl;
    @Value("${current.browser}")
    public String currentBrowser;

    @Autowired
    public WebDriver driver;

    @Autowired
    public ScenarioSession scenarioSession;

    public static final Logger log = LoggerFactory.getLogger(CommonSteps.class);
    public static final String PRETTY_REPORT = "PrettyReport";
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
    @Autowired
    public UpdateQAPercentage updateQAPercentage;
    @Autowired
    public AuditHistory auditHistory;
    @Autowired
    public Comments commentSection;
    @Autowired
    public ManageSubstances manageSubstances;
    @Autowired
    public NotificationsReport notificationsReport;
    @Autowired
    public Documents documents;

    public static boolean oneDriverOnly = true;
    public CommonSteps() {
        String selectedProfile = System.getProperty("spring.profiles.active");
        // for prod we need to replace url with www
        if (selectedProfile.equals("mhra")) {
            baseUrl = baseUrl.replace("mhra.", "www.");
        }

        //Make sure network connection is available
        String testUrl = NetworkUtils.getTestUrl(baseUrl);
        boolean connected = NetworkUtils.verifyConnectedToNetwork(testUrl, 10);
        if(!connected){
            NetworkUtils.shutdownIfRequired(connected, testUrl, log);
        }else {
            loadMapOfExcelData();
            addShutdownHooks();
        }
    }

    private void addShutdownHooks() {
        if(driver==null){
            if(!onlyOnce){
                onlyOnce=true;
                quit();
            }
        }else{
            //WaitUtils.setImplicitWaits(driver);
            PageUtils.setBrowserZoom(driver, currentBrowser);
        }
    }

    private void loadMapOfExcelData() {
        if(mapOfExcelDataAsMap == null && driver == null){
            //Load excel test data
            ExcelUtils excelUtils = new ExcelUtils();
            //mapOfExcelDataAsMap = excelUtils.getAllData("configs/data/xmlTestData1.xlsx");
            mapOfExcelDataAsMap = excelUtils.getAllDataAsMap("configs/data/xmlTestData2.xlsx");
            log.info("TEST DATA LOADED FROM : configs/data/xmlTestData2.xlsx");
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
                        log.info("If -Dgenerate.report=true than it generate the pretty report");
                        sleep(20000);
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
