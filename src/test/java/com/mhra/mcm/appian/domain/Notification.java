package com.mhra.mcm.appian.domain;

import com.mhra.mcm.appian.domain.sub.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by TPD_Auto on 22/07/2016.
 *
 * Capture all the mandatory fields
 *
 * NOTE THIS WILL USE RANDOM DATA
 *
 * - ONLY FOR TESTING PURPOSE
 */
@XmlRootElement(name="EcigProductSubmission")
public class Notification {

    public String ecIDNumber;
    Submitter submitter;
    Summary summary;
    SubmitterDetails submitterDetails;
    Product product;
    ProductIdentifiers productIdentifier;
    ProductDesign productDesign;
    Ingredient ingredient;
    BatteryWattageVoltage batteryWattageVoltage;
    ProductComponentDetails productComponentDetails;
    ProductEmission productEmission;

    public Notification(){

    }

    public Notification(int weight, int volume, String fixedEUID){
        String euIdentifier = RandomDataUtils.getEUIdentifier(fixedEUID);
        String ecId = RandomDataUtils.getECID(euIdentifier);
        ecIDNumber = ecId;
        summary = new Summary(ecId);
        submitter = new Submitter(euIdentifier, "TestE2E");
        submitterDetails = new SubmitterDetails();
        product = new Product("TestE2EBrand ");
        productIdentifier = new ProductIdentifiers();
        productDesign = new ProductDesign(weight, volume);
        batteryWattageVoltage = new BatteryWattageVoltage();
        productComponentDetails = new ProductComponentDetails();
        productEmission = new ProductEmission();
    }

    public Notification(int weight, int volume, String ingredientName, String fixedEUID){
        this(weight,volume,fixedEUID);
        ingredient = new Ingredient(ingredientName);
    }

    public Submitter getSubmitter() {
        return submitter;
    }

    public void setSubmitter(Submitter submitter) {
        this.submitter = submitter;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    public SubmitterDetails getSubmitterDetails() {
        return submitterDetails;
    }

    public void setSubmitterDetails(SubmitterDetails submitterDetails) {
        this.submitterDetails = submitterDetails;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductDesign getProductDesign() {
        return productDesign;
    }

    public Ingredient getIngredient() {
        if(ingredient == null)
            ingredient = new Ingredient();
        return ingredient;
    }

    public void setProductDesign(ProductDesign productDesign) {
        this.productDesign = productDesign;
    }

    //public String getEcIDNumber() {
    //    return ecIDNumber;
    //}

    public void setEcIDNumber(String ecIDNumber) {
        this.ecIDNumber = ecIDNumber;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public BatteryWattageVoltage getBatteryWattageVoltage() {
        return batteryWattageVoltage;
    }

    public void setBatteryWattageVoltage(BatteryWattageVoltage batteryWattageVoltage) {
        this.batteryWattageVoltage = batteryWattageVoltage;
    }

    public ProductIdentifiers getProductIdentifier() {
        return productIdentifier;
    }

    public void setProductIdentifier(ProductIdentifiers productIdentifier) {
        this.productIdentifier = productIdentifier;
    }

    public ProductComponentDetails getProductComponentDetails() {
        return productComponentDetails;
    }

    public void setProductComponentDetails(ProductComponentDetails productComponentDetails) {
        this.productComponentDetails = productComponentDetails;
    }

    public ProductEmission getProductEmission() {
        return productEmission;
    }

    public void setProductEmission(ProductEmission productEmission) {
        this.productEmission = productEmission;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "\n" + summary +
                "\n" + submitter +
                "\n" + submitterDetails +
                "\n" + product +
                "\n" + productDesign +
                "\n\n}\n";
    }


    public static void main(String args[]){
        Notification n = new Notification(10,10,null);
        System.out.println(n);

        try {
            JAXBContext context = JAXBContext.newInstance(Notification.class);

            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            m.marshal(n, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
