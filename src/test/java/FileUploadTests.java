import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class FileUploadTests {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://theinternet.przyklady.javastart.pl/upload");
    }

    @Test
    public void fileUploadTest() {
        WebElement buttonFieldUpload = driver.findElement(By.id("file-upload"));
        WebElement buttonSubmitUpload = driver.findElement(By.id("file-submit"));
        buttonFieldUpload.sendKeys("D:\\testfile.txt");
sleep();
buttonSubmitUpload.click();
sleep();
WebElement fileTextNameAfterUpload = driver.findElement(By.id("uploaded-files"));

assertEquals(fileTextNameAfterUpload.getText(), "testfile.txt");
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
