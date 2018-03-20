package com.mobizio.datamodel;

public class CustomerModel {

  private String jiraID;
  private String description;
  private String userName;
  private String email;
  private String password;
  private String confirmPassword;
  private String customerId;
  private String title;
  private String firstName;
  private String lastName;
  private String dob;
  private String gender;
  private String addressLine1;
  private String addressLine2;
  private String city;
  private String county;
  private String postCode;
  private String country;
  private String latitude;
  private String longitude;
  private String primaryTelephone;
  private String secondaryTelephone;
  private String likesToBeCalled;
  private String branch;
  private String role;
  private String tenantCustomerId;
  private String nfcTagContent;
  private String pin;
  private String userType;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getJiraID() {
    return jiraID;
  }

  public void setJiraID(String jiraID) {
    this.jiraID = jiraID;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getConfirmPassword() {
    return confirmPassword;
  }

  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDob() {
    return dob;
  }

  public void setDob(String dob) {
    this.dob = dob;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getAddressLine1() {
    return addressLine1;
  }

  public void setAddressLine1(String addressLine1) {
    this.addressLine1 = addressLine1;
  }

  public String getAddressLine2() {
    return addressLine2;
  }

  public void setAddressLine2(String addressLine2) {
    this.addressLine2 = addressLine2;
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

  public String getPostCode() {
    return postCode;
  }

  public void setPostCode(String postCode) {
    this.postCode = postCode;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getPrimaryTelephone() {
    return primaryTelephone;
  }

  public void setPrimaryTelephone(String primaryTelephone) {
    this.primaryTelephone = primaryTelephone;
  }

  public String getSecondaryTelephone() {
    return secondaryTelephone;
  }

  public void setSecondaryTelephone(String secondaryTelephone) {
    this.secondaryTelephone = secondaryTelephone;
  }

  public String getLikesToBeCalled() {
    return likesToBeCalled;
  }

  public void setLikesToBeCalled(String likesToBeCalled) {
    this.likesToBeCalled = likesToBeCalled;
  }

  public String getBranch() {
    return branch;
  }

  public void setBranch(String branch) {
    this.branch = branch;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getTenantCustomerId() {
    return tenantCustomerId;
  }

  public void setTenantCustomerId(String tenantCustomerId) {
    this.tenantCustomerId = tenantCustomerId;
  }

  public String getNfcTagContent() {
    return nfcTagContent;
  }

  public void setNfcTagContent(String nfcTagContent) {
    this.nfcTagContent = nfcTagContent;
  }

  public void setPin(String pin){this.pin=pin;}

  public String getPin(){return pin;}

  public void setUserType(String userType){this.userType=userType;}

  public String getUserType() { return userType;}

  @Override
  public String toString() {
    return "CustomerModel [userName=" + userName + ", email=" + email + ", password=" + password
        + ", confirmPassword=" + confirmPassword + ", title=" + title + ", firstName=" + firstName
        + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender + ", addressLine1="
        + addressLine1
        + ", addressLine2=" + addressLine2 + ", city=" + city + ", county=" + county + ", postCode="
        + postCode
        + ", country=" + country + ", latitude=" + latitude + ", longitude=" + longitude
        + ", primaryTelephone="
        + primaryTelephone + ", secondaryTelephone=" + secondaryTelephone + ", likesToBeCalled="
        + likesToBeCalled + ", branch=" + branch + ", role=" + role + ", tenantCustomerId="
        + tenantCustomerId
        + ", nfcTagContent=" + nfcTagContent + ", getUserName()=" + getUserName() + ", getEmail()="
        + getEmail()
        + ", getPassword()=" + getPassword() + ", getConfirmPassword()=" + getConfirmPassword()
        + ", getTitle()=" + getTitle() + ", getFirstName()=" + getFirstName() + ", getLastName()="
        + getLastName() + ", getDob()=" + getDob() + ", getGender()=" + getGender()
        + ", getAddressLine1()="
        + getAddressLine1() + ", getAddressLine2()=" + getAddressLine2() + ", getCity()="
        + getCity()
        + ", getCounty()=" + getCounty() + ", getPostCode()=" + getPostCode() + ", getCountry()="
        + getCountry()
        + ", getLatitude()=" + getLatitude() + ", getLongitude()=" + getLongitude()
        + ", getPrimaryTelephone()="
        + getPrimaryTelephone() + ", getSecondaryTelephone()=" + getSecondaryTelephone()
        + ", getLikesToBeCalled()=" + getLikesToBeCalled() + ", getBranch()=" + getBranch()
        + ", getRole()="
        + getRole() + ", getTenantCustomerId()=" + getTenantCustomerId() + ", getNfcTagContent()="
        + getNfcTagContent() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
        + ", toString()="
        + super.toString() + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((addressLine1 == null) ? 0 : addressLine1.hashCode());
    result = prime * result + ((addressLine2 == null) ? 0 : addressLine2.hashCode());
    result = prime * result + ((branch == null) ? 0 : branch.hashCode());
    result = prime * result + ((city == null) ? 0 : city.hashCode());
    result = prime * result + ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
    result = prime * result + ((country == null) ? 0 : country.hashCode());
    result = prime * result + ((county == null) ? 0 : county.hashCode());
    result = prime * result + ((dob == null) ? 0 : dob.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
    result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
    result = prime * result + ((likesToBeCalled == null) ? 0 : likesToBeCalled.hashCode());
    result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
    result = prime * result + ((nfcTagContent == null) ? 0 : nfcTagContent.hashCode());
    result = prime * result + ((password == null) ? 0 : password.hashCode());
    result = prime * result + ((postCode == null) ? 0 : postCode.hashCode());
    result = prime * result + ((primaryTelephone == null) ? 0 : primaryTelephone.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    result = prime * result + ((secondaryTelephone == null) ? 0 : secondaryTelephone.hashCode());
    result = prime * result + ((tenantCustomerId == null) ? 0 : tenantCustomerId.hashCode());
    result = prime * result + ((title == null) ? 0 : title.hashCode());
    result = prime * result + ((userName == null) ? 0 : userName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    CustomerModel other = (CustomerModel) obj;
    if (addressLine1 == null) {
      if (other.addressLine1 != null) {
        return false;
      }
    } else if (!addressLine1.equals(other.addressLine1)) {
      return false;
    }
    if (addressLine2 == null) {
      if (other.addressLine2 != null) {
        return false;
      }
    } else if (!addressLine2.equals(other.addressLine2)) {
      return false;
    }
    if (branch == null) {
      if (other.branch != null) {
        return false;
      }
    } else if (!branch.equals(other.branch)) {
      return false;
    }
    if (city == null) {
      if (other.city != null) {
        return false;
      }
    } else if (!city.equals(other.city)) {
      return false;
    }
    if (confirmPassword == null) {
      if (other.confirmPassword != null) {
        return false;
      }
    } else if (!confirmPassword.equals(other.confirmPassword)) {
      return false;
    }
    if (country == null) {
      if (other.country != null) {
        return false;
      }
    } else if (!country.equals(other.country)) {
      return false;
    }
    if (county == null) {
      if (other.county != null) {
        return false;
      }
    } else if (!county.equals(other.county)) {
      return false;
    }
    if (dob == null) {
      if (other.dob != null) {
        return false;
      }
    } else if (!dob.equals(other.dob)) {
      return false;
    }
    if (email == null) {
      if (other.email != null) {
        return false;
      }
    } else if (!email.equals(other.email)) {
      return false;
    }
    if (firstName == null) {
      if (other.firstName != null) {
        return false;
      }
    } else if (!firstName.equals(other.firstName)) {
      return false;
    }
    if (gender == null) {
      if (other.gender != null) {
        return false;
      }
    } else if (!gender.equals(other.gender)) {
      return false;
    }
    if (lastName == null) {
      if (other.lastName != null) {
        return false;
      }
    } else if (!lastName.equals(other.lastName)) {
      return false;
    }
    if (latitude == null) {
      if (other.latitude != null) {
        return false;
      }
    } else if (!latitude.equals(other.latitude)) {
      return false;
    }
    if (likesToBeCalled == null) {
      if (other.likesToBeCalled != null) {
        return false;
      }
    } else if (!likesToBeCalled.equals(other.likesToBeCalled)) {
      return false;
    }
    if (longitude == null) {
      if (other.longitude != null) {
        return false;
      }
    } else if (!longitude.equals(other.longitude)) {
      return false;
    }
    if (nfcTagContent == null) {
      if (other.nfcTagContent != null) {
        return false;
      }
    } else if (!nfcTagContent.equals(other.nfcTagContent)) {
      return false;
    }
    if (password == null) {
      if (other.password != null) {
        return false;
      }
    } else if (!password.equals(other.password)) {
      return false;
    }
    if (postCode == null) {
      if (other.postCode != null) {
        return false;
      }
    } else if (!postCode.equals(other.postCode)) {
      return false;
    }
    if (primaryTelephone == null) {
      if (other.primaryTelephone != null) {
        return false;
      }
    } else if (!primaryTelephone.equals(other.primaryTelephone)) {
      return false;
    }
    if (role == null) {
      if (other.role != null) {
        return false;
      }
    } else if (!role.equals(other.role)) {
      return false;
    }
    if (secondaryTelephone == null) {
      if (other.secondaryTelephone != null) {
        return false;
      }
    } else if (!secondaryTelephone.equals(other.secondaryTelephone)) {
      return false;
    }
    if (tenantCustomerId == null) {
      if (other.tenantCustomerId != null) {
        return false;
      }
    } else if (!tenantCustomerId.equals(other.tenantCustomerId)) {
      return false;
    }
    if (title == null) {
      if (other.title != null) {
        return false;
      }
    } else if (!title.equals(other.title)) {
      return false;
    }
    if (userName == null) {
      return other.userName == null;
    } else {
      return userName.equals(other.userName);
    }
  }
}
