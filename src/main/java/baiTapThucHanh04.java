import com.sun.imageio.plugins.wbmp.WBMPImageReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import java.sql.Driver;
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
        WebElement dragItems = chromeDriver.findElement(By.xpath("//span[contains(text(),'Draggable 1')]"));
        WebElement dropZone = chromeDriver.findElement(By.id("mydropzone"));
        List<WebElement> droppedItems = chromeDriver.findElements(By.id("droppedlist"));

        sleep(2000);
        Actions builder = new Actions(chromeDriver);
        builder.dragAndDrop(dragItems, dropZone).perform();
        sleep(2000);

        /*for (int i = 0; i < dragItems.size(); i++) {
            String textItems = String.format(textLocator, (i + 1));
            WebElement item = chromeDriver.findElement(By.xpath(textItems));
            builder.dragAndDrop(item, dropZone).perform();
            sleep(2000);*/
        // Assert.assertEquals(droppedItems.get(i).getText(),"Draggable "+ (i+1));
        // }
    }

    @Test
    public void exercise02() {
        chromeDriver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement seeAlertButton = chromeDriver.findElement(By.xpath("//button[@ondblclick='myFunction()']"));
        Assert.assertEquals(seeAlertButton.getText(), "Double-Click Me To See Alert");
        doubleClick(seeAlertButton);
        sleep(1000);
        Alert alert = chromeDriver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void exercise03() {
        chromeDriver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement clickMe = chromeDriver.findElement(By.xpath("//span[contains(text(),'right click me')]"));
        WebElement editITem = chromeDriver.findElement(By.xpath("//span[contains(text(),'Edit')]"));
        Assert.assertEquals(clickMe.getText(), "right click me");
        Actions actions = new Actions(chromeDriver);
        actions.contextClick(clickMe).moveToElement(editITem).click().perform();
        sleep(1000);
        Alert alert = chromeDriver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void exercise04() {
        chromeDriver.get("http://automationpractice.com/index.php");
        chromeDriver.manage().window().maximize();
        WebElement womenTab = chromeDriver.findElement(By.xpath("//a[@title='Women']"));
        //WebElement topsSubTab = chromeDriver.findElement(By.xpath("//li[@class='sfHoverForce']//ul[@class='submenu-container clearfix first-in-line-xs']//a[@title='Tops']"));
        clickElementJs(womenTab);
        sleep(1000);
    }

    public void clickElementJs(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) chromeDriver;
        executor.executeScript("arguments[0].click();", webElement);
    }

    public void doubleClick(WebElement element) {
        Actions builder = new Actions(chromeDriver);
        builder.doubleClick(element).perform();
    }


    //@AfterTest
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
