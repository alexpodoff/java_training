package ru.auto.addressbook.tests;

import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;
import ru.auto.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTO().homePage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("test1")
            .withLastname("test2")
            .withAddress("aaa bbb ccc")
            .withHomephone("12345678")
            .withEmail("omg@my.dom");
    app.contact().create(contact);
    app.goTO().homePage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt()))));
  }

}
