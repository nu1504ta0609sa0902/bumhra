package com.mhra.mcm.appian.domain.xmlPojo.sub.product;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Presentation {
    public double weightELiquid;
    public double volumeELiquid;

    public Presentation(int weight, int volume) {
        weightELiquid = weight;
        volumeELiquid = volume;
    }

    @Override
    public String toString() {
        return "#ProductDesign:" +
                "\nproduct.design.weightELiquid=" + weightELiquid +
                "\nproduct.design.volumeELiquid=" + volumeELiquid +
                '\n';
    }
}
