import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class DropDownTests {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest(){
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");
        driver = new ChromeDriver();
        driver.navigate().to("http://theinternet.przyklady.javastart.pl/dropdown");
    }

    @Test
    public void dropDownTest() {
        WebElement dropDownList = driver.findElement(By.id("dropdown"));
       Select select = new Select(dropDownList);

       String selectedOption = select.getFirstSelectedOption().getText();
       assertEquals(selectedOption,"Please select an option");

select.selectByValue("1");
sleep();
       selectedOption = select.getFirstSelectedOption().getText(); // trzeba powtorzyc bo pobiera na nowo element
assertEquals(selectedOption, "Option 1");
sleep();
select.selectByValue("2");
sleep();
        selectedOption = select.getFirstSelectedOption().getText();
assertEquals(selectedOption,"Option 2");


    }
    public void sleep(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterTest(){
        driver.close();
        driver.quit();
    }
}
