package com.mhra.mcm.appian.steps.common;

import com.mhra.mcm.appian.session.SessionKey;
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
	
	/**
	 * Take screen shot
	 * @param scenario
	 */
	@After  
    public void embedScreenshot(Scenario scenario) {
        //generatePrettyReportOnTheGo();
        if (scenario.isFailed()) {  
        	System.out.println("Scenario Failed");
    		System.out.println("==================================\n");
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
        	System.out.println("Scenario Passed");
    		//System.out.println("\n==================================");
        	
        }
    }

	
	@Before
	public void logScenarioNames(Scenario scenario) {
	    //System.setProperty("spring.profiles.active", browser);
		System.out.println("\n==================================\n");
		System.out.println("NEW SCENARIO");
		System.out.println(scenario.getName());
		System.out.println("\n==================================\n");

        //store current scenario and test environment details
		driver.manage().deleteAllCookies();
        scenarioSession.putData(SessionKey.scenarioName, scenario);
        String env = System.getProperty("spring.profiles.active");
        scenarioSession.putData(SessionKey.environment, env);

	}



    /**
     * This will generate pretty report on the go
     */
//    private void generatePrettyReportOnTheGo() {
//        if(pr == null && (generateReport==true)){
//            System.out.println("Will Create Pretty Report On The Go");
//            pr = new CreatePrettyReport();
//            pr.monitorFolder("PrettyReport");
//        }
//
//    }

}
