package ru.auto.addressbook.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import ru.auto.addressbook.appmanager.ApplicationManager;

public class TestBase {

    protected final ApplicationManager app = new ApplicationManager();

    @BeforeMethod
    public void setUp() {
        app.init();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        app.stop();
    }

}
