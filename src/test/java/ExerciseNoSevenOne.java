import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ExerciseNoSevenOne {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://theinternet.przyklady.javastart.pl/checkboxes");

    }

    @Test
    public void testCheckbox() {
        WebElement firstCheckbox = driver.findElement(By.xpath("//*[@id=\'checkboxes\']/input[1]"));
        WebElement secondCheckbox = driver.findElement(By.xpath("//*[@id=\'checkboxes\']/input[2]"));

        assertFalse(firstCheckbox.isSelected());
        assertTrue(secondCheckbox.isSelected());
        sleep();
        firstCheckbox.click();
        secondCheckbox.click();
        sleep();
        assertTrue(firstCheckbox.isSelected());
        assertFalse(secondCheckbox.isSelected());
        sleep();
    }

    public void sleep() {
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
