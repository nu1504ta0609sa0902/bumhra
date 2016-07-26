package com.mhra.mcm.appian.domain.sub;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class ProductDesign {
    public double weightELiquid;
    public double volumeELiquid;

    public ProductDesign(int weight, int volume) {
        weightELiquid = weight;
        volumeELiquid = volume;
    }

    @Override
    public String toString() {
        return "ProductDesign:" +
                "\n weightELiquid=" + weightELiquid +
                "\n volumeELiquid=" + volumeELiquid +
                '\n';
    }
}
