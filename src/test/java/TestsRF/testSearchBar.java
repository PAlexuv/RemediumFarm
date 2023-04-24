package TestsRF;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class testSearchBar extends BaseForTestingRF{
    @BeforeSuite
    public void acceptCookies(){
        baseHeader.acceptCookie();
    }

    @Test(description = "[Cauta produse] Verify user is able to find products when is searching by relevant keyword")
    public void searchFieldValidInput(){
        searchBar.searchBarValid();
        String validInputs = searchBar.confirmValidSearch();
        Assert.assertEquals(validInputs, "Tinctura rozmarin x 50ml (Dacia Pl)");
    }
}
