import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest {
    private WebDriver driver;
    private String BASE_URL = "https://www.amazon.com/";


    @BeforeTest
    public void setUp() throws InterruptedException{
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get(BASE_URL);
        Thread.sleep(1000);

        WebElement btnContinue = driver.findElement(By.xpath("//button[@class='a-button-text']"));
        btnContinue.click();
        Thread.sleep(1000);

        WebElement searchBox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
        searchBox.sendKeys("Adidas");
//        C1: Enter tu ban phim
//        Do search box nam trong form nen dung action submit thay vi click
        searchBox.submit();
        Thread.sleep(2000);
    }

    @Test
    public void testSearch() throws InterruptedException{
        WebElement textResult = driver.findElement(By.xpath("//h1[@data-csa-c-content-id='desktop/1/0/default/default']//span[1]"));
        Thread.sleep(2000);
        Assert.assertTrue(textResult.isDisplayed());
    }


    @Test
    public void testCheck() throws InterruptedException{
        WebElement womenCategoryText = driver.findElement(By.xpath("//a[contains(@class, 'a-size-base-plus a-color-base a-link-normal s-link-style') and text()=\"Women's Footwear\"]"));
        Assert.assertTrue(womenCategoryText.isDisplayed());

        WebElement checkBox = driver.findElement(By.xpath("//li[@id='p_n_g-101015233022111/121075132011']//i"));
        checkBox.click();
        Thread.sleep(2000);
        WebElement itemResult = driver.findElement(By.xpath("//h2[contains(@class, 'a-size-base-plus a-spacing-none a-color-base a-text-normal')]//span[contains(text(), 'Unisex-Adult Samba Indoor')]"));
        Assert.assertTrue(itemResult.isDisplayed());

        WebElement title = driver.findElement(By.xpath("//h2[@id='loom-desktop-top-slot_us-slds-sp-2-t1-a2-heading']"));
        Assert.assertTrue(title.isDisplayed());

        WebElement divImg = driver.findElement(By.xpath("//div[@cel_widget_id='MAIN-SEARCH_RESULTS-4']//div[@data-cy='image-container']"));
        Assert.assertTrue(divImg.isDisplayed());

        WebElement brandName = driver.findElement(By.xpath("//div[@cel_widget_id='MAIN-SEARCH_RESULTS-4']//div[@data-cy='title-recipe']//h2[1]//span"));
        Assert.assertTrue(brandName.isDisplayed());

        WebElement dayDelivery = driver.findElement(By.xpath("//div[@cel_widget_id='MAIN-SEARCH_RESULTS-4']//div[@data-cy='delivery-recipe']//span[contains(@aria-label, 'Delivery')]"));
        Assert.assertTrue(dayDelivery.isDisplayed());

        WebElement listPrice = driver.findElement(By.xpath("//div[@cel_widget_id='MAIN-SEARCH_RESULTS-4']//div[@data-cy='price-recipe']//div[contains(@aria-hidden, 'List')]"));
        Assert.assertTrue(listPrice.isDisplayed());

        WebElement price = driver.findElement(By.xpath("//div[@cel_widget_id='MAIN-SEARCH_RESULTS-4']//div[@data-cy='price-recipe']//a//span[contains(@class,'a-price') and @data-a-size='xl']"));
        Assert.assertTrue(price.isDisplayed());
    }


    @AfterTest
    public void tearDown() {
        if(driver != null){
            driver.quit();
        }
    }
}
