import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class windowHandle {
    ChromeDriver chromeDriver;
    String tryItButton = "//button[contains(text(),'Try it')]";
    String resultOfAlert = "demo";

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();

    }
    @Test
    public void testWindowUsingTitle(){
        //Store WindowHandle of parent browser window
        String parentWorldId = chromeDriver.getWindowHandle();
        //Clicking Visit us button will open Visit Us Page in a new child
        chromeDriver.findElement(By.id("visitbutton")).click();
        //Get Handles of all the open windows
        //Iterate through list and check if tile of each window matches with expected window title
        try{
            for(String windowId : chromeDriver.getWindowHandles()){
                String title = chromeDriver.switchTo().window(windowId).getTitle();
                if(title.equals("Visit Us")){
                    assertEquals ("Visit Us", chromeDriver.getTitle());
                    //Close the Visit Us window
                    chromeDriver.close();
                    break;
                }
            }
        } finally {
            //Switch to the parent browser window
            chromeDriver.switchTo().window(parentWorldId);
        }
        //Check driver context is in parent browser window
        assertEquals("Build my Card - Configuration", chromeDriver.getTitle());
    }

}
