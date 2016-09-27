package com.mhra.mcm.appian.utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Use this or the cucumber.xml don't use both
 * @author tayyibah
 *
 */
@Configuration
public class BrowserConfig {
	
	//@Autowired
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
			}else if(browser.equals("pjs") || browser.equals("phantom")){
				DesiredCapabilities ieCap = getPJSDesiredCapabilities();
				return new PhantomJSDriver(ieCap);
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

	private DesiredCapabilities getPJSDesiredCapabilities() {
		String path = System.getProperty("phantomjs.binary.path");
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		caps.setCapability("takesScreenshot", true);
		caps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
				"--webdriver=8910",
//				"--ssl-protocol=any",
//				"--ignore-ssl-errors=true",
				"--webdriver-loglevel=INFO"
		});

		//Only required if not in the path
		caps.setCapability(	PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, path );
		return caps;
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