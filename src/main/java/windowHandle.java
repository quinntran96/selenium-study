import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class windowHandle {
    ChromeDriver chromeDriver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();

    }
    @Test
    public void testWindowUsingTitle(){
        chromeDriver.get("https://www.w3schools.com/html/#gsc.tab=0");
        //WebElement body = chromeDriver.findElement(By.xpath("//body"));
        //body.sendKeys(Keys.CONTROL, "t");
        ((JavascriptExecutor)chromeDriver).executeScript("window.open(\"https://www.google.com/\")");
        ((JavascriptExecutor)chromeDriver).executeScript("window.open(\"https://shopee.sg/?gclid=EAIaIQobChMI8o_Hxce3-QIVjXwrCh0vdgoVEAAYASAAEgIJUPD_BwE\")");


        //Store WindowHandle of parent browser window
        String parentWorldId = chromeDriver.getWindowHandle();
        //Clicking Visit us button will open Visit Us Page in a new child
//        chromeDriver.findElement(By.id("visitbutton")).click();
        //Get Handles of all the open windows
        //Iterate through list and check if tile of each window matches with expected window title
        try{
            for(String windowId : chromeDriver.getWindowHandles()){
                String title = chromeDriver.switchTo().window(windowId).getTitle();
                System.out.println(title);
//                if(title.equals("Google")){
//                    assertEquals ("Google", chromeDriver.getTitle());
                    //Close the Visit Us window
//                    chromeDriver.close();
//                    break;
//                }
            }
        } finally {
            //Switch to the parent browser window
            chromeDriver.switchTo().window(parentWorldId);
//            chromeDriver.close();
        }
        //Check driver context is in parent browser window
//        assertEquals("Build my Card - Configuration", chromeDriver.getTitle());
    }

}
