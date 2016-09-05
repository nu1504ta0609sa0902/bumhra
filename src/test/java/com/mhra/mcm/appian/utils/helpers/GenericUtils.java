package com.mhra.mcm.appian.utils.helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mhra.mcm.appian.domain.webPagePojo.sub.Invoice;
import org.openqa.selenium.WebElement;

/**
 * Created by TPD_Auto on 11/08/2016.
 */
public class GenericUtils {

    /**
     * Get count of unique ECID
     * @param listOfMatches
     * @return
     */
    public static int getUniqueECIDCount(List<WebElement> listOfMatches) {
        Set<String> setOfEcid = new HashSet<>();
        for(WebElement e: listOfMatches){
            setOfEcid.add(e.getText());
        }

        return setOfEcid.size();
    }

    /**
     * Check status displayed is unique and its of type filterBy
     * @param listOfMatches
     * @param filterBy
     * @return
     */
    public static boolean isAllStatusMatching(List<WebElement> listOfMatches, String filterBy) {
        Set<String> setOfStatuses = new HashSet<>();
        for(WebElement e: listOfMatches){
            setOfStatuses.add(e.getText());
        }

        boolean matched = false;

        if(setOfStatuses.size() == 1){
            matched = setOfStatuses.contains(filterBy);
        }else if(setOfStatuses.size() == 0){
            //No matching items
            matched = true;
        }
        System.out.println("Number of items with status " + filterBy + " is : " + setOfStatuses.size());
        return matched;
    }

    public static boolean isInvoiceUnique(List<Invoice> loi) {
        Set<String> setOfEcid = new HashSet<>();
        for(Invoice e: loi){
            setOfEcid.add(e.Invoice_Id);
        }

        return setOfEcid.size() == loi.size();
    }
}
