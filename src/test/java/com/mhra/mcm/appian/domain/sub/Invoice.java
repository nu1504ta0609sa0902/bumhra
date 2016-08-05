package com.mhra.mcm.appian.domain.sub;

/**
 * Created by TPD_Auto on 27/07/2016.
 */
public class Invoice {

    public String Invoice_Id;
    public String Order_Date;
    public String Customer_reference;
    public String Customer_Number;
    public String Customer_Name;
    public String Customer_Postal_Code;
    public String Fee_Type;
    public String Quantity;
    public String Unit_price;
    public String Net_Amount;
    public String VAT;
    public String Gross_Amount;
    public String Description;
    public String Cost_Centre;
    public String Natural_Account;
    public String Project_Code;
    public String Transaction_Type;
    public String More_information1;
    public String More_information2;
    public String Email_Address;

    public Invoice(String commaDelimited){
        String[] data = commaDelimited.split(",");
        Invoice_Id = data[0];
        Order_Date = data[1];
        Customer_reference = data[2];
        Customer_Number = data[3];
        Customer_Name = data[4];
        Customer_Postal_Code = data[5];
        Fee_Type = data[6];
        Quantity = data[7];
        Unit_price = data[8];
        Net_Amount = data[9];
        VAT = data[10];
        Gross_Amount = data[11];
        Description = data[12];
        Cost_Centre = data[13];
        Natural_Account = data[14];
        Project_Code = data[15];
        Transaction_Type = data[16];
        More_information1 = data[17];
        More_information2 = data[18];
        Email_Address = data[19];
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Invoice invoice = (Invoice) o;

        return Invoice_Id.equals(invoice.Invoice_Id);

    }

    @Override
    public int hashCode() {
        return Invoice_Id.hashCode();
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "Invoice_Id='" + Invoice_Id + '\'' +
                ", Order_Date='" + Order_Date + '\'' +
                ", Customer_reference='" + Customer_reference + '\'' +
                ", Customer_Number='" + Customer_Number + '\'' +
                ", Customer_Name='" + Customer_Name + '\'' +
                ", Customer_Postal_Code='" + Customer_Postal_Code + '\'' +
                ", Fee_Type='" + Fee_Type + '\'' +
                ", Quantity='" + Quantity + '\'' +
                ", Unit_price='" + Unit_price + '\'' +
                ", Net_Amount='" + Net_Amount + '\'' +
                ", VAT='" + VAT + '\'' +
                ", Gross_Amount='" + Gross_Amount + '\'' +
                ", Description='" + Description + '\'' +
                ", Cost_Centre='" + Cost_Centre + '\'' +
                ", Natural_Account='" + Natural_Account + '\'' +
                ", Project_Code='" + Project_Code + '\'' +
                ", Transaction_Type='" + Transaction_Type + '\'' +
                ", More_information1='" + More_information1 + '\'' +
                ", More_information2='" + More_information2 + '\'' +
                ", Email_Address='" + Email_Address + '\'' +
                '}';
    }
}
