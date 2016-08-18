package com.mhra.mcm.appian.po;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class NewsPage extends _Page {

    @Autowired
    public NewsPage(WebDriver driver) {
        super(driver);
    }
}
