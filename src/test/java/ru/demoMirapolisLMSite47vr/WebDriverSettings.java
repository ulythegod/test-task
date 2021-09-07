package ru.demoMirapolisLMSite47vr;

import org.apache.log4j.BasicConfigurator;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.demoMirapolisLMSite47vr.loginPage.LoginPage;

public class WebDriverSettings {
    public WebDriver driver;
    public WebDriverWait wait;
    public LoginPage loginPage;

    /**
     * метод устанавливает свойства для веб драйвера
     */
    @Before
    public void setUp() {
        BasicConfigurator.configure();
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();

        loginPage = PageFactory.initElements(driver,LoginPage.class);
        loginPage.openPage();
        loginPage.getStarted();

        System.out.println("Start of test");
    }

    /**
     * метод закрывает все браузеры и завершает сеанс
     */
    @After
    public void close() {
        driver.quit();
        System.out.println("End of test");
    }
}
