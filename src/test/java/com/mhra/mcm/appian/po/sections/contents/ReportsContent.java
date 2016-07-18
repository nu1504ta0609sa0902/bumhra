package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.po._Page;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
public class ReportsContent extends _Page {

    @Autowired
    public ReportsContent(WebDriver driver) {
        super(driver);
    }
}
