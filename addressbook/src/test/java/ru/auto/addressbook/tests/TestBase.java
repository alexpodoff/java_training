package ru.auto.addressbook.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import ru.auto.addressbook.appmanager.ApplicationManager;
import sun.plugin2.util.BrowserType;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager(BrowserType.MOZILLA);

    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
