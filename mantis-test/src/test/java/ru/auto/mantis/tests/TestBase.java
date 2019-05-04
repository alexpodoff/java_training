package ru.auto.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.auto.mantis.appmanager.ApplicationManager;
import ru.auto.mantis.model.MantisUser;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Iterator;


public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite
    public void setUp() throws IOException {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }

    MantisUser getRandomUser() {
        Iterator<MantisUser> iterator = app.db().users().iterator();
        MantisUser user = iterator.next();
        while (user.getId() == 1 || user.getName().equals(app.getProperty("web.adminLogin"))) {
            user = iterator.next();
        }
        return user;
    }

    void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
        String status = app.soap().getIssueStatus(issueId);
        return !(status.equals("resolved") || status.equals("closed"));
    }
}
