package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.domain.utils.RandomDataUtils;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Product {

    String beginsWith;
    String brandName;
    String launchDate;
    String type;

    public Product(String testE2E) {
        beginsWith = testE2E;
        createDefaults();
    }

    private void createDefaults() {
        brandName = beginsWith + RandomDataUtils.getRandomNumberBetween(1, 100);
        launchDate = RandomDataUtils.getDateInFutureMonths(7);
        type = "1";
    }
}
