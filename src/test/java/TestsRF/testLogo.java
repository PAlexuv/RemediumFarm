package TestsRF;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testLogo extends BaseForTestingRF{

    @Test(description = "click on logo to reach Homepage")
    public void logoToHomepage(){
        baseHeader.acceptCookie();
        System.out.println("page title: " + getPageTitle());
        baseHeader.clickLogoPo();
        System.out.println("page title: " + getPageTitle());
       String title = getPageTitle();
        Assert.assertEquals(title, "Farmacie online si lant de farmacii din Cluj-Napoca - Remediumfarm");
    }
}
