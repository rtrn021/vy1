package com.vy.pages;

import com.vy.BasePage;
import com.vy.utils.BrowserUtils;
import com.vy.utils.Driver;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactsPage1 extends BasePage {
 public String wantedemail= "mbrackstone9@example.com";
 //String forxpathemail= "//td[contains(text(),'"+wantedemail+"')]";

   // @FindBys({
            @FindBy(xpath = "//td[@data-column-label='Email']")
            //@FindBy(xpath = "//td[contains(text(),'" + wantedemail + "')]")
    //})
    public
   List<WebElement> emailList;

            @FindBy (xpath= "//label[contains(text(),'of')]")
    public WebElement numberofpages;

            @FindBy (className = "grid-header")
    public WebElement tableofcontacts;

            @FindBy (css = "i[class='fa-chevron-right hide-text']")
    public WebElement nextbutton;

    //private final String forexpathemail ="//td[contains(text(),'\"+wantedemail+\"')]";
   // @FindBy (xpath = forexpathemail)
    //public WebElement wantedEmail;

    public void search(String column ){


 switch(column.toLowerCase()) {
     case("email"):
           searchList(emailList,wantedemail);
        }
    }
    public void search(String column, String wanteditem ){


        switch(column.toLowerCase()) {
            case("email"):
                searchList(emailList,wanteditem);
        }
    }

    public void searchList(List<WebElement> column, String wanteditem){
        String pageNum = numberofpages.getText();
        WebDriverWait wait = new WebDriverWait(Driver.get(), 300);

        int numberofpages = Integer.valueOf(pageNum.replaceAll("[\\D]", ""));

        outerloop:
        for (int z = 1; z <= numberofpages; z++) {

            for (WebElement thisone : column) {
                if (thisone.getText().equals(wanteditem)) {
                    thisone.click();
                    return;
                }
            }
            nextbutton.click();
            wait.until(ExpectedConditions.visibilityOf(tableofcontacts));
            BrowserUtils.waitFor(20);
        }



    }
}
