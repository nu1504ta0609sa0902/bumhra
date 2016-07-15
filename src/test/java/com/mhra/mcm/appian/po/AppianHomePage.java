package com.mhra.mcm.appian.po;

import com.mhra.mcm.appian.utils.ReadPropertiesFile;
import com.mhra.mcm.appian.utils.WaitUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * Created by TPD_Auto on 14/07/2016.
 */
@Component
public class AppianHomePage extends _Page {

    @FindBy(id="un")
    WebElement username;
    @FindBy(id="pw")
    WebElement password;
    @FindBy(css="input#remember")
    WebElement remember;
    @FindBy(css=".gwt-Anchor.pull-down-toggle")
    WebElement settings;
    @FindBy(css=".settings-pull-down .gwt-Anchor.pull-down-toggle")
    WebElement loggedInUsername;

    @Autowired
    public AppianHomePage(WebDriver driver) {
        super(driver);
    }

    public AppianHomePage loadPage(String url) {
        driver.get(url);
        return new AppianHomePage(driver);
    }

    public AppianHomePage login(String uname) {
        //logoutIfLoggedIn(uname);
        dontRemember();

        //get login details
        String selectedProfile = System.getProperty("spring.profiles.active");
        Properties props = ReadPropertiesFile.loadPropertiesFile("users.properties");
        uname = props.getProperty(selectedProfile + ".username." + uname);
        String pword = props.getProperty(selectedProfile + ".password." + uname);

        //login
        username.sendKeys(uname);
        password.sendKeys(pword);
        username.submit();

        return new AppianHomePage(driver);
    }

    private void dontRemember(){
        if(remember.getAttribute("checked")!=null){
            remember.click();
            log.info("We don't want the system to remember us");
        }
    }
    private void logoutIfLoggedIn(String uname) {
        if(settings.isDisplayed()){
            settings.click();
            driver.findElement(By.linkText("Sign Out")).click();
        }
    }

    public String getLoggedInUserName() {
        WaitUtils.waitForElementToBeClickable(driver,loggedInUsername, 10);
        return loggedInUsername.getText();
    }
}
