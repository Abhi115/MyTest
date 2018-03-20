package com.edureka.pages;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.edureka.util.Database;
import com.edureka.util.DriverHelper;
import com.edureka.util.SpreadsheetOperations;
import com.edureka.util.Timer;

public class DatabaseVerifications extends DriverHelper {
    String emailId = propertyReader.readRunTimeData("Email_Id");
    String phone = propertyReader.readTestData("PhoneNumber");
    String userName = propertyReader.readRunTimeData("UserName");

    public DatabaseVerifications(WebDriver driver) {
        super(driver);
    }

    /**
     * Verify
     * 
     * @param className
     * @param course_Id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T currencyVerificationInDatabase_For_OfferFramework(@SuppressWarnings("rawtypes") Class className) throws Exception {
        Database dbObject = new Database();

        String offer_Id = propertyReader.readRunTimeData("Offer_Id");
        ArrayList<String> currency = dbObject.getRecords("offer_discounts", "currency", "offer_id", offer_Id);
        ArrayList<String> currencyList = new ArrayList<>();
        currencyList.add("currency=0");
        currencyList.add("currency=1");
        currencyList.add("currency=2");
        currencyList.add("currency=3");
        currencyList.add("currency=4");
        currencyList.add("currency=5");
        currencyList.add("currency=6");
        Assert.assertTrue(currency.equals(currencyList));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Verify Data in
     * 
     * @param className
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerification_For_ClassRecordingVideo(@SuppressWarnings("rawtypes") Class className, String course_Id) throws Exception {
        Database dbObject = new Database();

        String user_ID = propertyReader.readRunTimeData("User_ID");
        String last_Module_Watched = dbObject.getRecord("user_courses", "last_module_watched", "user_id", user_ID);
        Assert.assertTrue(!(last_Module_Watched.equals(null)));

        String userId = dbObject.getRecord("post_orders", "courseid", "user_id", user_ID);
        Assert.assertTrue(userId.contains(user_ID));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Data Verifyfication for LMS
     * 
     * @param className
     * @param batchID
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerification_For_LMS(@SuppressWarnings("rawtypes") Class className) throws Exception {
        Database dbObject = new Database();

        String user_ID = propertyReader.readRunTimeData("User_ID");
        String batch_Id = dbObject.getRecord("user_courses", "batch_id", "user_id", user_ID);
        Assert.assertTrue(!(batch_Id.equals(null)));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Database verification for offer framework in offers Tables
     * 
     * @param className
     * @param course_Id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerification_For_OfferFramework(@SuppressWarnings("rawtypes") Class className, String discount_Description) throws Exception {
        Database dbObject = new Database();

        String offer_Id = propertyReader.readRunTimeData("Offer_Id");
        String offer_StartDate = dbObject.getRecord("offers", "start_date", "id", offer_Id);
        Assert.assertTrue(offer_StartDate.contains(presenttDate()));

        String offer_EndDate = dbObject.getRecord("offers", "end_date", "id", offer_Id);
        Assert.assertTrue(offer_EndDate.contains(nexDayDate()));

        String description = dbObject.getRecord("offers", "description", "id", offer_Id);
        Assert.assertTrue(description.contains(discount_Description));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Data verification for Inquiry
     * 
     * @param className
     * @param courseId
     * @param webSiteAction
     * @param event_context
     * @param utm_campaign
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationFor_Inquiry(@SuppressWarnings("rawtypes") Class className, String courseId, String webSiteAction, String event_context, String utm_campaign,String courseGroup,String eventType,String level_id) throws Exception {

        Database dbObject = new Database();

        String inquiry_Email = propertyReader.readRunTimeData("QueryEmail_Id");
        String user_Name = propertyReader.readRunTimeData("QueryUserName");

        String user_Id = dbObject.getRecord("user_leads", "user_id", "email", inquiry_Email);

        String course_Id = dbObject.getRecord("user_courses", "course_id", "user_id", user_Id);
        Assert.assertTrue(course_Id.contains(courseId));

        String isPaid = dbObject.getRecord("user_courses", "is_paid", "user_id", user_Id);
        Assert.assertTrue(isPaid.contains(isPaid));

        String course_Group = dbObject.getRecord("user_courses", "course_group", "user_id", user_Id);
        Assert.assertTrue(course_Group.contains(courseGroup));

        String startedFrom = dbObject.getRecord("user_courses", "started_from", "user_id", user_Id);
        Assert.assertTrue(startedFrom.contains(presenttDate()));

        String endOn = dbObject.getRecord("user_courses", "ends_on", "user_id", user_Id);
        Assert.assertTrue(endOn.contains(endDate()));


        String phoneNumber = dbObject.getRecord("users", "mobile", "email", inquiry_Email);
        Assert.assertTrue(phoneNumber.contains(phone));

        String email = dbObject.getRecord("users", "email", "first_name", user_Name);
        Assert.assertTrue(email.contains(inquiry_Email));

        String status = dbObject.getRecord("users", "status", "email", inquiry_Email);
        Assert.assertTrue(status.contains("1"));

        String password = dbObject.getRecord("users", "password", "email", inquiry_Email);
        Assert.assertTrue(!(password.equals(null)));

        String zohoLead_Id = dbObject.getRecord("user_leads", "zoholead_id", "email", inquiry_Email);
        Assert.assertTrue(!(zohoLead_Id.equals(null)));

        user_Id = dbObject.getRecord("user_leads", "user_id", "email", inquiry_Email);
        Assert.assertTrue(!(user_Id.equals(null)));

        //course_Id = dbObject.getRecord("user_leads", "course_id", new String[]{"email", "user_id"}, new String[]{inquiry_Email,user_Id});
        Assert.assertTrue(course_Id.contains(courseId));

        String website_action = dbObject.getRecord("user_leads", "website_action", "email", inquiry_Email);
        Assert.assertTrue(website_action.contains(webSiteAction));

        String event_Context = dbObject.getRecord("user_events", "event_context", "user_id", user_Id);
        Assert.assertTrue(event_Context.contains(event_context));

        String utm_Campaign = dbObject.getRecord("user_events", "utm_campaign", "user_id", user_Id);
        Assert.assertTrue(utm_Campaign.contains(utm_campaign));

        //Verify in User Event Table
        course_Id = dbObject.getRecord("user_events", "course_id", "user_id", user_Id);
        Assert.assertTrue(course_Id.contains(courseId));

        event_Context = dbObject.getRecord("user_events", "event_context", "user_id", user_Id);
        Assert.assertTrue(event_Context.contains(event_context));

        utm_Campaign = dbObject.getRecord("user_events", "utm_campaign", "user_id", user_Id);
        Assert.assertTrue(utm_Campaign.contains(utm_campaign));

        String event_Type = dbObject.getRecord("user_events", "event_type", "user_id", user_Id);
        Assert.assertTrue(event_Type.contains(eventType));

        //Verify data in Ambassdor table
        String level_Id = dbObject.getRecord("ambassadors", "level_id", "user_id", user_Id);
        Assert.assertTrue(level_Id.contains(level_id));

        String email_Url = dbObject.getRecord("ambassadors", "email_url", "user_id", user_Id);
        Assert.assertTrue(!(email_Url.equals(null)));

        String facebook_Url = dbObject.getRecord("ambassadors", "facebook_url", "user_id", user_Id);
        Assert.assertTrue(!(facebook_Url.equals(null)));

        String linkdln_Url = dbObject.getRecord("ambassadors", "linkedin_url", "user_id", user_Id);
        Assert.assertTrue(!(linkdln_Url.equals(null)));

        String twitter_url = dbObject.getRecord("ambassadors", "twitter_url", "user_id", user_Id);
        Assert.assertTrue(!(twitter_url.equals(null)));

        String mobile_url = dbObject.getRecord("ambassadors", "mobile_url", "user_id", user_Id);
        Assert.assertTrue(!(mobile_url.equals(null)));

        //Verify data in Completed_Queue_Jobs Table
        String courseStatus = propertyReader.readTestData("Status");
        String courseProperty =propertyReader.readTestData("Priority");
        status = dbObject.getRecord("completed_queue_jobs", "status", "id", user_Id);
        Assert.assertTrue(status.contains(courseStatus));

        String priority = dbObject.getRecord("completed_queue_jobs", "priority", "id", user_Id);
        Assert.assertTrue(priority.contains(courseProperty));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }
    /**
     * Database verification for Referral Prcoess
     * 
     * @param className
     * @param fName
     * @param referralLink
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationFor_ReferredUser(@SuppressWarnings("rawtypes") Class className, String email, String fName, String referralLink) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("Referral_User_ID", user_Id);

        String firstName = dbObject.getRecord("users ambassador_activities", "first_name", "email", email);
        Assert.assertTrue(firstName.equalsIgnoreCase(fName));

        String facebook_Url = dbObject.getRecord("ambassadors", "facebook_url", "user_id", user_Id);
        Assert.assertTrue(!(facebook_Url.equals(null)));

        String linkdln_Url = dbObject.getRecord("ambassadors", "linkedin_url", "user_id", user_Id);
        Assert.assertTrue(!(linkdln_Url.equals(null)));

        String twitter_url = dbObject.getRecord("ambassadors", "twitter_url", "user_id", user_Id);
        Assert.assertTrue(!(twitter_url.equals(null)));

        String mobile_url = dbObject.getRecord("ambassadors", "mobile_url", "user_id", user_Id);
        Assert.assertTrue(!(mobile_url.equals(null)));

        String referLink = dbObject.getRecord("ambassadors", "email_url", "user_id", user_Id);
        Assert.assertTrue(referLink.contains(referralLink));

        String ambassadorDate = dbObject.getRecord("ambassadors", "ambassador_date", "user_id", user_Id);
        Assert.assertTrue(ambassadorDate.contains(presenttDate()));

        String master_url = dbObject.getRecord("ambassadors", "master_url", "user_id", user_Id);
        Assert.assertTrue(!(master_url.equals(null)));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    @SuppressWarnings("unchecked")
    public <T> T dataVerificationForNotification(@SuppressWarnings("rawtypes") Class className, String courseId, String webSiteAction) throws Exception {

        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("user_leads", "user_id", "email", emailId);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String notification_Id = dbObject.getRecord("ne_users_notifications", "notification_id", "user_id", user_Id);
        Assert.assertTrue((notification_Id.equals(null)));

        String email = dbObject.getRecord("user_leads", "email", "first_name", userName);
        Assert.assertTrue(email.contains(emailId));

        String website_action = dbObject.getRecord("user_leads", "website_action", "email", emailId);
        Assert.assertTrue(website_action.contains(webSiteAction));

        String created = dbObject.getRecord("user_leads", "created", "email", emailId);
        Assert.assertTrue(created.contains(presenttDate()));

        String modified = dbObject.getRecord("user_leads", "modified", "email", emailId);
        Assert.assertTrue(modified.contains(presenttDate()));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Database verification for Referral Process
     * @param className
     * @param email
     * @param fName
     * @param referralLink
     * @param levelId
     * @param welcomemail
     * @param ambassador_Campaign_Id
     * @param ambassador_Level_Id
     * @param source
     * @param page_Url
     * @param campaign_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationForReferralProcess(@SuppressWarnings("rawtypes") Class className, String email,String referralLink, String levelId, String welcomemail, String ambassador_Campaign_Id, String ambassador_Level_Id, String source, String page_Url, String campaign_id) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("Referral_User_ID", user_Id);

        String facebook_Url = dbObject.getRecord("ambassadors", "facebook_url", "user_id", user_Id);
        Assert.assertTrue(!(facebook_Url.equals(null)));

        String level_Id = dbObject.getRecord("ambassadors", "level_id", "user_id", user_Id);
        Assert.assertTrue(level_Id.contains(levelId));

        String linkdln_Url = dbObject.getRecord("ambassadors", "linkedin_url", "user_id", user_Id);
        Assert.assertTrue(!(linkdln_Url.equals(null)));

        String twitter_url = dbObject.getRecord("ambassadors", "twitter_url", "user_id", user_Id);
        Assert.assertTrue(!(twitter_url.equals(null)));

        String mobile_url = dbObject.getRecord("ambassadors", "mobile_url", "user_id", user_Id);
        Assert.assertTrue(!(mobile_url.equals(null)));

        String referLink = dbObject.getRecord("ambassadors", "email_url", "user_id", user_Id);
        Assert.assertTrue(referLink.contains(referralLink));

        String ambassadorDate = dbObject.getRecord("ambassadors", "ambassador_date", "user_id", user_Id);
        Assert.assertTrue(ambassadorDate.contains(presenttDate()));

        String welcome_Email = dbObject.getRecord("ambassadors", "welcomemail", "user_id", user_Id);
        Assert.assertTrue( welcome_Email.contains(welcomemail));

        String ambassador_Campaign = dbObject.getRecord("ambassadors", "ambassador_campaign_id", "user_id", user_Id);
        Assert.assertTrue( ambassador_Campaign.trim().contains(ambassador_Campaign_Id));

        String ambassadorLevelId = dbObject.getRecord("ambassadors", "ambassador_level_id", "user_id", user_Id);
        Assert.assertTrue(ambassadorLevelId.trim().contains(ambassador_Level_Id));

        String activity = dbObject.getRecord("ambassador_activities", "activity_type", "user_id", user_Id);
        Assert.assertTrue(activity.contains(source));

        String referredDate = dbObject.getRecord("ambassador_activities", "created", "user_id", user_Id);
        Assert.assertTrue(referredDate.contains(presenttDate()));

        String pageUrl = dbObject.getRecord("ambassador_activities", "page_url", "user_id", user_Id);
        Assert.assertTrue(pageUrl.contains(page_Url));

        String levelID = dbObject.getRecord("ambassador_activities", "id", "user_id", user_Id);
        Assert.assertTrue(!(levelID.equals(null)));

        String campaign_ID = dbObject.getRecord("ambassador_activities", "campaign_id", "user_id", user_Id);
        Assert.assertTrue(campaign_ID.contains(campaign_id));

        String master_url = dbObject.getRecord("ambassadors", "master_url", "user_id", user_Id);
        Assert.assertTrue(!(master_url.equals(null)));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Verify Data in Ambassadors table
     * 
     * @param className
     * @param level_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInAmbassadors(@SuppressWarnings("rawtypes") Class className, String level_id) throws Exception {
        Database dbObject = new Database();

        String user_ID = propertyReader.readRunTimeData("User_ID");

        String level_Id = dbObject.getRecord("ambassadors", "level_id", "user_id", user_ID);
        Assert.assertTrue(level_Id.contains(level_id));

        String email_Url = dbObject.getRecord("ambassadors", "email_url", "user_id", user_ID);
        Assert.assertTrue(!(email_Url.equals(null)));

        String facebook_Url = dbObject.getRecord("ambassadors", "facebook_url", "user_id", user_ID);
        Assert.assertTrue(!(facebook_Url.equals(null)));

        String linkdln_Url = dbObject.getRecord("ambassadors", "linkedin_url", "user_id", user_ID);
        Assert.assertTrue(!(linkdln_Url.equals(null)));

        String twitter_url = dbObject.getRecord("ambassadors", "twitter_url", "user_id", user_ID);
        Assert.assertTrue(!(twitter_url.equals(null)));

        String mobile_url = dbObject.getRecord("ambassadors", "mobile_url", "user_id", user_ID);
        Assert.assertTrue(!(mobile_url.equals(null)));

        String welcomemail = dbObject.getRecord("ambassadors", "welcomemail", "user_id", user_ID);
        Assert.assertTrue((welcomemail.contains("0")));

        String ambassador_date = dbObject.getRecord("ambassadors", "ambassador_date", "user_id", user_ID);
        Assert.assertTrue((ambassador_date.isEmpty()));

        String ambassador_levelId = dbObject.getRecord("ambassadors", "ambassador_level_id", "user_id", user_ID);
        Assert.assertTrue((ambassador_levelId.contains("0")));

        String ambassador_campaign_id = dbObject.getRecord("ambassadors", "ambassador_campaign_id", "user_id", user_ID);
        Assert.assertTrue((ambassador_campaign_id.contains("0")));

        String active = dbObject.getRecord("ambassadors", "active", "user_id", user_ID);
        Assert.assertTrue((active.contains("1")));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Database verification in Post Order table
     * 
     * @param className
     * @param course_id
     * @param batchTitle
     * @param paymentGatway
     * @param currency
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInPostOrderTable(@SuppressWarnings("rawtypes") Class className, String course_id, String batchTitle, String paymentGatway, String currency) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("user_leads", "user_id",  new String[]{"email"},  new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String course_Id = dbObject.getRecord("post_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_Id.contains(course_id));

        String order_Id = dbObject.getRecord("post_orders", "orderid", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
        Assert.assertTrue(!(order_Id.equals(null)));

        String batch_Id = dbObject.getRecord("post_orders", "batchid", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
        Assert.assertTrue(!(batch_Id.equals(null)));

        String batch_Title = dbObject.getRecord("post_orders", "batchtitle", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
        Assert.assertTrue(batch_Title.contains(batchTitle));

        String price = propertyReader.readRunTimeData("PriceValue");

        if (currency.equals("USD")) {
            String price_USD = dbObject.getRecord("post_orders", "priceusd", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
            Assert.assertTrue(price_USD.trim().contains(price_USD.trim()));

            String serviceTax_INR = dbObject.getRecord("post_orders", "servicetaxusd", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
            Assert.assertTrue(serviceTax_INR.contains("0"));

            String currency_Id = dbObject.getRecord("post_orders", "currency_id", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
            Assert.assertTrue(currency_Id.contains("0"));
        } else if (currency.equals("INR")) {
            String price_INR = dbObject.getRecord("pre_orders", "priceinr", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
            Assert.assertTrue(price_INR.trim().contains(price.trim()));

            String serviceTax = propertyReader.readRunTimeData("ServiceTax");
            String serviceTax_INR = dbObject.getRecord("pre_orders", "servicetaxinr", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
            Assert.assertTrue(serviceTax_INR.contains(serviceTax));

            String currency_Id = dbObject.getRecord("pre_orders", "currency_id", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
            Assert.assertTrue(currency_Id.contains("1"));

        }
        String paymentGateway = dbObject.getRecord("post_orders", "gateway", new String[]{"courseid", "userid"}, new String[]{course_id,user_Id});
        Assert.assertTrue(paymentGateway.contains(paymentGatway));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }


    /**
     * Data verification in Pre_Order Table for INR currency
     * 
     * @param className
     * @param course_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInPreOrderTableForINR(@SuppressWarnings("rawtypes") Class className, String course_id, String batchTitle) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("user_leads", "user_id", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String course_Id = dbObject.getRecord("pre_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_Id.contains(course_id));

        String order_Id = dbObject.getRecord("pre_orders", "orderid", "userid", user_Id);
        Assert.assertTrue(!(order_Id.equals(null)));

        String batch_Id = dbObject.getRecord("pre_orders", "batchid", "userid", user_Id);
        Assert.assertTrue(!(batch_Id.equals(null)));

        String batch_Title = dbObject.getRecord("pre_orders", "batchtitle", "userid", user_Id);
        Assert.assertTrue(batch_Title.contains(batchTitle));


        String price = propertyReader.readRunTimeData("PriceValue"); 
        String price_INR = dbObject.getRecord("pre_orders", "priceinr", "userid",user_Id); 
        Assert.assertTrue(price_INR.contains(price));


        String serviceTax = propertyReader.readRunTimeData("ServiceTax");
        String serviceTax_INR = dbObject.getRecord("pre_orders", "servicetaxinr", "userid", user_Id);
        Assert.assertTrue(serviceTax_INR.contains(serviceTax));

        String currency_Id = dbObject.getRecord("pre_orders", "currency_id", "userid", user_Id);
        Assert.assertTrue(currency_Id.contains("1"));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Data verification in Pre-order table for USD
     * 
     * @param className
     * @param course_id
     * @param batchTitle
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInPreOrderTableForUSD(@SuppressWarnings("rawtypes") Class className, String course_id, String batchTitle) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("user_leads", "user_id", "email", emailId);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String course_Id = dbObject.getRecord("pre_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_Id.contains(course_id));

        String order_Id = dbObject.getRecord("pre_orders", "orderid", "userid", user_Id);
        Assert.assertTrue(!(order_Id.equals(null)));

        String batch_Id = dbObject.getRecord("pre_orders", "batchid", "userid", user_Id);
        Assert.assertTrue(!(batch_Id.equals(null)));

        String batch_Title = dbObject.getRecord("pre_orders", "batchtitle", "userid", user_Id);
        Assert.assertTrue(batch_Title.contains(batchTitle));

        /*
         * String price = propertyReader.readRunTimeData("PriceValue"); String
         * price_USD = dbObject.getRecord("pre_orders", "priceus", "userid",
         * user_Id); Assert.assertTrue(price.contains(price_USD));
         */

