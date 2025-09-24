import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AirbnbTest {
    private WebDriver driver;
    private String HOME_URL = "https://demo4.cybersoft.edu.vn/";


    @BeforeTest
    public void setUp() throws  InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOME_URL);
        Thread.sleep(10000);
    }

    @Test
    public void homeTest() throws InterruptedException{
//        Testlogo
        WebElement nameLogo = driver.findElement(By.xpath("//span[text()='airbnb']"));
        Assert.assertTrue(nameLogo.isDisplayed());
//      @Class = 'menu-phone' => tim the ma co class = 'menu-phone'
//      Neu the do co nhieu class => contains de tim class bat ky
//      Vd: //ul[contains(@class, 'menu-phone')]
        WebElement homeMenu = driver.findElement(By.xpath("//ul[contains(@class,'menu-phone')]//a[text()='Home']"));
        Assert.assertTrue(homeMenu.isDisplayed());
        Assert.assertEquals(homeMenu.getText(), "Home");

        WebElement avatarMenu = driver.findElement(By.xpath("//img[@src='https://cdn-icons-png.flaticon.com/512/6596/6596121.png']"));
        Assert.assertTrue(avatarMenu.isDisplayed());
    }

    @Test
    public void testSearchLocation() {
        WebElement SearchLocation = driver.findElement(By.xpath("//div[@class='grid grid-cols-12 smm:grid-cols-1 border-2 border-gray-300 md:rounded-full']"));
        Assert.assertTrue(SearchLocation.isDisplayed());
    }

    @Test
    public void testSearchDay() {
        WebElement SearchDay = driver.findElement(By.xpath("//div[contains(@class,'cursor-pointer') and contains(@class,'flex-col')]//p"));
        Assert.assertTrue(SearchDay.isDisplayed());
    }

    @Test
    public void testSearchGuest() {
        WebElement searchGuest = driver.findElement(By.xpath("//div[contains(@class,'cursor-pointer') and contains(@class,'flex justify-center')]//p"));
        Assert.assertTrue(searchGuest.isDisplayed());
    }

    @Test
    public void testMinusBtn() throws InterruptedException{
        WebElement searchGuest = driver.findElement(By.xpath("//div[contains(@class,'cursor-pointer') and contains(@class,'flex justify-center')]//p"));
        searchGuest.click();
        Thread.sleep(1000);
        WebElement minusBtn = driver.findElement(By.xpath("//div[contains(@class, 'text-md') and text()='Khách']/./following-sibling::div[1]//button[1]"));
        Assert.assertTrue(minusBtn.isDisplayed());
    }


    @Test
    public void testIncrementBtn() throws InterruptedException{
        WebElement searchGuest = driver.findElement(By.xpath("//div[contains(@class,'cursor-pointer') and contains(@class,'flex justify-center')]//p"));
        searchGuest.click();
        Thread.sleep(1000);
        WebElement incrementBtn = driver.findElement(By.xpath("//div[contains(@class, 'text-md') and text()='Khách']/./following-sibling::div[1]//button[2]"));
        Assert.assertTrue(incrementBtn.isDisplayed());
    }


    @Test
    public void testTypeBtn() {
        WebElement typeOfPlace = driver.findElement(By.xpath("//div[@id='root']/div[2]/div[2]//button[1]"));
        Assert.assertTrue(typeOfPlace.isDisplayed());
    }


    @Test
    public void testPriceBtn() {
        WebElement btnPrice = driver.findElement(By.xpath("//div[@id='root']/div[2]/div[2]//button[2]"));
        Assert.assertTrue(btnPrice.isDisplayed());
    }

    @Test
    public void testNameLocation() {
        WebElement nameLocation = driver.findElement(By.xpath("//div[@id='root']/div[3]//a[2]//h2"));
        Assert.assertTrue(nameLocation.isDisplayed());
    }

    @Test
    public void testTimeDrive() {
        WebElement txtTimeDrive = driver.findElement(By.xpath("//div[@id='root']/div[3]//a[3]//p"));
        Assert.assertTrue(txtTimeDrive.isDisplayed());
    }

    //div[text()='Khách']/following-sibling::div/button[.='+']
    //button[text()='Loại nơi ở']
    //h2[text()='Hồ Chí Minh']/parent::div

    @Test(description = "test location")
    public void testLocation() {
        WebElement cellLocation = driver.findElement(By.xpath("//a[@href='/rooms/ho-chi-minh' and .//h2[text()='Hồ Chí Minh']]"));
        Assert.assertTrue(cellLocation.isDisplayed(), "String message");

    }

    @AfterTest
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
