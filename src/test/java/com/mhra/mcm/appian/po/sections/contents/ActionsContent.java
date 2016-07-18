package com.mhra.mcm.appian.po.sections.contents;

import com.mhra.mcm.appian.po._Page;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by TPD_Auto on 18/07/2016.
 */
public class ActionsContent extends _Page {

    @Autowired
    public ActionsContent(WebDriver driver) {
        super(driver);
    }
}
