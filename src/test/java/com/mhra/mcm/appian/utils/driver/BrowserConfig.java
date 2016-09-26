package com.mhra.mcm.appian.utils.driver;

import java.net.URI;
import java.net.URISyntaxException;

import com.mhra.mcm.appian.utils.reporter.CreatePrettyReport;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Use this or the cucumber.xml don't use both
 * @author tayyibah
 *
 */
@Configuration
public class BrowserConfig {
	
	@Autowired
    public WebDriver driver;
	public String browser;

	@Bean
    public WebDriver getDriver() {

		if(browser == null){
			browser = System.getProperty("current.browser");
		}
		
    	if(browser!=null && driver==null){
    		
    		if(browser.equals("ff") || browser.equals("firefox")){
        		return new FirefoxDriver();
    		}else if(browser.equals("gc") || browser.equals("chrome")){
        		return new ChromeDriver();
    		}else if(browser.equals("ie") || browser.equals("internetexplorer")){
				DesiredCapabilities ieCap = getIEDesiredCapabilities();
        		return new InternetExplorerDriver(ieCap);
    		}else{
				DesiredCapabilities ieCap = getIEDesiredCapabilities();
				return new InternetExplorerDriver(ieCap);
    		}
    	}
		else{
			return null;
			//return new InternetExplorerDriver();
    	}
    }

	private DesiredCapabilities getIEDesiredCapabilities() {
		DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();

		ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
		ieCapabilities.setCapability("enablePersistentHover", false);

		ieCapabilities.setCapability("nativeEvents", true);
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("disable-popup-blocking", false);
		return ieCapabilities;
	}



}