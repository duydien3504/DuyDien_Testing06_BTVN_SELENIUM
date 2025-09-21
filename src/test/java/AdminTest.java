import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AdminTest {
    private WebDriver driver;
    private static final String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    private static final String USERNAME = "Admin";
    private static final String PASSWORD = "admin123";

    @BeforeTest
    public void setUp() throws  InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Login
        driver.get(LOGIN_URL);
        Thread.sleep(1000);
        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.sendKeys(USERNAME);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys(PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath("//button[@type ='submit']"));
        loginButton.click();
        Thread.sleep(5000);

        WebElement menu = driver.findElement(By.xpath("//span[text()='Admin']"));
        menu.click();
        Thread.sleep(2000);
    }

    @Test
    public void testHeaderBreadcum() {
        WebElement adminBreadcum1 = driver.findElement(By.xpath("//h6[text()= 'Admin']"));
        Assert.assertTrue(adminBreadcum1.isDisplayed(), "Admin breadcum khong hien thi");

        WebElement adminBreadcum2 = driver.findElement(By.xpath("//h6[text()= 'User Management']"));
        Assert.assertTrue(adminBreadcum2.isDisplayed(), "User Management breadcum khong hien thi");
    }

    @Test
    public void testSearchWithUserNameForm() throws  InterruptedException{
//        <div data-v-957b4417="" class="oxd-input-group__label-wrapper"><!---->
//                <label data-v-30ff22b1="" data-v-957b4417="" class="oxd-label">Username
//                </label>
//                </div>
//                <div data-v-957b4417="" class="">
//                <input data-v-1f99f73c="" class="oxd-input oxd-input--active">
//                </div>


//        //label[text() = 'Username'] /../ following-sibling::div//input
//        following-sibling: tim the ke voi dang xet
        WebElement usernameInput = driver.findElement(By.xpath("//label[text()='Username']/../following-sibling::div//input"));
        usernameInput.sendKeys("Ashwin");
//        Assert.assertTrue(usernameInput.isDisplayed());


        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(3000);
//        B1: Tim the chua ket qua tra ve sau khi click button search
//        B2: Kiem tra co hien thi len browser khong
        WebElement searchResult = driver.findElement(By.xpath("//div[@class='oxd-table-card']//div[contains(text(), 'Ashwin')]"));
        Assert.assertTrue(searchResult.isDisplayed());
    }

    @Test
    public void testSearchWithRole() throws InterruptedException{
        WebElement userRoleDropdown = driver.findElement(
                By.xpath("//label[text()='User Role']/../following-sibling::div//div[contains(@class,'oxd-select-text')]")
        );
        userRoleDropdown.click();

        driver.findElement(By.xpath("//div[@role='listbox']//div[normalize-space()='Admin']")).click();
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
        searchButton.click();
        Thread.sleep(3000);

        WebElement searchResult = driver.findElement(By.xpath("//div[@class='oxd-table-card']//div[contains(text(), 'Admin')]"));
        Assert.assertTrue(searchResult.isDisplayed());
        Thread.sleep(5000);
    }

    @Test
//    public void testSearchEN() throws InterruptedException{
//        WebElement usernameInput = driver.findElement(By.xpath("//label[text()='Employee Name']/../following-sibling::div//input"));
//        usernameInput.sendKeys("Jobin Sam");
//
//        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit']"));
//        searchButton.click();
//        Thread.sleep(3000);
//
//        WebElement searchResult = driver.findElement(By.xpath("//div[@class='oxd-table-card']//div[contains(text(), 'Jobin Sam')]"));
//        Assert.assertTrue(searchResult.isDisplayed());
//    }




    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
