package ru.auto.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;
import ru.auto.addressbook.model.Contacts;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.db().contacts();
        if (app.db().contacts().size() == 0) {
            app.goTO().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("test6")
                    .withLastname("test66")
                    .withAddress("6 6 6")
                    .withHomephone("66666666")
                    .withEmail("hell@my.dom"));
            app.goTO().homePage();
        }
    }

    @Test
    public void testModificationContcact() throws Exception {
        app.goTO().homePage();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withId(modifiedContact.getId())
                .withFirstname("test33331")
                .withLastname("test2")
                .withAddress("aaa bbb ccc")
                .withHomephone("12345678")
                .withEmail("omg@my.dom");
        app.contact().modifyById(contact);
        app.goTO().homePage();
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
