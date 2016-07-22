package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.domain.utils.RandomDataUtils;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Summary {

    //Summary section
    public String ecId;
    public String submissionType;
    public String startDate;
    public String endDate;
    public String status;

    public Summary(String ecId){
        this.ecId = ecId;
        createDefaultValues();
    }

    private void createDefaultValues() {
        submissionType = "1";
        startDate = RandomDataUtils.getDateInFutureMonths(6);
        endDate = RandomDataUtils.getDateInFutureMonths(24);
        status = "1";
    }
}
