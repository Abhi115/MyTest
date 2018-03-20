package mobile.sample.test;

import com.mobizio.mobile.core.AppiumFactory;
import com.mobizio.mobile.ui.android.LoginUI;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class AndroidSampleTest {

  @Test
  public void testApp() throws Exception {

    AppiumFactory appiumFactory = new AppiumFactory();
    AppiumDriver<MobileElement> appiumDriver = appiumFactory.androidSetUp();
    LoginUI loginPage = new LoginUI(appiumDriver);
    PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, 15, TimeUnit.SECONDS), loginPage);

    loginPage.loginInApp("Tetwire", "Test", "Password");

  }

}
