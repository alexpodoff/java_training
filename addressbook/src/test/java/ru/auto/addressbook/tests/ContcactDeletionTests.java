package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.auto.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContcactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoHomePage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData(
              "test6", "test66", "6 6 6", "66666666", "hell@my.dom"));
    }
  }

  @Test
  public void testDeleteContcact() throws Exception {
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    app.getContactHelper().deleteContactByIndex(index);
    app.getNavigationHelper().confirmAlert();
    app.getNavigationHelper().gotoHomePage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}
