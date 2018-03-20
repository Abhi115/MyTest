package com.mobizio.enums;

public class ValidationMessage {

  public enum Validation {
    LoginFailedMessage("Login Failed! Invalid username or password!"),
    NoMatchFound("No matches found"),
    PleaseFillThisField("Please fill out this field."),
    InvalidEmail("Not a valid email address"),
    NoDataAvailable("No data available in table"),
    UniqueUserNameValidation("Username must be unique");
    private String value;

    Validation(String value) {
      this.value = value;
    }

    public String toString() {
      return value;
    }
  }
}
