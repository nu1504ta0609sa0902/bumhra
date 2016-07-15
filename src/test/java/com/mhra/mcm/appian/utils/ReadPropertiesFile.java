package com.mhra.mcm.appian.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by TPD_Auto on 14/07/2016.
 */
public class ReadPropertiesFile {

    private final static String resourceFolder = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private final static String userFileLocation = "configs" + File.separator +  "users.properties";

    public static Properties loadPropertiesFile(String fileName){
        Properties prop = null;
        try {
            String root = new File("").getAbsolutePath();
            String location = root + File.separator + resourceFolder + userFileLocation;
            prop = new Properties();
            InputStream in = new FileInputStream(new File(location));
            prop.load(in);
            in.close();
        }catch(Exception e){
            prop = null;
            e.printStackTrace();
        }
        return prop;
    }

    private String getDataFileFullPath(String fileName) {
        File file = new File("");
        String rootFolder = file.getAbsolutePath();
        String data = (rootFolder + File.separator + resourceFolder + File.separator + fileName);
        return data;
    }


}
