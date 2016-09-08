package com.mhra.mcm.appian.domain.webPagePojo.sub;

import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Product {

    public String beginsWith;
    public String brandName;
    public String launchDate;
    public String type;

    public Product(String testE2E) {
        beginsWith = testE2E;
        createDefaults();
    }

    private void createDefaults() {
        brandName = beginsWith + RandomDataUtils.getRandomNumberBetween(1, 1000) + RandomDataUtils.getRandomTestName("");
        launchDate = RandomDataUtils.getDateInFutureMonths(7);
        type = "1";
    }

    @Override
    public String toString() {
        return "#Product: " +
                "\nproduct.brandName=" + brandName  +
                "\nproduct.launchDate=" + launchDate   +
                "\nproduct.type=" + type +
                '\n';
    }
}
