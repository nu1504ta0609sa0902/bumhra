package com.mhra.mcm.appian.domain.webPagePojo.sub;

import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

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
        startDate = RandomDataUtils.getDateInFutureMonths(7);
        endDate = RandomDataUtils.getDateInFutureMonths(24);
        status = "1";
    }

    @Override
    public String toString() {
        return "#Summary:" +
                "\nsummary.ecId=" + ecId +
                "\nsummary.submissionType=" + submissionType +
                "\nsummary.startDate=" + startDate +
                "\nsummary.endDate=" + endDate +
                "\nsummary.status=" + status +
                '\n';
    }
}
