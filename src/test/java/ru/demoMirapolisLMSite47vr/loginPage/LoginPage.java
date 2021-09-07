package ru.demoMirapolisLMSite47vr.loginPage;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private String correctInputBackgroundColor;
    private String passwordInputType;
    private String authorizationPageTitle;
    private String mainPageTitle;
    private String forgotPasswordPageTitle;

    @FindBy(id = "button_submit_login_form")
    private WebElement loginForm;

    @FindBy(name = "user")
    private WebElement nameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(id = "show_password")
    private WebElement showPasswordButton;

    @FindBy(className = "mira-default-login-page-link")
    private WebElement forgotPasswordLink;

    /**
     * конструктор класса LoginPage
     * @param driver
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        this.correctInputBackgroundColor = "rgba(254, 246, 246, 1)";
        this.passwordInputType = "text";
        this.authorizationPageTitle = "Авторизация";
        this.mainPageTitle = "Mirapolis LMS";
        this.forgotPasswordPageTitle = "Восстановление пароля";
    }

    /**
     * метод для заполнения поля для ввода логина
     * @param name
     */
    public void fillNameInput(String name) {
        nameInput.sendKeys(name);
    }

    /**
     * метод для заполнения поля для ввода пароля
     * @param password
     */
    public void fillPasswordInput(String password) {
        passwordInput.sendKeys(password);
    }

    /**
     * метод для проверки того, что произошел переход на главную страницу после успешной авторизации
     * (метод находит элемент главной страницы, сравнивает заголовки)
     */
    public void findMainPageElement() {
        String title = driver.getTitle();
        System.out.println("title " + title);
        System.out.println("mainPageTitle " + mainPageTitle);
        Assert.assertEquals(mainPageTitle, title);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("avatar-avatar")));
    }

    /**
     * метод для клика по кнопке Войти
     */
    public void submitAuthorizationForm() {
        loginForm.click();
    }

    /**
     * метод для клика по кнопке показа пароля
     */
    public void clickShowPasswordButton() {
        showPasswordButton.click();
    }

    /**
     * метод для проверки типа поля для ввода пароля
     */
    public void checkTypeOfPasswordInput() {
        String passwordInputActualType = passwordInput.getAttribute("type");
        Assert.assertEquals(passwordInputActualType, passwordInputType);
    }

    /**
     * метод для клипа по ссылке Забыли пароль?
     */
    public void clickForgotPasswordLink() {
        forgotPasswordLink.click();
    }

    /**
     * метод для проверки того, что произошел переход на страницу восстановления пароля
     * (метод находит элемент страницы восстановления пароля, сравнивает заголовки)
     */
    public void findForgotPasswordPageElement() {
        String title = driver.getTitle();
        Assert.assertEquals(forgotPasswordPageTitle, title);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("mira-forgot-password-form")));
    }

    /**
     * переход на страницу https://lmslite47vr.demo.mirapolis.ru/mira
     */
    public void openPage() {
        driver.get("https://lmslite47vr.demo.mirapolis.ru/mira");
    }

    /**
     * метод проверяет, что открылась страница авторизации
     */
    public void getStarted() {
        String title = driver.getTitle();
        Assert.assertEquals(authorizationPageTitle, title);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login_form_panel")));
    }

    /**
     * метод определяет, что элемент input изменил цвет в случае ошибки при вводе данных в форму
     * @param input
     * @param correctInputBackgroundColor
     */
    public void checkErrorsStyleOfInputElement(WebElement input, String correctInputBackgroundColor) {
        if (input != null && correctInputBackgroundColor != "") {
            String inputBackgroundColor = input.getCssValue("background-color");
            Assert.assertEquals(inputBackgroundColor, correctInputBackgroundColor);
        }
    }

    /**
     * метод для проверки того, видно ли пользователю сообщение об ошибке
     * @param loginPage
     */
    public void failedActions(LoginPage loginPage) {
        loginForm.click();
        try {
            driver.switchTo().alert().accept();
            System.out.println("Alert with error message was accepted");
        } catch (NoAlertPresentException e) {
            System.out.println("Alert with error message wasn't show");
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("invalid")));

        this.checkErrorsStyleOfInputElement(nameInput, correctInputBackgroundColor);
        this.checkErrorsStyleOfInputElement(passwordInput, correctInputBackgroundColor);
    }
}
