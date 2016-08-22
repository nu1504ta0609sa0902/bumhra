package com.mhra.mcm.appian.utils.helpers;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class RandomDataUtils {

    /**
     *
     * @return
     */
    public static String getEUIdentifier(String euid) {
        if(euid==null || euid.trim().equals("")){
            return String.valueOf((int)getRandomDigits(5));
        }else{
            return euid;
        }
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

    public static String getDateInFutureMonthsUS(int monthsInFuture) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, monthsInFuture);
        int dom = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH)+1;
        int year = cal.get(Calendar.YEAR);
        return year + "-" + month + "-" + dom;
    }

    public static String getRandomTestName(String test) {
        Calendar cal = Calendar.getInstance();
        return test + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_" + (cal.get(Calendar.MONTH)+1) + "_" + (int)getRandomDigits(5);
    }

    public static String getRandomNumberBetween(int min, int max) {
        Random random = new Random( System.currentTimeMillis() );
        int val = random.nextInt(max - min + 1) + min;
        return String.valueOf(val);
    }

    public static String generateCASNumber() {
        String a = getRandomNumberBetween(100, 10000);
        String b = getRandomNumberBetween(100, 10000);
        String c = getRandomNumberBetween(100, 10000);
        return a + "-" + b + "-" + c;
    }
}
