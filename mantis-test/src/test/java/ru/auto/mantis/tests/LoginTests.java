package ru.auto.mantis.tests;

import org.testng.annotations.Test;
import ru.auto.mantis.appmanager.HttpSession;
import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "secret"));
        assertTrue(session.isLoggedInAs("administrator"));
    }
}