package PageObjects.Header;

import PageObjects.BasePO.BasePoRF;
import org.openqa.selenium.By;

public class loginPo extends BasePoRF {

    private By userField = By.cssSelector("input[name='cs_customer_email']");
    private By passField = By.cssSelector("input[name='cs_customer_password']");
    private By loginButton = By.cssSelector("div[class='col-offset-3 col-9'] button[type='submit']");
    private By confirmationLogin = By.cssSelector(".section-title.t-font-heading.t-heading-color");

    public loginPo loginUser(String username, String parola) {

        goToUrl("https://www.remediumfarm.ro/?page=checkout");

        setText(userField, username);
        setText(passField, parola);
        click(loginButton);
        return this;
    }

    public String confirmLoggedIn(){
        return getTextByText(confirmationLogin);
    }

}
