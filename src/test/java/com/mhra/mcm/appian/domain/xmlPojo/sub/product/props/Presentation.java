package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.excelpojo.DO_Presentation;
import com.mhra.mcm.appian.domain.xmlPojo.sub.product.presentation.*;
import com.mhra.mcm.appian.utils.helpers.others.RandomDataUtils;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class Presentation {

    @XmlElement(name = "NationalMarket")
    private NationalMarket nationalMarket;
    @XmlElement(name = "BrandName")
    private BrandName brandName;
    @XmlElement(name = "BrandSubtypeNameExists")
    private BrandSubtypeNameExists brandSubtypeNameExists;
    @XmlElement(name = "BrandSubtypeName")
    private BrandSubtypeName brandSubtypeName;
    @XmlElement(name = "LaunchDate")
    private LaunchDate launchDate;
    @XmlElement(name = "WithdrawalIndication")
    private WithdrawalIndication withdrawalIndication;
    @XmlElement(name = "ProductNumberType")
    private ProductNumberType productNumberType;
    @XmlElement(name = "ProductNumber")
    private ProductNumber productNumber;
    @XmlElement(name = "PackageUnits")
    private PackageUnits packageUnits;
    @XmlElement(name = "UnitPacketPictureFile")
    private UnitPacketPictureFile unitPacketPictureFile;

    public Presentation(String name){
        nationalMarket = new NationalMarket("GB");
        brandName = new BrandName(name);
        brandSubtypeNameExists = new BrandSubtypeNameExists("true");
        brandSubtypeName = new BrandSubtypeName("ePen");
        launchDate = new LaunchDate(RandomDataUtils.getDateInFutureMonthsUS(6, true));
        withdrawalIndication = new WithdrawalIndication("false");
        productNumberType = new ProductNumberType("SUBMITTER");
        productNumber = new ProductNumber(""+RandomDataUtils.getRandomDigits(6));
        packageUnits = new PackageUnits(RandomDataUtils.getRandomNumberBetween(1, 20));
        unitPacketPictureFile = new UnitPacketPictureFile("");
    }

    public Presentation(String name, String productType){
        nationalMarket = new NationalMarket("GB");
        brandName = new BrandName(name);
        brandSubtypeNameExists = new BrandSubtypeNameExists("true");
        brandSubtypeName = new BrandSubtypeName("ePen");
        launchDate = new LaunchDate(RandomDataUtils.getDateInFutureMonthsUS(6, true));
        withdrawalIndication = new WithdrawalIndication("false");
        productNumberType = new ProductNumberType("SUBMITTER");
        productNumber = new ProductNumber(""+RandomDataUtils.getRandomDigits(6));
        packageUnits = new PackageUnits(RandomDataUtils.getRandomNumberBetween(1, 20));
        unitPacketPictureFile = new UnitPacketPictureFile("");
    }

    public Presentation(DO_Presentation doPresentation) {
        String name = doPresentation.brandName;
        if(name.equals("default") || name.equals("random")){
            name = RandomDataUtils.getRandomTestName("BrandName");
        }

        String ldate = doPresentation.launchDate;
        if(ldate.equals("default") || ldate.equals("random")){
            ldate = RandomDataUtils.getDateInFutureMonthsUS(6, true);
        }

        String pNumber = doPresentation.productNumber;
        if(pNumber.equals("default") || pNumber.equals("random")){
            pNumber = ""+(int)RandomDataUtils.getRandomDigits(6);
        }

        nationalMarket = new NationalMarket(doPresentation.nationalMarket);
        brandName = new BrandName(name);
        brandSubtypeNameExists = new BrandSubtypeNameExists(doPresentation.brandSubtypeNameExists);
        brandSubtypeName = new BrandSubtypeName(doPresentation.brandSubtypeName);
        launchDate = new LaunchDate(ldate);
        withdrawalIndication = new WithdrawalIndication(doPresentation.withdrawalIndication);
        productNumberType = new ProductNumberType(doPresentation.productNumberType);
        productNumber = new ProductNumber(pNumber);
        packageUnits = new PackageUnits(doPresentation.packageUnits);
        unitPacketPictureFile = new UnitPacketPictureFile(doPresentation.unitPacketPictureFile);
    }
}
