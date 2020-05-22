package vvssL5.features.scenariu;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.components.FileToUpload;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import vvssL5.features.pages.AddNewSnippet;
import vvssL5.features.pages.LoginPage;
import vvssL5.features.pages.MainPage;
import vvssL5.features.pages.UploadPage;

import java.util.List;

@RunWith(SerenityRunner.class)
public class TestScenariu {
    public static final String sessionId = "433617890001614681589258184053725592046";
    private String snippetValue = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam libero elit, faucibus non mauris sed, consequat porttitor justo. Praesent varius laoreet lacus nec lobortis. Pellentesque id mauris vulputate, dapibus quam ac, placerat felis.";

    public static final String user = "user";
    public static final String password = "parola";

    @Managed(driver="chrome", uniqueSession = true)
    private WebDriver webdriver;

    @Steps
    private LoginPage loginPage;
    @Steps
    private UploadPage uploadPage;
    @Steps
    private AddNewSnippet addNewSnippet;
    @Steps
    private MainPage mainPage;
    @Steps
    private Actor actor = Actor.named("Trambi");

    @Test
    public void TestScenario(){
        testLogin();
        testBtnAddNewSnippetLink();
        testAddNewSnippetForm();
        testBtnViewMySnippetLink();
        testDeleteSnippet();
        testUpload();
        testBtnLogout();
    }

    @Test
    public void testLogin() {
        loginPage.open();
        String currentUrl = webdriver.getCurrentUrl();
        actor.attemptsTo(Ensure.that(currentUrl.endsWith("login"))
                .isTrue());

        actor.attemptsTo(Ensure.that(loginPage.user.getAttribute("type").length())
                .isNotEqualTo(0));
        actor.attemptsTo(Ensure.that(loginPage.password.getAttribute("type").length())
                .isNotEqualTo(0));
        actor.attemptsTo(Ensure.that(loginPage.loginBtn.getAttribute("value").length())
                .isNotEqualTo(0));

        loginPage.enterUser(user);
        actor.attemptsTo(Ensure.that(loginPage.user.getTextValue())
                .isEqualTo(user));
        loginPage.enterPassword(password);
        actor.attemptsTo(Ensure.that(loginPage.password.getTextValue())
                .isEqualTo(password));
        loginPage.loginBtn.click();
        actor.attemptsTo(Ensure.that(webdriver.getCurrentUrl()).isNotEqualTo(currentUrl));
        String loggedUser = webdriver.findElement(By.className("menu-user")).getText();
        actor.attemptsTo(Ensure.that(loggedUser.contains(user+" <"+user+">")).isTrue());
    }
    @Test
    public void testBtnAddNewSnippetLink(){
        mainPage.open();
        String currentUrl = webdriver.getCurrentUrl();
        actor.attemptsTo(Ensure.that(mainPage.addSnippetBtn.getText().length())
               .isNotEqualTo(0));
        mainPage.onClickNewSnippetBtn();
        actor.attemptsTo(Ensure.that(webdriver.getCurrentUrl()).isNotEqualTo(currentUrl));
    }
    @Test
    public void testAddNewSnippetForm(){
        mainPage.open();
        mainPage.onClickMySnippetBtn();
        int countSnippets = getTrCount();
        addNewSnippet.open();
        String currentUrl = webdriver.getCurrentUrl();
        addNewSnippet.setTextAreaSnippet(snippetValue);
        actor.attemptsTo(Ensure.that(addNewSnippet.textAreaSnippet.getValue())
                .isEqualTo(snippetValue));
        addNewSnippet.onSubmit();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        actor.attemptsTo(Ensure.that(webdriver.getCurrentUrl()).isNotEqualTo(currentUrl));
        actor.attemptsTo(Ensure.that(countSnippets+1).isEqualTo(getTrCount()));
    }
    @Test
    public void testBtnViewMySnippetLink(){
        mainPage.open();
        String currentUrl = webdriver.getCurrentUrl();
        actor.attemptsTo(Ensure.that(mainPage.addSnippetBtn.getText().length())
                .isNotEqualTo(0));
        mainPage.onClickMySnippetBtn();
        actor.attemptsTo(Ensure.that(webdriver.getCurrentUrl()).isNotEqualTo(currentUrl));
    }
    @Test
    public void testBtnLogout(){
        mainPage.open();
        String currentUrl = webdriver.getCurrentUrl();
        mainPage.onClickLogoutBtn();
        actor.attemptsTo(Ensure.that(webdriver.getCurrentUrl()).isNotEqualTo(currentUrl));
    }
    @Test
    public void testUpload(){
        mainPage.open();
        mainPage.onClickUploadBtn();
        actor.attemptsTo(Ensure.that(webdriver.getCurrentUrl().contains("upload")).isTrue());

        FileToUpload fileToUpload = new FileToUpload(webdriver, "C:\\Users\\darab\\OneDrive\\Desktop\\vvss -serenityV2\\vvss-serenityV2\\src\\test\\resources\\narrative.txt");
        fileToUpload.fromLocalMachine().to(uploadPage.inputFile);

        uploadPage.onUpload();
        actor.attemptsTo(Ensure.that(webdriver.getCurrentUrl().contains("upload2")).isTrue());
        String uploadMessage = webdriver.findElement(By.className("content")).findElement(By.tagName("b")).getText();
        actor.attemptsTo(Ensure.that(uploadMessage.contains("File uploaded!")).isTrue());
    }

    public int getTrCount()
    {
        List<WebElement> snippets = webdriver.findElements(By.cssSelector("table tbody tr td a"));
        return snippets.size();
    }

    @Test
    public void testDeleteSnippet(){
        mainPage.open();
        String currentUrl = webdriver.getCurrentUrl();
        actor.attemptsTo(Ensure.that(mainPage.addSnippetBtn.getText().length())
                .isNotEqualTo(0));
        mainPage.onClickMySnippetBtn();
        int countSnippets = getTrCount();
        if(countSnippets>0) {
            WebElement deleteBtn = webdriver.findElement(By.cssSelector("table tbody tr:nth-child(2) td:nth-child(2) a"));
            deleteBtn.click();
            actor.attemptsTo(Ensure.that(countSnippets - 1).isEqualTo(getTrCount()));
        }
    }
}