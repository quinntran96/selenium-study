import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
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
    System.out.println("hello");
    chromeDriver.get("http://google.com");
    sleep(5000);
  }

  @AfterTest
  public void cleanUp() {
    chromeDriver.close();
  }

  private void sleep(int time){
    try{
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
