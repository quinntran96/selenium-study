import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class baiTapThucHanh03 {

    ChromeDriver chromeDriver;

    @BeforeTest
    public void setUpSketch() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
//        chromeDriver.get("https://www.sketch.com/signup");
    }

    @Test
    public void verifyLayoutOfSignUpPage() {
        chromeDriver.get("https://www.sketch.com/signup");
        sleep(2000);
        WebElement contentOfExistAccount = chromeDriver.findElement(
            By.xpath("//p[contains(@class,'kBDocM')]"));
        WebElement textOfSignInButton = chromeDriver.findElement(
            By.xpath("//a[contains(@class,'ckOmhQ')]//div[contains(@class,'eUPoJu')]"));
        WebElement signInButton = chromeDriver.findElement(
            By.xpath("//a[contains(@class,'ckOmhQ')]"));
        WebElement titleSignUp = chromeDriver.findElement(
            By.xpath("//h1[contains(@class,'dKHPES')]"));
        WebElement contentOf1stSection = chromeDriver.findElement(
            By.xpath("//label[contains(@class,'jdjpTR') and @for ='name-input']"));
        WebElement yourFirstNameTextBox = chromeDriver.findElement(
            By.xpath("//input[contains(@class,'gHtjd') and @type = 'text']"));
        WebElement selectYourRoleDropDown = chromeDriver.findElement(
            By.xpath("//span[@aria-haspopup='listbox']"));
        WebElement emailAddressTextBox = chromeDriver.findElement(
            By.xpath("//input[contains(@class,'gHtjd') and @type = 'email']"));
        WebElement passwordTextBox = chromeDriver.findElement(
            By.xpath("//input[contains(@class,'gHtjd') and @type = 'password']"));
        WebElement textOfTermOfServiceCheckBox = chromeDriver.findElement(By.xpath(
            "//input[@name='terms-of-service']//ancestor::div[contains(@class,'hUmSsM')]//following-sibling::div[contains(@class,'iDUryR')]/span[contains(@class,'eghTvo')]"));
        WebElement textOfPrivacyPolicy = chromeDriver.findElement(By.xpath(
            "//input[@name='privacy-policy']//ancestor::div[contains(@class,'hUmSsM')]//following-sibling::div[contains(@class,'iDUryR')]/span[contains(@class,'eghTvo')]"));
        WebElement textOfMarketingConsent = chromeDriver.findElement(By.xpath(
            "//input[@name='marketing-consent']//ancestor::div[contains(@class,'hUmSsM')]//following-sibling::div[contains(@class,'iDUryR')]/span[contains(@class,'eghTvo')]"));
        WebElement textOfSignUpButton = chromeDriver.findElement(
            By.xpath("//button[contains(@class,'gRyjND')]/div[contains(@class,'eUPoJu')]"));
        WebElement signUpButton = chromeDriver.findElement(
            By.xpath("//button[contains(@class,'gRyjND')]"));
        //verify content: "Already have an account?"
        Assert.assertEquals(contentOfExistAccount.getText(), "Already have an account?");
        //Verify text of Sign In button
        Assert.assertEquals(textOfSignInButton.getText(), "Sign In");
        //Verify Sign In button is enable
        signInButton.isEnabled();
        //Verify title
        Assert.assertEquals(titleSignUp.getText(), "Your Best Work Starts Here");
        //Verify label of first section
        Assert.assertEquals(contentOf1stSection.getText(), "What should we call you?");
        // Verify placeholder first name text-box
        Assert.assertEquals(yourFirstNameTextBox.getAttribute("placeholder"), "Your first name");
        // Verify first name text-box default is blank
        Assert.assertEquals(yourFirstNameTextBox.getAttribute("value"), "");
        //Verify placeholder Select your role
        Assert.assertEquals(selectYourRoleDropDown.getText(), "Select your role");
        //Verify placeholder Email Address
        Assert.assertEquals(emailAddressTextBox.getAttribute("placeholder"), "Email address");
        //Verify Email Address default blank
        Assert.assertEquals(emailAddressTextBox.getAttribute("value"), "");
        //Verify placeholder Password
        Assert.assertEquals(passwordTextBox.getAttribute("placeholder"), "Create a password");
        //Verify Password default blank
        Assert.assertEquals(passwordTextBox.getAttribute("value"), "");
        //Verify text of TermOfService
        Assert.assertEquals(textOfTermOfServiceCheckBox.getText(),
            "I have read and agree to the Terms of Service");
        //Verify text of Privacy Policy
        Assert.assertEquals(textOfPrivacyPolicy.getText(),
            "I have read and agree to the Privacy Policy");
        //Verify text of marketing checkbox
        Assert.assertEquals(textOfMarketingConsent.getText(), "Keep me updated on news and offers");
        //Verify text of Sign Up button
        Assert.assertEquals(textOfSignUpButton.getText(), "Sign Up");
        //Verify Sign Up button is enable
        signUpButton.isEnabled();
    }

    @Test
    public void verifyYourFirstNameTextBox() {
        chromeDriver.get("https://www.sketch.com/signup");
        WebElement yourFirstNameTextBox = chromeDriver.findElement(
            By.xpath("//input[contains(@class,'gHtjd') and @type = 'text']"));
        yourFirstNameTextBox.sendKeys("Tran Lan Anh");
        sleep(2000);
        Assert.assertEquals(yourFirstNameTextBox.getAttribute("value"), "Tran Lan Anh");
        //yourFirstNameTextBox.clear();
    }

    @Test
    public void verifySelectYourRoleDropdownList() {
        chromeDriver.get("https://www.sketch.com/signup");
        WebElement selectYourRoleDropDown = chromeDriver.findElement(
            By.xpath("//span[@aria-haspopup='listbox']"));
        List<WebElement> optionsOfDropdownList = chromeDriver.findElements(
            By.xpath("//li[@role='option']"));
        ArrayList<String> actualOptionsOfDropdownList = new ArrayList<>();
        ArrayList<String> expectedOptionsOfDropdownList = new ArrayList<>();
        expectedOptionsOfDropdownList.add("Select your role");
        expectedOptionsOfDropdownList.add("Designer");
        expectedOptionsOfDropdownList.add("Design System Manager");
        expectedOptionsOfDropdownList.add("Manager/Design Team Lead");
        expectedOptionsOfDropdownList.add("Product");
        expectedOptionsOfDropdownList.add("Developer");
        expectedOptionsOfDropdownList.add("Marketing Specialist");
        expectedOptionsOfDropdownList.add("Writer");
        expectedOptionsOfDropdownList.add("Finance/Admin");
        expectedOptionsOfDropdownList.add("Researcher");
        expectedOptionsOfDropdownList.add("Other");

        selectYourRoleDropDown.click();
        for (int i = 0; i < optionsOfDropdownList.size(); i++) {
            actualOptionsOfDropdownList.add(optionsOfDropdownList.get(i).getText());
        }
        for (int i = 1; i < optionsOfDropdownList.size(); i++) {
            optionsOfDropdownList.get(i).click();
            selectYourRoleDropDown.click();
            sleep(2000);
        }

//        boolean isEqual = true;
//        for (int i = 0; i < optionsOfDropdownList.size(); i++) {
//            if (!actualOptionsOfDropdownList.get(i).equals(expectedOptionsOfDropdownList.get(i))) {
//                isEqual = false;
//            }
//        }
        Assert.assertEquals(actualOptionsOfDropdownList, expectedOptionsOfDropdownList);
//        Assert.assertTrue(isEqual);

    }

    @DataProvider(name = "dataemail")
    public Object[][] email() {
        return new Object[][]{{"anhttl201196@gmail.com"}, {"123"}};
    }

    @Test(dataProvider = "dataemail")
    public void verifyEmailTextBox(String email) {
        chromeDriver.get("https://www.sketch.com/signup");
        WebElement emailAddressTextBox = chromeDriver.findElement(
            By.xpath("//input[contains(@class,'gHtjd') and @type = 'email']"));
        emailAddressTextBox.sendKeys(email);
        Assert.assertEquals(emailAddressTextBox.getAttribute("value"), email);
    }

    @Test
    public void verifyPassword() {
        chromeDriver.get("https://www.sketch.com/signup");
        WebElement passwordTextBox = chromeDriver.findElement(
            By.xpath("//input[contains(@class,'gHtjd') and @type = 'password']"));
        passwordTextBox.sendKeys("Lananh96");
        String valuePass = passwordTextBox.getAttribute("value");
    }

    @Test
    public void verifyTermsOfServiceCheckBox() {
        chromeDriver.get("https://www.sketch.com/signup");
        sleep(1000);
        WebElement termOfServiceCheckBox = chromeDriver.findElement(
            By.id("marketing-consent-input"));

        clickElement(termOfServiceCheckBox);

        sleep(10000);
    }

    public void scrollToElement(WebElement element) {
        Actions actions = new Actions(chromeDriver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void clearText(WebElement element) {
        element.clear();
        String text = element.getAttribute("value");
        if (text.length() > 0) {
            sendBackSpace(element, text.length());
        }
    }

    public String getValue(WebElement element) {
        String text = element.getAttribute("value");
        if (text.length() > 0) {
            return text;
        }
        // CHUA CHAC
        return element.getAttribute("text");
    }

    public void sendBackSpace(WebElement element, int length) {
        for (int i = 0; i < length + 5; i++) {
            element.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void clickElement(WebElement element) {
        sleep(100);
        scrollToElement(element);
        sleep(100);
        element.click();
        sleep(100);
    }

    public void clickElementJs(WebElement webElement) {
        JavascriptExecutor executor = (JavascriptExecutor) chromeDriver;
        executor.executeScript("arguments[0].click();", webElement);
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
