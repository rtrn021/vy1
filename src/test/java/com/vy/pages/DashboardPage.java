package com.vy.pages;

import com.vy.BasePage;
import com.vy.utils.Driver;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

  public DashboardPage() {
    PageFactory.initElements(Driver.get(), this);
  }

    //no need to explicitly write constructor, because it will use its parents constructor
}
