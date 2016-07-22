package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.domain.utils.RandomDataUtils;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Address {

    private String address;
    private String country;
    private String phone;
    private String email;
    private boolean productionSite;
    private boolean addressConfidential;

    public Address(){
        createDefault();
    }

    private void createDefault() {
        address = RandomDataUtils.getRandomNumberBetween(1, 200) + " " + RandomDataUtils.getRandomTestName("Test" ) + " GrowLand Avenue, London, UB7 6UU";
        country = RandomDataUtils.getRandomNumberBetween(1, 31);
        phone = "0794" + (int) RandomDataUtils.getRandomDigits(7);
        email = "testE2E@" + (int) RandomDataUtils.getRandomDigits(7) + ".com";
        productionSite = false;
        addressConfidential = false;
    }

}
