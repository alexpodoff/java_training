package ru.auto.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends BaseHelper {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void GroupPage() {
      click(By.linkText("groups"));
    }

    public void homePage() {
        click(By.linkText("home"));
    }

    public void confirmAlert() {
        wd.switchTo().alert().accept();
    }
}
