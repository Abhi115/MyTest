package com.mobizio.datamodel;

public class FormModel {

  private String name;
  private String description;
  private String defaultValue;
  private String hide;
  private String calculate;
  //private String ruleForHide;
  //private String ruleForCalculate;
  private String formName;
  private String formHeader;
  private String formInstruction;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public void setDefaultValue(String defaultValue) {
    this.defaultValue = defaultValue;
  }

  public String getHide() {
    return hide;
  }

  public void setHide(String hide) {
    this.hide = hide;
  }

  public String getCalculate() {
    return calculate;
  }

  public void setCalculate(String calculate) {
    this.calculate = calculate;
  }

  public String getFormName() {
    return formName;
  }

  public void setFormName(String formName) {
    this.formName = formName;
  }

  public String getFormHeader() {
    return formHeader;
  }

  public void setFormHeader(String formHeader) {
    this.formHeader = formHeader;
  }

  public String getFormInstruction() {
    return formInstruction;
  }

  public void setFormInstruction(String formInstruction) {
    this.formInstruction = formInstruction;
  }

	/*public String getRuleForHide() {
		return ruleForHide;
	}
	
	public void setRuleForHide(String ruleForHide) {
		this.ruleForHide = ruleForHide;
	}
	public String getRuleForCalculate() {
		return ruleForCalculate;
	}
	public void setRuleForCalculate(String ruleForCalculate) {
		this.ruleForCalculate = ruleForCalculate;
	}*/
}
