package com.mhra.mcm.appian.utils.helpers;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class RandomDataUtils {

    /**
     *
     * @return
     */
    public static String getEUIdentifier() {
        return String.valueOf((int)getRandomDigits(5));
    }

    public static double getRandomDigits(int numberOfDigits){
        Random r = new Random( System.currentTimeMillis() );
        int thousands = (int) Math.pow(10,numberOfDigits-1);
        return thousands + r.nextInt(9 * thousands);
    }

    public static String getECID(String euIdentifier) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        year = year % 2000;
        String productId = getRandomNumberBetween(30000, 90000);
        return euIdentifier + "-" + year + "-" + productId;
    }

    public static String getDateInFutureMonths(int monthsInFuture) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, monthsInFuture);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        return dom + "/" + month + "/" + year;
    }

    public static String getRandomTestName(String test) {
        Calendar cal = Calendar.getInstance();
        return test + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_" + (cal.get(Calendar.MONTH)+1);
    }

    public static String getRandomNumberBetween(int min, int max) {
        Random random = new Random( System.currentTimeMillis() );
        int val = random.nextInt(max - min + 1) + min;
        return String.valueOf(val);
    }
}
