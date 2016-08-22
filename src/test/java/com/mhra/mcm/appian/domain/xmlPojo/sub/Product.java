package com.mhra.mcm.appian.domain.xmlPojo.sub;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.props.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Product {

    @XmlElement(name = "ProductID")
    public ProductID productID;
    @XmlElement(name = "OtherProductsExist")
    public OtherProductsExist otherProductsExist;
    @XmlElement(name = "SameCompositionProductsExist")
    public SameCompositionProductsExist sameCompositionProductsExist;
    @XmlElement(name = "ProductType")
    public ProductType productType;
    @XmlElement(name = "Weight")
    public Weight weight;
    @XmlElement(name = "Volume")
    public Volume volume;
    @XmlElement(name = "ClpClassification")
    public ClpClassification clpClassification;
    @XmlElement(name = "Design")
    public Design design;

    @XmlElementWrapper(name = "Presentations")
    @XmlElement(name = "Presentation")
    public List<Presentation> presentations = new ArrayList<>();

    @XmlElementWrapper(name = "Ingredients")
    @XmlElement(name = "Ingredient")
    public List<Ingredient> ingredients = new ArrayList<>();

    @XmlElementWrapper(name = "Emissions")
    @XmlElement(name = "Emission")
    public List<Emission> emissions = new ArrayList<>();

    public Product(double w, double v, String ecId) {
        productID = new ProductID(ecId);
        otherProductsExist = new OtherProductsExist(false);
        sameCompositionProductsExist = new SameCompositionProductsExist(false);
        productType = new ProductType("5");
        weight = new Weight(w);
        volume = new Volume(v);
        clpClassification = new ClpClassification("None");
        design = new Design();

        presentations.add(new Presentation(RandomDataUtils.getRandomTestName("BrandName")));
        presentations.add(new Presentation(RandomDataUtils.getRandomTestName("BrandName")));
        ingredients.add(new Ingredient(RandomDataUtils.getRandomTestName("SupplementXML")));
        ingredients.add(new Ingredient(RandomDataUtils.getRandomTestName("SupplementXML2")));
        emissions.add(new Emission());
        emissions.add(new Emission());
    }

//
//    public ProductID getProductID() {
//        return productID;
//    }
//
//    public void setProductID(ProductID productID) {
//        this.productID = productID;
//    }
//
//    public OtherProductsExist getOtherProductsExist() {
//        return otherProductsExist;
//    }
//
//    public void setOtherProductsExist(OtherProductsExist otherProductsExist) {
//        this.otherProductsExist = otherProductsExist;
//    }
//
//    public SameCompositionProductsExist getSameCompositionProductsExist() {
//        return sameCompositionProductsExist;
//    }
//
//    public void setSameCompositionProductsExist(SameCompositionProductsExist sameCompositionProductsExist) {
//        this.sameCompositionProductsExist = sameCompositionProductsExist;
//    }
//
//    public ProductType getProductType() {
//        return productType;
//    }
//
//    public void setProductType(ProductType productType) {
//        this.productType = productType;
//    }
//
//    public Weight getWeight() {
//        return weight;
//    }
//
//    public void setWeight(Weight weight) {
//        this.weight = weight;
//    }
//
//    public Volume getVolume() {
//        return volume;
//    }
//
//    public void setVolume(Volume volume) {
//        this.volume = volume;
//    }
//
//    public ClpClassification getClpClassification() {
//        return clpClassification;
//    }
//
//    public void setClpClassification(ClpClassification clpClassification) {
//        this.clpClassification = clpClassification;
//    }
//
//    public Design getDesign() {
//        return design;
//    }
//
//    public void setDesign(Design design) {
//        this.design = design;
//    }
//
//    public List<Presentation> getPresentations() {
//        return presentations;
//    }
//
//    public void setPresentations(List<Presentation> presentations) {
//        this.presentations = presentations;
//    }
//
//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public void setIngredients(List<Ingredient> ingredients) {
//        this.ingredients = ingredients;
//    }
//
//    public List<Emission> getEmissions() {
//        return emissions;
//    }
//
//    public void setEmissions(List<Emission> emissions) {
//        this.emissions = emissions;
//    }
}