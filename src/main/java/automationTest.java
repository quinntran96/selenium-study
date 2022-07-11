import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class automationTest {
  ChromeDriver chromeDriver;

  @BeforeTest
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    chromeDriver = new ChromeDriver();
  }

  @Test
  public void test01() {
    chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");

    sleep(1000);
    WebElement input = chromeDriver.findElement(By.id("txtInput1"));
    String value = input.getAttribute("value");

    //    Assert.assertEquals(value, "Default value of input 2222");
    WebElement btn1 = chromeDriver.findElement(By.id("btnExample1"));
    //    WebElement btn2 = chromeDriver.findElement(By.className("btn-success"));
    WebElement text = chromeDriver.findElement(By.id("lbStatusButton"));
    WebElement textInput = chromeDriver.findElement(By.id("txtInput1"));
    WebElement select = chromeDriver.findElement(By.id("exampleSelect1"));
    List<WebElement> listOfOption = chromeDriver.findElements(By.tagName("option"));
    WebElement dropdownList = chromeDriver.findElement(By.id("exampleSelect1"));
    WebElement inputCheckBox1 = chromeDriver.findElement(By.id("defaultCheck1"));
    //        btn1.click();
    //        sleep(1000);

    //    WebElement btn2 = chromeDriver.findElement(By.className("btn-success"));
    //    btn2.click();
    //    sleep(5000);

    List<WebElement> btns = chromeDriver.findElements(By.className("btn-success"));

    //    for (int i = 0; i < btns.size(); i++) {
    //      btns.get(i).click();
    //      sleep(2000);
    //    }
    //    String textButton = text.getText();
    //    text.click();
    //    Assert.assertEquals(textButton, "Click on Button 1", "text wrong");
    //    textInput.clear();
    //    sleep(3000);
    //    textInput.sendKeys("Toi la LA");
    //    sleep(5000);
    //    String actualText = textInput.getAttribute("value");
    //    Assert.assertEquals(actualText, "Toi la LA", "text wrong");

    //    WebElement check1 = chromeDriver.findElement(By.id("defaultCheck1"));
    //    check1.click();
    //    sleep(2000);

    //    Select selectElement = new Select(select);
    //    selectElement.selectByIndex(1);
    //    sleep(3000);
    //    selectElement.selectByIndex(2);
    //    sleep(3000);
    //    selectElement.selectByValue("value5");
    //    sleep(3000);
    //    selectElement.selectByVisibleText("Option 4");
    //    sleep(3000);

    //    List<String> textOfOptions = new ArrayList<>();
    //    for (int i = 0; i < listOfOption.size(); i++) {
    //      String textOption = listOfOption.get(i).getText();
    //      textOfOptions.add(textOption);
    //    }
    //
    //    for (int i = 0; i < textOfOptions.size(); i++) {
    //      System.out.println(textOfOptions.get(i));
    //    }

    inputCheckBox1.click();
    sleep(3000);
    boolean isChecked = inputCheckBox1.isSelected();
    Assert.assertTrue(isChecked);

    // uncheck
    inputCheckBox1.click();
    isChecked = inputCheckBox1.isSelected();
    Assert.assertFalse(isChecked);
    
  }

  @Test
  public void test02() {
    chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
    String textLocator = "//div[text()='%s']";
    List<WebElement> buttons = chromeDriver.findElements(By.xpath("//button[contains(@onclick,'handleClick')]"));

    for (int i = 0; i < buttons.size(); i++) {
      buttons.get(i).click();
      sleep(2000);
      String text03 = "Click on Button " + (i + 1);
      String textLocator2 = String.format(textLocator, text03);
      WebElement textForButton = chromeDriver.findElement(By.xpath(textLocator2));
      System.out.println(textForButton.getText());
    }
  }

  @AfterTest
  public void cleanUp() {
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
