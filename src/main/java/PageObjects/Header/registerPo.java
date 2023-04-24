package PageObjects.Header;

import PageObjects.BasePO.BasePoRF;
import org.openqa.selenium.By;


public class registerPo extends BasePoRF {


    private By registerAccountClickAici = By.cssSelector("a[title='Click aici']");
    private By radioButtonApelativ = By.cssSelector("label[for='salutation_3']");
    private By prenumeField = By.cssSelector("input[name='firstname']");
    private By numeField = By.cssSelector("input[name='lastname']");
    private By telefonField = By.cssSelector("input[name='phone']");
    private By emailField = By.cssSelector("input[name='email'][type='email']");
    private By parolaField = By.cssSelector("input[name='password_confirmation']");
    private By confParolaField = By.cssSelector("input[name='password']");
    private By confirmRegisteredAccount = By.cssSelector(".section-title.t-font-heading.t-heading-color");
    private By inregistrareButton = By.cssSelector("div[class='form-actions col-8'] button[type='submit']");

    public registerPo registerUser(String prenumeText, String numeText,
                                   String telefonText, String emailText, String parolaText, String confParolaText){

        goToUrl("https://www.remediumfarm.ro/?page=checkout#register");

        scrollPage(10, -30);
        click(radioButtonApelativ);
        setText(prenumeField, prenumeText);
        setText(numeField, numeText);
        setText(telefonField, telefonText);
        setText(emailField, emailText);
        setText(parolaField, parolaText);
        setText(confParolaField, confParolaText);
        click(inregistrareButton);
        return this;
    }
    public String getConfirmationRegisterMessage(){
        return getTextByText(confirmRegisteredAccount);
    }
}






//        click(contulMeuButton);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        waitForElement(registerAccountClickAici);
////        WebElement loginFrame = driver.findElement(By.cssSelector(".box-login.toggled > .inner"));
////        driver.switchTo().frame(loginFrame);
//////        switchToFramesByLocator(loginFrame);
//
//        click(registerAccountClickAici);
////        switchToDefaultFrame();