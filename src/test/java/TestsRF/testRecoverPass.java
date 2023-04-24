package TestsRF;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

public class testRecoverPass extends BaseForTestingRF{

    @BeforeMethod
    public void acceptCookie(){
        baseHeader.acceptCookie();
    }

    @Test(description ="[Autentificare] [Ai uitat parola?] Verify user is able to reset a forgotten password")
    public void testRecoverPass(){
        contulMeu.forgottenPassword();
        String confirmationMessage =  contulMeu.confirmForgottenPass();
        assertTrue(confirmationMessage.contains("RemediumFarm.ro v-a trimis"));
    }
}
