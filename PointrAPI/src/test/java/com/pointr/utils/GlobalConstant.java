package com.pointr.utils;

public class GlobalConstant {

	public final static String resource = "src/test/resources/";
	public final static String inputFile = resource + "inputfiles/";
	public final static String outputFile = resource + "/outputfile/";
	public final static String poiInputs = resource + "/poiInputData/";
	public final static String poiinput = inputFile+ "/poi/";
	public final static String building =  inputFile + "/building/";
	public final static String mapDesigner =  inputFile + "/mapDesigner/";
	

	//Url of POI test method
	public static final String BUILDERSuffix = "/building";
	private static final String facilities = "?facilityId=0";
	public static final String PoISaveSuffix  = BUILDERSuffix + "/savedata"+facilities;
	public static final String PoIEditSuffix = BUILDERSuffix + "/editdata"+facilities;
	public static final String PoIpublishSuffix = "/poi/publish"+facilities;
	public static final String PoIDeleteSuffix = BUILDERSuffix + "/deletedata"+facilities;
	
	public static final String ExportData  = "/exportdata/retrievepoidata";
	
	//Url of Builder test method
	public static final String BuildingSuffix  = BUILDERSuffix + "/savedata"+facilities;
	public static final String EditBuildingSuffix  = BUILDERSuffix + "/editdata"+facilities;
	public static final String DeleteBuilderSuffix  = BUILDERSuffix + "/deletedata"+facilities;
	public static final String PublishBuilderSuffix  = BUILDERSuffix + "/publish"+facilities;
	
	//Url of Map designer
	public static final String MapDesigner  = "/MapDesigner";
	public static final String MapDesignerSuffix  = MapDesigner + "/Poi"+facilities;
	public static final String MapDesignerPublishSuffix  = MapDesigner + "/publish"+facilities;
	
	//url for configure
	public static final String RetrieveBeaconStatus  ="/Configuration/RetrieveBeaconStatus";
	public static final String RetrievePopStatus  ="/Configuration/RetrievePopStatus";
	public static final String RetrieveWebJobStatus  ="/Configuration/RetrieveWebJobStatus";
	public static final String RetrieveExceptionLogs  ="/Configuration/RetrieveExceptionLogs";
	public static final String RetrieveSystemActivities  ="/Configuration/RetrieveSystemActivities";
	public static final String RetrieveGlobalConfiguration  ="/Configuration/LoadGlobalConfigurationData";
	public static final String RetrieveWebConfiguration  ="/Configuration/LoadWebConfigData";
	public static final String RetrievePopConfiguration  ="/Configuration/RetrieveSnifferConfigurationData";
	public static final String RetrieveConfiguration  ="/Configuration/LoadConfigurationData";
	public static final String RetrieveUsers  ="/Configuration/RetrieveUsers";
	public static final String RetrieveSDKCrashLogs  ="/Configuration/RetrieveSdkCrashLogs";
	public static final String SaveWebConfiguration  ="/Configuration/SaveWebConfigData";
	public static final String EditWebConfiguration  ="/Configuration/EditGlobalConfigurationData";
	public static final String DeleteWebConfiguration  ="/Configuration/DeleteConfigurationData";
	public static final String SaveGlobalConfiguration  ="/Configuration/SaveGlobalConfigurationData";
	public static final String SaveConfiguration  ="/Configuration/SaveConfigurationData";
	public static final String DeleteGlobalConfiguration  ="/Configuration/DeleteGlobalConfigurationData";
	public static final String EditGlobalConfiguration  ="/Configuration/EditGlobalConfigurationData";
	public static final String EditConfiguration  ="/Configuration/EditConfigurationData";
	public static final String DeleteConfiguration  ="/Configuration/DeleteConfigurationData";
	public static final String RegisterUser  ="/Account/RegisterUser";

	//Http response Code
	public static final int HTTP_OK = 200;
	public static final int HTTP_BAD_REQUEST = 400;
	public static final int HTTP_UNAUTHORIZED = 401;
	public static final int HTTP_INTERNAL_SERVER_ERROR = 500;
	public static final int HTTP_NOT_FOUND = 404 ;
	public static final int HTTP_FORBIDDEN = 403 ;
	public static final int HTTP_SERVICE_UNAVAILABLE = 503;
	
	// Global Response Messages
	public static final String JSON_NOT_PARSE = "Json string cannot be parsed";
	public static final String AUTHENTICATION_REQUIRED = "Authentication required";
	public static final String POI_EXIST = "poi already exists";
	public static final String INVALID_POI = "Invalid poi code";
	public static final String INVALID_JSON = "Invalid json string";
	public static final String POI_NOT_FOUND = "Poi could not be found";
	public static final String InternalServerError = "Internal Server Error";
	public static final String ENTITY_VALIDATION_ERROR = "Validation failed for one or more entities. See 'EntityValidationErrors' property for more details.";
	public static final String TRIGGER_VALIDATION = "shape is not supported for Trigger";
	public static final String SOLIDWALL_VALIDATION = "shape is not supported for Solid Wall";
	public static final String OBJECT_REFERENCE_NOT_SET = "Object reference not set to an instance of an object.";
	public static final String SuccessMsg = "Succeed";
	public static final String EXISTING_PARAMETER_NAME = "Configuration Data with the same description is already exist for the facility.";
	public static final String DATA_NOT_FOUND = "No such Configuration data.";
	public static final String DESCRIPTION_REQUIRED = "Description is required";
	public static final String VALUE_NOT_COMPATIBLE = "Value is not compatible with data type";
	public static final String VALUE_REQUIRED = "Value is required";
	public static final String USER_REGISTERED = "You are successfully registered";
	public static final String MAXIMUM_LENGTH = "The field userName must be a string or array type with a maximum length of '100'.";
	public static final String USER_NAME_REQUIRED = "User Name is required";
	public static final String PASSWORD_REQUIRED = "Password is required";
	public static final String PASSWORD_LENGTH = "Password must be at least 6 characters and at most 100 characters long.";
	public static final String PASSWORD_NOT_MATCHED = "The password and confirmation password do not match.";
	public static final String INFORMATION_UPDATED = "Your info successfully updated";
	

	
	public enum FileName {

		PoiJsonData("PoiJsonData"),		
		PoiInputOne("PoiJsonSample_1"),
		PoiInputTwo("PoiJsonSample_2"),
		BuilingJsonFile("BuildingData"),
		MapDesignerFile("MapDesignerData");
		
		private String value;

		private FileName(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}
	}
	
	public enum JsonProperties {

		Name("name"),		
		Description("description"),
		Type("type"),
		Keywords("keywords"),
		Level("level"),
		ID("id"),
		Group("group");
		
		private String value;

		private JsonProperties(String value) {
			this.value = value;
		}

		public String toString() {
			return value;
		}
	}


}
