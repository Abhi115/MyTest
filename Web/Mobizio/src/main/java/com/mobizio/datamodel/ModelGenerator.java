package com.mobizio.datamodel;

import static com.mobizio.enums.FileNames.CustomerProperties;

import com.mobizio.enums.FileNames;
import com.mobizio.selenium.framework.Configuration;
import com.mobizio.selenium.framework.Utilities;
import java.util.Properties;

public class ModelGenerator {

  public static BranchModel generateBranch() throws Exception {
    Configuration propertyReader = new Configuration(FileNames.BranchProperties.toString());
    BranchModel branchModel = new BranchModel();
    String randomString = Utilities.randomString(3).toLowerCase();
    Properties prop = propertyReader.readApplicationData();
    branchModel.setName(prop.getProperty("name") + randomString);
    branchModel.setTenantBranchID(prop.getProperty("tenantBranchID") + Utilities.getRandomInteger(0, 10000));
    branchModel.setPrimaryTelephone(prop.getProperty("primaryTelephone"));
    branchModel.setSecondaryTelephone(prop.getProperty("secondaryTelephone"));
    branchModel.setAddressLine1(prop.getProperty("addressLine1"));
    branchModel.setAddressLine2(prop.getProperty("addressLine2"));
    branchModel.setCity(prop.getProperty("city"));
    branchModel.setCounty(prop.getProperty("county"));
    branchModel.setCountry(prop.getProperty("country"));
    branchModel.setPostcode(prop.getProperty("postcode"));
    branchModel.setFax(prop.getProperty("fax"));
    branchModel.setAlertNotificationEmail(prop.getProperty("name") + randomString + prop.getProperty("alertNotificationEmail"));
    branchModel.setAlertNotificationPhoneNumber(prop.getProperty("alertNotificationPhoneNumber"));
    return branchModel;
  }

  public static FormModel generateForm() throws Exception {
    Configuration propertyReader = new Configuration(FileNames.FormProperties.toString());
    FormModel formModel = new FormModel();
    Properties prop = propertyReader.readApplicationData();
    formModel.setName(prop.getProperty("name"));
    formModel.setDescription(prop.getProperty("description"));
    formModel.setDefaultValue(prop.getProperty("defaultValue"));
    formModel.setHide(prop.getProperty("hide"));
    formModel.setCalculate(prop.getProperty("calculate"));
    formModel.setFormName(prop.getProperty("formName") + Utilities.randomString(4).toLowerCase());
    formModel.setFormHeader(prop.getProperty("formHeader"));
    formModel.setFormInstruction(prop.getProperty("formInstruction"));
    return formModel;
  }

  public static DeviceModel generateDevice() throws Exception {
    Configuration propertyReader = new Configuration(FileNames.DeviceProperties.toString());
    DeviceModel deviceModel = new DeviceModel();
    Properties prop = propertyReader.readApplicationData();
    deviceModel.setName(prop.getProperty("name"));
    deviceModel.setOperatingSystemAndroid(prop.getProperty("operatingSystemAndroid"));
    deviceModel.setUUID(prop.getProperty("uuid") + Utilities.getRandomInteger(0, 10000));
    deviceModel.setModel(prop.getProperty("model"));
    deviceModel.setPhoneNumber(prop.getProperty("phoneNumber"));
    deviceModel.setAssignee(null);
    deviceModel.setBranch(prop.getProperty("branch"));
    return deviceModel;
  }

  public static DeviceModel generateDetailsToEditDevice() throws Exception {
    DeviceModel deviceModel = new DeviceModel();
    Configuration propertyReader = new Configuration(FileNames.DeviceProperties.toString());
    Properties prop = propertyReader.readApplicationData();
    deviceModel.setName(prop.getProperty("name"));
    deviceModel.setOperatingSystemAndroid(prop.getProperty("operatingSystemAndroid"));
    deviceModel.setOperatingSystemiOS(prop.getProperty("operatingSystemIOS"));
    deviceModel.setUUID(prop.getProperty("uuid") + Utilities.getRandomInteger(0, 10000));
    deviceModel.setModel(prop.getProperty("model"));
    deviceModel.setPhoneNumber(prop.getProperty("phoneNumber"));
    deviceModel.setAssignee(prop.getProperty("assignee"));
    deviceModel.setNewAssignee(prop.getProperty("newAssignee"));
    deviceModel.setBranch(prop.getProperty("branch"));
    return deviceModel;
  }

