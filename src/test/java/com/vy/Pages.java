package com.vy;

import com.vy.pages.CalendarEventsPage;
import com.vy.pages.DashboardPage;
import com.vy.pages.LoginPage;

public class Pages {

  public static LoginPage loginPage;
  public static DashboardPage dashboardPage;
  public static CalendarEventsPage calendarEventsPage;

  public static void initialise(){

    loginPage = new LoginPage();
    dashboardPage = new DashboardPage();
    calendarEventsPage = new CalendarEventsPage();

  }

}
