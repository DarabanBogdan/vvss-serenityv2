package vvssL5.features.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import vvssL5.features.scenariu.TestScenariu;

@DefaultUrl("https://google-gruyere.appspot.com/"+ TestScenariu.sessionId +"/login")
public class LoginPage extends PageObject {

    @FindBy(name="uid")
    public WebElementFacade user;

    @FindBy(name="pw")
    public WebElementFacade password;

    @FindBy(css="input[value='Login']")
    public WebElementFacade loginBtn;
    public void login() {
        loginBtn.click();
    }

    public void enterUser(String value) {
        user.type(value);
    }
    public void enterPassword(String value) {
        password.type(value);
    }
}