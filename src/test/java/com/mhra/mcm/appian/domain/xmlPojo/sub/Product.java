package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Product {

    public ProductID productID;
    public OtherProductsExist otherProductsExist;
    public SameCompositionProductsExist sameCompositionProductsExist;
    public ProductType productType;
    public Weight weight;
    public Volume volume;
    public ClpClassification clpClassification;
    public Design design;

    public List<Presentation> presentations = new ArrayList<>();
    public List<Presentation> ingredients = new ArrayList<>();
    public List<Presentation> emissions = new ArrayList<>();

    public Product(double weight, double volume, String ecId) {
        productID = new ProductID(ecId);
        otherProductsExist = new OtherProductsExist();
    }
}