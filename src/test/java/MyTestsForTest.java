import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;

public class MyTestsForTest {
    private WebDriver driver;

    @BeforeMethod
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\DRIVERS\\chromedriver2.exe");

        driver = new ChromeDriver();
        driver.navigate().to("http://przyklady.javastart.pl/test/full_form.html");
    }

    @Test
    public void typingIntoWebElementTest() {

        WebElement userNameField = driver.findElement(By.id("username"));
        sleep();
        userNameField.sendKeys("Selenium Start");
        String typeUserNameValue = userNameField.getAttribute("value");
        sleep();
        assertEquals(typeUserNameValue, "Selenium Start");
    }

    @Test
    public void filePickingTest() {

        sleep();

        WebElement uploadFilePicker = driver.findElement(By.id("upload_file"));
        uploadFilePicker.sendKeys("D:\\test.txt");

        sleep();
    }

    @Test
    public void typingAndClearingValueInsideWebElementTest() {

        WebElement userNameField = driver.findElement(By.id("username"));
        sleep();
        userNameField.sendKeys("Selenium Start");

        String typeUserNameValue = userNameField.getAttribute("value");
        sleep();

        assertEquals(typeUserNameValue, "Selenium Start");

        userNameField.clear();
        sleep();

        String emptyUserNameField = userNameField.getAttribute("value");
        assertEquals(emptyUserNameField, "");
    }

    private void sleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void chekRadioButtonTest() {
        WebElement radioButtonMale = driver.findElement(By.cssSelector("input[value='male']"));
        WebElement radioButtonFemale = driver.findElement(By.cssSelector("input[value='female']"));
        sleep();
        radioButtonMale.click();
        sleep();
        assertTrue(radioButtonMale.isSelected());
        sleep();
        radioButtonFemale.click();
        sleep();
        assertTrue(radioButtonFemale.isSelected());
        assertFalse(radioButtonMale.isSelected());
    }

    @Test
    public void checkboxButtonTest() {
        WebElement checkboxPizza = driver.findElement(By.cssSelector("input[value='pizza']"));
        WebElement checkboxSpaghetti = driver.findElement(By.cssSelector("input[value='spaghetti']"));
        WebElement checkboxHamburger = driver.findElement(By.cssSelector("input[value='hamburger']"));

        assertFalse(checkboxPizza.isSelected());
        assertFalse(checkboxSpaghetti.isSelected());
        assertFalse(checkboxHamburger.isSelected());

        checkboxPizza.click();
        checkboxSpaghetti.click();
        checkboxHamburger.click();

        assertTrue(checkboxPizza.isSelected());
        assertTrue(checkboxSpaghetti.isSelected());
        assertTrue(checkboxHamburger.isSelected());
        sleep();
        checkboxPizza.click();
        checkboxSpaghetti.click();
        checkboxHamburger.click();
        sleep();
        assertFalse(checkboxPizza.isSelected());
        assertFalse(checkboxSpaghetti.isSelected());
        assertFalse(checkboxHamburger.isSelected());

    }

    @Test
    public void dropDownTest() {
        // Tworzymy WebElement odpowiadający kontrolce dropdown
        WebElement countryWebElement = driver.findElement(By.id("country"));
        //Tworzymy instancję klasy Select w konstruktorze podając WebElement odpowiadający kontrolce dropdown
        Select countryDropDown = new Select(countryWebElement);
        //Pobieramy listę wszystkich opcji z kontrolki countryDropDown, w formie listy WebElementów
        List<WebElement> options = countryDropDown.getOptions();
        //Tworzymy listę Stringów, która będzie nam potrzebna w trakcie wyciągania nazw z kontrolki dropdown
        List<String> namesOfOptions = new ArrayList<>();
        //Iterujemy przez całą listę opcji (options), wykorzystując pętle For Each
        for (WebElement option : options) {
            //Do listy Stringów (listy opcji), dodajemy kolejno nazwę każdej opcji dla zadanego webelementu
            //Wykorzystujemy do tego metodę getText() z Selenium
            namesOfOptions.add(option.getText());
            //Wypisujemy nazwę opcji na ekranie
            System.out.println(option.getText());
        }
//tworzymy aserację listy. porównanamy oczekiwane wartości do listy pobranej 'namesOfOptions'
        List<String> expectedNamesOfOptions = new ArrayList<>();
        expectedNamesOfOptions.add("Germany");
        expectedNamesOfOptions.add("Poland");
        expectedNamesOfOptions.add("UK");
        sleep();
        assertEquals(namesOfOptions, expectedNamesOfOptions);
    }
    //test na wybieranie opcji kontolki drop down
    @Test
    public void selectingOptionsFromDropDownTest(){
        WebElement countryWebElement = driver.findElement(By.id("country"));
        Select countryDropDown = new Select(countryWebElement);
        countryDropDown.selectByIndex(1);
        sleep();
        assertEquals(countryDropDown.getFirstSelectedOption().getText(),"Poland");

        countryDropDown.selectByValue("de_DE");

        sleep();

        assertEquals(countryDropDown.getFirstSelectedOption().getText(), "Germany");

        countryDropDown.selectByVisibleText("UK");
        sleep();
        assertEquals(countryDropDown.getFirstSelectedOption().getText(),"UK");
    }
    @Test
    public void checkIfElementsOnPageTest(){
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement emailLabel = driver.findElement(By.cssSelector("span[class='help-block']"));
        System.out.println("Is usernameField displayed: " + usernameField.isDisplayed());
        System.out.println("Is usernameField enabled: " + usernameField.isEnabled());

        System.out.println("Is passwordField displayed: " + passwordField.isDisplayed());
        System.out.println("Is passwordField enabled: " + passwordField.isEnabled());

        System.out.println("Is emailLabel displayed: " + emailLabel.isDisplayed());
        System.out.println("Is emailLabel enabled: " + emailLabel.isEnabled());

        assertTrue(usernameField.isDisplayed());
        assertTrue(passwordField.isDisplayed());
        assertTrue(emailLabel.isDisplayed());

        assertTrue(usernameField.isEnabled());
        assertFalse(passwordField.isEnabled());
    }
    @AfterMethod
    public void afterTest() {
        driver.close();
        driver.quit();
    }
}
