import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class HoverTests {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://theinternet.przyklady.javastart.pl/hovers");
    }

    @Test
    public void hoverTest() {
        WebElement firstAvatar = driver.findElement(By.xpath("//*[@id='content']/div/div[1]"));
        WebElement secondAvatar = driver.findElement(By.xpath("//*[@id='content']/div/div[2]"));
        WebElement thirdAvatar = driver.findElement(By.xpath("//*[@id='content']/div/div[3]"));

        Actions action = new Actions(driver);
        action.moveToElement(firstAvatar).perform();

        WebElement firstUserName = driver.findElement(By.xpath("//div[1]/div/h5"));
        WebElement secondUserName = driver.findElement(By.xpath("//div[2]/div/h5"));
        WebElement thirdUserName = driver.findElement(By.xpath("//div[3]/div/h5"));
        assertEquals(firstUserName.getText(), "name: user1");
        assertEquals(secondUserName.getText(), "");
        assertEquals(thirdUserName.getText(), "");
        sleep();

        action.moveToElement(secondAvatar).perform();
        assertEquals(firstUserName.getText(), "");
        assertEquals(secondUserName.getText(), "name: user2");
        assertEquals(thirdUserName.getText(), "");
        sleep();
        action.moveToElement(thirdAvatar).perform();
        assertEquals(firstUserName.getText(), "");
        assertEquals(secondUserName.getText(), "");
        assertEquals(thirdUserName.getText(), "name: user3");
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
    public void afterTest() {
        driver.close();
        driver.quit();
    }
}
