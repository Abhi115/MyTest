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

public class MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent extends DriverTestCase{
    private DashboardPage dashboardPage;
    private SignInModalPage signInModalPage;
    private UserHomePage userHomePage;
    private SelectedCoursePage selectedCoursePage;
    private OrderSummaryPage orderSummaryPage;
    private RazorPayPage razorPayPage;
    private PayPalPage payPalPage;
    private MyProfilePage myProfilePage;
    private AdminDashboard adminDashboard;
    private AdminAddOfferPage adminAddOfferPage;
    static String email;
    static String password;
    static String campaignSource;
    static String campaignName;
    static String campaignMedium;
    
    @Test
    public void test_018MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent() throws Exception {
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
            adminAddOfferPage=adminAddOfferPage.getOfferId();

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

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            String edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            String courseName = propertyReader.readTestData("Hadoop_Admin_Certification");
            addLog("Select Course " + courseName);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);


            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            String currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

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

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            Thread.sleep(5000);
            switchParentWindow(parentWidnow);
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Referral Tab
            myProfilePage=myProfilePage.clickOnReferralTab();
            Thread.sleep(5000);
            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();
            Thread.sleep(3000);
            // Enter Email
            String referralEmail= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail);
            addLog("Enter email "+referralEmail);
            myProfilePage=myProfilePage.referFriend(referralEmail);

            // Get Referral Link
            String referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Get page url
            String page_Url = myProfilePage.getUrl();
            addLog("Get Page url: "+page_Url);
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            Thread.sleep(5000);
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  
            Thread.sleep(5000);
            // Database verification for Referral Process
            email = propertyReader.readRunTimeData("Email_Id");
           
            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout(); 

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

            // Navigate to the Referral Link
            addLog("Navigate to the "+referralLink);
            dashboardPage=dashboardPage.changeUrl(referralLink);

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            
            signInModalPage=signInModalPage.clickSignUp();
            Thread.sleep(10000);
            //Sign up
            userName = propertyReader.readTestData("UserName");
            referralEmail = propertyReader.readRunTimeData("RefferralEmail");
            String phNumber = propertyReader.readTestData("PhoneNumber");
            String password = propertyReader.readTestData("Password");
            addLog("Sign up user B who is refferrad by A in TC_001");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,referralEmail,phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            // Click on Sign create Account button
            addLog("Click on Sign create Account button");
            userHomePage=signInModalPage.clickOnCreateAccountButton();

            //Verify Edureka logo at at Home page
            addLog("Verify Edureka logo at at All Courses page");
            userHomePage=userHomePage.verifyEdurekaLogoOnHomePage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " +courseName);
            Thread.sleep(5000);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();
            Thread.sleep(5000);
            // Verify Razor page
            addLog("Verify Razor page");
            razorPayPage=razorPayPage.verifyRazorForReferral();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            bankName = propertyReader.readTestData("Bank");
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
            Thread.sleep(5000);
            myProfilePage= myProfilePage.verifyPage();
            Thread.sleep(5000);
            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);
            Thread.sleep(5000);
            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();
            Thread.sleep(5000);
            // Click on Profile Dropdown
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  


            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // login user A
            addLog("Login user A");
            userHomePage= signInModalPage.loginApp();

            //Verify User Page
            addLog("Verify User Page");
            userHomePage = userHomePage.verifyUserPage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course" +courseName);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);
           
            // On click of Enroll when none of the offer is present

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();
            
            // Change Currency
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmountForCreditPointsCourse(currency);
            
            //
            String course_Id= propertyReader.readTestData("Course_ID_BigData");
            String course_Title= propertyReader.readTestData("BigData_Hadoop_Title");

            orderSummaryPage=orderSummaryPage.dataVerificationInUser_PreOrderTable(course_Id, course_Title, "",currency,"MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent","pre_orders");

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
            bankName = propertyReader.readTestData("Bank");
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

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            Thread.sleep(5000);
            switchParentWindow(parentWidnow);
            
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Verify Data in User table
            addLog("Verify Data in User table");
            myProfilePage=myProfilePage.dataVerificationInUserTable();

            // Verify Data in Pre-Order table
            addLog("Verify Data in Pre-Order table");
            
            String paymentGateway = propertyReader.readTestData("PaymentGatewayRazor");
            myProfilePage=myProfilePage.dataVerificationInUser_PreOrderTable(course_Id,course_Title,paymentGateway,currency,"MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent","pre_orders_RazorPayment");

            // Database verification in Post order table
            addLog("Database verification in Post order table");
            myProfilePage=myProfilePage.dataVerificationInUser_PostOrderTable(course_Id,course_Title,paymentGateway,currency);
            //Verify the Url  on my profile page after payment
            myProfilePage=myProfilePage.verifyCourseIdInUrl(course_Id);
            
            //Data Verification in event table for payment type Razorpay
            String eventType=propertyReader.readTestData("EventTypeRazorpay");;
            myProfilePage=myProfilePage.dataVerificationInUser_Events_Table_PaymentType(eventType);
            
            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);            
            
            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout(); 
           
            //Credit Points for second payment
            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

             edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            courseName = propertyReader.readTestData("Hadoop_Admin_Certification");
            addLog("Select Course " + courseName);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);


            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

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
            bankName = propertyReader.readTestData("Bank");
            addLog("Select Bank" +bankName +" from bank table ");
            razorPayPage=razorPayPage.selectBank(bankName);

            // Click on pay button
            addLog("Click on pay button");
            razorPayPage=razorPayPage.clickOnPayButton();
            Thread.sleep(5000);
            // Get Parent window Id
            addLog("Get Parent Window ID");
            parentWidnow = getParentWindowHandle();

            // Switch Child Window and Click on Succss button
            addLog("Switch Child Window and Click on Succss button");
            switchPreviewWindow();
            myProfilePage=razorPayPage.clickOnSuccessButton();

            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            Thread.sleep(5000);
            switchParentWindow(parentWidnow);
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Referral Tab
            myProfilePage=myProfilePage.clickOnReferralTab();
            Thread.sleep(5000);
            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();
            Thread.sleep(3000);
            // Enter Email
            String referralEmail2= randomString(3)+"@tech.edureka.in";
            propertyReader.updatePropertyTestData("RefferralEmail", referralEmail2);
            addLog("Enter email "+referralEmail2);
            myProfilePage=myProfilePage.referFriend(referralEmail2);
            Thread.sleep(5000);
            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);

            // Verify Success Message for sent refferral mail
            addLog("Verify Success Message for sent refferral mail");
            myProfilePage=myProfilePage.VerifySuccessRefferralMailSentMsg();

            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();

            // Get page url
            page_Url = myProfilePage.getUrl();
            addLog("Get Page url: "+page_Url);
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            Thread.sleep(5000);
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  
            Thread.sleep(5000);
            // Database verification for Referral Process
            email = propertyReader.readRunTimeData("Email_Id");
           
            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout(); 

            // Verify Dashboard
            addLog("Verify Dashboard");
            dashboardPage=dashboardPage.verifyDashboard();

            // Navigate to the Referral Link
            addLog("Navigate to the "+referralLink);
            dashboardPage=dashboardPage.changeUrl(referralLink);

            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            
            signInModalPage=signInModalPage.clickSignUp();
            Thread.sleep(10000);
           
            //Sign up
            userName = propertyReader.readTestData("UserName");
            referralEmail = propertyReader.readRunTimeData("RefferralEmail");
            phNumber = propertyReader.readTestData("PhoneNumber");
            password = propertyReader.readTestData("Password");
            addLog("Sign up user D who is refferrad by C");
            signInModalPage=signInModalPage.enterUserNameEmailAndPhoneNumnber(userName,referralEmail,phNumber);
            signInModalPage=signInModalPage.enterPassword(password);

            // Click on Sign create Account button
            addLog("Click on Sign create Account button");
            userHomePage=signInModalPage.clickOnCreateAccountButton();

            //Verify Edureka logo at at Home page
            addLog("Verify Edureka logo at at All Courses page");
            userHomePage=userHomePage.verifyEdurekaLogoOnHomePage();

            // Select Course
            courseName = propertyReader.readTestData("BigData&Hadoop");
            addLog("Select Course " +courseName);
            Thread.sleep(5000);
            selectedCoursePage=userHomePage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("verify Total amount");
            orderSummaryPage=orderSummaryPage.verifyTotalAmount(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();
            Thread.sleep(5000);
            // Verify Razor page
            addLog("Verify Razor page");
            razorPayPage=razorPayPage.verifyRazorForReferral();

            // Click on Net Banking tab
            addLog("Click on Net Banking tab");
            razorPayPage=razorPayPage.clickOnNetBankingTab();

            // Select bank
            bankName = propertyReader.readTestData("Bank");
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
            Thread.sleep(5000);
            myProfilePage= myProfilePage.verifyPage();
            Thread.sleep(5000);
            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();

            // Click on Referral Tab
            addLog("Click on Referral Tab");
            myProfilePage=myProfilePage.clickOnReferralTab();

            // Click on Refer Friend button
            addLog("Click on Refer Friend button");
            myProfilePage= myProfilePage.clickOnReferButton();

            // Get Referral Link
            referralLink = myProfilePage.copyReferralLink();
            addLog("Copy Referral Link "+ referralLink);
            Thread.sleep(5000);
            // Close Refer pop up
            addLog("Close Refer pop up");
            myProfilePage=myProfilePage.closeReferPopUp();
            Thread.sleep(5000);
            // Click on Profile Dropdown
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);  

            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout();  

            // On click of Pay securely(Paypal) when none of the offer is present
                       
            // Click on Signin Navigation Header
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // login with valid user name and password
            addLog("login with valid user name and password");
            userHomePage=signInModalPage.loginApp();

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
            courseName = propertyReader.readTestData("ApacheSpark&Scala");
            addLog("Select Course" +courseName);
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);
            
            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            currency= propertyReader.readTestData("USDCurrency");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Verify total Amount for USD is equal to Net price
            addLog("Verify total Amount for USD is equal to Net price");
            orderSummaryPage=orderSummaryPage.verifyTotalAmountForCreditPointsCourse(currency);

            // Verify USD Currency Exclusive Of Service Tax
            addLog("Verify USD Currency Exclusive Of Service Tax");
            orderSummaryPage=orderSummaryPage.verifyUSDCurrency();

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
            Thread.sleep(5000);
            // Switch to Parent Window and verify my Profile page
            addLog("Switch to Parent Window and verify my Profile page");
            myProfilePage= myProfilePage.verifyPage();

            // Verify Payment success message
            addLog("Verify payment success message 'Thank you for making payment !'");
            myProfilePage= myProfilePage.verifySuccessPaymentMessage();


            // Verify Data in User table
            addLog("Verify Data in User table");
            myProfilePage=myProfilePage.dataVerificationInUserTable();

            // Verify Data in Pre-Order table
            paymentGateway = propertyReader.readTestData("PaymentGatewayPaypal");
            course_Id= propertyReader.readTestData("Course_ID_ApacheSpark&Scala");
            course_Title= propertyReader.readTestData("ApacheSpark_Title");
            
            addLog("Verify Data in Pre-Order table");
            myProfilePage=myProfilePage.dataVerificationInUser_PreOrderTable(course_Id,course_Title,paymentGateway,currency,"MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent","pre_orders_PapalPayment");

            // Database verification in Post order table
            addLog("Database verification in Post order table");
            myProfilePage=myProfilePage.dataVerificationInUser_PostOrderTable(course_Id,course_Title,paymentGateway,currency);
           
            //Data Verification in event table for payment type Razorpay
            eventType=propertyReader.readTestData("EventTypePaypal");
            myProfilePage=myProfilePage.dataVerificationInUser_Events_Table_PaymentType(eventType);
          
           
             // On click of Pay securely(CC avenue) when any type of discount with B1S2 is present:
             
           
            // Click on Profile Dropdown
            userName = propertyReader.readRunTimeData("UserName");
            addLog("Click on Profile Dropdown");
            myProfilePage=myProfilePage.clickOnProfileDropdown(userName);            
            
            // Logout from the application
            addLog("Logout from the application.");
            dashboardPage = myProfilePage.logout(); 
            
            addLog("Click on SignIn Navigation header");
            signInModalPage = dashboardPage.clickSignInHeader();

            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
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
            menuTab = propertyReader.readTestData("Tab_Sales");
            addLog("Select Menu Tab " +menuTab);
            adminDashboard=adminDashboard.selectMenuTab(menuTab);
            
            // Select Menu Tab
            String subMenuTab = propertyReader.readTestData("SubMenu_ChangeGateway");
            addLog("Select Menu Tab " +subMenuTab);
            adminDashboard=adminDashboard.selectSubMenu(subMenuTab);
           
            //Change Payment Gateway
            String gateway=propertyReader.readTestData("Gateway_CCAvenue");
            adminAddOfferPage=adminDashboard.changePaymentGateway(gateway);
            
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

 
            // Verify LogIn Is Default
            addLog("Verify LogIn Is Default");
            signInModalPage = signInModalPage.verifyLoginIsDefault();

            // click on Sign up link
            addLog("click on Sign up link");
            signInModalPage=signInModalPage.clickSignUp();

            edurekaDomain = propertyReader.readApplicationFile("EdurekaDomain");
            addLog("Sign up user");
            userHomePage= signInModalPage.signUp(UserHomePage.class, edurekaDomain);

            // Verify User Home Page
            addLog("Verify User Home Page");
            userHomePage=userHomePage.verifyUserPage();

            // Select Course
             courseName = propertyReader.readTestData("Course_DevOps_Certification");
            addLog("Select Course" +courseName);
            selectedCoursePage=dashboardPage.selectCourse(courseName);

            // Verify Selected course should be open.
            addLog("Verify Selected course should be open");
            selectedCoursePage=selectedCoursePage.verifySelectedPopularCoursePage(courseName);

            // Click on Enroll Button
            addLog("Click on Enroll Button");
            orderSummaryPage=selectedCoursePage.clickOnEnrollButton(OrderSummaryPage.class);

            // Verify Order Summary Page
            addLog("Verify Order Summary Page");
            orderSummaryPage=orderSummaryPage.verifyPage();

            // Change Currency
            currency= propertyReader.readTestData("INRCurrency");
            addLog("Select currecnt" +currency +" from currnecy table");
            orderSummaryPage=orderSummaryPage.changeCurrency(currency);

            // Click on Razor pay securely button
            addLog("Click on Razor pay securely button");
            razorPayPage= orderSummaryPage.clickOnRazorPaySecurelyButton();
           
            //Verify elements on CC Avenue page
            razorPayPage=razorPayPage.verifyCCAvenuePage();
            
            //Navigate to url
            String url=propertyReader.readApplicationFile("Edureka-URL");
            userHomePage=razorPayPage.navigateToUrl(url);
            
            
            // Click on Profile dropdown
            addLog("Click on Profile dropdown");
            userName=propertyReader.readRunTimeData("UserName");
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
            menuTab = propertyReader.readTestData("Tab_Sales");
            addLog("Select Menu Tab " +menuTab);
            adminDashboard=adminDashboard.selectMenuTab(menuTab);
            
            // Select Menu Tab
            subMenuTab = propertyReader.readTestData("SubMenu_ChangeGateway");
            addLog("Select Menu Tab " +subMenuTab);
            adminDashboard=adminDashboard.selectSubMenu(subMenuTab);
           
            //Change Payment Gateway
            gateway=propertyReader.readTestData("Gateway_Razorpay");
            adminAddOfferPage=adminDashboard.changePaymentGateway(gateway);
            
        	//Deactibvate Offer

			// Select Menu Tab
			menuTab = propertyReader.readTestData("Tab_Offer_Admin");
			addLog("Select Menu Tab " +menuTab);
			adminDashboard=adminDashboard.selectMenuTab(menuTab);

			// Select View offer List from offer Admin
			String viewOfferList= propertyReader.readTestData("ViewOfferList");
			addLog("Select View offer List from offer Admin");
			adminAddOfferPage=adminDashboard.selectOptionFromAdminOfferTab1(viewOfferList);

			// Search Offer
			String offer_Id= propertyReader.readRunTimeData("Offer_Id");
			addLog("Search Offer by " +offer_Id);
			adminAddOfferPage=adminAddOfferPage.searchOffer(offer_Id);

			// Edit Offer 
			addLog("Edit Offer");
			adminAddOfferPage = adminAddOfferPage.editOffer();

			// Select Status
			status = propertyReader.readTestData("InactivateStatus");
			addLog("select "+status+" from status dropdown");
			adminAddOfferPage= adminAddOfferPage.selectStatus(status);

			// Select Menu Tab
			addLog("Select Menu Tab " +menuTab);
			adminAddOfferPage=adminAddOfferPage.selectMenuTab(menuTab);

			// Select View offer List from offer Admin
			addLog("Select View offer List from offer Admin");
			adminAddOfferPage=adminAddOfferPage.selectOptionFromAdminOfferTab(viewOfferList);

			userHomePage=adminAddOfferPage.clickOnLogo();

			// Click on Profile dropdown
			addLog("Click on Profile dropdown");
			userHomePage= userHomePage.clickOnProfileDropdown(userName);

			// Logout Application
			addLog("Logout Application");
			dashboardPage=userHomePage.logout();
       
        }

        catch (final Error e) {
            captureScreenshot("test_018MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent");
            throw e;
        } catch (final Exception e) {
            captureScreenshot("test_018MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent");
            throw e;
        }
    }
}