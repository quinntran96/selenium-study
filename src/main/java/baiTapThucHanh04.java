import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

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
        chromeDriver.get("https://demo.guru99.com/test/drag_drop.html");
        WebElement dragItems = chromeDriver.findElement(By.id("credit1"));
        WebElement dropZone = chromeDriver.findElement(By.id("loan"));
        List<WebElement> droppedItems = chromeDriver.findElements(By.id("droppedlist"));

        sleep(2000);
        Actions builder = new Actions(chromeDriver);
        builder.dragAndDrop(dragItems, dropZone).build().perform();

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
        WebElement seeAlertButton = chromeDriver.findElement(
            By.xpath("//button[@ondblclick='myFunction()']"));
        Assert.assertEquals(seeAlertButton.getText(), "Double-Click Me To See Alert");
        doubleClick(seeAlertButton);
        sleep(1000);
        Alert alert = chromeDriver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void exercise03() {
        chromeDriver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement clickMe = chromeDriver.findElement(
            By.xpath("//span[contains(text(),'right click me')]"));
        WebElement editITem = chromeDriver.findElement(By.xpath("//span[contains(text(),'Edit')]"));
        Assert.assertEquals(clickMe.getText(), "right click me");
        Actions actions = new Actions(chromeDriver);
        actions.contextClick(clickMe).moveToElement(editITem).click().perform();
        sleep(1000);
        Alert alert = chromeDriver.switchTo().alert();
        alert.accept();
        alert.dismiss();
        alert.getText();
        alert.sendKeys("");
    }

    @Test
    public void exercise04() {
        chromeDriver.get("http://automationpractice.com/index.php");
        chromeDriver.manage().window().maximize();
        //WebElement womenTab = chromeDriver.findElement(By.xpath("//a[@title='Women']"));
        //clickElementJs(womenTab);

        waitElementExisted(By.xpath("//a[@title='Women']"), 2);
        WebElement womenTab = chromeDriver.findElement(By.xpath("//a[@title='Women']"));
        clickElementJs(womenTab);
        WebElement subItemsIsTops = chromeDriver.findElement(By.xpath("//div[contains(@class,'block_content')]//ul[contains(@class,'tree dynamized')]//li//a[contains(text(),'Tops')]"));
        WebElement subItemsISDresses = chromeDriver.findElement(By.xpath("//div[contains(@class,'block_content')]//ul[contains(@class,'tree dynamized')]//li//a[contains(text(),'Dresses') and @href ='http://automationpractice.com/index.php?id_category=8&controller=category']"));
        verifyJs(subItemsIsTops);
        verifyJs(subItemsISDresses);
        clickElementJs(subItemsIsTops);
        chromeDriver.navigate().back();
        verifyJs(subItemsIsTops);
        verifyJs(subItemsISDresses);
        chromeDriver.navigate().forward();

        WebElement subItemsIsTShirts = chromeDriver.findElement(By.xpath("//div[contains(@class,'block_content')]//ul[contains(@class,'tree dynamized')]//li//a[contains(text(),'T-shirts')]"));

        waitElementExisted(By.xpath("//abc"), 10);

//        WebElement subItemsIsTops = chromeDriver.findElement(By.xpath(
//            "(//span[@class='grower CLOSE']/following-sibling::a[contains(text(),'Tops')])[1]"));
//        WebElement subItemsISDresses = chromeDriver.findElement(By.xpath(
//            "(//span[@class='grower CLOSE']/following-sibling::a[contains(text(),'Dresses')])[1]"));
//
//        clickElementJs(subItemsIsTops);
//        chromeDriver.navigate().back();
//        chromeDriver.navigate().forward();
//        WebElement subItemsIsTShirts = chromeDriver.findElement(By.xpath(
//            "//div[contains(@class,'block_content')]//ul[contains(@class,'tree dynamized')]//li//a[contains(text(),'T-shirts')]"));
//
//        sleep(1000);

    }

    @Test
    public void iframe(){
        chromeDriver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_iframe");
        chromeDriver.switchTo().frame("iframeResult");
        WebElement textFrame0 = chromeDriver.findElement(By.xpath("//body/h1[contains(text(),'iframe')]"));
        String expectedText = textFrame0.getText();
        System.out.println(expectedText);
    }

    public void verifyJs (WebElement webElement){
        JavascriptExecutor executor = (JavascriptExecutor) chromeDriver;
        executor.executeScript("return document.title");
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

    public WebElement waitElementExisted(By by, int seconds) {
        WebDriverWait driverWait = new WebDriverWait(chromeDriver, Duration.ofSeconds(seconds));

        return driverWait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver d) {
                return d.findElement(by);
            }
        });
    }

    public void tearDown() {
        chromeDriver.quit();
    }
}
