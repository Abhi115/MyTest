package com.edureka.paymentProcess;

import org.testng.annotations.Test;

import com.edureka.pages.AdminAddOfferPage;
import com.edureka.pages.AdminDashboard;
import com.edureka.pages.DashboardPage;
import com.edureka.pages.MyProfilePage;
import com.edureka.pages.OrderSummaryPage;
import com.edureka.pages.PayPalPage;
import com.edureka.pages.RazorPayPage;
import com.edureka.pages.SelectedCoursePage;
import com.edureka.pages.SignInModalPage;
import com.edureka.pages.UserHomePage;
import com.edureka.util.DriverTestCase;

public class MakePaymentWhenB1S2OfferAndCourseOtherThanIwillChooseLater extends DriverTestCase{
	private DashboardPage dashboardPage;
	private SignInModalPage signInModalPage;
	private UserHomePage userHomePage;
	private SelectedCoursePage selectedCoursePage;
	private OrderSummaryPage orderSummaryPage;
	private RazorPayPage razorPayPage;
	private MyProfilePage myProfilePage;
	private PayPalPage payPalPage;
	private AdminDashboard adminDashboard;
	private AdminAddOfferPage adminAddOfferPage;
	static String email;
	static String password;
	static String campaignSource;
	static String campaignName;
	static String campaignMedium;

