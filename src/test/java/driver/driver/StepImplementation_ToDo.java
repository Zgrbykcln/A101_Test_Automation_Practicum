package driver.driver;

import com.thoughtworks.gauge.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;


public class StepImplementation_ToDo {

    private final WebDriver driver;

    public StepImplementation_ToDo() {
        this.driver = DriverFactory.getDriver();
    }

    @Step("Open the A101 website")
    public void gotoApp() throws InterruptedException {

        System.out.println(DriverFactory.getDriver());
        driver.get("https://www.a101.com.tr/");
        String title = driver.getTitle();
        assertEquals(title, "A101 HARCA HARCA BİTMEZ");
    }

    @Step("Accept all cookies")
    public void acceptAllCookies() throws InterruptedException {

        WebElement addButton = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
        addButton.click();
    }

    @Step("Giyim--> Aksesuar--> Kadın İç Giyim-->Dizaltı Çorap section entered")
    public void selectItems2() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement itemOne = driver.findElement(By.xpath("(//a[@href='/giyim-aksesuar/'])[1]"));
        actions.moveToElement(itemOne).perform();
        Thread.sleep(5000);
        WebElement itemTwo = driver.findElement(By.cssSelector(".js-navigation-item:nth-child(4) .list:nth-child(2) > li:nth-child(8) > a"));
        itemTwo.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Step("Add to cart")
    public void addNewItem() throws InterruptedException {

        WebElement addItem = driver.findElement(By.xpath("(//h3[@class='name'])[1]"));
        Thread.sleep(2000);
        addItem.click();

        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='add-to-basket button green block with-icon js-add-basket']"));
        addToCartButton.click();

        WebElement text = driver.findElement(By.xpath("//div[@class='selected-variant-text']//span[text()='SİYAH']"));
        Boolean containsText = text.getText().contains("SİYAH");
        Assert.assertTrue("Expected text is not contained", containsText);

        WebElement showCart = driver.findElement(By.xpath("//a[@class='go-to-shop']"));
        showCart.click();
        WebElement confirmCart = driver.findElement(By.xpath("//a[@class='button green checkout-button block js-checkout-button']"));
        confirmCart.click();

    }

    @Step("Continue without membership")
    public void WithoutMembership() throws InterruptedException {

        WebElement continueWithoutAMember = driver.findElement(By.xpath("//a[@class='auth__form__proceed js-proceed-to-checkout-btn']"));
        continueWithoutAMember.click();
        WebElement eMail = driver.findElement(By.xpath("//input[@name='user_email']"));
        eMail.click();
        eMail.sendKeys("ozgurBuyukcelen@gmail.com");

        WebElement submit = driver.findElement(By.xpath("//button[@class='button block green']"));
        submit.click();


    }

    @Step("Add new address")
    public void addNewAddress() throws InterruptedException {

        WebElement continueWithoutAMember = driver.findElement(By.xpath("(//a[@class='new-address js-new-address'])[1]"));
        continueWithoutAMember.click();

        WebElement addressTitle = driver.findElement(By.xpath("//input[@placeholder='Ev adresim, iş adresim vb.']"));
        addressTitle.sendKeys("Ev");

        WebElement addressName = driver.findElement(By.xpath("//input[@name='first_name']"));
        addressName.sendKeys("Ozgur");

        WebElement addressLastName = driver.findElement(By.xpath("//input[@name='last_name']"));
        addressLastName.sendKeys("Buyukcelen");

        WebElement addressPhoneNumber = driver.findElement(By.xpath("//input[@name='phone_number']"));
        addressPhoneNumber.click();
        addressPhoneNumber.sendKeys("5999999999");

        Select dropdownCity = new Select(driver.findElement(By.xpath("//select[@name='city']")));
        dropdownCity.selectByVisibleText("İSTANBUL");

        Select dropdownTownship = new Select(driver.findElement(By.xpath("//select[@name='township']")));
        dropdownTownship.selectByVisibleText("ADALAR");

        Thread.sleep(2000);
        Select dropdownDistrict = new Select(driver.findElement(By.xpath("//select[@name='district']")));
        dropdownDistrict.selectByValue("35391");

        WebElement addressAddress = driver.findElement(By.xpath("//textarea[@class='js-address-textarea']"));
        addressAddress.sendKeys("Test Address");

        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)");

        Thread.sleep(3000);
        WebElement continueButton = driver.findElement(By.xpath("//button[@class='button green js-set-country js-prevent-emoji']"));
        continueButton.click();

        Thread.sleep(3000);
        WebElement saveContinueButton = driver.findElement(By.xpath("//button[@class='button block green js-proceed-button'"));
        saveContinueButton.click();

    }

    @Step("The payment page is checked")
    public void paymentPageChecked() throws InterruptedException {

        WebElement element = null;
        try {
            element = driver.findElement(By.xpath("//span[@class='order-complete']"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

