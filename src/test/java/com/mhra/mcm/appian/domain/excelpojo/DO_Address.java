package com.mhra.mcm.appian.domain.excelpojo;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Address {
    public String key;
    public String name;
    public String address;
    public String country;
    public String phoneNumber;
    public String email;

    /**
     *
     * @param line
     */
    public DO_Address(String line){
        //System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");

            key = data[0];
            name = data[1];
            address = data[2];
            //Make sure commas are replace with ++ in the excel sheet
            address = replace("++", ",", address);
            country = data[3];
            phoneNumber = data[4];
            email = data[5];
        }
    }


    private String replace(String text, String with, String line) {
        line = line.replace(text, with);
        return line;
    }

    @Override
    public boolean equals(Object obj) {
        DO_Address o = (DO_Address) obj;
        return this.key.equals(o.key);
    }
}
