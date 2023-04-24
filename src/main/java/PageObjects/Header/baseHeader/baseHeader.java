package PageObjects.Header.baseHeader;

import PageObjects.BasePO.BasePoRF;
import PageObjects.Header.*;
import org.openqa.selenium.By;

public class baseHeader extends BasePoRF {

    private By logoButton = By.cssSelector("#logo");
    private By searchBarField = By.xpath("//div[@class='input-section']");
    private By contulMeuButton = By.cssSelector("#login-link");
    private By favoriteButton = By.cssSelector(".wishlist-products-section.left");
    private By cosulMeuButton = By.cssSelector(".box-cart.left.boxcartdefault");
    private By acceptCookieButton = By.cssSelector("input[value='Sunt de acord']");

    public void acceptCookie(){
        acceptCookies(acceptCookieButton);
    }

    public logoPo clickLogoPo(){
    click(logoButton);
    return new logoPo();
    }

    public searchBarPo clickSearchBar(){
        click(searchBarField);
        return new searchBarPo();
    }

    public contulMeuPo clickContulMeu(){
        click(contulMeuButton);
        return new contulMeuPo();
    }

    public favoritePo clickFavorite(){
        click(favoriteButton);
        return new favoritePo();
    }

    public cosulMeuPo clickCosulMeu(){
        click(cosulMeuButton);
        return new cosulMeuPo();
    }
}
