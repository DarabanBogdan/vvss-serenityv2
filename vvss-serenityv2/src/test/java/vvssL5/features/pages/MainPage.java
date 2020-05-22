package vvssL5.features.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import vvssL5.features.scenariu.TestScenariu;

@DefaultUrl("https://google-gruyere.appspot.com/"+ TestScenariu.sessionId +"/login?uid="+ TestScenariu.user+"&pw="+ TestScenariu.password)
public class MainPage extends PageObject{

    @FindBy(css="a[href='/"+ TestScenariu.sessionId +"/newsnippet.gtl']")
    public WebElementFacade addSnippetBtn;

    @FindBy(css="a[href='/"+ TestScenariu.sessionId +"/snippets.gtl']")
    public WebElementFacade mySnippetBtn;

    @FindBy(css="a[href='/"+ TestScenariu.sessionId +"/upload.gtl']")
    public WebElementFacade uploadBtn;

    @FindBy(css="a[href='/"+ TestScenariu.sessionId +"/logout']")
    public WebElementFacade logoutBtn;

    public void onClickNewSnippetBtn(){
        addSnippetBtn.click();
    }
    public void onClickMySnippetBtn(){
        mySnippetBtn.click();
    }
    public void onClickUploadBtn(){
        uploadBtn.click();
    }
    public void onClickLogoutBtn(){
        logoutBtn.click();
    }
}
