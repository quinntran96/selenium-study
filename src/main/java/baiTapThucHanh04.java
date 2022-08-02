import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class baiTapThucHanh04 {
    ChromeDriver chromeDriver;
    String textLocator = "//span[contains(text(),'Draggable %s')]";

    @BeforeTest
    public void accessToSite() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }


    @Test
    public void exercise01() {
        chromeDriver.get("https://demo.seleniumeasy.com/drag-and-drop-demo.html");
        List<WebElement> dragItems = chromeDriver.findElements(By.xpath("//span[contains(text(),'Draggable')]"));
        WebElement dropZone = chromeDriver.findElement(By.id("mydropzone"));
        List<WebElement> droppedItems = chromeDriver.findElements(By.id("droppedlist"));

        sleep(2000);
        Actions builder = new Actions(chromeDriver);
        for (int i = 0; i < dragItems.size(); i++) {
            String textItems = String.format(textLocator, (i + 1));
            WebElement item = chromeDriver.findElement(By.xpath(textItems));
            builder.dragAndDrop(item, dropZone).perform();
            sleep(2000);
           // Assert.assertEquals(droppedItems.get(i).getText(),"Draggable "+ (i+1));
        }
    }

    @AfterTest
    public void closePage() {
        chromeDriver.close();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
