package CustomerManagement;

import static com.mobizio.Utilities.Constants.BRANCH_ADMIN_ROLE;

import com.mobizio.datamodel.ModelGenerator;
import com.mobizio.datamodel.UserModel;
import com.mobizio.dataproviders.DataProviders;
import com.mobizio.selenium.framework.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

/*==============================================================================================================================
 File Name    : MB_8554.java
 ClassName    : MB_8554
 Summary      : Verify that branch admin is able to add relation between client and other user.
 ===============================================================================================================================
 History      :   Company            Date            Action
                  360logica                         Initial Version

 ===============================================================================================================================
 Remarks      : test_MB_8554_VerifyThatBranchAdminIsAbleToAddRelationBetweenClientAndOtherUser
 ===============================================================================================================================*/

public class MB_8554 extends BaseTest {

  private UserModel userModel;

  @Factory(dataProvider = "ServerLogin", dataProviderClass = DataProviders.class)
  public MB_8554(String browser) {
    super(browser, new String[]{BRANCH_ADMIN_ROLE});
  }

  @BeforeMethod
  public void initData() throws Exception {
    userModel = ModelGenerator.generateNewUserDetails(false);
  }

  @Test(description = "Verify that branch admin is able to add relation between client and other user.")
  public void test_MB_8554_VerifyThatBranchAdminIsAbleToAddRelationBetweenClientAndOtherUser() throws Exception {

    for (String loggedInUserType : loggedInUserTypes) {

    }
  }
}
