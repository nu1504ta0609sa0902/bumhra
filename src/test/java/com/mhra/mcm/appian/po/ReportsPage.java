package com.mhra.mcm.appian.po;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
public class ReportsPage extends _Page {

    @Autowired
    public ReportsPage(WebDriver driver) {
        super(driver);
    }
}
