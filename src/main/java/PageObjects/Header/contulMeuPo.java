package PageObjects.Header;

import PageObjects.BasePO.BasePoRF;
import org.openqa.selenium.By;

public class contulMeuPo extends BasePoRF {

    private By forgotPassButton = By.cssSelector("a[title='Ai uitat parola?']");
    private By forgotPassMailField = By.cssSelector("input[name='forgotemail']");
    private By forgotPassSendMailButton = By.cssSelector("div[class='col-12'] button[type='submit']");
    private By forgotPassConfirmationText = By.cssSelector(".notice.success");

    public contulMeuPo forgottenPassword(){
        goToUrl("https://www.remediumfarm.ro/?page=checkout");
        click(forgotPassButton);
        setText(forgotPassMailField, "alexandrupascal@yahoo.com");
        click(forgotPassSendMailButton);
        return this;
    }
    public String confirmForgottenPass(){
        return getTextByText(forgotPassConfirmationText);
    }

}
