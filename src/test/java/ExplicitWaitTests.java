import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ExplicitWaitTests {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://theinternet.przyklady.javastart.pl/dynamic_controls");
    }

    @Test
    public void waitForDisappearingElement() {
        WebElement checkbox = driver.findElement(By.id("checkbox"));

        assertFalse(checkbox.isSelected());
        assertTrue(checkbox.isDisplayed());

        WebElement removeButton = driver.findElement(By.xpath("//*[@id='checkbox-example']/button"));
        removeButton.click();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.invisibilityOf(checkbox)); // od razu wykona po tym jak zniknie nie musi czekac
        webDriverWait.until(ExpectedConditions.textToBe(By.id("message"), "It's gone!"));

        WebElement addButton = driver.findElement(By.xpath("//*[@id='checkbox-example']/button"));
        addButton.click();

        WebDriverWait webDriverWait2 = new WebDriverWait(driver, Duration.ofSeconds(15));

        webDriverWait2.until(ExpectedConditions.presenceOfElementLocated(By.id("checkbox")));
        WebElement checkbox2 = driver.findElement(By.id("checkbox")); //po zaladowaniu treba znalezc raz jeszcze
        assertFalse(checkbox2.isSelected());
        assertTrue(checkbox2.isDisplayed());


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
