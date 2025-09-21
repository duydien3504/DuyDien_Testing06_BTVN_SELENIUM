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

public class DashboardTest {
    private WebDriver driver;
    private WebDriverWait wait;
    private String DASHBOARD_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
    private String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";

    @BeforeTest
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

//        Login
        driver.get(LOGIN_URL);
        Thread.sleep(1000);
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(USERNAME);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type ='submit']"));
        loginButton.click();
        Thread.sleep(5000);

    }


//    Test 1 kiem tra logo
    @Test
    public void testLogoSideBar() throws InterruptedException{
//        Tim element chua logo
//        Luu y: neu element khong chua id class  => tim element cha
        WebElement logo = driver.findElement(By.className("oxd-brand-banner"));
        Assert.assertTrue(logo.isDisplayed(), "Logo khong hien thi");
//        Testcase 2
//        Test menu sidebar
        String []menuItems = {"Admin", "PIM", "Leave", "Time", "Recruitment", "My Info", "Performance", "Dashboard", "Directory", "Maintenance", "Claim","Buzz"};
//        xpath (//span)
        for(String menuName: menuItems){
            WebElement menu = driver.findElement(By.xpath("//span[text()='" + menuName + "']"));
            Assert.assertTrue(menu.isDisplayed(), "Menu "+menuName+" khong hien thi");
        }

    }


    @Test
    public void testHeaderDashboard() throws InterruptedException{
        //        Test title
//          //h6[]
        WebElement dashboardTitle = driver.findElement(By.xpath("//h6[text()='Dashboard']"));
        Assert.assertTrue(dashboardTitle.isDisplayed(), "Dashboard khong hien thi");

        WebElement userDropdown = driver.findElement(By.className("oxd-userdropdown-tab"));
        Assert.assertTrue(userDropdown.isDisplayed(), "User dropdown khong hien thi");

    }


    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
