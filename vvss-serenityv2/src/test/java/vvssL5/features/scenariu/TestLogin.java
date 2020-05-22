package vvssL5.features.scenariu;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.*;
import org.junit.Test;
import org.openqa.selenium.interactions.Actions;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SerenityRunner.class)
public class TestLogin {

    @Managed(driver="chrome", uniqueSession = true)
    private WebDriver webdriver;

    private List<String[]> allInfo = getInfoFromCsv();

    private List<String[]> getInfoFromCsv() {
        BufferedReader bufferedReader = null;
        String line = "";
        String separatedBy = ",";
        List<String[]> allInfo = new ArrayList<>();
        try {
            String path = "src\\test\\resources\\WikiTestData.csv";
            bufferedReader = new BufferedReader(new FileReader(path));
            while ((line = bufferedReader.readLine()) != null) {
                String[] info = line.split(separatedBy);
                allInfo.add(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return allInfo;
    }
    @Test
    public void loginTestFailed() {
        webdriver.manage().window().maximize();
        webdriver.get("https://google-gruyere.appspot.com/"+TestScenariu.sessionId+"/login");
        WebElement username = webdriver.findElement(By.name("uid"));
        WebElement password = webdriver.findElement(By.name("pw"));
        WebElement login = webdriver.findElement(By.cssSelector("input[value='Login']"));

        Actions actions = new Actions(webdriver);
        String userFromCSV = allInfo.get(0)[0];
        String passFromCSV = allInfo.get(0)[1];
        username.sendKeys(userFromCSV);
        password.sendKeys(passFromCSV);
        actions.moveToElement(login).perform();
        actions.click(login).perform();
        String expectedURL = "https://google-gruyere.appspot.com/"+TestScenariu.sessionId+"/login?uid="+userFromCSV+"&pw="+passFromCSV;
        String actualURL = webdriver.getCurrentUrl();
        System.out.println("EXPECT: " + expectedURL);
        System.out.println("ACTUAL: "+ actualURL);
        Assert.assertEquals(expectedURL, actualURL);
    }

    @Test
    public void loginTestSuccessfull() {
        webdriver.manage().window().maximize();
        webdriver.get("https://google-gruyere.appspot.com/"+TestScenariu.sessionId+"/login");

        WebElement username = webdriver.findElement(By.name("uid"));
        WebElement password = webdriver.findElement(By.name("pw"));
        WebElement login = webdriver.findElement(By.cssSelector("input[value='Login']"));

        Actions actions = new Actions(webdriver);
        String userFromCSV = allInfo.get(1)[0];
        String passFromCSV = allInfo.get(1)[1];
        username.sendKeys(userFromCSV);
        password.sendKeys(passFromCSV);
        actions.moveToElement(login).perform();
        actions.click(login).perform();
        String expectedURL = "https://google-gruyere.appspot.com/"+TestScenariu.sessionId+"/login?uid="+userFromCSV+"&pw="+passFromCSV;
        String actualURL = webdriver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualURL);
    }

}

