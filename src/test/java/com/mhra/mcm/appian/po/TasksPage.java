package com.mhra.mcm.appian.po;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
public class TasksPage extends _Page {

    @Autowired
    public TasksPage(WebDriver driver) {
        super(driver);
    }
}
