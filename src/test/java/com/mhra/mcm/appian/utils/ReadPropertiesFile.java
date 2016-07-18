package com.mhra.mcm.appian.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by TPD_Auto on 14/07/2016.
 */
public class ReadPropertiesFile {

    private final static String resourceFolder = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private final static String userFileLocation = "configs" + File.separator +  "users.properties";
    private final static Map<String, Properties> mapOfProperties = new HashMap<String, Properties>();

    /**
     * Load properties files from system
     * @param fileName
     * @return
     */
    public static Properties loadPropertiesFile(String fileName){

        Properties prop = mapOfProperties.get(fileName);

        if(prop == null){
            try {
                String root = new File("").getAbsolutePath();
                String location = root + File.separator + resourceFolder + userFileLocation;
                prop = new Properties();
                InputStream in = new FileInputStream(new File(location));
                prop.load(in);
                in.close();

                //update map
                mapOfProperties.put(fileName, prop);
            }catch(Exception e){
                prop = null;
                e.printStackTrace();
            }
        }

        return prop;
    }

}