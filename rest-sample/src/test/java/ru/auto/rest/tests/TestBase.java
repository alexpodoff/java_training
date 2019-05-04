package ru.auto.rest.tests;

import org.testng.SkipException;
import ru.auto.rest.appmanager.ApplicationManager;

import java.io.IOException;

public class TestBase {

    protected static final ApplicationManager app
            = new ApplicationManager();


    void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        String status = app.rest().getIssueStatus(issueId);
        return !(status.equals("resolved") || status.equals("closed"));
    }
}
