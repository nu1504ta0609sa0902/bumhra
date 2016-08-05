package com.mhra.mcm.appian.steps.common;

import com.mhra.mcm.appian.session.SessionKey;
import com.mhra.mcm.appian.utils.reporter.CreatePrettyReport;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;


import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * @author TPD_Auto
 * 
 */
@Scope("cucumber-glue")
public class SharedSteps extends CommonSteps {

	public SharedSteps(){
		generatePrettyReportOnTheGo();
	}

	/**
	 * Take screen shot
	 * @param scenario
	 */
	@After  
    public void embedScreenshot(Scenario scenario) {
		//generatePrettyReportOnTheGo();
        if (driver!=null && scenario.isFailed()) {
        	log.info("Scenario Failed");
    		log.info("==================================\n");
            try {  
            	byte[] bytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            	scenario.embed(bytes, "image/png");
            } catch (WebDriverException wde) {  
                System.err.println(wde.getMessage());  
            } catch (ClassCastException cce) {  
                cce.printStackTrace();  
            } catch (Exception e){
            	e.printStackTrace();
            }
        }  else{
        	log.info("Scenario Passed");
    		//log.info("\n==================================");
        	
        }
    }


	@Before
	public void logScenarioNames(Scenario scenario) {
		//generatePrettyReportOnTheGo();
		if(driver!=null){
			log.info("\n==================================\n");
			log.info("NEW SCENARIO");
			log.info(scenario.getName());
			log.info("\n==================================\n");

			//store current scenario and test environment details
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			scenarioSession.putData(SessionKey.scenarioName, scenario);
			String env = System.getProperty("spring.profiles.active");
			scenarioSession.putData(SessionKey.environment, env);
		}

	}



	public static CreatePrettyReport pr;
    /**
     * This will generate pretty report on the go
     */
    private void generatePrettyReportOnTheGo() {
		String generateReport = System.getProperty("generate.report");
		if(pr == null && generateReport != null && ( generateReport.trim().equals("true") || generateReport.trim().equals("yes"))){
            log.info("Will Create Pretty Report On The Go");
            pr = new CreatePrettyReport();
            pr.monitorFolder("PrettyReport");
        }
    }

}
