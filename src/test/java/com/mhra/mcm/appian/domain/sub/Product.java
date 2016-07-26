package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.utils.RandomDataUtils;

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
        brandName = beginsWith + RandomDataUtils.getRandomNumberBetween(1, 1000);
        launchDate = RandomDataUtils.getDateInFutureMonths(7);
        type = "1";
    }

    @Override
    public String toString() {
        return "Product: " +
                "\n brandName='" + brandName + '\'' +
                "\n launchDate='" + launchDate + '\'' +
                "\n type='" + type + '\'' +
                '\n';
    }
}
