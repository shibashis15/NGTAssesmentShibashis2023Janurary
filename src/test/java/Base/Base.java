package Base;

import PageObject.AccountPage;
import PageObject.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Properties;

public class Base {

    protected WebDriver driver;
    protected Properties props;
    protected LoginPage loginPage;

        public void initializeBrowser() {
            String driverPath = System.getProperty("user.dir") + "/src/test/resources/drivers/chromedriver.exe";
            System.setProperty("webdriver.chrome.driver", driverPath);

            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));

            try {
                props = new Properties();
                props.load(Files.newBufferedReader(Paths.get("src/test/resources/data.properties")));
            } catch (IOException exception) {
                System.out.println("Error reading properties file: " + exception.getMessage());
            }
        }

@Test
public void TestCase1() throws InterruptedException {
    initializeBrowser();
    loginPage = new LoginPage(driver);
    driver.get(props.getProperty("url"));

    loginPage.getUserName().sendKeys("banerjeeshibashis01@gmail.com");
        loginPage.getPassword().sendKeys("Qwerty12345*");
        loginPage.getLogInButton().click();
        Thread.sleep(Long.parseLong("30000"));
        loginPage.getLogInButton().click();
        loginPage.getCartButton().click();
        String actualCart = loginPage.getCartStatus().getText();
    Assert.assertEquals(actualCart , "ADD ITEMS FROM WISHLIST" , "Empty Cart");
}
@Test
public void TestCase2() throws InterruptedException {
    initializeBrowser();

    AccountPage accountPage = new AccountPage(driver);
    loginPage = new LoginPage(driver);
    driver.get(props.getProperty("url"));

    loginPage.getUserName().sendKeys("banerjeeshibashis01@gmail.com");
    loginPage.getPassword().sendKeys("Qwerty12345*");
    loginPage.getLogInButton().click();
    Thread.sleep(Long.parseLong("30000"));
    loginPage.getLogInButton().click();
    accountPage.getProfile().click();
    accountPage.getSubMenu().click();
    String actualName = accountPage.getAccountName().getText();
    Assert.assertEquals(actualName , "Shibashis Banerjee" , "Wrong Name");
}
    public void closeBrowser(){
        driver.quit();
    }
}
