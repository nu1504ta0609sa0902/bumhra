package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.utils.RandomDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Submitter {

    public final String beginsWith;
    public String name;
    public String submitterType;
    public boolean sme;
    public String euIdentifier;
    public String tcaNumber;
    public boolean confidential;
    public List<Address> listOfAddresses = new ArrayList<>();

    public Submitter(String ecId, String testNameBeginsWith) {
        beginsWith = testNameBeginsWith;
        euIdentifier = ecId;
        createDefaults();
    }

    private void createDefaults() {
        name = RandomDataUtils.getRandomTestName(beginsWith);
        submitterType = "1";
        sme = false;
        confidential = false;
        tcaNumber = "" + (int) RandomDataUtils.getRandomDigits(5);
        Address submitterAddress = new Address();
        listOfAddresses.add(submitterAddress);
    }

    @Override
    public String toString() {
        return "Submitter:" +
                "\n name='" + name + '\'' +
                "\n submitterType='" + submitterType + '\'' +
                "\n sme=" + sme +
                "\n euIdentifier='" + euIdentifier + '\'' +
                "\n tcaNumber='" + tcaNumber + '\'' +
                "\n confidential=" + confidential +
                "\n listOfAddresses=" + listOfAddresses +
                '\n';
    }
}
