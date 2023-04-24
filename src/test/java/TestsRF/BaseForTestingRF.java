package TestsRF;

import PageObjects.BasePO.BasePoRF;
import PageObjects.Header.baseHeader.*;
import PageObjects.Header.*;
import Utils.TestListener;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public class BaseForTestingRF extends BasePoRF {

    protected baseHeader baseHeader;
    protected contulMeuPo contulMeu;
    protected registerPo register;
    protected loginPo login;
    protected cosulMeuPo cosulMeu;
    protected favoritePo favorite;
    protected logoPo logo;
    protected searchBarPo searchBar;


    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        //assert that we are on the correct page of website is working
        Assert.assertTrue(gotoHomepage(), "Unable to navigate to " + baseUrl);
        baseHeader = new baseHeader();
        contulMeu = new contulMeuPo();
        cosulMeu = new cosulMeuPo();
        favorite = new favoritePo();
        logo = new logoPo();
        searchBar = new searchBarPo();
        register = new registerPo();
        login = new loginPo();
    }


    @AfterSuite(alwaysRun = true)
    public void tearDown(){
        closeBrowser();
    }
}
