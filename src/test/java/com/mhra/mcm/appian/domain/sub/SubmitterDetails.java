package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.utils.RandomDataUtils;

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
        return "SubmitterDetails:" +
                "\n hasVAT=" + hasVAT +
                "\n vatNumber='" + vatNumber + '\'' +
                "\n hasEnterer=" + hasEnterer +
                "\n listOfEntererAddresses=" + listOfEntererAddresses +
                "\n hasParent=" + hasParent +
                "\n listOfParentAddresses=" + listOfParentAddresses +
                "\n hasAffiliate=" + hasAffiliate +
                "\n listOfAffiliateAddresses=" + listOfAffiliateAddresses +
                '\n';
    }
}
