package com.mhra.mcm.appian.domain;

import com.mhra.mcm.appian.domain.sub.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

/**
 * Created by TPD_Auto on 22/07/2016.
 *
 * Capture all the mandatory fields
 *
 * NOTE THIS WILL USE RANDOM DATA
 *
 * - ONLY FOR TESTING PURPOSE
 */
public class Notification {

    public String ecIDNumber;
    Submitter submitter;
    Summary summary;
    SubmitterDetails submitterDetails;
    Product product;
    ProductDesign productDesign;
    Ingredient ingredient;

    public Notification(int weight, int volume){
        String euIdentifier = RandomDataUtils.getEUIdentifier();
        String ecId = RandomDataUtils.getECID(euIdentifier);
        ecIDNumber = ecId;
        summary = new Summary(ecId);
        submitter = new Submitter(euIdentifier, "TestE2E");
        submitterDetails = new SubmitterDetails();
        product = new Product("TestE2EBrand ");
        productDesign = new ProductDesign(weight, volume);
    }

    public Notification(int weight, int volume, String ingredientName){
        this(weight,volume);
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

    public static void main(String args[]){
        Notification n = new Notification(10,10);
        System.out.println(n);
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

}
