import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.framework.qual.EnsuresQualifierIf;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.*;

public class test1 {
    ChromeDriver chromeDriver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
    }

    @Test
    public void baiTap1() {

        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);
        List<WebElement> buttons = chromeDriver.findElements(By.cssSelector("button[class='btn btn-success'], button[class='btn btn-danger']"));
        List<String> textOfButtons = new ArrayList<>();

        for (int i = 0; i < buttons.size(); i++) {
            String text01 = buttons.get(i).getText();
            textOfButtons.add(text01);
        }
        for (int i = 0; i < textOfButtons.size(); i++) {
            System.out.println(textOfButtons.get(i));
        }
    }

    @Test
    public void baiTap2() {

        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);
        List<WebElement> buttons = chromeDriver.findElements(By.cssSelector("button[class='btn btn-success'], button[class='btn btn-danger']"));
        List<String> textOfButtons = new ArrayList<>();
        WebElement lbStatusButton = chromeDriver.findElement(By.id("lbStatusButton"));

        for (int i = 0; i < buttons.size(); i++) {
            String text01 = buttons.get(i).getText();
            textOfButtons.add(text01);
        }

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
            sleep(2000);
            String text02 = lbStatusButton.getText();
            Assert.assertEquals(text02, "Click on Button " + (i + 1));
        }
    }

    @Test
    public void baiTap3() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);
        List<WebElement> selectOptions = chromeDriver.findElements(By.tagName("option"));
        List<String> textOfOptions = new ArrayList<>();
        for (int i = 0; i < selectOptions.size(); i++) {
            String lb01 = selectOptions.get(i).getText();
            textOfOptions.add(lb01);
        }

        for (int i = 0; i < selectOptions.size(); i++) {
            System.out.println(textOfOptions.get(i));
        }

    }

    @Test
    public void baiTap4() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);
        List<WebElement> selectOptions = chromeDriver.findElements(By.tagName("option"));
        List<String> textOfOptions = new ArrayList<>();
        for (int i = 0; i < selectOptions.size(); i++) {
            String lb01 = selectOptions.get(i).getText();
            textOfOptions.add(lb01);
        }

        for (int i = 0; i < selectOptions.size(); i++) {
            Assert.assertEquals(textOfOptions.get(i), "Option " + (i + 1));
        }
    }

    @Test
    public void baiTap5() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);
        WebElement dropdownListClick = chromeDriver.findElement(By.id("dropdownMenuLink"));
        List<WebElement> dropdownList = chromeDriver.findElements(By.className("dropdown-item"));
        List<String> itemsOfDropdown = new ArrayList<>();

        String[] expectItems = new String[]{"Action 1", "Action 2", "Action 3"};
        List<String> itemsExpect = Arrays.asList(expectItems);


        dropdownListClick.click();

        for (int i = 0; i < dropdownList.size(); i++) {
            dropdownList.get(i).click();
            dropdownListClick.click();
            sleep(3000);
       }
        for (int i = 0; i < dropdownList.size(); i++) {
            itemsOfDropdown.add(dropdownList.get(i).getText());
        }

        boolean isEqual = true;
        for (int i = 0; i < dropdownList.size(); i++) {
                if(!Objects.equals(itemsExpect.get(i), itemsOfDropdown.get(i))) {
                    isEqual = false;
            }
        }
        Assert.assertTrue(isEqual);
    }


    @AfterTest
    public void closeSite() {
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
