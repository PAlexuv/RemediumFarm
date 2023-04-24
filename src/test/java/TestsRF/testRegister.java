package TestsRF;

import Utils.DataProviderMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testRegister extends BaseForTestingRF {
    @BeforeMethod
    public void clickContulMeu(){
        baseHeader.acceptCookie();
//        baseHeader.clickContulMeu();
    }

    @Test(description = "[Inregistrare] Verify user is able to create a new account using valid data",
    dataProviderClass = DataProviderMethod.class, dataProvider = "dpRegisterValidInput")
    public void testValidRegisterAccount(String validRegData){

        String[] regValidInputs = validRegData.split(",");
        register.registerUser(regValidInputs[0], regValidInputs[1], regValidInputs[2], regValidInputs[3], regValidInputs[4], regValidInputs[5]);

        String confirmMessage = register.getConfirmationRegisterMessage();
        Assert.assertEquals(confirmMessage, "Autentificare");
    }
}
