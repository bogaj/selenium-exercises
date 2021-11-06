import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MyActionTest {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://przyklady.javastart.pl/test/hover_mouse.html");

    }
@Test
    public void hoverSmileTest() {
        WebElement smileyIcon = driver.findElement(By.id("smiley"));
        WebElement smileyIconTwo = driver.findElement(By.id("smiley2"));
    Actions action = new Actions(driver);

    action.moveToElement(smileyIcon).click().build().perform();
    sleep();

    action.clickAndHold(smileyIconTwo).build().perform();
sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @AfterMethod
    public  void afterTest() {
        driver.close();
        driver.quit();
    }
}
