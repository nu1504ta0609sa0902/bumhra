package com.mhra.mcm.appian.domain.webPagePojo.sub;

import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class SubmitterDetails {

    public boolean hasVAT;
    public String vatNumber;
    public boolean hasEnterer;
    public List<Address> listOfEntererAddresses = new ArrayList<>();
    public boolean hasParent;
    public List<Address> listOfParentAddresses = new ArrayList<>();
    public boolean hasAffiliate;
    public List<Address> listOfAffiliateAddresses = new ArrayList<>();

    public SubmitterDetails(){
        createDefualts();
    }

    private void createDefualts() {
        hasVAT = false;
        if(hasVAT)
        vatNumber = "0161" + (int) RandomDataUtils.getRandomDigits(7);
        hasEnterer = false;
        hasParent = false;
        hasAffiliate = false;
    }

    @Override
    public String toString() {
        return "#SubmitterDetails:" +
                "\nsubmitter.details.hasVAT=" + hasVAT +
                "\nsubmitter.details.vatNumber='" + vatNumber +
                "\nsubmitter.details.hasEnterer=" + hasEnterer +
                "\nsubmitter.details.listOfEntererAddresses=" + listOfEntererAddresses +
                "\nsubmitter.details.hasParent=" + hasParent +
                "\nsubmitter.details.listOfParentAddresses=" + listOfParentAddresses +
                "\nsubmitter.details.hasAffiliate=" + hasAffiliate +
                "\nsubmitter.details.listOfAffiliateAddresses=" + listOfAffiliateAddresses +
                '\n';
    }
}
