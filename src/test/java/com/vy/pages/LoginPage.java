package com.vy.pages;

import com.vy.utils.ConfigurationReader;
import com.vy.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage {


    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "prependedInput")
    public WebElement usernameInput;

    @FindBy(id = "prependedInput2")
    public WebElement passwordInput;

    @FindBy(id = "_submit")
    public WebElement loginBtn;


    public void login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
        Assert.assertEquals(Driver.get().getCurrentUrl(), "https://qa1.vytrack.com/");
    }
    public void login(String name) {
        name= name.toLowerCase();
        String username = ConfigurationReader.get(name+"_username");
        String password = ConfigurationReader.get(name+"_password");
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
    }

    public void loginAsStoreManager() {
        login("storemanager");
    }

    public void loginAsDriver() throws InterruptedException {
        login("driver");
        Thread.sleep(333);
        System.out.println(Driver.get().getCurrentUrl());
        Assert.assertEquals(Driver.get().getCurrentUrl(), "https://qa1.vytrack.com/");
    }

}