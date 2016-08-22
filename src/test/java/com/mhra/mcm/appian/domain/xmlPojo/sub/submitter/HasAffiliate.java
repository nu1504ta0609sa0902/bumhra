package com.mhra.mcm.appian.domain.xmlPojo.sub.submitter;

import com.mhra.mcm.appian.domain.sub.Address;
import com.mhra.mcm.appian.utils.helpers.RandomDataUtils;

import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TPD_Auto on 22/07/2016.
 */
public class HasAffiliate {

    @XmlAttribute
    private boolean confidential;
    private String name;
    private String address;
    private String country;
    private String phoneNumber;
    private String email;

    public HasAffiliate(){

    }

//    public boolean isConfidential() {
//        return confidential;
//    }
//
//    public void setConfidential(boolean confidential) {
//        this.confidential = confidential;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
