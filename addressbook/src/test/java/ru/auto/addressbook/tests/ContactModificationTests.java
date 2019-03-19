package ru.auto.addressbook.tests;

import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testModificationContcact() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        if (! app.getContactHelper().isThereAContact()) {
            app.getContactHelper().createContact(new ContactData(
                    "test6", "test66", "6 6 6", "66666666", "hell@my.dom"));
        }
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().modifyContact(new ContactData(
                "test11", "test12", "xxx yyy zzz", "87654321", "wtf@my.dom"));
        app.getNavigationHelper().gotoHomePage();
    }
}