        String serviceTax_INR = dbObject.getRecord("pre_orders", "servicetaxusd", "userid", user_Id);
        Assert.assertTrue(serviceTax_INR.contains("0"));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * verify Data in User_Course_Table
     * 
     * @param className
     * @param courseId
     * @param isPaidValue
     * @param courseGroup
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInUser_Course_Table(@SuppressWarnings("rawtypes") Class className, String courseId, String isPaidValue, String courseGroup) throws Exception {

        Database dbObject = new Database();

        String user_ID = propertyReader.readRunTimeData("User_ID");

        String course_Id = dbObject.getRecord("user_courses", "course_id", "user_id", user_ID);
        Assert.assertTrue(course_Id.contains(courseId));

        String isPaid = dbObject.getRecord("user_courses", "is_paid", new String[]{"course_id", "user_id"}, new String[]{courseId,user_ID});
        System.out.println("isPaid "+isPaid);
        Assert.assertTrue(isPaid.contains(isPaid));
        Assert.assertTrue(isPaid.contains(isPaidValue));

        String course_Group = dbObject.getRecord("user_courses", "course_group", new String[]{"course_id", "user_id"}, new String[]{courseId,user_ID});
        System.out.println("course_Group :"+course_Group);
        System.out.println("courseGroup :"+courseGroup);
        Assert.assertTrue(course_Group.contains(courseGroup));

        
        String startedFrom = dbObject.getRecord("user_courses", "started_from", new String[]{"course_id", "user_id"}, new String[]{courseId,user_ID});
        
        System.out.println("startedFrom "+startedFrom +" UI "+ presenttDate());
        Assert.assertTrue(startedFrom.contains(presenttDate()));

        String endOn = dbObject.getRecord("user_courses", "ends_on", new String[]{"course_id", "user_id"}, new String[]{courseId,user_ID});
        Assert.assertTrue(endOn.contains(endDate()));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Verify Data in User Events table
     * 
     * @param className
     * @param courseId
     * @param event_context
     * @param utm_source
     * @param utm_campaign
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInUser_Events_Table(@SuppressWarnings("rawtypes") Class className, String courseId, String event_context, String utm_campaign, String event) throws Exception {

        Database dbObject = new Database();

        String user_ID = propertyReader.readRunTimeData("User_ID");

        String course_Id = dbObject.getRecord("user_events", "course_id", "user_id", user_ID);
        Assert.assertTrue(course_Id.contains(courseId));

        String event_Context = dbObject.getRecord("user_events", "event_context", new String[]{"user_id", "course_id"}, new String[]{user_ID,courseId});
        Assert.assertTrue(event_Context.contains(event_context));

        String utm_Campaign = dbObject.getRecord("user_events", "utm_campaign", new String[]{"course_id", "user_id"}, new String[]{courseId,user_ID});
        Assert.assertTrue(utm_Campaign.contains(utm_campaign));

        String event_Type = dbObject.getRecord("user_events", "event_type", new String[]{"course_id", "user_id"}, new String[]{courseId,user_ID});
        Assert.assertTrue(event_Type.contains(event));
        
        String course_group = dbObject.getRecord("user_events", "course_group", new String[]{"course_id", "user_id"}, new String[]{courseId,user_ID});
        System.out.println("course_group "+course_group);
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Verify Data in user leads table
     * 
     * @param className
     * @param courseId
     * @param webSiteAction
     * @param country
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInUserLeadTable(@SuppressWarnings("rawtypes") Class className, String courseId, String webSiteAction, String country, String campaignSource, String campaignName, String campaignMedium) throws Exception {

        Database dbObject = new Database();

        String zohoLead_Id = dbObject.getRecord("user_leads", "zoholead_id", "email", emailId);
        Assert.assertTrue(!(zohoLead_Id.equals(null)));

        String user_Id = dbObject.getRecord("user_leads", "user_id",new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String course_Id = dbObject.getRecord("user_leads", "course_id",  "user_id", user_Id);
        System.out.println("DB "+course_Id+" UI "+courseId);
        Assert.assertTrue(course_Id.contains(courseId));

        String email = dbObject.getRecord("user_leads", "email", "first_name", userName);
        Assert.assertTrue(email.contains(emailId));

        String website_action = dbObject.getRecord("user_leads", "website_action", "email", emailId);
        Assert.assertTrue(website_action.contains(webSiteAction));

        String ip = dbObject.getRecord("user_leads", "user_ip", "email", emailId);
        Assert.assertTrue(!(ip.equals(null)));

        String potential_Id = dbObject.getRecord("user_leads", "zoho_potential_id", "email", emailId);
        Assert.assertTrue(!(potential_Id.equals(null)));

        String created = dbObject.getRecord("user_leads", "created", "email", emailId);
        Assert.assertTrue(created.contains(presenttDate()));

        String modified = dbObject.getRecord("user_leads", "modified", "email", emailId);
        Assert.assertTrue(modified.contains(presenttDate()));

        String countryName = dbObject.getRecord("user_leads", "country", "email", emailId);
        Assert.assertTrue(countryName.toLowerCase().contains(country.toLowerCase()));

        String lead_Id = dbObject.getRecord("user_leads", "id", "email", emailId);
        Assert.assertTrue(!(lead_Id.equals(null)));

        String campaign_Source = dbObject.getRecord("utm_params", "campaign_source", "lead_id", lead_Id);
        Assert.assertTrue(campaign_Source.contains(campaignSource));

        String campaign_Name = dbObject.getRecord("utm_params", "campaign_name", "lead_id", lead_Id);
        Assert.assertTrue(campaign_Name.contains(campaignName));

        String campaign_Medium = dbObject.getRecord("utm_params", "campaign_medium", "lead_id", lead_Id);
        Assert.assertTrue(campaign_Medium.contains(campaignMedium));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Verify Data in user Table
     * 
     * @param className
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInUserTable(@SuppressWarnings("rawtypes") Class className, String custStatus) throws Exception {

        Database dbObject = new Database();

        String phoneNumber = dbObject.getRecord("users", "mobile", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(phoneNumber.contains(phone));

        String customerStatus = dbObject.getRecord("users", "customer_status", new String[]{"email"}, new String[]{emailId});
        Assert.assertEquals(customerStatus.replace("#", ""), custStatus, customerStatus.replace("#", "")+" is displaying instead of "+custStatus);
        System.out.println("customerStatus"+customerStatus);

        String user = dbObject.getRecord("users", "first_name", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(user.contains(userName));

        String email = dbObject.getRecord("users", "email", "first_name", userName);
        Assert.assertTrue(email.contains(emailId));

        String status = dbObject.getRecord("users", "status", "email", emailId);
        Assert.assertTrue(status.contains("1"));

        String password = dbObject.getRecord("users", "password", "email", emailId);
        Assert.assertTrue(!(password.equals(null)));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }


    /**
     * Verify Data in user Table
     * 
     * @param className
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInUserTable(@SuppressWarnings("rawtypes") Class className) throws Exception {

        Database dbObject = new Database();

        String phoneNumber = dbObject.getRecord("users", "mobile", "email", emailId);
        Assert.assertTrue(phoneNumber.contains(phone));

        String user = dbObject.getRecord("users", "first_name", "email", emailId);
        Assert.assertTrue(user.contains(userName));

        String email = dbObject.getRecord("users", "email", "first_name", userName);
        Assert.assertTrue(email.contains(emailId));

        String status = dbObject.getRecord("users", "status", "email", emailId);
        Assert.assertTrue(status.contains("1"));

        String password = dbObject.getRecord("users", "password", "email", emailId);
        Assert.assertTrue(!(password.equals(null)));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }
    /**
     * Verify Data in QueueJobs table
     * 
     * @param className
     * @param courseStatus
     * @param coursePriority
     * @param event
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInCompleted_Queue_Jobs_Table(@SuppressWarnings("rawtypes") Class className,String courseStatus, String coursePriority, String event) throws Exception {

        Database dbObject = new Database();

        String user_ID = propertyReader.readRunTimeData("User_ID");
        String status = dbObject.getRecord("completed_queue_jobs", "status", "id", user_ID);
        System.out.println("DB "+status +" UI "+courseStatus);
        Assert.assertTrue(status.contains(courseStatus));

        String priority = dbObject.getRecord("completed_queue_jobs", "priority", "id", user_ID);
        Assert.assertTrue(priority.contains(coursePriority));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify Credit Point in Ambaassadors table
     * @param className
     * @param email
     * @param points
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T _VerificationCreditPointsInAmbassadorsTableReferralProcess(@SuppressWarnings("rawtypes") Class className, String email, String points) throws Exception {
        Database dbObject = new Database();
        String email_Id = dbObject.getRecord("users", "email", "first_name", userName);
        Assert.assertTrue(email_Id.contains(emailId));

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("Referral_User_ID", user_Id);
        String status = dbObject.getRecord("users", "status", "email", emailId);
        Assert.assertTrue(status.contains("1"));

        String crdeitPoints = dbObject.getRecord("ambassadors", "available_points", "user_id", user_Id);
        Assert.assertTrue(crdeitPoints.contains(points));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify Redeemption points in Referral Redemptions table
     * @param className
     * @param email
     * @param points
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T _VerificationRedeemptionPointsIn_Referral_redemptions(@SuppressWarnings("rawtypes") Class className, String email, String points) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("Referral_User_ID", user_Id);

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify Referral Link in Ambassdor Table
     * @param className
     * @param email
     * @param referralLink
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyReferralLinkIn_AmbassdorTable(@SuppressWarnings("rawtypes") Class className, String email,String referralLink) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));

        String referLink = dbObject.getRecord("ambassadors", "email_url", "user_id", user_Id);
        Assert.assertTrue(referLink.contains(referralLink));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }


    /**
     *  Verify Enrolled count in Ambassador_Transaction Table
     * @param className
     * @param email
     * @param referralLink
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyDataIn_AmbassadorTransactionTable(@SuppressWarnings("rawtypes") Class className, String email, String enorlled_User) throws Exception {
        Database dbObject = new Database();

        String user_Id_A = dbObject.getRecord("users ambassador_activities", "id", new String[]{"email"}, new String[]{email});
        Assert.assertTrue(!(user_Id_A.equals(null)));

        String enorll_Count = dbObject.getRecordsWhenValueInNotNull("ambassador_transactions", "count(*)",new String[]{"referrer_id","medium"}, new String[]{user_Id_A,"REFERRAL"}, "order_id");
        Assert.assertTrue(enorll_Count.contains(enorlled_User));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify Data in Referral Redemption table
     * @param className
     * @param email
     * @param redeemption_Points
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyDataIn_Referral_redemptionsTable(@SuppressWarnings("rawtypes") Class className, String email, String redeemption_Points) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));

        String value = dbObject.getRecord("referral_redemptions", "value", "user_id", user_Id);
        Assert.assertTrue(value.contains(redeemption_Points));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify Refer Source in Ambassador Activities Table 
     * @param className
     * @param email
     * @param source
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyReferSourceIn_Ambassador_activitiesTable(@SuppressWarnings("rawtypes") Class className,String email, String source) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));

        String activity = dbObject.getRecord("ambassador_activities", "activity_type", "user_id", user_Id);
        Assert.assertTrue(activity.contains(source));

        String referredDate = dbObject.getRecord("ambassador_activities", "created", "user_id", user_Id);
        Assert.assertTrue(referredDate.contains(presenttDate()));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify Referred Date in Ambassador Activities Table
     *  email
     * @param className
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyReferDateIn_Ambassador_activitiesTable(@SuppressWarnings("rawtypes") Class className, String email) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));

        String referredDate = dbObject.getRecord("ambassador_activities", "created", "user_id", user_Id);
        Assert.assertTrue(referredDate.contains(presenttDate()));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Check user exist or not
     * @param email
     * @return
     * @throws Exception
     */
    public String checkUserIsRegistered(String email) throws Exception{
        Database dbObject = new Database();
        String firstName = dbObject.getRecord("users", "first_name", "email", email);
        return firstName;
    }
    /**
     * Data verification in Pre_Order Table for INR currency
     * 
     * @param className
     * @param course_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInPreOrderTable(@SuppressWarnings("rawtypes") Class className, String course_Id, String batchTitle,String gateway,String currency,String testCaseName,String sheetName) throws Exception {
    	String freeCourseIdVal;
    	Database dbObject = new Database();
        SpreadsheetOperations so=new SpreadsheetOperations();

        String user_Id = dbObject.getRecord("user_leads", "user_id", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);
        System.out.println("User Id : "+user_Id);

        String course_id = dbObject.getRecord("pre_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_id.contains(course_Id));

        String order_Id = dbObject.getRecord("pre_orders", "orderid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertTrue(!(order_Id.equals(null)));

        //batch id 
        String batch_Id = dbObject.getRecord("pre_orders", "userid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertTrue(!(batch_Id.equals(null)));

        //batch title
        String batch_Title = dbObject.getRecord("pre_orders", "batchtitle", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        System.out.println("Actual batch title:"+batch_Title);
        System.out.println("Expected batch title:"+batchTitle);
        Assert.assertTrue(batch_Title.contains(batchTitle));

        //cart status
        String cart_status=so.getExcelData(testCaseName, sheetName, "cart_status");
        String cartStatus = dbObject.getRecord("pre_orders", "cart_status", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        if (cart_status.equals("null")) {
            cart_status="";
            freeCourseIdVal = dbObject.getRecordsWhenOneColumnValueIsNull("pre_orders", "freecourseid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id},"cart_status");
        }
        else
        	freeCourseIdVal = dbObject.getRecord("pre_orders", "freecourseid", new String[]{"courseid", "userid","cart_status"}, new String[]{course_Id,user_Id,cart_status});
        System.out.println("cart_status"+cart_status);
        Assert.assertTrue(cartStatus.trim().contains(cart_status.trim()));
        
        //free course id
        String freeCourseId=so.getExcelData(testCaseName, sheetName, "freecourseid");
        if (freeCourseId.equals("null")) 
            freeCourseId="";      
        Assert.assertTrue(freeCourseIdVal.trim().contains(freeCourseId.trim()));



        //Gateway
        String gateway_dbValue=dbObject.getRecord("pre_orders", "gateway", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertTrue(gateway_dbValue.contains(gateway));



        //Discount plan
        String discountplan=so.getExcelData(testCaseName, sheetName, "discountplan");
        String discountplanVal=dbObject.getRecord("pre_orders", "discountplan", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        if (discountplan.equals("null"))
            discountplan="";
        System.out.println("discountplan"+discountplan);
        System.out.println("discountplanVal"+discountplanVal);
        Assert.assertTrue(discountplanVal.trim().contains(discountplan.trim()));

        //ambassadorflag
        String ambassadorflag=so.getExcelData(testCaseName, sheetName, "ambassadorflag");
        String ambassadorflag_Val=dbObject.getRecord("pre_orders", "ambassadorflag", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertTrue(ambassadorflag_Val.contains(ambassadorflag));

        if (testCaseName.contains("MakePaymentWhenCreditPointsAvailable") || testCaseName.contains("MakePaymentWhenCreditPointsAvailableAndB1S2OfferIsPresent")) {
            //ambassadormoneyusd
            String availablePoints=dbObject.getRecord("ambassadors", "available_points", new String[]{"course_id", "userid"}, new String[]{course_Id,user_Id});
            String ambassadormoneyusd=dbObject.getRecord("pre_orders", "ambassadormoneyusd", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(ambassadormoneyusd.contains(availablePoints));

            //am_money_value
            String am_money_value=dbObject.getRecord("pre_orders", "am_money_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(am_money_value.contains(availablePoints));

            //am_dis_value
            String am_dis_value=dbObject.getRecord("pre_orders", "am_dis_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(am_dis_value.contains(availablePoints));
        }
        else{
            //ambassadormoneyusd
            String ambassadormoneyusd=dbObject.getRecord("pre_orders", "ambassadormoneyusd", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(ambassadormoneyusd.contains("0"));

            //am_money_value
            String am_money=so.getExcelData(testCaseName, sheetName, "am_money_value");
            String am_money_value=dbObject.getRecord("pre_orders", "am_money_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(am_money_value.contains(am_money));

            //am_dis_value
            String am_dis_value=dbObject.getRecord("pre_orders", "am_dis_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(am_dis_value.contains("0"));
        }

        //all_discount
        String all_disc=so.getExcelData(testCaseName, sheetName, "all_discounts");
        String all_discount=dbObject.getRecord("pre_orders", "all_discounts", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        if (all_disc.equals("null")) 
            all_disc="";
        Assert.assertTrue(all_discount.contains(all_disc));


        //original_value
        String original_value=dbObject.getRecord("pre_orders", "original_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertTrue(!(original_value.equals(null)));

        //discount_value
        String discountValue = propertyReader.readRunTimeData("DiscountValue");
        String discount_value=dbObject.getRecord("pre_orders", "discount_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        System.out.println("discountValue"+discountValue);
        System.out.println("Discount value :"+discount_value);
        Assert.assertTrue(discount_value.contains(discountValue));

        //final_value
        String totalAmount = propertyReader.readRunTimeData("TotalAmount");
        String finalValue=dbObject.getRecord("pre_orders", "final_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        System.out.println("finalValue :"+finalValue);
        Assert.assertTrue(finalValue.contains(totalAmount));
        String price = propertyReader.readRunTimeData("PriceValue"); 
        if (currency.equals("USD")) {
            String price_USD = dbObject.getRecord("post_orders", "priceusd", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(price_USD.trim().contains(price_USD.trim()));

            String serviceTax_USD = dbObject.getRecord("post_orders", "servicetaxusd", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(serviceTax_USD.contains("0"));

            String currency_Id = dbObject.getRecord("post_orders", "currency_id", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(currency_Id.contains("0"));
        } else if (currency.equals("INR")) {
            String price_INR = dbObject.getRecord("pre_orders", "priceinr", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(price_INR.trim().contains(price.trim()));

            String serviceTax = propertyReader.readRunTimeData("ServiceTax");
            String serviceTax_INR = dbObject.getRecord("pre_orders", "servicetaxinr", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(serviceTax_INR.contains(serviceTax));

            String currency_Id = dbObject.getRecord("pre_orders", "currency_id", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
            Assert.assertTrue(currency_Id.contains("1"));

        }
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Data verification in Pre_Order Table for INR currency
     * 
     * @param className
     * @param course_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T  dataVerificationInUser_QAUserFavoritesTable(@SuppressWarnings("rawtypes") Class className,String courseId,String courseGroup,String entityType) throws Exception {
        Database dbObject = new Database();
        String userId=propertyReader.readRunTimeData("User_ID");
        String query=propertyReader.readTestData("QA_EventTypeQuery");
        query=query.replace("#", userId);
        query=query.replace("$", courseId);
        query=query.replace("&", courseGroup);
        String entityTypeDbVal=dbObject.executeQuery(query);
        Assert.assertTrue(entityTypeDbVal.equals(entityType));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Database verirication for Referred user
     * @param className
     * @param email
     * @param levelId
     * @param welcomemail
     * @param ambassador_Campaign_Id
     * @param ambassador_Level_Id
     * @param source
     * @param campaign_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationForReferredUser(@SuppressWarnings("rawtypes") Class className, String email, String levelId, String welcomemail, String ambassador_Campaign_Id, String ambassador_Level_Id, String source, String campaign_id) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("Referral_User_ID", user_Id);

        String facebook_Url = dbObject.getRecord("ambassadors", "facebook_url", "user_id", user_Id);
        Assert.assertTrue(!(facebook_Url.equals(null)));

        String level_Id = dbObject.getRecord("ambassadors", "level_id", "user_id", user_Id);
        Assert.assertTrue(level_Id.contains(levelId));

        String linkdln_Url = dbObject.getRecord("ambassadors", "linkedin_url", "user_id", user_Id);
        Assert.assertTrue(!(linkdln_Url.equals(null)));

        String twitter_url = dbObject.getRecord("ambassadors", "twitter_url", "user_id", user_Id);
        Assert.assertTrue(!(twitter_url.equals(null)));

        String mobile_url = dbObject.getRecord("ambassadors", "mobile_url", "user_id", user_Id);
        Assert.assertTrue(!(mobile_url.equals(null)));

        String ambassadorDate = dbObject.getRecord("ambassadors", "ambassador_date", "user_id", user_Id);
        Assert.assertTrue(ambassadorDate.contains(presenttDate()));

        String welcome_Email = dbObject.getRecord("ambassadors", "welcomemail", "user_id", user_Id);
        Assert.assertTrue( welcome_Email.contains(welcomemail));

        String ambassador_Campaign = dbObject.getRecord("ambassadors", "ambassador_campaign_id", "user_id", user_Id);
        Assert.assertTrue( ambassador_Campaign.trim().contains(ambassador_Campaign_Id));

        String ambassadorLevelId = dbObject.getRecord("ambassadors", "ambassador_level_id", "user_id", user_Id);
        Assert.assertTrue(ambassadorLevelId.trim().contains(ambassador_Level_Id));

        String activity = dbObject.getRecord("ambassador_activities", "activity_type", "user_id", user_Id);
        Assert.assertTrue(activity.contains(source));

        String referred_Id = dbObject.getRecord("ambassador_activities", "referrer_id", "user_id", user_Id);
        Assert.assertTrue(!(referred_Id.equals(null)));

        String levelID = dbObject.getRecord("ambassador_activities", "id", "user_id", user_Id);
        Assert.assertTrue(!(levelID.equals(null)));

        String campaign_ID = dbObject.getRecord("ambassador_activities", "campaign_id", "user_id", user_Id);
        Assert.assertTrue(campaign_ID.contains(campaign_id));

        String master_url = dbObject.getRecord("ambassadors", "master_url", "user_id", user_Id);
        Assert.assertTrue(!(master_url.equals(null)));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }
    /**
     * Data verification in Pre_Order Table for INR currency
     * 
     * @param className
     * @param course_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInUser_Events_Table_PaymentType(@SuppressWarnings("rawtypes") Class className,String eventType) throws Exception {

        Database dbObject = new Database();

        String user_ID = propertyReader.readRunTimeData("User_ID");

        String event_Type = dbObject.getRecord("user_events", "event_type", "user_id", user_ID);
        Assert.assertTrue(event_Type.contains(eventType));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify AMBASSADOR_TRANSACTIONS User_id refferer_id level_id and campaign_id (taken from AMBASSADOR) , order_id(taken fron post_orders), revenue, value 
     * @param className
     * @param email
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyData_InAmassador_Transaction_ForReferral(@SuppressWarnings("rawtypes") Class className, String email , String course_Id) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));

        String order_Id = dbObject.getRecord("post_orders", "orderid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});

        String total_Usd = dbObject.getRecord("post_orders", "totalusd", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});

        String revenue = dbObject.getRecord("ambassador_transactions", "revenue", "order_id", order_Id);
        Assert.assertTrue(revenue.contains(total_Usd));

        String medium = dbObject.getRecord("ambassador_transactions", "medium", "order_id", order_Id);
        Assert.assertTrue(medium.contains("REFERRAL"));

        String ambassador_Campaign = dbObject.getRecord("ambassadors", "ambassador_campaign_id", "user_id", user_Id);
        String ambassadorLevelId = dbObject.getRecord("ambassadors", "ambassador_level_id", "user_id", user_Id);

        String level_Id = dbObject.getRecord("ambassador_transactions", "level_id", "order_id", order_Id);
        Assert.assertTrue(level_Id.contains(ambassadorLevelId));

        String campaign = dbObject.getRecord("ambassador_transactions", "campaign_id", "order_id", order_Id);
        Assert.assertTrue(campaign.contains(ambassador_Campaign));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Verify Data for Redeem
     * @param className
     * @param email
     * @param course_Id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyData_For_redemptions_In_ReferralProcess(@SuppressWarnings("rawtypes") Class className, String email, String course_Id) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));

        String ambassadorLevelId = dbObject.getRecord("ambassadors", "ambassador_level_id", "user_id", user_Id);
        Assert.assertTrue(ambassadorLevelId.contains("5"));

        String ambassador_Campaign = dbObject.getRecord("ambassadors", "ambassador_campaign_id", "user_id", user_Id);

        String availablePoints = dbObject.getRecord("ambassadors", "available_points", "user_id", user_Id);
        Assert.assertTrue(availablePoints.contains("0"));

        String order_Id = dbObject.getRecord("post_orders", "orderid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});

        String medium = dbObject.getRecord("referral_redemptions", "medium", "order_id", order_Id);
        Assert.assertTrue(medium.contains("ORDER"));

        String userId = dbObject.getRecord("referral_redemptions", "user_id", "order_id", order_Id);
        Assert.assertTrue(userId.contains(user_Id));

        String level_ID = dbObject.getRecord("referral_redemptions", "level_id", "order_id", order_Id);
        Assert.assertTrue(ambassadorLevelId.contains(level_ID));

        String campaign = dbObject.getRecord("referral_redemptions", "campaign_id", "order_id", order_Id);
        Assert.assertTrue(campaign.contains(ambassador_Campaign));

        String created = dbObject.getRecord("referral_redemptions", "created", "order_id", order_Id);
        Assert.assertTrue(created.contains(presenttDate()));

        String modified = dbObject.getRecord("referral_redemptions", "modified", "order_id", order_Id);
        Assert.assertTrue(modified.contains(presenttDate()));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }


    /**
     *  Database verification for referred user in User Table
     * @param className
     * @param custStatus
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationForReferredUserInUserTable(@SuppressWarnings("rawtypes") Class className, String custStatus) throws Exception {

        Database dbObject = new Database();
        emailId = propertyReader.readRunTimeData("RefferralEmail");
        String userName = propertyReader.readTestData("UserName");

        String phoneNumber = dbObject.getRecord("users", "mobile", "email", emailId);
        Assert.assertTrue(phoneNumber.contains(phone));

        String customerStatus = dbObject.getRecord("users", "customer_status", "email", emailId);
        Assert.assertEquals(customerStatus.replace("#", ""), custStatus, customerStatus+" is displaying instead of "+custStatus);

        String user = dbObject.getRecord("users", "first_name", "email", emailId);
        Assert.assertTrue(user.contains(userName));

        String email = dbObject.getRecord("users", "email", "first_name", userName);
        Assert.assertTrue(email.contains(emailId));

        String status = dbObject.getRecord("users", "status", "email", emailId);
        Assert.assertTrue(status.contains("1"));

        String password = dbObject.getRecord("users", "password", "email", emailId);
        Assert.assertTrue(!(password.equals(null)));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Database Verification for Referral pop
     * @param className
     * @param email
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T datbaseVerificationFor_ReferralPop(@SuppressWarnings("rawtypes") Class className, String email) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("Referral_User_ID", user_Id);

        String linkdln_Url = dbObject.getRecord("ambassadors", "linkedin_url",new String[]{"user_id"}, new String[]{user_Id});
        Assert.assertTrue(!(linkdln_Url.equals(null)));
        propertyReader.updatePropertyTestData("LinkdlnUrl", linkdln_Url);

        String twitter_url = dbObject.getRecord("ambassadors", "twitter_url", new String[]{"user_id"}, new String[]{user_Id});
        Assert.assertTrue(!(twitter_url.equals(null)));
        propertyReader.updatePropertyTestData("TwitterUrl", twitter_url);
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Get Campaign and Level Id
     * @param className
     * @param email
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T getCampaignAndLevelIdFromAmbassadorTable(@SuppressWarnings("rawtypes") Class className, String email) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", "email", email);
        Assert.assertTrue(!(user_Id.equals(null)));
        String ambassador_Campaign = dbObject.getRecord("ambassadors", "ambassador_campaign_id", "user_id", user_Id);
        String ambassadorLevelId = dbObject.getRecord("ambassadors", "ambassador_level_id", "user_id", user_Id);
        propertyReader.updatePropertyTestData("Campaign_Id", ambassador_Campaign);
        propertyReader.updatePropertyTestData("Level_Id", ambassadorLevelId);
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Database verification in Post Order table
     * 
     * @param className
     * @param course_id
     * @param batchTitle
     * @param paymentGatway
     * @param currency
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInPostOrderTables(@SuppressWarnings("rawtypes") Class className, String course_Id, String batchTitle, String paymentGatway, String currency) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("user_leads", "user_id", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String course_id = dbObject.getRecord("post_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_id.contains(course_Id));

        String order_Id = dbObject.getRecord("post_orders", "orderid", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
        Assert.assertTrue(!(order_Id.equals(null)));

        String batch_Id = dbObject.getRecord("post_orders", "batchid", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
        Assert.assertTrue(!(batch_Id.equals(null)));

        String batch_Title = dbObject.getRecord("post_orders", "batchtitle", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
        System.out.println("batch_Title :"+batch_Title);
        System.out.println("batchTitle : "+batchTitle);
        Assert.assertTrue(batch_Title.contains(batchTitle));

        String price = propertyReader.readRunTimeData("PriceValue");
        System.out.println("Expected Price :"+price);

        if (currency.equals("USD")) {
            String price_USD = dbObject.getRecord("post_orders", "priceusd", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
            System.out.println("Price Usd :"+price);
            Assert.assertTrue(price_USD.trim().contains(price_USD.trim()));

            String serviceTax_INR = dbObject.getRecord("post_orders", "servicetaxusd", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
            Assert.assertTrue(serviceTax_INR.contains("0"));

            String currency_Id = dbObject.getRecord("post_orders", "currency_id", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
            Assert.assertTrue(currency_Id.contains("0"));
        } else if (currency.equals("INR")) {
            System.out.println("I am in else");
            String price_INR = dbObject.getRecord("pre_orders", "priceinr", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
            Assert.assertTrue(price_INR.trim().contains(price.trim()));

            String serviceTax = propertyReader.readRunTimeData("ServiceTax");
            String serviceTax_INR = dbObject.getRecord("pre_orders", "servicetaxinr", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
            Assert.assertTrue(serviceTax_INR.contains(serviceTax));

            String currency_Id = dbObject.getRecord("pre_orders", "currency_id", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
            Assert.assertTrue(currency_Id.contains("1"));

        }
        String paymentGateway = dbObject.getRecord("post_orders", "gateway", new String[]{"userid","courseid"}, new String[]{user_Id,course_Id});
        Assert.assertTrue(paymentGateway.contains(paymentGatway));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Database verification in Post Order table on the basis of PreOrder table
     * 
     * @param className
     * @param course_id
     * @param batchTitle
     * @param paymentGatway
     * @param currency
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInPostOrderOnTheBasisOfPreOrderTable(@SuppressWarnings("rawtypes") Class className, String course_Id, String batchTitle, String paymentGatway, String currency) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("user_leads", "user_id", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String course_id = dbObject.getRecord("post_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_id.contains(course_Id));

        String order_Id = dbObject.getRecord("pre_orders", "orderid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});

        //am_money_value
        String pre_am_money_value= dbObject.getRecord("pre_orders", "am_money_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        String post_am_money_value= dbObject.getRecord("post_orders", "am_money_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertEquals(pre_am_money_value, post_am_money_value);

        //am_dis_value
        String pre_am_dis_value= dbObject.getRecord("pre_orders", "am_dis_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        String post_am_dis_value= dbObject.getRecord("post_orders", "am_dis_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertEquals(pre_am_dis_value, post_am_dis_value);

        //Status
        String status= dbObject.getRecord("post_orders", "status", new String[]{"courseid", "userid","orderid"}, new String[]{course_Id,user_Id,order_Id});
        Assert.assertTrue(status.equals("OK"));

        //api_cc
        String api_cc= dbObject.getRecord("post_orders", "api_cc", new String[]{"courseid", "userid","orderid"}, new String[]{course_Id,user_Id,order_Id});
        Assert.assertTrue(api_cc.equals("1"));

        //api_crm
        String api_crm= dbObject.getRecord("post_orders", "api_crm", new String[]{"courseid", "userid","orderid"}, new String[]{course_Id,user_Id,order_Id});
        Assert.assertTrue(api_crm.equals("1"));

        //api_creator
        String api_creator= dbObject.getRecord("post_orders", "api_creator", new String[]{"courseid", "userid","orderid"}, new String[]{course_Id,user_Id,order_Id});
        Assert.assertTrue(api_creator.equals("1"));

        //api_gtw
        String api_gtw= dbObject.getRecord("post_orders", "api_gtw", new String[]{"courseid", "userid","orderid"}, new String[]{course_Id,user_Id,order_Id});
        Assert.assertTrue(api_gtw.equals("0"));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }
    /**
     * Database verification in Post Order table on the basis of PreOrder table
     * 
     * @param className
     * @param course_id
     * @param batchTitle
     * @param paymentGatway
     * @param currency
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInCustomerRecordsTable(@SuppressWarnings("rawtypes") Class className, String course_Id,String courseName,String paymentGatway, String currency) throws Exception {
        Database dbObject = new Database();
        Timer timer = new Timer();
        String user_Id = dbObject.getRecord("user_leads", "user_id", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);

        String course_id = dbObject.getRecord("post_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_id.contains(course_Id));

        String order_Id = dbObject.getRecord("pre_orders", "orderid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        System.out.println("order id :"+order_Id);
        //first_name
        String firstName=propertyReader.readRunTimeData("UserName");
        String first_name= dbObject.getRecord("customer_records", "first_name", new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(firstName.contains(first_name));

        //Email
        String email=propertyReader.readRunTimeData("Email_Id");
        String emailDB= dbObject.getRecord("customer_records", "email", new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(email.contains(emailDB));

        //Mobile Number
        String phoneNumber=propertyReader.readTestData("PhoneNumber");
        String phone_Number= dbObject.getRecord("customer_records", "mobile", new String[]{"order_id"}, new String[]{order_Id});
        System.out.println("phoneNumber"+phoneNumber);
        System.out.println("phone_Number"+phone_Number);
        Assert.assertTrue(phone_Number.contains(phoneNumber.trim()));

        //Country
        String country= dbObject.getRecord("customer_records", "country", new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(!(country.equals(null)));

        //courseName
        String course_name= dbObject.getRecord("customer_records", "course_name", new String[]{"order_id"}, new String[]{order_Id});
        System.out.println("course_name :"+course_name);
        System.out.println("courseName  :"+courseName);
        Assert.assertTrue(course_name.contains(courseName));

        //Batch Title
        String batchTitle=dbObject.getRecord("customer_records", "batch_title", new String[]{"course_id", "user_id"}, new String[]{course_Id,user_Id});
        String batch_title= dbObject.getRecord("customer_records", "batch_title", new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(batch_title.contains(batchTitle));

        //student_count 
        String studentCount=propertyReader.readRunTimeData("StudentCount");
        int sCount=Integer.parseInt(studentCount);
        String stCount= dbObject.getRecord("customer_records", "students_count",new String[]{"order_id"}, new String[]{order_Id});
        int stCount1=Integer.parseInt(stCount);
        Assert.assertEquals(sCount+1, stCount1);
       
        //Payment Amount
        String totalAmount=propertyReader.readRunTimeData("TotalAmount");
        String paymentAmount= dbObject.getRecord("customer_records", "payment_amount",new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(paymentAmount.contains(totalAmount));

        //Payment Gateway
        String paymentGateway= dbObject.getRecord("customer_records", "payment_gateway",new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(paymentGateway.contains(paymentGatway));


        //lead_date
        String created_date=dbObject.getRecord("user_leads", "created", new String[]{"course_id", "user_id"}, new String[]{course_Id,user_Id});
        String lead_date= dbObject.getRecord("customer_records", "lead_date",new String[]{"order_id"}, new String[]{order_Id});
        System.out.println("created_date : "+created_date);
        System.out.println("lead_date : "+lead_date);
        Assert.assertTrue(created_date.equals(lead_date));
        //sales_date
        String currentDate=timer.getCurrentDate();
        String sales_date= dbObject.getRecord("customer_records", "sales_date",new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(sales_date.contains(currentDate));

        //days_to_closure
        String noOfdays=dbObject.getRecord("customer_records", "datediff(sales_date, lead_date)",new String[]{"order_id"}, new String[]{order_Id});
        String days_to_closure=dbObject.getRecord("customer_records", "days_to_closure",new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(days_to_closure.equals(noOfdays));      

        //lead_utm_src
        String lead_utm_src= dbObject.getRecord("customer_records", "lead_utm_src",new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(lead_utm_src.contains("Dir"));

        //sales_utm_src
        String lead_id=dbObject.getRecord("user_leads", "id", new String[]{"course_id", "user_id"}, new String[]{course_Id,user_Id});
        String comapignSrc=dbObject.getRecord("utm_params", "campaign_source", new String[]{"lead_id"}, new String[]{lead_id});
        System.out.println(comapignSrc);
        String sales_utm_src= dbObject.getRecord("customer_records", "sales_utm_src",new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(sales_utm_src.contains(comapignSrc));

        // lead_utm_campaign
        String lead_utm_campaign= dbObject.getRecord("customer_records", "lead_utm_campaign",new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(lead_utm_campaign.contains("Dir"));

        //sales_utm_campaign
        String comapignName=dbObject.getRecord("utm_params", "campaign_name", new String[]{"lead_id"}, new String[]{lead_id});
        String sales_utm_campaign= dbObject.getRecord("customer_records", "sales_utm_campaign", new String[]{"order_id"}, new String[]{order_Id});
        Assert.assertTrue(sales_utm_campaign.contains(comapignName));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Database Verification For Existing Customer, Remind and Earned Credit in Referral
     * @param className
     * @param email
     * @param referral_Email
     * @param earned_credit_points
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T databaseVerificationForExisting_Customer_Remind_Earned_CreditInReferral(@SuppressWarnings("rawtypes") Class className, String email, String referral_Email, String earned_credit_points) throws Exception {
        Database dbObject = new Database();

        String user_Id_A = dbObject.getRecord("users ambassador_activities", "id", new String[]{"email"}, new String[]{email});
        Assert.assertTrue(!(user_Id_A.equals(null)));
        propertyReader.updatePropertyTestData("Referral_User_ID", user_Id_A);

        String user_Id_B = dbObject.getRecord("users ambassador_activities","id", new String[]{"email"}, new String[]{referral_Email});
        Assert.assertTrue(!(user_Id_B.equals(null)));
        propertyReader.updatePropertyTestData("Refferred_User_Id", user_Id_B);

        String credit_Points = dbObject.getRecordsWhenValueInNotNull("ambassador_transactions", "value",new String[]{"referrer_id","user_id","medium"}, new String[]{user_Id_A, user_Id_B,"REFERRAL"}, "order_id");
        Assert.assertTrue(!(credit_Points.equals(null)));

        double creditPoints=Integer.parseInt(earned_credit_points);
        double credit_Points_1 = round((creditPoints/60),2);
        String earned_Credit= String.valueOf(credit_Points_1);
        Assert.assertTrue(credit_Points.contains(earned_Credit));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Verify Referral Count
     * @param className
     * @param email
     * @param count
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T verifyReferralCountForRefferalProcess(@SuppressWarnings("rawtypes") Class className, String email, String count) throws Exception {
        Database dbObject = new Database();

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", new String[]{"email"}, new String[]{email});
        Assert.assertTrue(!(user_Id.equals(null)));

        String referral_count = dbObject.getRecord("ambassador_activities", "count(distinct email)", new String[]{"user_id","activity_type"}, new String[]{user_Id,"EMAIL_REFER"});
        Assert.assertTrue(referral_count.contains(count));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Get Available points of User A and User B
     * @param className
     * @param email
     * @param referralEmail
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T getAvailablePointsOfUserAndUserB(@SuppressWarnings("rawtypes") Class className, String email, String referralEmail) throws Exception {
        Database dbObject = new Database();

        String user_Id_A = dbObject.getRecord("users ambassador_activities", "id", new String[]{"email"}, new String[]{email});
        Assert.assertTrue(!(user_Id_A.equals(null)));

        String user_Id_B = dbObject.getRecord("users ambassador_activities", "id",new String[]{"email"}, new String[]{referralEmail});
        Assert.assertTrue(!(user_Id_B.equals(null)));

        String crdeitPoints_A = dbObject.getRecord("ambassadors", "available_points",new String[]{"user_id"}, new String[]{user_Id_A});
        propertyReader.updatePropertyTestData("UserACreditPoints",crdeitPoints_A); 

        String crdeitPoints_B = dbObject.getRecord("ambassadors", "available_points",new String[]{"user_id"}, new String[]{user_Id_B});
        propertyReader.updatePropertyTestData("UserBCreditPoints",crdeitPoints_B); 
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Compare Earning points
     * @param className
     * @param email
     * @param referralEmail
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T compareCreditPoints(@SuppressWarnings("rawtypes") Class className, String email, String referralEmail) throws Exception {
        Database dbObject = new Database();

        String userACreditPoints = propertyReader.readRunTimeData("UserACreditPoints");

        String user_Id_A = dbObject.getRecord("users ambassador_activities", "id", new String[]{"email"}, new String[]{email});
        Assert.assertTrue(!(user_Id_A.equals(null)));

        String user_Id_B = dbObject.getRecord("users ambassador_activities", "id",new String[]{"email"}, new String[]{referralEmail});
        Assert.assertTrue(!(user_Id_B.equals(null)));

        String crdeitPoints_A = dbObject.getRecord("ambassadors", "available_points",new String[]{"user_id"}, new String[]{user_Id_A});
        Assert.assertTrue(crdeitPoints_A.contains(userACreditPoints));

        String creditPoints_B = dbObject.getRecord("ambassadors", "available_points",new String[]{"user_id"}, new String[]{user_Id_B});

        String value = dbObject.getRecord("ambassador_transactions", "value", new String[]{"user_id"}, new String[]{user_Id_B});
        float earnedPoints= Float.parseFloat(value.trim());

        String userB_EarnedPoints= String.valueOf(earnedPoints);
        Assert.assertTrue(creditPoints_B.contains(userB_EarnedPoints));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     *  Compare Credit points after referred Existing Customer     
     * @param className
     * @param email
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T compareCreditPointAfterReferExistingCustomer(@SuppressWarnings("rawtypes") Class className, String email) throws Exception {
        Database dbObject = new Database();

        String availPoints= propertyReader.readRunTimeData("UserACreditPoints");

        String user_Id = dbObject.getRecord("users ambassador_activities", "id", new String[]{"email"}, new String[]{email});
        Assert.assertTrue(!(user_Id.equals(null)));

        String crdeitPoints_A = dbObject.getRecord("ambassadors", "available_points",new String[]{"user_id"}, new String[]{user_Id});

        Assert.assertTrue(availPoints.contains(crdeitPoints_A));
        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }

    /**
     * Get Courses based on Category
     * @param query
     * @return
     * @throws Exception
     */
    public ArrayList<String> getCourseBasedOnCategory(String query) throws Exception {
        Database dbObject = new Database();
        ArrayList<String> courses = dbObject.executeQueryAndGetDataInList(query);
        return courses;
    }

    /**
     *  Check user exist or not
     * @param email
     * @return
     * @throws Exception
     */
    public String getCoursePrice(String courseId,String currency) throws Exception{
        Database dbObject = new Database();
        String currencyId = dbObject.getRecord("currency_rates", "currency_id", "CURRENCY_NAME", currency);
        String coursePrice=  dbObject.getRecord("course_prices", "value",  new String[]{"course_id","currency_id"}, new String[]{courseId,currencyId});
        return coursePrice;
    }

    /**
     *  Execute Signle Query
     * @param query
     * @return
     * @throws Exception
     */
    public String executeSignleQuery(String query) throws Exception{
        Database dbObject = new Database();
        String output = dbObject.executeQuery(query);
        return output;
    }

    /**
     * Get User Id
     * @return
     * @throws Exception
     */
    public String getUserId() throws Exception{
        Database dbObject = new Database();
        String user_Id = dbObject.getRecord("user_leads", "user_id", "email", emailId);
        return user_Id;
    }
    /**
     * Data verification in Pre_Order Table for INR currency
     * 
     * @param className
     * @param course_id
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public <T> T dataVerificationInPreOrderTableForLeadProcess(@SuppressWarnings("rawtypes") Class className, String course_Id, String batchTitle) throws Exception {
        Database dbObject = new Database();
        
        String user_Id = dbObject.getRecord("user_leads", "user_id", new String[]{"email"}, new String[]{emailId});
        Assert.assertTrue(!(user_Id.equals(null)));
        propertyReader.updatePropertyTestData("User_ID", user_Id);
        System.out.println("User Id : "+user_Id);

        String course_id = dbObject.getRecord("pre_orders", "courseid", "userid", user_Id);
        Assert.assertTrue(course_id.contains(course_Id));

        String order_Id = dbObject.getRecord("pre_orders", "orderid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertTrue(!(order_Id.equals(null)));

        //batch id 
        String batch_Id = dbObject.getRecord("pre_orders", "userid", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        Assert.assertTrue(!(batch_Id.equals(null)));

        //batch title
        String batch_Title = dbObject.getRecord("pre_orders", "batchtitle", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        System.out.println("Actual batch title:"+batch_Title);
        System.out.println("Expected batch title:"+batchTitle);
        Assert.assertTrue(batch_Title.contains(batchTitle));
        
        String finalValue=dbObject.getRecord("pre_orders", "final_value", new String[]{"courseid", "userid"}, new String[]{course_Id,user_Id});
        System.out.println("finalValue :"+finalValue);
        Assert.assertTrue(!(finalValue.equals(null)));

        return (T) PageFactory.initElements(DriverHelper.driver, className);
    }
    public void getStudentCount(String title) throws Exception{
    	Database dbObject = new Database();
    	String studentCount_Query = propertyReader.readTestData("Query_Students_Count").replace("#", title);
    	//student_count 
    	String studentCount= dbObject.executeQuery(studentCount_Query);
    	propertyReader.updatePropertyTestData("StudentCount", studentCount);
    }
}