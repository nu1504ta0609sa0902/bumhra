package com.mhra.mcm.appian.utils.helpers;

import com.mhra.mcm.appian.domain.Notification;

import java.util.Map;

/**
 * Created by TPD_Auto on 02/08/2016.
 */
public class NotificationUtils {

    /**
     * Change notification data with the values passed into the system
     * @param dataValues
     * @return
     */
    public static Notification updateDefaultNotification(Map<String, String> dataValues) {
        Notification notification = new Notification(2, 2);
        String type = dataValues.get("type");
        String tcaNumber = dataValues.get("tcaNumber");
        String submitterName = dataValues.get("submitterName");
        String ingredient = dataValues.get("ingredient");

        if(type!=null){
            notification.getSummary().submissionType = type;
        }
        if(tcaNumber!=null){
            notification.getSubmitter().tcaNumber = tcaNumber;
        }
        if(submitterName!=null){
            if(submitterName.equals("random")){
                String sn = notification.getSubmitter().name;
                sn = sn + RandomDataUtils.getRandomNumberBetween(1000,10000);
                notification.getSubmitter().name = sn;
            }
        }
        if(ingredient!=null){
            notification.getIngredient().ingredientName = ingredient;
        }

        return notification;
    }
}
