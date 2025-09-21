import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String BASE_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Setup full man hinh
        driver.manage().window().maximize();
    }


//    priority de chay theo thu tu mong muon
    @Test(priority = 1)
    public void testLoginSuccess() throws InterruptedException {
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(USERNAME);
        Thread.sleep(1000);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(PASSWORD);
        Thread.sleep(1000);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type ='submit']"));
        loginButton.click();
        Thread.sleep(3000);

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(BASE_URL)));
        Thread.sleep(2000);

//        Assert.assertNotEquals(BASE_URL, driver.getCurrentUrl());
        Assert.assertTrue(driver.getCurrentUrl().contains("/dashboard"));
        Thread.sleep(2000);
    }



    @Test(priority = 2)
    public void testLoginFail() throws InterruptedException {
        driver.get(BASE_URL);
        Thread.sleep(2000);

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys("Hahahah");
        Thread.sleep(1000);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("ABCD1234");
        Thread.sleep(1000);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type ='submit']"));
        loginButton.click();
        Thread.sleep(3000);


        Assert.assertEquals(driver.getCurrentUrl(), BASE_URL);
        Thread.sleep(2000);
    }

    @AfterTest
    public void tearDown() {
        if(driver != null) {
            driver.quit();
        }
    }
}
