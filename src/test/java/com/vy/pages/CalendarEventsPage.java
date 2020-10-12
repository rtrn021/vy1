package com.vy.pages;

import com.vy.BasePage;
import com.vy.utils.BrowserUtils;
import com.vy.utils.Driver;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CalendarEventsPage extends BasePage {

  public CalendarEventsPage() {
    PageFactory.initElements(Driver.get(), this);
  }

  @FindBy(css = "[title='Create Calendar event']")
  public WebElement createCalendarEvent;

  @FindBy(xpath = "//div[@class='toolbar']//input")
  public WebElement pageNumber;

  @FindBy(xpath = "//div[@class='toolbar']//button")
  public WebElement viewPerPage;

  static final String rowsXpath = "//tbody[@class='grid-body']/tr";

  @FindBy(xpath = "//i[normalize-space()='Next']")
  public WebElement nextBtn;

  @FindBy(xpath = "//i[normalize-space()='Prev']")
  public WebElement prevBtn;

  @FindBy(xpath = "(//div[@class='pagination pagination-centered']/label)[3]")
  public WebElement recordsNumber;



  public void pageArrival(){
    BrowserUtils.waitForVisibility(createCalendarEvent,30);
    BrowserUtils.waitForClickablility(createCalendarEvent,10);
  }

  public void verifyPageNumber(String pageNumberValue) {
    System.out.println(
        "BrowserUtils.doesElementExist(pageNumber) = " + BrowserUtils.doesElementExist(pageNumber));
    BrowserUtils.waitForVisibility(pageNumber, 20);
    Assert.assertEquals(pageNumber.getAttribute("value"), pageNumberValue);
  }

  public void verifyViewPerPageValue(String viewPerPageValue) {
    System.out
        .println("BrowserUtils.doesElementExist() = " + BrowserUtils.doesElementExist(viewPerPage));
    BrowserUtils.waitForVisibility(viewPerPage, 20);
    Assert.assertEquals(viewPerPage.getText().strip(), viewPerPageValue);
  }



  public int countRecords() {
    pageArrival();
    List<WebElement> rowElements = Driver.get().findElements(By.xpath(rowsXpath));
    int count = rowElements.size();
    System.out.println("count = " + count);
    while (!pageNumber.getAttribute("value").equals(totalRecordsPage())){
      BrowserUtils.clickWithTries(nextBtn,3);
      pageArrival();
      BrowserUtils.waitFor(1);
      BrowserUtils.waitForVisibility(By.xpath(rowsXpath),5);
      rowElements = Driver.get().findElements(By.xpath(rowsXpath));
      count+=rowElements.size();
      System.out.println("count = " + count);
      BrowserUtils.waitForVisibility(pageNumber,5);

    }

    return count;
  }

  public void verifyRecordsNumberEqualsToCountOfRecords(){
    BrowserUtils.waitForVisibility(recordsNumber,10);
    System.out.println("recordsNumber.getText() = " + recordsNumber.getText());
    String recordsNo = recordsNumber.getText().substring(recordsNumber.getText().indexOf("Of")+3,recordsNumber.getText().lastIndexOf(" ") );
    System.out.println("recordsNo = " + recordsNo);
    Assert.assertEquals(countRecords(), Integer.parseInt(recordsNo));
  }


  public String totalRecordsPage(){
    WebElement element = Driver.get().findElement(By.xpath("(//div[@class='pagination pagination-centered']/label)[2]"));
    String totalRecords = element.getText().substring(element.getText().indexOf(" ")+1,element.getText().lastIndexOf(" "));
    System.out.println("PageNumber/TotalPages = " + pageNumber.getAttribute("value") + "/" + totalRecords);
    return totalRecords;
  }

}