	@Test
	public void test_021MakePaymentWhenB1S2OfferAndCourseOtherThanIwillChooseLater() throws Exception {

		try {

			// Navigate to app url
			addLog("Navigate to the Edureka application url");
			dashboardPage = applicationSetup();

			// Verify Edureka Dashboard Page
			addLog("Verify Edureka Dashboard Page");
			dashboardPage=  dashboardPage.verifyDashboard();


			// Click on Signin Navigation Header
			addLog("Click on SignIn Navigation header");
			signInModalPage = dashboardPage.clickSignInHeader();

			// Verify Login is selected as default
			addLog("Verify Login is selected as default");
			signInModalPage = signInModalPage.verifyLoginIsDefault();

			// Login Application through Admin credentials
			email = propertyReader.readApplicationFile("Admin_UserName");
			password = propertyReader.readApplicationFile("Admin_Password");
			addLog("Login Application through Admin credentials");
			signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

			// Click on Start Learning Course button
			addLog("Click on Start Learning Course button");
			userHomePage=signInModalPage.clickStartLearningButton(UserHomePage.class);

			// Verify Admin User Home page
			addLog("Verify Admin User Home page");
			userHomePage=userHomePage.verifyAdminUserPage();

			// Click on Profile dropdown
			String userName = propertyReader.readTestData("Admin_UserName");
			addLog("Click on Profile dropdown");
			userHomePage= userHomePage.clickOnProfileDropdown(userName);

			// Select Admin from Prfile Dropdown
			addLog("Select Admin from Profile dropdown");
			adminDashboard=userHomePage.selectAdmin();

			// Verify Admin Dashboard
			addLog("Verify Admin Dashboard");
			adminDashboard= adminDashboard.verifyAdminDashboard();

			// Select Menu Tab
			String menuTab = propertyReader.readTestData("Tab_Offer_Admin");
			addLog("Select Menu Tab " +menuTab);
			adminDashboard=adminDashboard.selectMenuTab(menuTab);

			// Select Create Offer from Offer Admin
			String createOffer= propertyReader.readTestData("Create_Offer");
			addLog("Select Create Offer from offer Admin");
			adminAddOfferPage=adminDashboard.selectOptionFromAdminOfferTab(createOffer);

			// Verify Admin Add Offer page
			addLog("Verify Admin Add Offer Page");
			adminAddOfferPage = adminAddOfferPage.verifyAdminAddOfferPage();

			// Create Flat Discount
			String offerType = propertyReader.readTestData("B1SN_Discount_Type");
			String discountTitle= propertyReader.readTestData("Title_Discount");
			addLog("Create Flat Discount");
			adminAddOfferPage=adminAddOfferPage.createDiscount(offerType, discountTitle);

			// Check User, Customer and Repeat Customer check boxes
			addLog("Check User, Customer and Repeat Customer check boxes");
			adminAddOfferPage=adminAddOfferPage.checkUser_Customer_RepeatCustomer();

			// Click on Submit Button
			addLog("Click on Submit Button");
			adminAddOfferPage=adminAddOfferPage.clickSubmitButton();

			// select Banner Header
			addLog("Select Banner Header");
			adminAddOfferPage=adminAddOfferPage.selectBannerHeader();

			// Get Parent window Id
			addLog("Get Parent Window ID");
			String parentWidnow = getParentWindowHandle();

			// Switch window and Select banner
			String bannerType = propertyReader.readTestData("Banner_Holy");
			addLog("Select Banner " +bannerType);
			switchPreviewWindow();
			adminAddOfferPage=adminAddOfferPage.selectBanner(bannerType);

			// Switch to Parent Window and select Enter Message Near Upcoming Batches
			addLog("Switch to Parent Window and select Enter Message Near Upcoming Batches");
			switchParentWindow(parentWidnow);
			adminAddOfferPage= adminAddOfferPage.enterMessageNearUpcomingBatches();

			// Enter Banner Text
			String bannerText = propertyReader.readTestData("Banner_Text");
			addLog("Enter Banner Text");
			switchPreviewWindow();
			adminAddOfferPage= adminAddOfferPage.enterBannerText(bannerText);

			// Get and Update Offer Id
			addLog("Get and Update Offer Id");
			switchParentWindow(parentWidnow);
			adminAddOfferPage=adminAddOfferPage.getB1S2OfferId();

			// Select Status
			String status = propertyReader.readTestData("Active_Status");
			addLog("select "+status+" from status dropdown");
			adminAddOfferPage= adminAddOfferPage.selectStatus(status);

			// Click on Edureka Logo
			addLog("Click on Edureka Logo");
			userHomePage=adminAddOfferPage.clickOnLogo();

			// Click on Profile dropdown
			addLog("Click on Profile dropdown");
			userHomePage= userHomePage.clickOnProfileDropdown(userName);

			// Logout Application
			addLog("Logout Application");
			dashboardPage=userHomePage.logout();

			// Click on Signin Navigation Header
			addLog("Click on SignIn Navigation header");
			signInModalPage = dashboardPage.clickSignInHeader();

			// Verify Login is selected as default
			addLog("Verify Login is selected as default");
			signInModalPage = signInModalPage.verifyLoginIsDefault();

			// click on Sign up link
			addLog("click on Sign up link");
			signInModalPage=signInModalPage.clickSignUp();

			// Singup
			String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
			addLog("Sign up user");
			userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

			// Verify User Home Page
			addLog("Verify User Home Page");
			userHomePage=userHomePage.verifyUserPage();


			// Select Course
			String courseName = propertyReader.readTestData("BigData&Hadoop");
			addLog("Select Course" +courseName);
			selectedCoursePage=dashboardPage.selectCourse(courseName);

			// Verify Selected course should be open.
			addLog("Verify Selected course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

			// Click on Enroll Button
			addLog("Click on Enroll Button");
			orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

			//On click of Enroll when none of the offer is present


			// Verify Order Summary Page
			addLog("Verify Order Summary Page");
			orderSummaryPage=orderSummaryPage.verifyPage();

			// Change Currency
			String currency= propertyReader.readTestData("INRCurrency");
			addLog("Select currecnt" +currency +" from currnecy table");
			orderSummaryPage=orderSummaryPage.changeCurrency(currency);

			// Verify total Amount
			addLog("verify Total amount");
			orderSummaryPage=orderSummaryPage.verifyTotalAmountWithoutNetPrice(currency);

			//Verify data in Pre order table
			String course_Id= propertyReader.readTestData("Course_ID_BigData");
			String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");
			orderSummaryPage=orderSummaryPage.dataVerificationInUser_PreOrderTable(course_Id, course_Title, "",currency,"MakePaymentWhenB1S2OfferAndCourseOtherThanIwillChooseLater","pre_orders");

			//Click Course othen that I will choose later
			String courseOther=propertyReader.readTestData("Courses_Other");
			orderSummaryPage=orderSummaryPage.selectB1S2Course(courseOther);

			// On click of Pay securely(Razorpay) when none of the offer is present:

			// Click on Razor pay securely button
			addLog("Click on Razor pay securely button");
			razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();

			// Verify Razor page
			addLog("Verify Razor page");
			razorPayPage=razorPayPage.verifyPage();

			// Click on Net Banking tab
			addLog("Click on Net Banking tab");
			razorPayPage=razorPayPage.clickOnNetBankingTab();

			// Select bank
			String bankName = propertyReader.readTestData("Bank");
			addLog("Select Bank" +bankName +" from bank table ");
			razorPayPage=razorPayPage.selectBank(bankName);

			// Click on pay button
			addLog("Click on pay button");
			razorPayPage=razorPayPage.clickOnPayButton();

			// Get Parent window Id
			addLog("Get Parent Window ID");
			parentWidnow = getParentWindowHandle();

			// Switch Child Window and Click on Succss button
			addLog("Switch Child Window and Click on Succss button");
			switchPreviewWindow();
			myProfilePage=razorPayPage.clickOnSuccessButton();
			Thread.sleep(5000);
			// Switch to Parent Window and verify my Profile page
			addLog("Switch to Parent Window and verify my Profile page");
			switchParentWindow(parentWidnow);
			myProfilePage= myProfilePage.verifyPage();

			// Verify Payment success message
			addLog("Verify payment success message 'Thank you for making payment !'");
			myProfilePage= myProfilePage.verifySuccessPaymentMessage();

			// Verify Data in User table
			addLog("Verify Data in User table");
			myProfilePage=myProfilePage.dataVerificationInUserTable("3");

			// Verify Data in Pre-Order table
			addLog("Verify Data in Pre-Order table");

			String paymentGateway = propertyReader.readTestData("PaymentGatewayRazor");
			myProfilePage=myProfilePage.dataVerificationInUser_PreOrderTable(course_Id,course_Title,paymentGateway,currency,"MakePaymentWhenB1S2OfferAndCourseOtherThanIwillChooseLater","pre_orders_RazorPayment");

			// Verify Data in User Course Table
			String course_Group = propertyReader.readTestData("Course_Group_BigData");
			String isPaidValue= propertyReader.readTestData("PaidCourses_Is_Paid_Value");
			addLog("Verify Data in User Course Table");
			myProfilePage=myProfilePage.dataVerificationInUser_CoursedTable(course_Id, isPaidValue, course_Group);

			// Database verification in Post order table
			addLog("Database verification in Post order table");
			myProfilePage=myProfilePage.dataVerificationInUser_PostOrderTable(course_Id,course_Title,paymentGateway,currency);

			//Data Verification in event table for payment type Razorpay
			String eventType=propertyReader.readTestData("EventTypeRazorpay");
			myProfilePage=myProfilePage.dataVerificationInUser_Events_Table_PaymentType(eventType);

			//Verify the Url  on my profile page after payment
			myProfilePage=myProfilePage.verifyCourseIdInUrl(course_Id);

			// Click on Profile Dropdown
			userName = propertyReader.readRunTimeData("UserName");
			addLog("Click on Profile Dropdown");
			myProfilePage=myProfilePage.clickOnProfileDropdown(userName);            

			// Logout from the application
			addLog("Logout from the application.");
			dashboardPage = myProfilePage.logout();


			//On click of Pay securely(Paypal) when none of the offer is present


			// Click on Signin Navigation Header
			addLog("Click on SignIn Navigation header");
			signInModalPage = dashboardPage.clickSignInHeader();

			// Verify LogIn Is Default
			addLog("Verify LogIn Is Default");
			signInModalPage = signInModalPage.verifyLoginIsDefault();

			edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
			addLog("login with valid user id and password");
			userHomePage= signInModalPage.loginApp();

			// Verify User Home Page
			addLog("Verify User Home Page");
			userHomePage=userHomePage.verifyUserPage();

			// Select Course
			courseName = propertyReader.readTestData("ApacheSpark&Scala");
			course_Id=propertyReader.readTestData("Course_ID_ApacheSpark&Scala");
			course_Title=propertyReader.readTestData("ApacheSpark_Title");
			addLog("Select Course" +courseName);
			selectedCoursePage=dashboardPage.selectCourse(courseName);

			// Verify Selected course should be open.
			addLog("Verify Selected course should be open");
			selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

			//Get Date
			String date=selectedCoursePage.getDate(course_Id);
			String title=course_Title+"_"+date+"%";

			// Click on Enroll Button
			addLog("Click on Enroll Button");
			orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);
			
			//get Student count before payment
			addLog("get Student count before payment");
			orderSummaryPage.getStudentCount(title);
			
			// Verify Order Summary Page
			addLog("Verify Order Summary Page");
			orderSummaryPage=orderSummaryPage.verifyPage();

			currency= propertyReader.readTestData("USDCurrency");
			orderSummaryPage=orderSummaryPage.changeCurrency(currency);

			// Verify total Amount for USD is equal to Net price
			addLog("Verify total Amount for USD is equal to Net price");
			orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

			// Verify USD Currency Exclusive Of Service Tax
			addLog("Verify USD Currency Exclusive Of Service Tax");
			orderSummaryPage=orderSummaryPage.verifyUSDCurrency();

			//Select course other than I will choose later
			courseOther=propertyReader.readTestData("Courses_Free");
			addLog("Select Course other than I will choose later");
			orderSummaryPage=orderSummaryPage.selectB1S2Course(courseOther);

			// Click on Paypal link
			addLog("Click on Paypal link");
			payPalPage= orderSummaryPage.clickOnPaypalLink_New();

			// Verify Paypal Page
			addLog("Verify Paypal Page");
			payPalPage=payPalPage.verifyPage();

			// Enter Email, Password and Login paypal account
			addLog("Enter Email, Password and Login paypal account");
			payPalPage=payPalPage.enterEmailAndPassword();

			// Make payment by clicking on Continue button
			addLog("Make payment by clicking on Continue button");
			myProfilePage=payPalPage.clickOnContinueButton();

			// Switch to Parent Window and verify my Profile page
			addLog("Switch to Parent Window and verify my Profile page");
			myProfilePage= myProfilePage.verifyPage();

			// Verify Payment success message
			addLog("Verify payment success message 'Thank you for making payment !'");
			myProfilePage= myProfilePage.verifySuccessPaymentMessage();


			// Verify Data in User table
			addLog("Verify Data in User table");
			myProfilePage=myProfilePage.dataVerificationInUserTable("3");

			// Verify Data in Pre-Order table
			paymentGateway = propertyReader.readTestData("PaymentGatewayPaypal");
			course_Id= propertyReader.readTestData("Course_ID_ApacheSpark&Scala");
			course_Title= propertyReader.readTestData("ApacheSpark_Title");
			courseName=propertyReader.readTestData("ApacheSpark&Scala");
			currency= propertyReader.readTestData("USDCurrency");
			addLog("Verify Data in Pre-Order table");
			myProfilePage=myProfilePage.dataVerificationInUser_PreOrderTable(course_Id,course_Title,paymentGateway,currency,"MakePaymentWhenB1S2OfferAndCourseOtherThanIwillChooseLater","pre_orders_PapalPayment");

			// Verify Data in User Course Table
			course_Group = propertyReader.readTestData("Course_Group_ApacheSpark&Scala");
			isPaidValue= propertyReader.readTestData("PaidCourses_Is_Paid_Value");
			addLog("Verify Data in User Course Table");
			myProfilePage=myProfilePage.dataVerificationInUser_CoursedTable(course_Id, isPaidValue, course_Group);

			// Database verification in Post order table
			addLog("Database verification in Post order table");
			myProfilePage=myProfilePage.dataVerificationInUser_PostOrderTable(course_Id,course_Title,paymentGateway,currency);

			// Database verification in Post order table
			addLog("Database verification in Post order table on the basis of pre order table");
			myProfilePage=myProfilePage.dataVerificationIn_PostOrderOnTheBasisOfPreOrderTable(course_Id,course_Title,paymentGateway,currency);

			// Database verification in Post order table
			addLog("Database verification in customer records tables");
			myProfilePage=myProfilePage.dataVerificationIn_CustomerRecordsTable(course_Id,courseName,paymentGateway,currency);

			//Verify the Url  on my profile page after payment
			myProfilePage=myProfilePage.verifyCourseIdInUrl(course_Id);

			//Event Type
			eventType=propertyReader.readTestData("EventTypePaypal");
			addLog("Verifying event type"+eventType+" user_events table");
			myProfilePage=myProfilePage.dataVerificationInUser_Events_Table_PaymentType(eventType);

			// Click on Profile Dropdown
			userName = propertyReader.readRunTimeData("UserName");
			addLog("Click on Profile Dropdown");
			myProfilePage=myProfilePage.clickOnProfileDropdown(userName);            

			// Logout from the application
			addLog("Logout from the application.");
			dashboardPage = myProfilePage.logout(); 

			//Deactibvate B1SN Offer

			// Login Application through Admin credentials

			// Click on Signin Navigation Header
			addLog("Click on SignIn Navigation header");
			signInModalPage = dashboardPage.clickSignInHeader();

			// Verify Login is selected as default
			addLog("Verify Login is selected as default");
			signInModalPage = signInModalPage.verifyLoginIsDefault();
			email = propertyReader.readApplicationFile("Admin_UserName");
			password = propertyReader.readApplicationFile("Admin_Password");
			addLog("Login Application through Admin credentials");
			signInModalPage= signInModalPage.enterEmailAndPassword(email, password);

			// Click on Start Learning Course button
			addLog("Click on Start Learning Course button");
			userHomePage=signInModalPage.clickStartLearningButton(UserHomePage.class);

			// Verify Admin User Home page
			addLog("Verify Admin User Home page");
			userHomePage=userHomePage.verifyAdminUserPage();

			// Click on Profile dropdown
			userName = propertyReader.readTestData("Admin_UserName");
			addLog("Click on Profile dropdown");
			userHomePage= userHomePage.clickOnProfileDropdown(userName);

			// Select Admin from Prfile Dropdown
			addLog("Select Admin from Profile dropdown");
			adminDashboard=userHomePage.selectAdmin();

			// Verify Admin Dashboard
			addLog("Verify Admin Dashboard");
			adminDashboard= adminDashboard.verifyAdminDashboard();

			// Select Menu Tab
			menuTab = propertyReader.readTestData("Tab_Offer_Admin");
			addLog("Select Menu Tab " +menuTab);
			adminDashboard=adminDashboard.selectMenuTab(menuTab);               

			// Select View offer List from offer Admin
			String viewOfferList= propertyReader.readTestData("ViewOfferList");
			adminAddOfferPage=adminDashboard.selectOptionFromAdminOfferTab(viewOfferList);

			// Search Offer
			String offer_Id= propertyReader.readRunTimeData("B1S2OfferId");
			addLog("Search Offer by " +offer_Id);
			adminAddOfferPage=adminAddOfferPage.searchOffer(offer_Id);

			// Edit Offer 
			addLog("Edit Offer");
			adminAddOfferPage = adminAddOfferPage.editOffer();

			// Select Status
			status = propertyReader.readTestData("InactivateStatus");
			addLog("select "+status+" from status dropdown");
			adminAddOfferPage= adminAddOfferPage.selectStatus(status);

			// Clear browser cache
			String url = propertyReader.readTestData("BrowserCacheUrl");
			addLog("Clear Browser cache");
			adminAddOfferPage=adminAddOfferPage.clearOfferCache(url);
		}
		catch (final Error e) {
			captureScreenshot("test_021MakePaymentWhenB1S2OfferAndCourseOtherThanIwillChooseLater");
			throw e;
		} catch (final Exception e) {
			captureScreenshot("test_021MakePaymentWhenB1S2OfferAndCourseOtherThanIwillChooseLater");
			throw e;
		}
	} 
}