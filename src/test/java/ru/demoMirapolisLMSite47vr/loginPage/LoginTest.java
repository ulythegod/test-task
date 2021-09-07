package ru.demoMirapolisLMSite47vr.loginPage;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.demoMirapolisLMSite47vr.WebDriverSettings;
import ru.demoMirapolisLMSite47vr.helpers.RandomStringHelper;

public class LoginTest extends WebDriverSettings {
    String correctLogin = "fominaelena";
    String correctPassword = "z0K6CTQR";

    /**
     * метод проверяет, что с корректными данными происходит авторизация
     */
    @Test
    public void loginTest() {
        loginPage.fillNameInput(correctLogin);
        loginPage.fillPasswordInput(correctPassword);
        System.out.println("Entered correct login and password: " + correctLogin + " " + correctPassword);
        loginPage.submitAuthorizationForm();
        System.out.println("Clicked submit button");

        loginPage.findMainPageElement();
        System.out.println("Main page opened after successful login");
    }

    /**
     * метод проверяет, что если ничего не введено, пользователю видно сообщение об ошибке
     */
    @Test
    public void loginTestFailedEmptyData() {
        loginPage.failedActions(loginPage);
    }

    /**
     * метод проверяет, что если введены некорректные данные, пользователю видно сообщение об ошибке
     */
    @Test
    public void loginTestFailedRandomData() {
        String randomLogin = RandomStringHelper.generateRandomString(10);
        String randomPassword = RandomStringHelper.generateRandomString(10);
        loginPage.fillNameInput(randomLogin);
        loginPage.fillPasswordInput(randomPassword);
        System.out.println("Entered random incorrect login and password: " + randomLogin + " " + randomPassword);
        loginPage.failedActions(loginPage);
    }

    /**
     * метод проверяет, что если введен только логин, пользователю видно сообщение об ошибке
     */
    @Test
    public void loginTestCorrectLogin() {
        loginPage.fillNameInput(correctLogin);
        System.out.println("Entered only correct login: " + correctLogin);
        loginPage.failedActions(loginPage);
    }

    /**
     * метод проверяет, что если введен только корректный логин и некорректный пароль, пользователю видно сообщение об ошибке
     */
    @Test
    public void loginTestCorrectLoginAndRandomPassword() {
        loginPage.fillNameInput(correctLogin);
        String randomPassword = RandomStringHelper.generateRandomString(10);
        loginPage.fillPasswordInput(randomPassword);
        System.out.println("Entered correct login and incorrect password: " + correctLogin + " " + randomPassword);
        loginPage.failedActions(loginPage);
    }

    /**
     * метод проверяет, что кнопка показать пароль делает пароль видимым для пользователя
     */
    @Test
    public void showPasswordTest() {
        loginPage.fillPasswordInput(correctPassword);
        loginPage.clickShowPasswordButton();
        loginPage.checkTypeOfPasswordInput();
    }

    /**
     * метод проверяет, что при клике на ссылку "Забыли пароль?", происходит переход на страницу восстановления пароля
     */
    @Test
    public void forgetPasswordLink() {
        loginPage.clickForgotPasswordLink();
        System.out.println("Clicked forget password link");

        loginPage.findForgotPasswordPageElement();
        System.out.println("Forgot password page opened after the click");
    }
}
