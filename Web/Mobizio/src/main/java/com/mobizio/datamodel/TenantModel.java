package com.mobizio.datamodel;

import com.mobizio.selenium.framework.Utilities;

public class TenantModel {

  private String tenantName = "AutomationHealthTenant" + Utilities.getRandomInteger(0, 10000);
  private String primaryTelephone = "989493" + Utilities.getRandomInteger(0, 10000);
  private String address1 = "Automation Address 1";
  private String address2 = "Automation Address 2";
  private String city = "Glasgow";
  private String county = "County";
  private String country = "United Kingdom";
  private String postCode = "DE22 3BG";
  private String fax = "91889125";
  private String website = "www.mobiziotesting.com";

  public String getTenantName() {
    return tenantName;
  }

  public void setTenantName(String tenantName) {
    this.tenantName = tenantName;
  }

  public String getPrimaryTelephone() {
    return primaryTelephone;
  }

  public void setPrimaryTelephone(String primaryTelephone) {
    this.primaryTelephone = primaryTelephone;
  }

  public String getAddress1() {
    return address1;
  }

  public void setAddress1(String address1) {
    this.address1 = address1;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getCounty() {
    return county;
  }

  public void setCounty(String county) {
    this.county = county;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getFax() {
    return fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

}
