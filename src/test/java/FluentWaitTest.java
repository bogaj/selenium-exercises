import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class FluentWaitTest {

    private WebDriver driver;

    @BeforeMethod
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://theinternet.przyklady.javastart.pl/dynamic_loading/1");


    }

    @Test
    public void fluentWaitTest(){
        WebElement helloWorldSign = driver.findElement(By.id("finish"));
        assertFalse(helloWorldSign.isDisplayed());
        WebElement startButton = driver.findElement(By.xpath("//*[@id='start']/button"));
        startButton.click();
        FluentWait <WebDriver> fluentWait = new FluentWait<>(driver);
        fluentWait.withTimeout(Duration.ofSeconds(10))
        .pollingEvery(Duration.ofMillis(250))
        .until(ExpectedConditions.visibilityOf(helloWorldSign));
        assertTrue(helloWorldSign.isDisplayed());
    }
    @AfterMethod
    public void afterTest(){
        driver.close();
        driver.quit();
    }
}
