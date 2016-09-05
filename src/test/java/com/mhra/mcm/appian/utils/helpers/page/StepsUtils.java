package com.mhra.mcm.appian.utils.helpers.page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mhra.mcm.appian.domain.webPagePojo.sub.Invoice;
import cucumber.api.DataTable;
import gherkin.formatter.model.DataTableRow;

/**
 * Created by TPD_Auto on 28/07/2016.
 */
public class StepsUtils {

    /**
     * Find the invoice associated with notifications
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


    public static Map<String, String> convertDataTableToMap(DataTable dataTable) {
        Map<String, String> dataMap = new HashMap<>();
        List<List<String>> row = dataTable.raw();

        int rowNumber = 0;
        int columnNumber = 0;
        List<String> headings = null;
        for(List<String> x: row){

            if(rowNumber > 0){
                for(String data: x){
                    dataMap.put(headings.get(columnNumber), data);
                    columnNumber++;
                }
                //reset columnNumber
                columnNumber = 0;
            }else{
                headings = x;
                rowNumber++;
            }
        }
        return dataMap;
    }
}
