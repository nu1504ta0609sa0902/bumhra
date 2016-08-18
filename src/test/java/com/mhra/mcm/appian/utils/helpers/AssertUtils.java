package com.mhra.mcm.appian.utils.helpers;

/**
 * Created by TPD_Auto on 15/07/2016.
 */
public class AssertUtils {

    public static String getExpectedName(String name){
        String val = "";
        switch (name){
            case "rdt1": val = "rdt 1"; break;
            case "ipu1": val = "ipu 1"; break;
            case "vrmm1": val = "vrmm 1"; break;
            case "fin1": val = "finance 1"; break;
            case "comm1": val = "comms 1"; break;
            case "super1": val = "super 1"; break;
            case "super2": val = "super 2"; break;
        }
        return val;
    }
}
