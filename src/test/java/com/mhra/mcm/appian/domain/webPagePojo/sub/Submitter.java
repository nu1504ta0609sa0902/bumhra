package com.mhra.mcm.appian.domain.webPagePojo.sub;

import java.util.ArrayList;
import java.util.List;

import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

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
        submitterType = "2";
        sme = false;
        confidential = false;
        tcaNumber = RandomDataUtils.getRandomNumberBetween(1000*100, 1000*1000);
        Address submitterAddress = new Address();
        listOfAddresses.add(submitterAddress);
    }

    @Override
    public String toString() {
        return "#Submitter:" +
                "\nsubmitter.name=" + name +
                "\nsubmitter.submitterType=" + submitterType +
                "\nsubmitter.sme=" + sme +
                "\nsubmitter.euIdentifier=" + euIdentifier +
                "\nsubmitter.tcaNumber=" + tcaNumber +
                "\nsubmitter.confidential=" + confidential +
                "\n\nsubmitter.listOfAddresses=" + listOfAddresses +
                '\n';
    }
}
