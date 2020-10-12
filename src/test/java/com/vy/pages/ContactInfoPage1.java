package com.vy.pages;

import com.vy.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactInfoPage1 extends BasePage {

   // @FindBys({
            @FindBy(xpath = "//h1[@class='user-name']")
    //@FindBy(xpath= "//div[@class='sub-title']")})
    public WebElement fullname;

    @FindBy(className = "phone")
    public WebElement phone;
    @FindBy(className  = "email")
    public WebElement email;
}
