package ru.academits;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SeleniumWebDriverManagerTests {

    @Test
    public void openDemoQaTest() {
        WebDriver driver = null;

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        String url = "https://demoqa.com/";

        driver.get(url);
        driver.manage().window().maximize();

        System.out.println("Current URL= " + driver.getCurrentUrl());
        Assertions.assertEquals(url, driver.getCurrentUrl());

        driver.quit();
    }

    @Test
    public void openQa() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        String url = "https://demoqa.com/automation-practice-form";
        driver.manage().window().maximize();
        driver.get(url);

        Assertions.assertEquals(url, driver.getCurrentUrl());

        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);

        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
        firstNameInput.sendKeys("Yuliya");

        Assertions.assertEquals("Yuliya", firstNameInput.getAttribute("value"));

        WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
        lastNameInput.sendKeys("Volkova");

        Assertions.assertEquals("Volkova", lastNameInput.getAttribute("value"));

        WebElement emailInput = driver.findElement(By.cssSelector("#userEmail"));
        emailInput.sendKeys("test@gmail.com");

        Assertions.assertEquals("test@gmail.com", emailInput.getAttribute("value"));

        WebElement genderRadioButton = driver.findElement(By.xpath("//input[@id='gender-radio-2']/parent::div"));
        genderRadioButton.click();

        Assertions.assertTrue(driver.findElement(By.xpath("//input[@id='gender-radio-2']/parent::div/input")).isSelected());

        WebElement mobileInput = driver.findElement(By.cssSelector("input#userNumber"));
        mobileInput.sendKeys("1234567899");

        Assertions.assertEquals("1234567899", mobileInput.getAttribute("value"));

        WebElement dateOfBirthInput = driver.findElement(By.cssSelector("input#dateOfBirthInput"));
        dateOfBirthInput.click();

        Assertions.assertTrue(dateOfBirthInput.isEnabled());

        WebElement selectMonthDropdown = driver.findElement(By.cssSelector("select.react-datepicker__month-select"));
        Select select = new Select(selectMonthDropdown);
        select.selectByVisibleText("June");
        List<WebElement> allOptions = select.getOptions();

        Assertions.assertEquals("June", allOptions.get(5).getText());

        WebElement selectYearDropdown = driver.findElement(By.cssSelector("select.react-datepicker__year-select"));
        Select select1 = new Select(selectYearDropdown);
        select1.selectByVisibleText("2005");

        Assertions.assertEquals("2005", selectYearDropdown.getAttribute("value"));

        WebElement dayOfBirthInput = driver.findElement(By.xpath("//div[@aria-label='Choose Friday, June 17th, 2005']"));
        dayOfBirthInput.click();
        WebElement fullDateOfBirth = driver.findElement(By.cssSelector("input#dateOfBirthInput"));

        Assertions.assertEquals("17 Jun 2005", fullDateOfBirth.getAttribute("value"));

        WebElement subjectsInput = driver.findElement(By.cssSelector("input#subjectsInput"));
        subjectsInput.sendKeys("g");

        Assertions.assertEquals("g", subjectsInput.getAttribute("value"));

        WebElement selectSubjects = driver.findElement(By.xpath("//div[contains(text(), 'Biology')]"));
        selectSubjects.click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        WebElement selectSubjects1 = driver.findElement(By.xpath("//div[contains(text(), 'Biology')]"));

        Assertions.assertEquals("Biology", selectSubjects1.getText());

        WebElement hobbiesCheckBox = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']/div[2]/div[1]"));
        hobbiesCheckBox.click();

        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id='hobbiesWrapper']/div[2]/div[1]/input")).isSelected());

        WebElement chooseFileButton = driver.findElement(By.cssSelector("input#uploadPicture"));
        Path path = Paths.get("C:/Users/Gleb/IdeaProjects/4-selenium WeDriverBase/School.PNG");

        Assertions.assertTrue(Files.exists(path));

        chooseFileButton.sendKeys("C:/Users/Gleb/IdeaProjects/4-selenium WeDriverBase/School.PNG");
        Assertions.assertTrue(Files.exists(path));

        WebElement currentAddressInput = driver.findElement(By.cssSelector("#currentAddress"));
        currentAddressInput.sendKeys("Novosibirsk");

        Assertions.assertEquals("Novosibirsk", currentAddressInput.getAttribute("value"));


        WebElement stateInput = driver.findElement(By.cssSelector("input#react-select-3-input"));
        stateInput.sendKeys("n");

        Assertions.assertEquals("n", stateInput.getAttribute("value"));

        WebElement stateSelect = driver.findElement(By.xpath("//div[contains(text(), 'NCR')]"));
        stateSelect.click();
        WebElement stateSelect1 = driver.findElement(By.xpath("//div[contains(text(), 'NCR')]"));

        Assertions.assertEquals("NCR", stateSelect1.getText());

        WebElement cityInput = driver.findElement(By.cssSelector("input#react-select-4-input"));
        cityInput.sendKeys("d");

        Assertions.assertEquals("d", cityInput.getAttribute("value"));

        WebElement citySelect = driver.findElement(By.xpath("//*[contains(text(), 'Delhi')]"));
        citySelect.click();
        WebElement citySelect1 = driver.findElement(By.xpath("//*[contains(text(), 'Delhi')]"));

        Assertions.assertEquals("Delhi", citySelect1.getText());

        WebElement bookStoreDropdown = driver.findElement(By.xpath("//div[contains(text(), 'Book Store Application')]"));
        bookStoreDropdown.click();

        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();

        WebDriverWait wait = new WebDriverWait(driver, 30, 500);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submit")));

        WebElement submitButton = driver.findElement(By.cssSelector("#submit"));
        submitButton.click();

        WebElement nameValue = driver.findElement(By.xpath("//td[contains (text(), 'Yuliya Volkova')]"));
        Assertions.assertEquals("Yuliya Volkova", nameValue.getText());

        WebElement nameStudentValue = driver.findElement(By.xpath("//td[contains (text(), 'test@gmail.com')]"));
        Assertions.assertEquals("test@gmail.com", nameStudentValue.getText());

        WebElement genderValue = driver.findElement(By.xpath("//td[contains (text(), 'Female')]"));
        Assertions.assertEquals("Female", genderValue.getText());

        WebElement mobileValue = driver.findElement(By.xpath("//td[contains (text(), '1234567899')]"));
        Assertions.assertEquals("1234567899", mobileValue.getText());

        WebElement dateOfBirthValue = driver.findElement(By.xpath("//td[contains (text(), '17 June,2005')]"));
        Assertions.assertEquals("17 June,2005", dateOfBirthValue.getText());

        WebElement subjectsValue = driver.findElement(By.xpath("//td[contains (text(), 'Biology')]"));
        Assertions.assertEquals("Biology", subjectsValue.getText());

        WebElement hobbiesValue = driver.findElement(By.xpath("//td[contains (text(), 'Sports')]"));
        Assertions.assertEquals("Sports", hobbiesValue.getText());

        WebElement pictureValue = driver.findElement(By.xpath("//td[contains (text(), 'School.PNG')]"));
        Assertions.assertEquals("School.PNG", pictureValue.getText());

        WebElement addressValue = driver.findElement(By.xpath("//td[contains (text(), 'Novosibirsk')]"));
        Assertions.assertEquals("Novosibirsk", addressValue.getText());

        WebElement stateAndCityValue = driver.findElement(By.xpath("//td[contains (text(), 'NCR Delhi')]"));
        Assertions.assertEquals("NCR Delhi", stateAndCityValue.getText());

        Actions action1 = new Actions(driver);
        action1.sendKeys(Keys.PAGE_DOWN).build().perform();

        WebDriverWait wait1 = new WebDriverWait(driver, 30, 500);
        wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#submit")));

        WebElement closeButton = driver.findElement(By.cssSelector("#closeLargeModal"));
        closeButton.click();

        driver.quit();
    }
}





