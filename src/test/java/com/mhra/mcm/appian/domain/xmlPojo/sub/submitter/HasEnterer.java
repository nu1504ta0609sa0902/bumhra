package com.mhra.mcm.appian.domain.xmlPojo.sub.submitter;

import com.mhra.mcm.appian.domain.sub.Address;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class HasEnterer {


    @XmlAttribute
    private boolean confidential;
    @XmlElement(name = "Name")
    private String name;
    @XmlElement(name = "Address")
    private String address;
    @XmlElement(name = "Country")
    private String country;
    @XmlElement(name = "PhoneNumber")
    private String phoneNumber;
    @XmlElement(name = "Email")
    private String email;

    public HasEnterer(){

        this.name = "Test MHRA ENTERER";
        this.address = "151 Buckingham Palace Parent Road, London";
        this.country = "GB";
        this.phoneNumber = "+44 20 8524 04 98";
        this.email = "mhra.uat@gmail.com";
    }

//    public boolean isConfidential() {
//        return confidential;
//    }
//
//    public void setConfidential(boolean confidential) {
//        this.confidential = confidential;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
