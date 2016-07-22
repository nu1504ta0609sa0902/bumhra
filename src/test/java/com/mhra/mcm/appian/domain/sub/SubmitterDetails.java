package com.mhra.mcm.appian.domain.sub;

import com.mhra.mcm.appian.domain.utils.RandomDataUtils;

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
        hasVAT = true;
        vatNumber = "" + RandomDataUtils.getRandomDigits(10);
        hasEnterer = false;
        hasParent = false;
        hasAffiliate = false;
    }
}
