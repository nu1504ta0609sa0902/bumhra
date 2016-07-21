package com.mhra.mcm.appian.po.sections.filters;

import com.mhra.mcm.appian.po._Page;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
@Component
public class ActionsFilter extends _Page {

    @Autowired
    public ActionsFilter(WebDriver driver) {
        super(driver);
    }
}
