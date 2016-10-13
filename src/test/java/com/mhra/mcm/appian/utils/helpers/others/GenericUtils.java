package com.mhra.mcm.appian.utils.helpers.others;

import com.mhra.mcm.appian.domain.webPagePojo.sub.Invoice;
import org.openqa.selenium.WebElement;

import java.util.*;

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

    /**
     * Invoice ID should be unique
     * @param loi
     * @return
     */
    public static boolean isInvoiceUnique(List<Invoice> loi) {
        Set<String> setOfEcid = new HashSet<>();
        for(Invoice e: loi){
            setOfEcid.add(e.Invoice_Id);
            System.out.println(e.Invoice_Id);
        }

        return setOfEcid.size() == loi.size();
    }

    /**
     * Annual price should be £60 at the time of writing 06/09/2016
     * @param price
     * @param loi
     * @return
     */
    public static boolean isUnitPriceMatch(String price, List<Invoice> loi) {
        boolean allMatched = true;
        for(Invoice i: loi){
            String unit_price = i.Unit_price;
            if(!unit_price.equals(price)){
                allMatched = false;
                break;
            }
        }
        return allMatched;
    }

    public static Invoice getRandomInvoice(List<Invoice> loi) {
        String index = RandomDataUtils.getSimpleRandomNumberBetween(0, loi.size() - 1);
        Invoice i = loi.get(Integer.parseInt(index));
        return i;
    }

    public static Invoice getInvoiceForECID(List<Invoice> loi, String ecid) {
        Invoice invoice = null;
        for(Invoice i: loi){
            String description = i.Description;
            if(description.equals(ecid)){
                invoice = i;
                break;
            }
        }

        return invoice;
    }

    public static String generateECIDBySpecifiedNumber(int i, String ecid) {
        String[] data = ecid.split("-");
        String productId = data[2];
        String newECID = data[0] + "-" + data[1] + "-"+ ( Integer.parseInt(productId) + 1 );
        return newECID;
    }

    public static boolean isRecentData(double timeLimit, String hour, String min) {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
        instance.set(Calendar.MINUTE, Integer.parseInt(min));
        System.out.println(instance.getTime());

        long receivedTime = instance.getTimeInMillis();
        long currentTime = new Date().getTime();
        double time = timeLimit * 60 * 1000;

        if (receivedTime > (currentTime - time))
            return true;
        else
            return false;

    }
}
