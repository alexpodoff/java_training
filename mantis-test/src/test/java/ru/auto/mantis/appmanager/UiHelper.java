package ru.auto.mantis.appmanager;

import org.openqa.selenium.By;

public class UiHelper extends BaseHelper {

    public UiHelper(ApplicationManager app) {
        super(app);
    }

    public void adminLogin() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), app.getProperty("web.adminLogin"));
        type(By.name("password"), app.getProperty("web.adminPassword"));
        click(By.xpath("//input[@value='Login']"));
    }

    public void resetUsersPass(String user) {
        wd.get("http://localhost/mantisbt-1.3.4/my_view_page.php");
        wd.findElement(By.linkText("Manage")).click();
        wd.findElement(By.linkText("Manage Users")).click();
        wd.findElement(By.linkText(user)).click();
        wd.findElement(By.xpath("//input[@value='Reset Password']")).click();
    }
}
