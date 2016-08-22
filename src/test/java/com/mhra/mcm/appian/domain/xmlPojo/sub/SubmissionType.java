package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.sub.Address;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class SubmissionType {

    public boolean confidential;
    public String type;

    public SubmissionType(boolean confidential, String type) {
        this.confidential = confidential;
        this.type = type;
    }

}
