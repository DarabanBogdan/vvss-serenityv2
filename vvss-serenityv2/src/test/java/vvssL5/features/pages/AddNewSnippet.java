package vvssL5.features.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;
import vvssL5.features.scenariu.TestScenariu;

@DefaultUrl("https://google-gruyere.appspot.com/"+ TestScenariu.sessionId +"/newsnippet.gtl")
public class AddNewSnippet extends PageObject {
    @FindBy(name="snippet")
    public WebElementFacade textAreaSnippet;
    @FindBy(css="input[value='Submit']")
    public WebElementFacade submitBtn;

    public void setTextAreaSnippet(String value){
        textAreaSnippet.type(value);
    }

    public void onSubmit(){
        submitBtn.click();
    }
}
