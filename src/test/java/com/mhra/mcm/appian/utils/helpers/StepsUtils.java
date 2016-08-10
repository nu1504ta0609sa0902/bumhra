package com.mhra.mcm.appian.utils.helpers;

import com.mhra.mcm.appian.domain.Notification;
import com.mhra.mcm.appian.domain.sub.Invoice;

import java.util.List;

/**
 * Created by TPD_Auto on 28/07/2016.
 */
public class StepsUtils {

    /**
     * Find the invoice associated with notification
     *
     * @param listOfInvoices
     * @return
     */
    public static Invoice getInvoiceForNotification(List<Invoice> listOfInvoices, String ecIDNumber) {
        Invoice found = null;
        for(Invoice i: listOfInvoices){
            if(i.Description.contains(ecIDNumber)){
                found = i;
                break;
            }
        }

        return found;
    }
}