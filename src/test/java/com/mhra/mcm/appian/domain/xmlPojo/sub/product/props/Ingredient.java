package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.excelpojo.DO_Ingredient;
import com.mhra.mcm.appian.domain.excelpojo.DO_ToxicologyDetails;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.*;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxCardioPulmonary;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxCmr;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxEmission;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.ingredient.toxicology.ToxOther;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Ingredient {

    //@XmlAttribute
    private boolean confidential;

    @XmlElement(name = "Name")
    private IngredientName ingredientName;
    @XmlElement(name = "CasNumberExists")
    private CasNumberExists casNumberExists;
    @XmlElement(name = "CasNumber")
    private IngredientCasNumber ingredientCasNumber;
    @XmlElement(name = "FlNumber")
    private FlNumber flNumber;
    @XmlElement(name = "FemaNumber")
    private FemaNumber femaNumber;
    @XmlElement(name = "EcNumber")
    private EcNumber ecNumber;
    @XmlElement(name = "RecipeQuantity")
    private RecipeQuantity recipeQuantity;

    @XmlElementWrapper(name = "Functions")
    @XmlElement(name = "Function")
    public List<Function> functions = new ArrayList<>();

    @XmlElement(name = "ToxicityStatus")
    private ToxicityStatus toxicityStatus;
    @XmlElement(name = "ReachRegistration")
    private ReachRegistration reachRegistration;
    @XmlElement(name = "ClpWhetherClassification")
    private ClpWhetherClassification clpWhetherClassification;

    @XmlElement(name = "ToxicologicalDetails")
    private ToxicologicalDetails toxicologicalDetails;


    public Ingredient(String name) {
        confidential = true;
        ingredientName = new IngredientName(name);
        casNumberExists = new CasNumberExists("");
        ingredientCasNumber = new IngredientCasNumber(RandomDataUtils.generateCASNumber());
        femaNumber = new FemaNumber("");
        flNumber = new FlNumber("");
        ecNumber = new EcNumber("");
        recipeQuantity = new RecipeQuantity("");

        functions.add(new Function(RandomDataUtils.getRandomNumberBetween(1, 20)));
        functions.add(new Function(RandomDataUtils.getRandomNumberBetween(1, 20)));

        toxicityStatus = new ToxicityStatus("");
        reachRegistration = new ReachRegistration("");
        clpWhetherClassification = new ClpWhetherClassification("");

        //toxicologicalDetails = new ToxicologicalDetails("");
    }

    public Ingredient(String name, String casNumberGenerated, DO_Ingredient doIngredient, DO_ToxicologyDetails doToxicologyDetails) {
        confidential = true;
        ingredientName = new IngredientName(doIngredient.name);
        casNumberExists = new CasNumberExists(doIngredient.casNumberExists);
        ingredientCasNumber = new IngredientCasNumber(casNumberGenerated);
        femaNumber = new FemaNumber(doIngredient.femaNumber);
        flNumber = new FlNumber(doIngredient.fINumber);
        ecNumber = new EcNumber(doIngredient.ecNumber);
        recipeQuantity = new RecipeQuantity(doIngredient.recipeQuantity);

        functions.add(new Function(RandomDataUtils.getRandomNumberBetween(1, 20)));
        functions.add(new Function(RandomDataUtils.getRandomNumberBetween(1, 20)));

        toxicityStatus = new ToxicityStatus(doIngredient.toxicityStatus);
        reachRegistration = new ReachRegistration(doIngredient.reachRegistration);
        clpWhetherClassification = new ClpWhetherClassification(doIngredient.clpWhetherClassification);

        toxicologicalDetails = new ToxicologicalDetails(doToxicologyDetails);
    }


    public void setToxicologyStatus(ToxicityStatus toxicityStatus) {
        this.toxicityStatus = toxicityStatus;
    }

    public void setToxicologyCardioPulmonary(ToxCardioPulmonary toxCardioPulmonary, String addReport) {
        this.toxicologicalDetails.setToxCardioPulmonary(toxCardioPulmonary);
        if(addReport!=null && addReport.toLowerCase().equals("true"))
            this.toxicologicalDetails.setToxCardioPulmonaryFile();
    }

    public void setToxicologyCmr(ToxCmr toxCmr, String addReport) {
        this.toxicologicalDetails.setToxCmr(toxCmr);
        if(addReport!=null && addReport.toLowerCase().equals("true"))
            this.toxicologicalDetails.setToxCmrFile();
    }

    public void setToxicologyOther(ToxOther toxOther, String addReport) {
        this.toxicologicalDetails.setToxOther(toxOther);
        if(addReport!=null && addReport.toLowerCase().equals("true"))
            this.toxicologicalDetails.setToxOtherFile();
    }
}
