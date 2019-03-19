package ru.auto.addressbook.tests;

import org.testng.annotations.*;
import ru.auto.addressbook.model.ContactData;

public class ContcactDeletionTests extends TestBase {

  @Test
  public void testDeleteContcact() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(
              "test6", "test66", "6 6 6", "66666666", "hell@my.dom"));
      app.getNavigationHelper().gotoHomePage();
    }
    app.getContactHelper().deleteFirstContact();
    app.getNavigationHelper().confirmAlert();
    app.getNavigationHelper().gotoHomePage();
  }
}
