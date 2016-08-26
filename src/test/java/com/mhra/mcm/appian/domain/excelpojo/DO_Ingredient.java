package com.mhra.mcm.appian.domain.excelpojo;

/**
 * Created by TPD_Auto on 26/08/2016.
 */
public class DO_Ingredient {

    public String key;
    public String name;
    public String casNumberExists;
    public String casNumber;
    public String fINumber;
    public String femaNumber;
    public String ecNumber;
    public String recipeQuantity;
    public String toxicityStatus;
    public String reachRegistration;
    public String clpWhetherClassification;

    /**
     *
     * @param line
     */
    public DO_Ingredient(String line){
        System.out.println(line);
        if(line!=null && !line.trim().equals("")) {
            line = line.replace("FALSE", "false");
            line = line.replace("TRUE", "true");
            String[] data = line.split(",");
            key = data[0];
            name = data[1];
            casNumberExists = data[2];
            casNumber = data[3];
            fINumber = data[4];
            femaNumber = data[5];
            ecNumber = data[6];
            recipeQuantity = data[7];
            toxicityStatus = data[8];
            reachRegistration = data[9];
            clpWhetherClassification = data[10];
            System.out.println();

        }
    }



    @Override
    public boolean equals(Object obj) {
        DO_Ingredient o = (DO_Ingredient) obj;
        return this.key.equals(o.key);
    }
}
