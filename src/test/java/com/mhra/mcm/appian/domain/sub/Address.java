package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Address {

    public String address;
    public String countryIndex;
    public String countryName;
    public String phone;
    public String email;
    public boolean productionSite;
    public boolean addressConfidential;

    public Address(){
        createDefault();
    }

    private void createDefault() {
        address = RandomDataUtils.getRandomNumberBetween(1, 200) + " " + RandomDataUtils.getRandomTestName("Test" ).replace("_","") + " GrowLand Avenue, London, UB7 6UU";
        countryIndex = RandomDataUtils.getRandomNumberBetween(1, 31);
        countryName = "United Kingdom";
        phone = "07941" + (int) RandomDataUtils.getRandomDigits(7);
        email = "testE2E@" + (int) RandomDataUtils.getRandomDigits(7) + ".com";
        email = "mhra.uat@gmail.com";
        productionSite = false;
        addressConfidential = false;
    }

    @Override
    public String toString() {
        return "#Address:" +
                "\naddress.address=" + address +
                "\naddress.country=" + countryName +
                "\naddress.phone=" + phone +
                "\naddress.email=" + email +
                "\naddress.productionSite=" + productionSite +
                "\naddress.addressConfidential=" + addressConfidential +
                '\n';
    }
}
