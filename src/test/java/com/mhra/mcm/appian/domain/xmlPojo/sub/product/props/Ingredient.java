package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

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

        toxicologicalDetails = new ToxicologicalDetails("");
    }

    public Ingredient(String name, String casNumberGenerated) {
        confidential = true;
        ingredientName = new IngredientName(name);
        casNumberExists = new CasNumberExists("false");
        ingredientCasNumber = new IngredientCasNumber(casNumberGenerated);
        femaNumber = new FemaNumber("");
        flNumber = new FlNumber("");
        ecNumber = new EcNumber("");
        recipeQuantity = new RecipeQuantity("");

        functions.add(new Function(RandomDataUtils.getRandomNumberBetween(1, 20)));
        functions.add(new Function(RandomDataUtils.getRandomNumberBetween(1, 20)));

        toxicityStatus = new ToxicityStatus("");
        reachRegistration = new ReachRegistration("");
        clpWhetherClassification = new ClpWhetherClassification("");

        toxicologicalDetails = new ToxicologicalDetails("");
    }

    public void setToxicologyStatus(ToxicityStatus toxicityStatus) {
        this.toxicityStatus = toxicityStatus;
    }

    public void setToxicologyCardioPulmonary(ToxCardioPulmonary toxCardioPulmonary, String addReport) {
        this.toxicologicalDetails.setToxCardioPulmonary(toxCardioPulmonary);
        if(addReport!=null && addReport.equals("true"))
            this.toxicologicalDetails.setToxCardioPulmonaryFile();
    }

    public void setToxicologyCmr(ToxCmr toxCmr, String addReport) {
        this.toxicologicalDetails.setToxCmr(toxCmr);
        if(addReport!=null && addReport.equals("true"))
            this.toxicologicalDetails.setToxCmrFile();
    }

    public void setToxicologyOther(ToxOther toxOther, String addReport) {
        this.toxicologicalDetails.setToxOther(toxOther);
        if(addReport!=null && addReport.equals("true"))
            this.toxicologicalDetails.setToxOtherFile();
    }
}