  public static CustomerModel generateCustomer(boolean fullDetails) throws Exception {
    Configuration propertyReader = new Configuration(CustomerProperties.toString());
    CustomerModel customerModel = new CustomerModel();
    Properties prop = propertyReader.readApplicationData();
    customerModel.setFirstName(prop.getProperty("firstName"));
    customerModel.setUserName(customerModel.getFirstName() + Utilities.randomString(5));
    customerModel.setLastName(prop.getProperty("lastName"));
    customerModel.setEmail(customerModel.getFirstName() + customerModel.getLastName() + "@gmail.com");
    customerModel.setPassword(prop.getProperty("customerPassword"));
    customerModel.setConfirmPassword(prop.getProperty("confirmPassword"));
    customerModel.setCustomerId(prop.getProperty("customerId") + Utilities.getRandomInteger(100000, 999999));
    customerModel.setBranch(prop.getProperty("branchName"));
    customerModel.setPin(prop.getProperty("pin"));
    if (fullDetails) {
      customerModel.setTitle(prop.getProperty("title"));
      customerModel.setGender(prop.getProperty("gender"));
      customerModel.setAddressLine1(prop.getProperty("addressLine1"));
      customerModel.setAddressLine2(prop.getProperty("addressLine2"));
      customerModel.setPostCode(prop.getProperty("postcode"));
      customerModel.setCounty(prop.getProperty("county"));
      customerModel.setDob(prop.getProperty("dob"));
      customerModel.setCountry(prop.getProperty("country"));
      customerModel.setLatitude(prop.getProperty("latitude"));
      customerModel.setLongitude(prop.getProperty("longitude"));
      customerModel.setPrimaryTelephone(prop.getProperty("primaryTelephone"));
      customerModel.setSecondaryTelephone(prop.getProperty("secondaryTelephone"));
      customerModel.setLikesToBeCalled(prop.getProperty("likesToBeCalled"));
      customerModel.setNfcTagContent(prop.getProperty("nfcTagContent"));
      customerModel.setCity(prop.getProperty("city"));
    }
    return customerModel;
  }

  public static GroupModel generateGroupData() throws Exception {
    String randomString = Utilities.randomString(4).toLowerCase();
    Configuration propertyReader = new Configuration(FileNames.GroupProperties.toString());
    GroupModel groupModel = new GroupModel();
    Properties prop = propertyReader.readApplicationData();
    groupModel.setName(prop.getProperty("name") + randomString);
    groupModel.setUser1(prop.getProperty("user1"));
    groupModel.setUser2(prop.getProperty("user2"));
    groupModel.setUser3(prop.getProperty("user3"));
    groupModel.setGroup1(prop.getProperty("group1"));
    groupModel.setGroup2(prop.getProperty("group2"));
    return groupModel;
  }

  public static RoleModel generateNewRole() throws Exception {
    String randomString = Utilities.randomString(4).toLowerCase();
    RoleModel roleModel = new RoleModel();
    Configuration propertyReader = new Configuration(FileNames.RoleProperties.toString());
    Properties prop = propertyReader.readApplicationData();
    roleModel.setName(prop.getProperty("name") + randomString);
    roleModel.setUser(prop.getProperty("user"));
    roleModel.setDescription(prop.getProperty("description"));
    return roleModel;
  }

  public static TaskTypeModel generateNewTaskType() throws Exception {
    String randomString = Utilities.randomString(4).toLowerCase();
    Configuration propertyReader = new Configuration(FileNames.TaskTypeProperties.toString());
    Properties prop = propertyReader.readApplicationData();
    TaskTypeModel taskTypeModel = new TaskTypeModel();
    taskTypeModel.setName(prop.getProperty("name") + randomString);
    taskTypeModel.setFormName(prop.getProperty("formName"));
    taskTypeModel.setTaskTypes(prop.getProperty("taskTypePage"));
    taskTypeModel.setNewTaskTypes(prop.getProperty("newTaskTypePage"));
    return taskTypeModel;
  }

  public static UserModel generateNewUserDetails(boolean fullDetails) throws Exception {
    UserModel userModel = new UserModel();
    Configuration propertyReader = new Configuration(FileNames.UserCarerProperties.toString());
    Properties prop = propertyReader.readApplicationData();
    userModel.setFirstName(prop.getProperty("firstName"));
    userModel.setUserName(userModel.getFirstName() + Utilities.randomString(5));
    userModel.setLastName(prop.getProperty("lastName"));
    userModel.setEmail(userModel.getUserName() + "@mailinator.com");
    userModel.setPassword(prop.getProperty("password"));
    userModel.setConfirmPassword(prop.getProperty("confirmPassword"));
    userModel.setUserType(prop.getProperty("userType"));
    userModel.setTenantUserId(prop.getProperty("tenantUserId") + Utilities.getRandomInteger(0, 10000));
    userModel.setTenant(prop.getProperty("tenant"));
    userModel.setBranch(prop.getProperty("branchName"));
    userModel.setPin(prop.getProperty("pin"));
    if (fullDetails) {
      userModel.setGender(prop.getProperty("gender"));
      userModel.setTitle(prop.getProperty("title"));
      userModel.setDob(prop.getProperty("dob"));
      userModel.setAddressLine1(prop.getProperty("addressLine1"));
      userModel.setAddressLine2(prop.getProperty("addressLine2"));
      userModel.setCity(prop.getProperty("city"));
      userModel.setCounty(prop.getProperty("county"));
      userModel.setCountry(prop.getProperty("country"));
      userModel.setPostCode(prop.getProperty("postcode"));
      userModel.setLatitude(prop.getProperty("latitude"));
      userModel.setLongitude(prop.getProperty("longitude"));
      userModel.setPrimaryTelephone(prop.getProperty("primaryTelephone"));
      userModel.setSecondaryTelephone(prop.getProperty("secondaryTelephone"));
    }
    return userModel;

  }
}
