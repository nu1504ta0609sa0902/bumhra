package com.mhra.mcm.appian.pageobjects.sections.filters;

import com.mhra.mcm.appian.pageobjects._Page;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ReportsFilter extends _Page {

    @Autowired
    public ReportsFilter(WebDriver driver) {
        super(driver);
    }
}
