import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class baiTapThucHanh05 {
    ChromeDriver chromeDriver;
    String tryItButton = "//button[contains(text(),'Try it')]";
    String resultOfAlert = "demo";

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();

    }
    @Test
    public void exercise01() {
        chromeDriver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_alert");
        //wait(By.id("iframeResult"),10);
        chromeDriver.switchTo().frame("iframeResult");
        WebElement tryItButtonLocator = chromeDriver.findElement(By.xpath(tryItButton));
        tryItButtonLocator.click();
        Alert alert = chromeDriver.switchTo().alert();
        Assert.assertEquals(alert.getText(), "I am an alert box!");
        alert.accept();
        sleep(1000);
    }
    @Test
    public void exercise02(){
        chromeDriver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
        chromeDriver.switchTo().frame("iframeResult");
        WebElement tryItButtonLocator = chromeDriver.findElement(By.xpath(tryItButton));
        WebElement resultLocator = chromeDriver.findElement(By.id("demo"));
        tryItButtonLocator.click();
        Alert alert = chromeDriver.switchTo().alert();
        alert.accept();
        Assert.assertEquals(resultLocator.getText(),"You pressed OK!");
    }

    @Test
    public void exercise03(){
        chromeDriver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_prompt");
        chromeDriver.switchTo().frame("iframeResult");
        WebElement tryItButtonLocator = chromeDriver.findElement(By.xpath(tryItButton));
        WebElement resultLocator = chromeDriver.findElement(By.id("demo"));
        tryItButtonLocator.click();
        Alert alert = chromeDriver.switchTo().alert();
        alert.sendKeys("SW Test Academy");
        alert.accept();
        Assert.assertEquals(resultLocator.getText(),"Hello SW Test Academy! How are you today?");
    }
    
    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void wait (By by, int seconds){
        WebDriverWait driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(seconds));
        driverWait.until(new ExpectedCondition<WebElement>() {
            @Override
                    public WebElement apply(WebDriver d){
                return d.findElement(by);
            }
        });
    }

    @AfterTest
    public void closePage() {
        chromeDriver.close();
    }
}
