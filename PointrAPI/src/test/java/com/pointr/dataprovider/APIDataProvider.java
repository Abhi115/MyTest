package com.pointr.dataprovider;

import org.testng.annotations.DataProvider;
import com.pointr.utils.GlobalConstant;
import com.pointr.utils.GlobalConstant.JsonProperties;

public class APIDataProvider {

	private static final String longString = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
					+ "Aenean commodo ligula eget dolor. Aenean massa. Cum sociis "
					+ "natoque penatibus et magnis dis parturient montes, nascetur "
					+ "ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, "
					+ "pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo,"
					+ " fringilla vel, aliquet nec, vulputate eget, arcu"; 
	private static final String shortString  = "Lorem ipsum dolor sit amet, consectetuer";
	
	@DataProvider(name = "PoiTestData")
	public static Object[][] poiTestData() {
		
		return new Object[][] {  
			{ JsonProperties.ID.toString(), "-", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.ID.toString(), shortString, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR},
			{ JsonProperties.Description.toString(), "@!@& (( Test Secription ", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Description.toString(), "1234567", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Description.toString(), "1234567", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Type.toString(), "", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), " ", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), "Test", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), "-1", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.OBJECT_REFERENCE_NOT_SET},
			{ JsonProperties.Type.toString(), "@@@Test", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},			
			{ JsonProperties.ID.toString(), "@!@&", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Name.toString(), "@!@&" , GlobalConstant.HTTP_OK , ""},			
			{ JsonProperties.Name.toString(), "12345765", GlobalConstant.HTTP_OK , "" }, 
			{ JsonProperties.ID.toString(), "1234565", GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Name.toString(), "TestName", GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.ID.toString(), "TestName", GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Name.toString(),  longString, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR, GlobalConstant.ENTITY_VALIDATION_ERROR },
			{ JsonProperties.ID.toString(), longString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR},
			{ JsonProperties.Description.toString(), longString, GlobalConstant.HTTP_OK , "" }, 
			{ JsonProperties.Description.toString(), "" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Description.toString(),  shortString, GlobalConstant.HTTP_OK , ""}
		};		
	}
	
	@DataProvider(name = "PoiTestDataForEdit")
	public static Object[][] poiTestDataForEdit() {
		
		return new Object[][] {  
			{ JsonProperties.Name.toString(), "@!@&" , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Name.toString(), "12345765", GlobalConstant.HTTP_OK , "" },			
			{ JsonProperties.Name.toString(), "TestName", GlobalConstant.HTTP_OK , "" },			
			{ JsonProperties.Name.toString(), longString ,
					GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError}, 
			{ JsonProperties.Description.toString(), longString, GlobalConstant.HTTP_OK , "" }, 
			{ JsonProperties.Description.toString(), "" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Description.toString(), shortString , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "-12345765" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), longString , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), " " , GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Type.toString(), " " , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), "" ,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), "!@#$" ,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), "-3" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
			{ JsonProperties.Type.toString(), shortString , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), " 3 " , GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Description.toString(), " " , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Description.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Name.toString(), shortString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
			{ JsonProperties.Name.toString(), " " , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
			{ JsonProperties.Name.toString(), " Test Name 360 " , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Name.toString(), "-123456" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Name.toString(), longString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
			
			{ JsonProperties.Level.toString(), "3" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
			{ JsonProperties.Level.toString(), " 0 " , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Level.toString(), shortString , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Level.toString(), " " , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Level.toString(), "0" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Level.toString(), "!@#$" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Level.toString(), "" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
			
			{ JsonProperties.ID.toString(), "!@#$",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
			{ JsonProperties.ID.toString(), "-poi150209990330339",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
			{ JsonProperties.ID.toString(), shortString,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
			{ JsonProperties.ID.toString(), " poi150209990330339 ",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND}
		};		
	}
		
	
	@DataProvider(name = "poiComplexTestData")
	public static Object[][] poiComplexTestData() {		
		return new Object[][] {  
			{ "Facility", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150209597061170\",\"level\":\"1\",\"name\":\"poi1\",\"description\":\"facility\",\"type\":\"3\",\"keywords\":\"\",\"startshelf\":\"\",\"endshelf\":\"\",\"minZoomLevel\":\"out\",\"maxZoomLevel\":\"in\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[15.127845764160156,-54.2535514831543]}}" , 200 , ""}, 
//			{ "Thematic area", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150209608576993\",\"level\":\"1\",\"name\":\"poi2\",\"description\":\"thematicarea\",\"type\":\"37\",\"keywords\":\"\",\"startshelf\":\"\",\"endshelf\":\"\",\"minZoomLevel\":\"out\",\"maxZoomLevel\":\"in\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[42.127845764160156,-39.84446144104004]}}", 200 , ""},
//			{ "Area subset", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150209615525691\",\"level\":\"1\",\"name\":\"poi3\",\"description\":\"areasubset\",\"type\":\"4\",\"keywords\":\"\",\"startshelf\":\"\",\"endshelf\":\"\",\"minZoomLevel\":\"out\",\"maxZoomLevel\":\"in\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[58.877845764160156,-50.59446144104004]}}" , 200 , ""},
//			{ "External trigger", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150209622434592\",\"level\":\"1\",\"name\":\"poi4\",\"description\":\"externaltrigger\",\"type\":\"21\",\"keywords\":\"\",\"startshelf\":\"\",\"endshelf\":\"\",\"minZoomLevel\":\"out\",\"maxZoomLevel\":\"in\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[53.127845764160156,-78.09446144104004],[53.127845764160156,-58.09446144104004],[83.37784576416016,-58.09446144104004],[83.37784576416016,-78.09446144104004],[53.127845764160156,-78.09446144104004]]]}}" , 200 , ""},
//			{ "Area subset", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi15020963430735\",\"level\":\"1\",\"name\":\"poi5\",\"description\":\"trigger\",\"type\":\"1\",\"keywords\":\"\",\"startshelf\":\"\",\"endshelf\":\"\",\"minZoomLevel\":\"out\",\"maxZoomLevel\":\"in\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[58.877845764160156,-27.09446144104004],[58.877845764160156,-15.094461441040039],[87.12784576416016,-15.094461441040039],[87.12784576416016,-27.09446144104004],[58.877845764160156,-27.09446144104004]]]}}", 200 , ""}
		};		
	}
	
	
	@DataProvider(name = "BuildingJsonData")
	public static Object[][] buildingJsonData() {		
		return new Object[][] {  
//			{ "Solid Wall", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150209990330339\",\"level\":\"1\",\"name\":\"Solid Wall\",\"description\":\"wal\",\"type\":\"2\",\"keywords\":\"\",\"floorsArray\":\"1\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[49.127845764160156,-47.501420974731445],[49.127845764160156,-26.251420974731445],[92.37784576416016,-26.251420974731445],[92.37784576416016,-47.501420974731445],[49.127845764160156,-47.501420974731445]]]}}" , false}, 
			{ "Lift", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210014876116\",\"level\":\"1\",\"name\":\"Lift\",\"description\":\"\",\"type\":\"5\",\"keywords\":\"\",\"floorsArray\":\"1,2\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[11.127845764160156,-34.59233093261719]}}", false},
//			{ "Stairs", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210022487579\",\"level\":\"1\",\"name\":\"Stairs\",\"description\":\"\",\"type\":\"6\",\"keywords\":\"\",\"floorsArray\":\"1,2\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[12.377845764160156,-47.84233093261719]}}" , false},
//			{ "Angular Stairs", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210027789370\",\"level\":\"1\",\"name\":\"Angular stairs\",\"description\":\"\",\"type\":\"7\",\"keywords\":\"\",\"floorsArray\":\"1\",\"portalid\":\"poi150210027789370\",\"property\":\"Angular stairs\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[52.377845764160156,-13.342330932617188]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210028155962\",\"level\":\"2\",\"name\":\"Angular stairs\",\"description\":\"\",\"type\":\"7\",\"keywords\":\"\",\"portalid\":\"poi150210027789370\",\"property\":\"Angular stairs\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[46.29475402832031,-28.592330932617188]}}]", true},
//			{ "Escalator", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210031803582\",\"level\":\"1\",\"name\":\"Escalator\",\"description\":\"\",\"type\":\"8\",\"keywords\":\"\",\"floorsArray\":\"1\",\"portalid\":\"poi150210031803582\",\"property\":\"Escalator\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[82.12784576416016,-84.09233093261719]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi15021003218324\",\"level\":\"2\",\"name\":\"Escalator\",\"description\":\"\",\"type\":\"8\",\"keywords\":\"\",\"portalid\":\"poi150210031803582\",\"property\":\"Escalator\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[14.544754028320312,-17.092330932617188]}}]", true},
//			{ "Travelator", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210036830516\",\"level\":\"1\",\"name\":\"Travelator\",\"description\":\"\",\"type\":\"9\",\"keywords\":\"\",\"floorsArray\":\"1\",\"portalid\":\"poi150210036830516\",\"property\":\"Travelator\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[30.877845764160156,-15.342330932617188]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210037990449\",\"level\":\"1\",\"name\":\"Travelator\",\"description\":\"\",\"type\":\"9\",\"keywords\":\"\",\"portalid\":\"poi150210036830516\",\"property\":\"Travelator\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[36.627845764160156,-82.34233093261719]}}]",true},
//			{ "Service Lift", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210046839970\",\"level\":\"1\",\"name\":\"Service lift\",\"description\":\"\",\"type\":\"10\",\"keywords\":\"\",\"floorsArray\":\"1,2\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[47.377845764160156,-22.592330932617188]}}", false},
//			{ "IBeacon", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210056096271\",\"level\":\"1\",\"name\":\"beacon\",\"description\":\"test\",\"type\":\"12\",\"keywords\":\"\",\"isProximityBeacon\":\"false\",\"uuid\":\"test\",\"major\":\"12\",\"minor\":\"434\",\"floorsArray\":\"1\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[14.127845764160156,-82.59233093261719]}}", false},
//			{ "Transparent wall", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210063159731\",\"level\":\"2\",\"name\":\"TransparentWall\",\"description\":\"\",\"type\":\"13\",\"keywords\":\"\",\"floorsArray\":\"2\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[19.544754028320312,-59.59233093261719],[19.544754028320312,-22.342330932617188],[67.79475402832031,-22.342330932617188],[67.79475402832031,-59.59233093261719],[19.544754028320312,-59.59233093261719]]]}}", false},
//			{ "Proximity Beacon", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210067910419\",\"level\":\"2\",\"name\":\"test2\",\"description\":\"becon\",\"type\":\"14\",\"keywords\":\"\",\"isProximityBeacon\":\"false\",\"uuid\":\"beacon\",\"major\":\"77\",\"minor\":\"645\",\"floorsArray\":\"2\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[21.794754028320312,-79.59233093261719]}}", false},
//			{ "Skewed Lift", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi15021007331607\",\"level\":\"1\",\"name\":\"Skewed lift\",\"description\":\"\",\"type\":\"15\",\"keywords\":\"\",\"floorsArray\":\"1\",\"portalid\":\"poi15021007331607\",\"property\":\"Skewed lift\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[30.127845764160156,-38.09233093261719]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210073884811\",\"level\":\"2\",\"name\":\"Skewedlift\",\"description\":\"\",\"type\":\"15\",\"keywords\":\"\",\"portalid\":\"poi15021007331607\",\"property\":\"Skewed lift\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[91.54475402832031,-91.34233093261719]}}]",true},
//			{ "Master Pop", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236325602824\",\"level\":\"0\",\"name\":\"Test\",\"description\":\"Testing Test\",\"type\":\"16\",\"deviceSerial\":\"00-14-22-01-23-45\",\"deviceMacIdentifier\":\"00-14-22-01-23-45\",\"snifferMacIdentifier\":\"\",\"isBoundaryDevice\":\"false\",\"keywords\":\"\",\"rssiThreshold\":\"\",\"floorsArray\":\"0\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[63.796875,-36.44791603088379]}}", false},
//			{ "Service stairs", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236375027661\",\"level\":\"1\",\"name\":\"Service stairs\",\"description\":\"Service Stairs\",\"type\":\"11\",\"keywords\":\"Item1, Item2\",\"floorsArray\":\"1\",\"portalid\":\"poi150236375027661\",\"property\":\"Service stairs\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[49.73957824707031,-60.03124809265137]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236375644978\",\"level\":\"0\",\"name\":\"Service stairs\",\"description\":\"Service Stairs\",\"type\":\"11\",\"keywords\":\"Item1, Item2\",\"portalid\":\"poi150236375027661\",\"property\":\"Service stairs\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[73.796875,-39.28124809265137]}}]", true},
//			{ "Customer Zone", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236414591317\",\"level\":\"0\",\"name\":\"Customer Zone\",\"description\":\"Customer Zone\",\"type\":\"18\",\"keywords\":\"Zone1, Zone2\",\"floorsArray\":\"0\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[53.296875,-52.44791603088379],[40.546875,-8.447916030883789],[23.796875,-45.94791603088379],[53.296875,-52.44791603088379]]]}}", false},
//			{ "Analytics Zone", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236440890135\",\"level\":\"0\",\"name\":\"Analytics Zone\",\"description\":\"Analytics Zone\",\"type\":\"19\",\"keywords\":\"Analytics Zone1, Analytics Zone2\",\"afausersegment\":\"4\",\"durationType\":\"atLeast\",\"duration\":\"100\",\"floorsArray\":\"0\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[20.796875,-3.947916030883789],[66.546875,-5.197916030883789],[66.046875,-11.197916030883789],[19.296875,-11.697916030883789],[20.796875,-3.947916030883789]]]}}", false},
//			{ "InterFacility Portal", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi15023646192925\",\"level\":\"0\",\"name\":\"InterFacility Portal\",\"description\":\"InterFacility Portal\",\"type\":\"20\",\"keywords\":\"InterFacility Portal1\",\"floorsArray\":\"0\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[17.046875,-43.44791603088379]}}", false},
//			{ "Security Control", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236480951292\",\"level\":\"0\",\"name\":\"Security Control\",\"description\":\"Security Control\",\"type\":\"22\",\"keywords\":\"Security Control1\",\"floorsArray\":\"0\",\"portalid\":\"poi150236480951292\",\"property\":\"Security Control\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[17.546875,-51.44791603088379]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236482589142\",\"level\":\"0\",\"name\":\"Security Control\",\"description\":\"Security Control\",\"type\":\"22\",\"keywords\":\"Security Control1\",\"portalid\":\"poi150236480951292\",\"property\":\"Security Control\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[28.046875,-59.94791603088379]}}]", true},
//			{ "Passport Control", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236499466640\",\"level\":\"0\",\"name\":\"Passport Control\",\"description\":\"Passport Control\",\"type\":\"23\",\"keywords\":\"Passport Control1\",\"floorsArray\":\"0\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[73.296875,-55.94791603088379]}}", false},
//			{ "Ramp", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236517077148\",\"level\":\"0\",\"name\":\"Ramp\",\"description\":\"Ramp\",\"type\":\"24\",\"keywords\":\"Ramp1, Ramp2\",\"floorsArray\":\"0\",\"portalid\":\"poi150236517077148\",\"property\":\"Ramp\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[64.296875,-75.69791603088379]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi15023651758654\",\"level\":\"1\",\"name\":\"Ramp\",\"description\":\"Ramp\",\"type\":\"24\",\"keywords\":\"Ramp1, Ramp2\",\"portalid\":\"poi150236517077148\",\"property\":\"Ramp\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[37.48957824707031,-74.69791603088379]}}]", true},
//			{ "Steps", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi15023653053221\",\"level\":\"0\",\"name\":\"Steps\",\"description\":\"Steps\",\"type\":\"25\",\"keywords\":\"Steps1, Steps2\",\"floorsArray\":\"0\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[79.796875,-63.19791603088379]}}", false},
//			{ "Border Control", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236539301026\",\"level\":\"0\",\"name\":\"Border Control\",\"description\":\"Border Control\",\"type\":\"27\",\"keywords\":\"Border Control 1, Border Control 2\",\"floorsArray\":\"0\",\"portalid\":\"poi150236539301026\",\"property\":\"Border Control\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[57.546875,-61.19791603088379]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150236539963184\",\"level\":\"0\",\"name\":\"Border Control\",\"description\":\"Border Control\",\"type\":\"27\",\"keywords\":\"Border Control 1, Border Control 2\",\"portalid\":\"poi150236539301026\",\"property\":\"Border Control\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[61.046875,-62.69791603088379]}}]", true}
		};		
	}

	
	@DataProvider(name = "BuildingTestData")
	public static Object[][] buildingTestData() {
		
		return new Object[][] { 
			
			{ JsonProperties.Name.toString(), shortString , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Description.toString(), shortString , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.ID.toString(), "TestTest", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "TestTest", GlobalConstant.HTTP_OK , "" },			
			{ JsonProperties.Name.toString(), "@!@&" , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Description.toString(), "@!@&" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.ID.toString(), "@!@&", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "@!@&", GlobalConstant.HTTP_OK , "" }, 
			{ JsonProperties.Type.toString(), "@!@&", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Level.toString(), "@!@&", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Type.toString(), "Test", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Level.toString(), "Test", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON },
						
			{ JsonProperties.Name.toString(), "Solid Wall" , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Description.toString(), "Solid Wall" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.ID.toString(), "Test", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "Solid Wall", GlobalConstant.HTTP_OK , "" }, 
			{ JsonProperties.Type.toString(), "2", GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Level.toString(), "1", GlobalConstant.HTTP_OK , "" },		
			
			{ JsonProperties.Name.toString(), "" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR}, 			
			{ JsonProperties.Description.toString(), "" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.ID.toString(), "", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "", GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Type.toString(), "", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Level.toString(), "", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON },
			
			{ JsonProperties.Name.toString(), "TestTest" , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Description.toString(), "TestTest" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.ID.toString(), "TestTest", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "TestTest", GlobalConstant.HTTP_OK , "" },
			
			{ JsonProperties.Name.toString(), longString ,
					GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR}, 
			{ JsonProperties.ID.toString(), longString ,
					GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR},
			{ JsonProperties.Description.toString(), longString, GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Keywords.toString(), longString, GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Description.toString(), shortString , GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Level.toString(), "-8" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Name.toString(), "8" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Description.toString(), "12" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Type.toString(), "-123", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.OBJECT_REFERENCE_NOT_SET},
			{ JsonProperties.Keywords.toString(), "123", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), shortString, GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Name.toString(), " Solid Wall " , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Level.toString(), " 1 ", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Type.toString(), " 2 ", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.ID.toString(), " Test ", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), " TestTest ", GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Name.toString(), " " , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR ,GlobalConstant.ENTITY_VALIDATION_ERROR },
			{ JsonProperties.Level.toString(), " ", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Type.toString(), " ", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
			{ JsonProperties.Keywords.toString(), " ",GlobalConstant.HTTP_OK , ""}
		};		
	}
	
	
	@DataProvider(name = "BuildingEditData")
	public static Object[][] buildingEditData(){
		
		return new Object[][] { 
			
			{ JsonProperties.Name.toString(), "@!@&" , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Description.toString(), "@!@&" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "@!@&", GlobalConstant.HTTP_OK , "" }, 
			{ JsonProperties.Type.toString(), "@!@&", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Level.toString(), "@!@&", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Type.toString(), "Test", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Level.toString(), "Test", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
						
			{ JsonProperties.Name.toString(), "Solid Wall" , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Description.toString(), "Solid Wall" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "Solid Wall", GlobalConstant.HTTP_OK , "" }, 
			{ JsonProperties.Type.toString(), "2", GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Level.toString(), "1", GlobalConstant.HTTP_OK , "" },		
			
			{ JsonProperties.Name.toString(), "" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError}, 			
			{ JsonProperties.Description.toString(), "" , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "", GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Type.toString(), "", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Level.toString(), "", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
			
			{ JsonProperties.Name.toString(), "TestTest" , GlobalConstant.HTTP_OK , ""}, 			
			{ JsonProperties.Description.toString(), "TestTest" , GlobalConstant.HTTP_OK , ""},			
			{ JsonProperties.Keywords.toString(), "TestTest", GlobalConstant.HTTP_OK , "" },			
			 
			{ JsonProperties.Description.toString(), longString, GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Keywords.toString(), longString, GlobalConstant.HTTP_OK , "" },
			{ JsonProperties.Description.toString(), shortString , GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Name.toString(), longString, GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
			{ JsonProperties.Keywords.toString(), " ", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Keywords.toString(), "-12345", GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Type.toString(), " ", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Type.toString(), "-2", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError },
			{ JsonProperties.Type.toString(), " 2 ", GlobalConstant.HTTP_OK , "" },
			
			{ JsonProperties.Description.toString(), " " , GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Name.toString(), " ", GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
			{ JsonProperties.Name.toString(), " test user 360 ", GlobalConstant.HTTP_OK , ""},
			{ JsonProperties.Name.toString(), "-123456", GlobalConstant.HTTP_OK , ""},
			
			{ JsonProperties.Level.toString(), "2", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND },
			{ JsonProperties.Level.toString(), " ", GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON },
			{ JsonProperties.Level.toString(), " 1 ", GlobalConstant.HTTP_OK , "" },
			
			{ JsonProperties.ID.toString(), "!@#$",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
			{ JsonProperties.ID.toString(), "-poi150209990330339",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
			{ JsonProperties.ID.toString(), shortString,GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
			{ JsonProperties.ID.toString(), " poi150209990330339 ",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND}
		};
	}

	
	@DataProvider(name="MapDesigner")
	public static Object[][] mapDesignerTestData(){
		return new Object[][] {  
//			{"Trigger", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"1\",\"name\":\"Trigger-elichan\",\"description\":\"elly\",\"keywords\":\"\",\"group\":430,\"id\":\"poi278759500986150\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[54.66015625,-79.75],[56.78515625,-75.9375],[54.28515625,-75.625],[54.66015625,-79.75]]]}}", false},
			{"Solid Wall", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"2\",\"name\":\"SolidWall -eli\",\"description\":\"dons\",\"keywords\":\"\",\"group\":431,\"id\":\"poi389988500521636\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[53.53515625,-81.9375],[56.34765625,-81.8125],[60.890625,-83.75],[53.53515625,-81.9375]]]}}", false},
//			{"Facility", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"3\",\"name\":\"Facilityelican\",\"description\":\"\",\"keywords\":\"\",\"group\":432,\"id\":\"poi920209000268556\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[53.72265625,-70.4375],[57.47265625,-70.1875],[55.97265625,-72.9375],[54.22265625,-70.8125],[53.72265625,-70.4375]]]}}", false},
//			{"Area Subset", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"4\",\"name\":\"Areasubseteli\",\"description\":\"\",\"keywords\":\"\",\"group\":433,\"id\":\"poi470903500278645\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[53.03515625,-74.5],[50.97265625,-74.6875],[52.22265625,-75.875],[53.03515625,-74.5]]]}}", false},
//			{"Stairs", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210022487579\",\"level\":\"1\",\"name\":\"Stairs\",\"description\":\"\",\"type\":\"6\",\"keywords\":\"\",\"floorsArray\":\"1,2\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[12.377845764160156,-47.84233093261719]}}", false},
//			{"Travelator", "[{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210036830516\",\"level\":\"1\",\"name\":\"Travelator\",\"description\":\"\",\"type\":\"9\",\"keywords\":\"\",\"floorsArray\":\"1\",\"portalid\":\"poi150210036830516\",\"property\":\"Travelator\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[30.877845764160156,-15.342330932617188]}},{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210037990449\",\"level\":\"1\",\"name\":\"Travelator\",\"description\":\"\",\"type\":\"9\",\"keywords\":\"\",\"portalid\":\"poi150210036830516\",\"property\":\"Travelator\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[36.627845764160156,-82.34233093261719]}}]", true},
//			{"Service Stairs", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210046839970\",\"level\":\"1\",\"name\":\"Servicestairs\",\"description\":\"\",\"type\":\"11\",\"keywords\":\"\",\"floorsArray\":\"1,2\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[47.377845764160156,-22.592330932617188]}}", false},
//			{"IBeacon", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"12\",\"name\":\"IBeaconely\",\"description\":\"elibeacon\",\"keywords\":\"\",\"isProximityBeacon\":false,\"uuid\":\"123222252525\",\"major\":\"71\",\"minor\":\"12\",\"group\":435,\"id\":\"poi705419500368102\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[48.91015625,-69]}}", false},
//			{"Transparent Wall", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150210063159731\",\"level\":\"2\",\"name\":\"TransparentWall\",\"description\":\"\",\"type\":\"13\",\"keywords\":\"\",\"floorsArray\":\"2\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[19.544754028320312,-59.59233093261719],[19.544754028320312,-22.342330932617188],[67.79475402832031,-22.342330932617188],[67.79475402832031,-59.59233093261719],[19.544754028320312,-59.59233093261719]]]}}", false},
//			{"Proximity Beacon", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"14\",\"name\":\"ProximityBeaconeli\",\"description\":\"elli\",\"keywords\":\"\",\"isProximityBeacon\":false,\"uuid\":\"54543215451\",\"major\":\"45\",\"minor\":\"8\",\"group\":438,\"id\":\"poi184232000869919\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[38.640625,-73.75]}}", false},
//			{"Master Pop", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"16\",\"name\":\"MasterPopeli\",\"description\":\"masterrz\",\"keywords\":\"\",\"deviceMacIdentifier\":\"test1\",\"isBoundaryDevice\":false,\"deviceSerial\":\"test1\",\"snifferMacIdentifier\":\"b8:27:eb:36:c3:77\",\"rssiThreshold\":\"100\",\"group\":439,\"id\":\"poi426592500605617\"},\"geometry\":{\"type\":\"Point\",\"coordinates\":[29.361328125,-75.28125]}}", false},
//			{"Customer Zone", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"18\",\"name\":\"CustomerZonetest\",\"description\":\"\",\"keywords\":\"\",\"isFootfallAnalyticsEnabled\":true,\"isDwellTimeAnalyticsEnabled\":true,\"isFluxEnabled\":true,\"group\":440,\"id\":\"poi181475500861061\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[31.830078125,-72.78125],[31.017578125,-74.65625],[33.517578125,-74.90625],[31.830078125,-72.78125]]]}}", false},
//			{"External Trigger", "{\"type\":\"Feature\",\"properties\":{\"id\":\"poi150209622434592\",\"level\":\"1\",\"name\":\"poi4\",\"description\":\"externaltrigger\",\"type\":\"3\",\"keywords\":\"\",\"startshelf\":\"\",\"endshelf\":\"\",\"minZoomLevel\":\"out\",\"maxZoomLevel\":\"in\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[53.127845764160156,-78.09446144104004],[53.127845764160156,-58.09446144104004],[83.37784576416016,-58.09446144104004],[83.37784576416016,-78.09446144104004],[53.127845764160156,-78.09446144104004]]]}}", false},
//			{"Security Control", "[{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"22\",\"name\":\"SecurityControl\",\"description\":\"\",\"keywords\":\"\",\"group\":446,\"id\":\"poi170559500448883\",\"portalid\":446},\"geometry\":{\"type\":\"Point\",\"coordinates\":[38.53515625,-87.875]}},{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"22\",\"name\":\"SecurityControl\",\"description\":\"\",\"keywords\":\"\",\"group\":446,\"id\":\"poi670563000278571\",\"portalid\":446},\"geometry\":{\"type\":\"Point\",\"coordinates\":[40.22265625,-91.25]}}]", true},
//			{"Passport Control", "[{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"23\",\"name\":\"PassportControl\",\"description\":\"\",\"keywords\":\"\",\"group\":447,\"id\":\"poi728074500052028\",\"portalid\":447},\"geometry\":{\"type\":\"Point\",\"coordinates\":[37.34765625,-95.8125]}},{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"23\",\"name\":\"PassportControl\",\"description\":\"\",\"keywords\":\"\",\"group\":447,\"id\":\"poi428078000381099\",\"portalid\":447},\"geometry\":{\"type\":\"Point\",\"coordinates\":[42.84765625,-94.9375]}}]", true},
//			{"Ramp", "[{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"24\",\"name\":\"Ramp\",\"description\":\"\",\"keywords\":\"\",\"group\":445,\"id\":\"poi345819000024851\",\"portalid\":445},\"geometry\":{\"type\":\"Point\",\"coordinates\":[39.16015625,-81.875]}},{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"24\",\"name\":\"Ramp\",\"description\":\"\",\"keywords\":\"\",\"group\":445,\"id\":\"poi545837000812532\",\"portalid\":445},\"geometry\":{\"type\":\"Point\",\"coordinates\":[43.66015625,-82.9375]}}]", true},
//			{"Steps", "[{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"25\",\"name\":\"Steps\",\"description\":\"\",\"keywords\":\"\",\"group\":448,\"id\":\"poi404333000963366\",\"portalid\":448},\"geometry\":{\"type\":\"Point\",\"coordinates\":[9.6953125,-72.625]}},{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"25\",\"name\":\"Steps\",\"description\":\"\",\"keywords\":\"\",\"group\":448,\"id\":\"poi904339500201646\",\"portalid\":448},\"geometry\":{\"type\":\"Point\",\"coordinates\":[18.3203125,-65.75]}}]", true},
//			{"Gate", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"26\",\"name\":\"Gate\",\"description\":\"\",\"keywords\":\"\",\"group\":443,\"id\":\"poi458612500066324\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[41.78515625,-71.5],[40.84765625,-74],[46.09765625,-73.8125],[41.78515625,-71.5]]]}}", false},
//			{"Border Control", "[{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"27\",\"name\":\"BorderControl\",\"description\":\"\",\"keywords\":\"\",\"group\":444,\"id\":\"poi191286000942392\",\"portalid\":444},\"geometry\":{\"type\":\"Point\",\"coordinates\":[42.16015625,-76.25]}},{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"27\",\"name\":\"BorderControl\",\"description\":\"\",\"keywords\":\"\",\"group\":444,\"id\":\"poi491290000029984\",\"portalid\":444},\"geometry\":{\"type\":\"Point\",\"coordinates\":[45.53515625,-76.1875]}}]", true},
//			{"Check-In", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"28\",\"name\":\"Check-In\",\"description\":\"\",\"keywords\":\"\",\"group\":450,\"id\":\"poi421797000660640\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[23.330078125,-80.3125],[23.017578125,-81.9375],[25.048828125,-81.25],[23.330078125,-80.3125]]]}}", false},
//			{"Office", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"29\",\"name\":\"Office\",\"description\":\"\",\"keywords\":\"\",\"group\":451,\"id\":\"poi562629500248377\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[22.173828125,-83.9375],[21.798828125,-84.6875],[23.267578125,-84.125],[22.173828125,-83.9375]]]}}", false},
//			{"Attraction", "{\"type\":\"Feature\",\"properties\":{\"level\":0,\"type\":\"30\",\"name\":\"Attraction\",\"description\":\"\",\"keywords\":\"\",\"group\":452,\"id\":\"poi876928000550080\"},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[25.236328125,-82.875],[25.205078125,-84.90625],[27.392578125,-84.78125],[26.986328125,-82.40625],[25.236328125,-82.875]]]}}", false}
			//{"InterFacility Portal", "", false},  //not yet implemented, will provided further information later
			//{"InterFacility Portal", "", false}, //The sample json will be provided. 
		};	
	}
		@DataProvider(name = "MapDesignerTestData")
		public static Object[][] mapDesTestData() {
			return new Object[][] { 
				
				{ JsonProperties.Level.toString(), "" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON}, 	
				{ JsonProperties.Level.toString(), "!@#$" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
				{ JsonProperties.Level.toString(), "-1" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Level.toString(), shortString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
				{ JsonProperties.Level.toString(), " 0 " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Level.toString(), " " , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
				
				{ JsonProperties.Type.toString(), "" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
				{ JsonProperties.Type.toString(), "!@#$" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
				{ JsonProperties.Type.toString(), "-1" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.OBJECT_REFERENCE_NOT_SET},
				{ JsonProperties.Type.toString(), shortString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
				{ JsonProperties.Type.toString(), " 2 " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Type.toString(), " " , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.INVALID_JSON},
				
				{ JsonProperties.Name.toString(), "" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR},
				{ JsonProperties.Name.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Name.toString(), "123" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Name.toString(), longString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR},
				{ JsonProperties.Name.toString(), " Solid Wall " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Name.toString(), " " , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR},
				{ JsonProperties.Name.toString(), "-123" , GlobalConstant.HTTP_OK , ""},
				
				{ JsonProperties.Description.toString(), "" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Description.toString(), "-123" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Description.toString(), "123" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Description.toString(), shortString , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Description.toString(), " test description added " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Description.toString(), " " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Description.toString(), longString , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Description.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
				
				{ JsonProperties.Keywords.toString(), "" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Keywords.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Keywords.toString(), "123" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Keywords.toString(), longString , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Keywords.toString(), " 1 23 " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Keywords.toString(), "-123" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Keywords.toString(), " " , GlobalConstant.HTTP_OK , ""},
				
				{ JsonProperties.Group.toString(), "" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Group.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Group.toString(), " 4 31 " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Group.toString(), " " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Group.toString(), "-431" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Group.toString(), longString , GlobalConstant.HTTP_OK , ""},
				
				{ JsonProperties.ID.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.ID.toString(), shortString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.ENTITY_VALIDATION_ERROR},
				{ JsonProperties.ID.toString(), " Poi 4654644646 " , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.ID.toString(), "-123" , GlobalConstant.HTTP_OK , ""},
				{ JsonProperties.Type.toString(), "478" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.OBJECT_REFERENCE_NOT_SET}
			};	
	}
		
		@DataProvider(name = "MapDesignerEditTestData")
		 public static Object[][] mapDesEditData() {
		  return new Object[][] { 
		   
		   { JsonProperties.Level.toString(), "" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},  
		   { JsonProperties.Level.toString(), "!@#$" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
		   { JsonProperties.Level.toString(), "-1" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND},
		   { JsonProperties.Level.toString(), shortString , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
		   { JsonProperties.Level.toString(), " 1 " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Level.toString(), " " , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
		   
		   { JsonProperties.Type.toString(), "" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
		   { JsonProperties.Type.toString(), "!@#$" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
		   { JsonProperties.Type.toString(), "-2" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
		   { JsonProperties.Type.toString(), shortString , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
		   { JsonProperties.Type.toString(), " 2 " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Type.toString(), " " , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.INVALID_JSON},
		   
		   { JsonProperties.Name.toString(), "" , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
		   { JsonProperties.Name.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Name.toString(), "123" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Name.toString(), longString , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
		   { JsonProperties.Name.toString(), " Solid Wall " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Name.toString(), " " , GlobalConstant.HTTP_INTERNAL_SERVER_ERROR , GlobalConstant.InternalServerError},
		   { JsonProperties.Name.toString(), "-123" , GlobalConstant.HTTP_OK , ""},
		  
		   { JsonProperties.Description.toString(), "" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), "-123" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), "123" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), shortString , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), " test description added " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), " " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), longString , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Description.toString(), "test user" , GlobalConstant.HTTP_OK , ""},
		   
		   { JsonProperties.Keywords.toString(), "" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Keywords.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Keywords.toString(), "123" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Keywords.toString(), longString , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Keywords.toString(), " 123 " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Keywords.toString(), "-123" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Keywords.toString(), " " , GlobalConstant.HTTP_OK , ""},
		   
		   { JsonProperties.Group.toString(), "" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Group.toString(), "!@#$" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Group.toString(), " 431 " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Group.toString(), " " , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Group.toString(), "-431" , GlobalConstant.HTTP_OK , ""},
		   { JsonProperties.Group.toString(), longString , GlobalConstant.HTTP_OK , ""},
		   
		   { JsonProperties.ID.toString(), "!@#$%" , GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.POI_NOT_FOUND}
		   
		  };  
		 }
		
		@DataProvider(name = "WebConfigurationSaveTestData")
		public static Object[][] webConfigurationSaveTestData() {		
			return new Object[][] {  
				{"Test","Test",GlobalConstant.HTTP_OK , ""}, 
				{longString,"Test",GlobalConstant.HTTP_OK , ""},
				{"-123test","Test",GlobalConstant.HTTP_OK , ""},
				{"!@#$%","Test",GlobalConstant.HTTP_OK , ""},
				{"123","Test",GlobalConstant.HTTP_OK , ""},
				{"Test","!@#$%",GlobalConstant.HTTP_OK , ""},
				{"Test",longString,GlobalConstant.HTTP_OK , ""},
				{"Test","123",GlobalConstant.HTTP_OK , ""},
				{"Test","-123test",GlobalConstant.HTTP_OK , ""}
			};		
		}
		
		@DataProvider(name = "GlobalConfigurationSaveTestData")
		public static Object[][] globalConfigurationSaveTestData() {		
			return new Object[][] {  
				{"test",0.1,"Float",GlobalConstant.HTTP_OK , ""},
				{"test",1,"Integer",GlobalConstant.HTTP_OK , ""},
				{"test",true,"Boolean",GlobalConstant.HTTP_OK , ""}, 
				{"test",false,"Boolean",GlobalConstant.HTTP_OK , ""},
				{"test",0,"Boolean",GlobalConstant.HTTP_OK , ""},
				{"test",1,"Boolean",GlobalConstant.HTTP_OK , ""},
				{"test","test","String",GlobalConstant.HTTP_OK , ""},
				{"!@#$","test","String",GlobalConstant.HTTP_OK , ""},
				{longString,"test","String",GlobalConstant.HTTP_OK , ""},
				{"123","test","String",GlobalConstant.HTTP_OK , ""},
				{"-123","test","String",GlobalConstant.HTTP_OK , ""},
				{"test","test","Float",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test","test","Integer",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test",0.1,"Integer",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test","test","Boolean",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test",2,"Boolean",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test",0.1,"Boolean",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE}
			};		
		}
		
		@DataProvider(name = "ConfigurationSaveTestData")
		public static Object[][] configurationSaveTestData() {		
			return new Object[][] {  
				{"test",0.1,"Float",GlobalConstant.HTTP_OK , ""},
				{"test",1,"Integer",GlobalConstant.HTTP_OK , ""},
				{"test",true,"Boolean",GlobalConstant.HTTP_OK , ""}, 
				{"test",false,"Boolean",GlobalConstant.HTTP_OK , ""},
				{"test",0,"Boolean",GlobalConstant.HTTP_OK , ""},
				{"test",1,"Boolean",GlobalConstant.HTTP_OK , ""},
				{"test","test","String",GlobalConstant.HTTP_OK , ""},
				{"!@#$","test","String",GlobalConstant.HTTP_OK , ""},
				{longString,"test","String",GlobalConstant.HTTP_OK , ""},
				{"123","test","String",GlobalConstant.HTTP_OK , ""},
				{"-123","test","String",GlobalConstant.HTTP_OK , ""},
				{"test","test","Float",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test","test","Integer",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test",0.1,"Integer",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test","test","Boolean",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test",2,"Boolean",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE},
				{"test",0.1,"Boolean",GlobalConstant.HTTP_BAD_REQUEST ,GlobalConstant.VALUE_NOT_COMPATIBLE}
			};		
		}
		
		@DataProvider(name = "GlobalConfigurationEditTestData")
		public static Object[][] globalConfigurationEditTestData() {		
			return new Object[][] {  
				{"360test",0.1,"Float",0.2,GlobalConstant.HTTP_OK , ""},
				{"360test",1,"Integer",2,GlobalConstant.HTTP_OK , ""},
				{"360test",true,"Boolean",false,GlobalConstant.HTTP_OK , ""},
				{"360test","test","String","360test",GlobalConstant.HTTP_OK , ""},
			};		
		}
		
		@DataProvider(name = "ConfigurationEditTestData")
		public static Object[][] configurationEditTestData() {		
			return new Object[][] {  
				{"360test",0.1,"Float",0.2,GlobalConstant.HTTP_OK , ""},
				{"360test",1,"Integer",2,GlobalConstant.HTTP_OK , ""},
				{"360test",true,"Boolean",false,GlobalConstant.HTTP_OK , ""},
				{"360test","test","String","360test",GlobalConstant.HTTP_OK , ""},
			};		
		}
		
		@DataProvider(name = "RegisterUser")
		public static Object[][] registerUser() {		
			return new Object[][] {  
//				{"User","360test","1",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
//				{"User","360test","2",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
//				{"User","360test","6",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
//				{"!@#$%","360test","6",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
//				{"123","360test","6",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
//				{"-123","360test","6",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
//				{longString,"360test","6",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.MAXIMUM_LENGTH},
//				{shortString,"360test","6",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
//				{"360 User","360test","6",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
				{"360 User","","1",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.PASSWORD_REQUIRED},
				{"360 User","test","1",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.PASSWORD_LENGTH},
				{"360 User",longString,"1",GlobalConstant.HTTP_BAD_REQUEST , GlobalConstant.PASSWORD_LENGTH},
				{"360 User","!@#$%^","1",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
				{"360 User","testtest","1",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
				{"360 User","12345678","1",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
				{"360 User","-12345678","1",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED},
				{"360 User","test@360","1",GlobalConstant.HTTP_OK , GlobalConstant.USER_REGISTERED}
			};		
		}
}

