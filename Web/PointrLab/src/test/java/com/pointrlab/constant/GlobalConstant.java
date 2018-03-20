package com.pointrlab.constant;
import com.isometrix.pages.HomePage;
import com.isometrix.testscript.Test;

public class GlobalConstant {

	public enum FileNames {
		TestDataRelativePath("src/test/resources/testdata/"), TestdataProperties("TestData"), POIProperties("Poi");

		private String value;
		
		public static final String Path_TestData = "D://ToolsQA//OnlineStore//src//testData//";
		public static final String File_TestData = "TestData.xlsx";

		private FileNames(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
			
			
		}
	}

	public enum ValidationMessage {
		
		BlankUserNameValidation("The userName field is required."), 
		BlankPasswordValidation("The userName field is required."), 
		WrongUserNameValidation("Incorrect credentials"), 
		WrongPasswordValidation("The user name or password provided is incorrect.");

		private String value;

		private ValidationMessage(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}

	}

	public enum FixedValues {
		ROLEMGMT("Role Management"), 
		FACILITYMGMT("Facility Management"),
		ACCOUNTCONTROLLERLOGIN("AccountController - Login"),
		DEFAULT("Default");

		private String value;

		private FixedValues(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}

	}
}