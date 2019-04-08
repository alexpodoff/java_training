package ru.auto.addressbook.tests;

import org.testng.annotations.*;
import ru.auto.addressbook.model.ContactData;
import ru.auto.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactDeletionTests extends TestBase {

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
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteById(deletedContact);
    app.goTO().confirmAlert();
    app.goTO().homePage();
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(deletedContact)));
  }
}
