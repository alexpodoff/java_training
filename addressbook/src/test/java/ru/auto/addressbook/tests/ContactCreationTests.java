package ru.auto.addressbook.tests;

import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().createContact(new ContactData(
            "test1", "test2", "aaa bbb ccc", "12345678", "omg@my.dom"));
    app.getNavigationHelper().gotoHomePage();
  }

}
