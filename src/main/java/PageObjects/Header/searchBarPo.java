package PageObjects.Header;

import PageObjects.BasePO.BasePoRF;
import org.openqa.selenium.By;

public class searchBarPo extends BasePoRF {

    private By searchField = By.cssSelector(".input-section");
    private By confirmationText = By.cssSelector("h2[class='product-name'] a[title='Tinctura rozmarin x 50ml (Dacia Pl)']");

    public void searchBarValid(){
        setText(searchField, "rozmarin");
        clickEnter(searchField);
    }
    public String confirmValidSearch(){
        return getTextByText(confirmationText);
    }
}
