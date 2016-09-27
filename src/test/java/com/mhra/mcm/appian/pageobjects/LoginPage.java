package com.mhra.mcm.appian.pageobjects;

import com.mhra.mcm.appian.pageobjects.sections.MainNavigationBar;
import com.mhra.mcm.appian.utils.helpers.others.FileUtils;
import com.mhra.mcm.appian.utils.helpers.page.AssertUtils;
import com.mhra.mcm.appian.utils.helpers.page.WaitUtils;
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
public class LoginPage extends _Page {

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
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage loadPage(String url) {
        WaitUtils.nativeWait(2);
        driver.get(url);
        return new LoginPage(driver);
    }

    public MainNavigationBar login(String uname) {
        dontRemember();

        //get login details
        String selectedProfile = System.getProperty("spring.profiles.active");
        Properties props = FileUtils.loadPropertiesFile("users.properties");
        uname = props.getProperty(selectedProfile + ".username." + uname);
        String pword = props.getProperty(selectedProfile + ".password." + uname);

        //login
        username.sendKeys(uname);
        password.sendKeys(pword);
        username.submit();

        return new MainNavigationBar(driver);
    }

    public MainNavigationBar reloginUsing(String uname){
        logoutIfLoggedIn();
        MainNavigationBar login = login(uname);
        return login;
    }

    private void dontRemember(){
        if(remember.getAttribute("checked")!=null){
            remember.click();
        }
    }

    private LoginPage logoutIfLoggedIn() {
        try {
            WaitUtils.waitForElementToBeClickable(driver,settings, 10, false);
            if (settings.isDisplayed()) {
                settings.click();
                driver.findElement(By.linkText("Sign Out")).click();
                WaitUtils.waitForElementToBeClickable(driver,remember, 10);
                WaitUtils.nativeWait(2);
            }
        }catch(Exception e){
            WaitUtils.waitForElementToBeClickable(driver,username, 10, false);
        }
        return new LoginPage(driver);
    }

    public String getLoggedInUserName() {
        WaitUtils.waitForElementToBeClickable(driver,loggedInUsername, 10);
        return loggedInUsername.getText();
    }

    public boolean isAlreadyLoggedInAsUser(String username) {
        try {
            WaitUtils.waitForElementToBeClickable(driver,loggedInUsername, 10, false);
            String userNameDisplayed = loggedInUsername.getText().toLowerCase();
            String expectedName = AssertUtils.getExpectedName(username).toLowerCase();
            return userNameDisplayed.equals(expectedName);
        }catch (Exception e){
            //Not logged in
            return false;
        }
    }
}
