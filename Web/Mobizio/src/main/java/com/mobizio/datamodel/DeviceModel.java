package com.mobizio.datamodel;

public class DeviceModel {

  private String uuid;
  private String name;
  private String operatingSystemAndroid;
  private String operatingSystemiOS;
  private String operatingSystemOther;
  private String model;
  private String phoneNumber;
  private String assignee;
  private String branch;
  private String newAssignee;

  public String getUUID() {
    return uuid;
  }

  public void setUUID(String uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;

  }

  public String getOperatingSystemAndroid() {
    return operatingSystemAndroid;
  }

  public void setOperatingSystemAndroid(String operatingSystemAndroid) {
    this.operatingSystemAndroid = operatingSystemAndroid;
  }

  public String getOperatingSystemiOS() {
    return operatingSystemiOS;
  }

  public void setOperatingSystemiOS(String operatingSystemiOS) {
    this.operatingSystemiOS = operatingSystemiOS;
  }

  public String getOperatingSystemOther() {
    return operatingSystemOther;
  }

  public void setOperatingSystemOther(String operatingSystemOther) {
    this.operatingSystemOther = operatingSystemOther;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getAssignee() {
    return assignee;
  }

  public void setAssignee(String assignee) {
    this.assignee = assignee;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getNewAssignee() {
    return newAssignee;
  }

  public void setNewAssignee(String newAssignee) {
    this.newAssignee = newAssignee;
  }

}
