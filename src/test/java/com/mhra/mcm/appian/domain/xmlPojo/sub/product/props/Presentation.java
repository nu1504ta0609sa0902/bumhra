package com.mhra.mcm.appian.domain.xmlPojo.sub.product.props;

import com.mhra.mcm.appian.domain.xmlPojo.sub.product.presentation.*;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

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
        launchDate = new LaunchDate(RandomDataUtils.getDateInFutureMonthsUS(6));
        withdrawalIndication = new WithdrawalIndication("false");
        productNumberType = new ProductNumberType("SUBMITTER");
        productNumber = new ProductNumber(""+RandomDataUtils.getRandomDigits(6));
        packageUnits = new PackageUnits(RandomDataUtils.getRandomNumberBetween(1, 20));
        unitPacketPictureFile = new UnitPacketPictureFile("");
    }
}
