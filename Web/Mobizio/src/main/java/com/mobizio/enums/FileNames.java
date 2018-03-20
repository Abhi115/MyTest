package com.mobizio.enums;

public enum FileNames {

  TestDataRelativePath("src/test/resources/testdata/"),
  UserCarerProperties("UserCarer"),
  UserTenantAdminProperties("UserTenantAdmin"),
  CustomerProperties("Customer"),
  DeviceProperties("Device"),
  BranchProperties("Branch"),
  TaskProperties("Task"),
  FormProperties("Form"),
  RoleProperties("Role"),
  TaskTypeProperties("TaskType"),
  SectionProperties("Section"),
  ServiceProperties("Service"),
  GroupProperties("Group");

  private String value;

  FileNames(String value) {
    this.value = value;
  }

  public String toString() {
    return value;
  }
}
