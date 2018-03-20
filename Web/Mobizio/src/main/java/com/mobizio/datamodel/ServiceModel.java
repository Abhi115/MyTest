package com.mobizio.datamodel;

import com.mobizio.selenium.framework.Utilities;

public class ServiceModel {

  private String serviceName = "AutomationHealthService." + Utilities.getRandomInteger(0, 10000);

  public String getServiceName() {
    return serviceName;
  }

  public void setServiceName(String serviceName) {
    this.serviceName = serviceName;
  }

}
