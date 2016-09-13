package com.mhra.mcm.appian.utils.helpers.others;

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
        String productId = getSimpleRandomNumberBetween(10000, 99999);
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
        return test + "_" + cal.get(Calendar.DAY_OF_MONTH) + "_" + (cal.get(Calendar.MONTH)+1) + "_" + getRandomNumberBetween(100, 1000000);
    }

    public static String getRandomNumberBetween(int min, int max) {
        Random random = new Random( System.currentTimeMillis() );
        int val = random.nextInt(max - min + 1) + min;
        val = new Random().nextInt(max) + min;
        return String.valueOf(val);
    }

    public static String getSimpleRandomNumberBetween(int min, int max) {
        int val = ( int )( Math.random() * max );
        if(val < min){
            val = val + min;
        }
        return String.valueOf(val);
    }

    public static String generateCASNumber() {
        String a = getRandomNumberBetween(1, 1000);
        String b = getRandomNumberBetween(1, 10000);
        String c = getRandomNumberBetween(1, 100000);
        return a + "-" + b + "-" + c;
    }

    public static boolean getRandomBooleanValue() {
        String val = getRandomNumberBetween(1, 100);
        int v = Integer.parseInt(val);
        int rem = v % 2;
        return rem == 0;
    }

    public static String getRandomFloatNumberBetween(int from, int to) {
        String a = getRandomNumberBetween(from, to);
        double x = (Integer.parseInt(a) * Math.PI)/ 3;
        double td = ((int)(x * 100))/100.0;
        return String.valueOf(td);
    }

//    public static void main(String[] args){
//        for(int x = 0; x < 99; x++) {
//            String v = getSimpleRandomNumberBetween(10000, 99999);
//            System.out.println(v);
//        }
//    }

}
