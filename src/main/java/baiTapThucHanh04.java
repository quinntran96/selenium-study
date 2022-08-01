import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class baiTapThucHanh04 {
    ChromeDriver chromeDriver;
    List<WebElement> dragItems = chromeDriver.findElements(By.xpath("//span[contains(text(),'Draggable')]"));
    WebElement dropZone = chromeDriver.findElement(By.id("mydropzone"));
    List<WebElement> droppedItems = chromeDriver.findElements(By.id("droppedlist"));

    @BeforeTest
    public void accessToSite() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }

    @Test
    public void exercise01() {
    chromeDriver.get("https://demo.seleniumeasy.com/drag-and-drop-demo.html");
    
    }

    @AfterTest
    public void closePage() {
        chromeDriver.close();
    }

    private void sleep (int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
