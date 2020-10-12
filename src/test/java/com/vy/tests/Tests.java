package com.vy.tests;

import com.vy.Pages;
import com.vy.TestBase;
import org.testng.annotations.Test;

public class Tests extends TestBase {

  @Test
  public void t2() {
    Pages.loginPage.loginAsStoreManager();
    Pages.dashboardPage.navigateToModule("Activities", "Calendar Events");
    Pages.calendarEventsPage.verifyPageNumber("1");

  }

  @Test
  public void t3() {
    Pages.loginPage.loginAsStoreManager();
    Pages.dashboardPage.navigateToModule("Activities", "Calendar Events");
    Pages.calendarEventsPage.verifyViewPerPageValue("25");

  }

  @Test
  public void t4() {
    Pages.loginPage.loginAsStoreManager();
    Pages.dashboardPage.navigateToModule("Activities", "Calendar Events");
    Pages.calendarEventsPage.verifyRecordsNumberEqualsToCountOfRecords();

  }

  @Test
  public void t5() {
    Pages.loginPage.loginAsStoreManager();
    Pages.dashboardPage.navigateToModule("Activities", "Calendar Events");


  }

}
