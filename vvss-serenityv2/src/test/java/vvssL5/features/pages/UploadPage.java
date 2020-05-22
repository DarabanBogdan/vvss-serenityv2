package vvssL5.features.pages;

import net.thucydides.core.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import vvssL5.features.scenariu.TestScenariu;

@DefaultUrl("https://google-gruyere.appspot.com/"+ TestScenariu.sessionId +"/upload.gtl")
public class UploadPage extends PageObject {

    @FindBy(name="upload_file")
    public WebElementFacade inputFile;

    @FindBy(css="input[value='Upload']")
    public WebElementFacade uploadBtn;

    public void onUpload(){
        uploadBtn.click();
    }
}