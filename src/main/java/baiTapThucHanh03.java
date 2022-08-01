import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class baiTapThucHanh03 {
    ChromeDriver chromeDriver;

    @BeforeTest
    public void setUpSketch() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.get("https://www.sketch.com/signup");
    }

    @Test
    public void verifyLayoutOfSignUpPage() {
        chromeDriver.get("https://www.sketch.com/signup");
        sleep(2000);
        WebElement contentOfExistAccount = chromeDriver.findElement(By.xpath("//p[contains(@class,'kBDocM')]"));
        WebElement textOfSignInButton = chromeDriver.findElement(By.xpath("//a[contains(@class,'ckOmhQ')]//div[contains(@class,'eUPoJu')]"));
        WebElement signInButton = chromeDriver.findElement(By.xpath("//a[contains(@class,'ckOmhQ')]"));
        WebElement titleSignUp = chromeDriver.findElement(By.xpath("//h1[contains(@class,'dKHPES')]"));
        WebElement contentOf1stSection = chromeDriver.findElement(By.xpath("//label[contains(@class,'jdjpTR') and @for ='name-input']"));
        WebElement yourFirstNameTextBox = chromeDriver.findElement(By.xpath("//input[contains(@class,'gHtjd') and @type = 'text']"));
        WebElement selectYourRoleDropDown = chromeDriver.findElement(By.xpath("//span[@aria-haspopup='listbox']"));
        WebElement emailAddressTextBox = chromeDriver.findElement(By.xpath("//input[contains(@class,'gHtjd') and @type = 'email']"));
        WebElement passwordTextBox = chromeDriver.findElement(By.xpath("//input[contains(@class,'gHtjd') and @type = 'password']"));
        WebElement textOfTermOfServiceCheckBox = chromeDriver.findElement(By.xpath("//input[@name='terms-of-service']//ancestor::div[contains(@class,'hUmSsM')]//following-sibling::div[contains(@class,'iDUryR')]/span[contains(@class,'eghTvo')]"));
        WebElement textOfPrivacyPolicy = chromeDriver.findElement(By.xpath("//input[@name='privacy-policy']//ancestor::div[contains(@class,'hUmSsM')]//following-sibling::div[contains(@class,'iDUryR')]/span[contains(@class,'eghTvo')]"));
        WebElement textOfMarketingConsent = chromeDriver.findElement(By.xpath("//input[@name='marketing-consent']//ancestor::div[contains(@class,'hUmSsM')]//following-sibling::div[contains(@class,'iDUryR')]/span[contains(@class,'eghTvo')]"));
        WebElement textOfSignUpButton = chromeDriver.findElement(By.xpath("//button[contains(@class,'gRyjND')]/div[contains(@class,'eUPoJu')]"));
        WebElement signUpButton = chromeDriver.findElement(By.xpath("//button[contains(@class,'gRyjND')]"));
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
        Assert.assertEquals(textOfTermOfServiceCheckBox.getText(), "I have read and agree to the Terms of Service");
        //Verify text of Privacy Policy
        Assert.assertEquals(textOfPrivacyPolicy.getText(), "I have read and agree to the Privacy Policy");
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
        WebElement yourFirstNameTextBox = chromeDriver.findElement(By.xpath("//input[contains(@class,'gHtjd') and @type = 'text']"));
        yourFirstNameTextBox.sendKeys("Tran Lan Anh");
        yourFirstNameTextBox.sendKeys("a");
        String value = yourFirstNameTextBox.getAttribute("value");
        for (int i = 0; i < value.length() + 5 ; i++) {
            yourFirstNameTextBox.sendKeys(Keys.BACK_SPACE);
        }
        sleep(5000);
       // Assert.assertEquals(yourFirstNameTextBox.getAttribute("value"), "Tran Lan Anh");
        //yourFirstNameTextBox.clear();
    }

    @Test
    public void verifySelectYourRoleDropdownList() {
        WebElement selectYourRoleDropDown = chromeDriver.findElement(By.xpath("//span[@aria-haspopup='listbox']"));
        List<WebElement> optionsOfDropdownList = chromeDriver.findElements(By.xpath("//li[@role='option']"));
        ArrayList<String> listOptionsOfDropdownList = new ArrayList<>();
        ArrayList<String> listOptionsExpectation = new ArrayList<>();
        listOptionsExpectation.add("Select your role");
        listOptionsExpectation.add("Designer");
        listOptionsExpectation.add("Design System Manager");
        listOptionsExpectation.add("Manager/Design Team Lead");
        listOptionsExpectation.add("Product");
        listOptionsExpectation.add("Developer");
        listOptionsExpectation.add("Marketing Specialist");
        listOptionsExpectation.add("Writer");
        listOptionsExpectation.add("Finance/Admin");
        listOptionsExpectation.add("Researcher");
        listOptionsExpectation.add("Other");
        selectYourRoleDropDown.click();
        for (int i = 0; i < optionsOfDropdownList.size(); i++) {
            listOptionsOfDropdownList.add(optionsOfDropdownList.get(i).getText());
        }
        for (int i = 1; i < optionsOfDropdownList.size(); i++) {
            optionsOfDropdownList.get(i).click();
            selectYourRoleDropDown.click();
            sleep(2000);
        }
        boolean isEqual = true;
        for (int i = 0; i < optionsOfDropdownList.size(); i++) {
            if (!Objects.equals(listOptionsOfDropdownList.get(i), listOptionsExpectation.get(i))) {
                isEqual = false;
            }
        }
        Assert.assertTrue(isEqual);

    }

    @Test
    public void verifyEmailTextBox() {
        WebElement emailAddressTextBox = chromeDriver.findElement(By.xpath("//input[contains(@class,'gHtjd') and @type = 'email']"));
        emailAddressTextBox.sendKeys("anhttl201196@gmail.com");
        Assert.assertEquals(emailAddressTextBox.getAttribute("value"), "anhttl201196@gmail.com");
    }

    @Test
    public void verifyPassword() {
        WebElement passwordTextBox = chromeDriver.findElement(By.xpath("//input[contains(@class,'gHtjd') and @type = 'password']"));
        passwordTextBox.sendKeys("Lananh96");
    }

    @Test
    public void verifyTermsOfServiceCheckBox() {
        chromeDriver.get("https://www.sketch.com/signup");
        WebElement termOfServiceCheckBox = chromeDriver.findElement(By.xpath("//input[@type='checkbox' and @name='terms-of-service']"));
        termOfServiceCheckBox.click();
        sleep(5000);
        /*boolean isSelected = termOfServiceCheckBox.isSelected();
        if (!isSelected) {
            termOfServiceCheckBox.click();
            sleep(2000);
        }
        Assert.assertTrue(true);*/
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
