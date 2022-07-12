import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class baiTapThucHanh02 {
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

        List<WebElement> buttons = chromeDriver.findElements(By.xpath("//button[contains(@onclick,'handleClick')]"));
        List<String> textOfButtons = new ArrayList<>();
        WebElement lbStatusButton = chromeDriver.findElement(By.xpath("//div[@id='lbStatusButton']"));

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
    public void baiTap2() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);

        List<WebElement> selectOptions = chromeDriver.findElements(By.xpath("//option[contains(@value,'value')]"));
        List<String> textOfOptions = new ArrayList<>();

        for (int i = 0; i < selectOptions.size(); i++) {

            String lb01 = selectOptions.get(i).getText();
            textOfOptions.add(lb01);

        }

        for (int i = 0; i < selectOptions.size(); i++) {

            System.out.println(textOfOptions.get(i));
            Assert.assertEquals(textOfOptions.get(i), "Option " + (i + 1));

        }
    }

    @Test
    public void baiTap3() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);

        WebElement dropdownListClick = chromeDriver.findElement(By.xpath("//a[@id='dropdownMenuLink']"));
        List<WebElement> dropdownList = chromeDriver.findElements(By.xpath("//span[contains(@onclick,'dropdownChange')]"));
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
            if (!Objects.equals(itemsExpect.get(i), itemsOfDropdown.get(i))) {
                isEqual = false;
            }
        }
        Assert.assertTrue(isEqual);
    }

    @Test
    public void baiTap4() {
        chromeDriver.get("https://auto.fresher.dev/lessons/lession7/index.html");
        sleep(2000);

        List<WebElement> radioButtons = chromeDriver.findElements(By.xpath("//input[@name='exampleRadios']"));
        String valuesOfRadioButton = "//label[@for='exampleRadios%s']";

        for (int i = 0; i < radioButtons.size(); i++) {

            WebElement radioButtonByItem = radioButtons.get(i);
            radioButtonByItem.click();

            String values = String.format(valuesOfRadioButton, (i + 1));
            WebElement labelOfRadioButton = chromeDriver.findElement(By.xpath(values));

            if (i == 0) {
                Assert.assertEquals(labelOfRadioButton.getText(), "Male");
            } else if (i == 1) {
                Assert.assertEquals(labelOfRadioButton.getText(), "Female");
            } else {
                Assert.assertEquals(labelOfRadioButton.getText(), "Gay");
            }
        }
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
