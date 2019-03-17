package ru.auto.addressbook.tests;

import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testModificationContcact() throws Exception {
        app.getNavigationHelper().gotoHomePage();
        app.getContactHelper().selectContactById(666);
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("test11", "test12", "xxx yyy zzz", "87654321", "wtf@my.dom"));
        app.getContactHelper().confirmContactModification();
        app.getNavigationHelper().gotoHomePage();
    }
}