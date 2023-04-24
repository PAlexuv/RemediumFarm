package TestsRF;

import Utils.DataProviderMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testLogin extends BaseForTestingRF{

    @BeforeMethod
    public void acceptCookie(){
        baseHeader.acceptCookie();
    }

    @Test(description = "[Autentificare] Verify user is able to login using valid data",
    dataProviderClass = DataProviderMethod.class, dataProvider = "dpLoginValidInput")
    public void testLoginValidData(String validLoginData){

        String[] loginValidData = validLoginData.split(",");
        login.loginUser(loginValidData[0], loginValidData[1]);

        String confirmLoggedIn = login.confirmLoggedIn();
        Assert.assertEquals(confirmLoggedIn, "Datele mele");
    }


}
