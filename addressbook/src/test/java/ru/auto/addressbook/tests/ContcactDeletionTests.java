package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.auto.addressbook.model.ContactData;

import java.util.Set;

public class ContcactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTO().homePage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstname("test6")
              .withLastname("test66")
              .withAddress("6 6 6")
              .withHomephone("66666666")
              .withEmail("hell@my.dom"));
    }
  }

  @Test
  public void testDeleteContcact() throws Exception {
    Set<ContactData> before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteById(deletedContact);
    app.goTO().confirmAlert();
    app.goTO().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(deletedContact);
    Assert.assertEquals(before, after);
  }
}
