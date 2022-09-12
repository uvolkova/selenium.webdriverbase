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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.Keys.PAGE_DOWN;

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

        String firstName = "Yuliya";
        String lastName = "Volkova";
        String name = firstName +" "+ lastName;
        String email = "test@gmail.com";
        String mobile = "1234567899";
        String subjects = "Biology";
        String currentAddress = "Novosibirsk";
        String dateOfBirth = "17 Jun 2005";
        String hobbies = "Sports";
        String gender = "Female";
        String picture = "School.PNG";
        String state = "NCR";
        String city = "Delhi";
        String stateAndCity = state +" "+ city;
        String month = "June";
        String year = "2005";


        WebElement firstNameInput = driver.findElement(By.cssSelector("#firstName"));
        firstNameInput.sendKeys(firstName);

        WebElement lastNameInput = driver.findElement(By.cssSelector("#lastName"));
        lastNameInput.sendKeys(lastName);

        WebElement emailInput = driver.findElement(By.cssSelector("#userEmail"));
        emailInput.sendKeys(email);

        WebElement genderRadioButton = driver.findElement(By.xpath("//input[@id='gender-radio-2']/parent::div"));
        genderRadioButton.click();

        Assertions.assertTrue(driver.findElement(By.xpath("//input[@id='gender-radio-2']/parent::div/input")).isSelected());

        WebElement mobileInput = driver.findElement(By.cssSelector("input#userNumber"));
        mobileInput.sendKeys(mobile);

        WebElement dateOfBirthInput = driver.findElement(By.cssSelector("input#dateOfBirthInput"));
        dateOfBirthInput.click();

        WebElement selectMonthDropdown = driver.findElement(By.cssSelector("select.react-datepicker__month-select"));
        Select select = new Select(selectMonthDropdown);
        select.selectByVisibleText(month);

        WebElement selectYearDropdown = driver.findElement(By.cssSelector("select.react-datepicker__year-select"));
        Select select1 = new Select(selectYearDropdown);
        select1.selectByVisibleText(year);

        WebElement dayOfBirthInput = driver.findElement(By.xpath("//div[@aria-label='Choose Friday, June 17th, 2005']"));
        dayOfBirthInput.click();
        WebElement fullDateOfBirth = driver.findElement(By.cssSelector("input#dateOfBirthInput"));

        Assertions.assertEquals(dateOfBirth, fullDateOfBirth.getAttribute("value"));

        WebElement subjectsInput = driver.findElement(By.cssSelector("input#subjectsInput"));
        subjectsInput.sendKeys("g");

        WebElement selectSubjects = driver.findElement(By.xpath("//div[contains(text(), 'Biology')]"));
        selectSubjects.click();

        WebElement hobbiesCheckBox = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']/div[2]/div[1]"));
        hobbiesCheckBox.click();

        Assertions.assertTrue(driver.findElement(By.xpath("//*[@id='hobbiesWrapper']/div[2]/div[1]/input")).isSelected());

        File file = new File("src/School.PNG");
        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        selectPictureButton.sendKeys(file.getAbsolutePath());
        Assertions.assertTrue(Files.exists(Path.of("src/School.PNG")));

        WebElement currentAddressInput = driver.findElement(By.cssSelector("#currentAddress"));
        currentAddressInput.sendKeys(currentAddress);

        WebElement stateInput = driver.findElement(By.cssSelector("input#react-select-3-input"));
        stateInput.sendKeys("n");


        WebElement stateSelect = driver.findElement(By.xpath("//div[contains(text(), 'NCR')]"));
        stateSelect.click();

        WebElement cityInput = driver.findElement(By.cssSelector("input#react-select-4-input"));
        cityInput.sendKeys("d");

        WebElement citySelect = driver.findElement(By.xpath("//*[contains(text(), 'Delhi')]"));
        citySelect.click();

        WebElement bookStoreDropdown = driver.findElement(By.xpath("//div[contains(text(), 'Book Store Application')]"));
        bookStoreDropdown.click();

        WebDriverWait wait = new WebDriverWait(driver, 30, 100);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

        Actions action = new Actions(driver);
        action.sendKeys(Keys.PAGE_DOWN).build().perform();

        WebDriverWait wait2 = new WebDriverWait(driver, 30, 100);
        wait2.until(ExpectedConditions.elementToBeClickable(By.id("submit")));

        WebElement submitButton = driver.findElement(By.cssSelector("#submit"));
        submitButton.click();

        WebElement nameValue = driver.findElement(By.xpath("//td[contains (text(), 'Yuliya Volkova')]"));
        Assertions.assertEquals(name, nameValue.getText());

        WebElement nameStudentValue = driver.findElement(By.xpath("//td[contains (text(), 'test@gmail.com')]"));
        Assertions.assertEquals(email, nameStudentValue.getText());

        WebElement genderValue = driver.findElement(By.xpath("//td[contains (text(), 'Female')]"));
        Assertions.assertEquals(gender, genderValue.getText());

        WebElement mobileValue = driver.findElement(By.xpath("//td[contains (text(), '1234567899')]"));
        Assertions.assertEquals(mobile, mobileValue.getText());

        WebElement dateOfBirthValue = driver.findElement(By.xpath("//td[contains (text(), '17 June,2005')]"));
        Assertions.assertEquals("17 June,2005", dateOfBirthValue.getText());

        WebElement subjectsValue = driver.findElement(By.xpath("//td[contains (text(), 'Biology')]"));
        Assertions.assertEquals(subjects, subjectsValue.getText());

        WebElement hobbiesValue = driver.findElement(By.xpath("//td[contains (text(), 'Sports')]"));
        Assertions.assertEquals(hobbies, hobbiesValue.getText());

        WebElement pictureValue = driver.findElement(By.xpath("//td[contains (text(), 'School.PNG')]"));
        Assertions.assertEquals(picture, pictureValue.getText());

        WebElement addressValue = driver.findElement(By.xpath("//td[contains (text(), 'Novosibirsk')]"));
        Assertions.assertEquals(currentAddress, addressValue.getText());

        WebElement stateAndCityValue = driver.findElement(By.xpath("//td[contains (text(), 'NCR Delhi')]"));
        Assertions.assertEquals(stateAndCity, stateAndCityValue.getText());

        WebDriverWait wait3 = new WebDriverWait(driver, 30, 500);
        wait3.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#closeLargeModal")));

        Actions action1 = new Actions(driver);
        action1.sendKeys(Keys.PAGE_DOWN).build().perform();

        WebDriverWait wait1 = new WebDriverWait(driver, 30, 500);
        wait1.until(ExpectedConditions.elementToBeClickable(By.cssSelector("#closeLargeModal")));

        WebElement closeButton = driver.findElement(By.cssSelector("#closeLargeModal"));
        closeButton.click();

        driver.quit();
    }
}





