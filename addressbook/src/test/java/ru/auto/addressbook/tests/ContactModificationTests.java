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
    public void testModificationContcact() throws Exception {
        app.goTO().homePage();
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstname("test33331")
                .withLastname("test2")
                .withAddress("aaa bbb ccc")
                .withHomephone("12345678")
                .withEmail("omg@my.dom");
        app.contact().modifyById(modifiedContact);
        app.goTO().homePage();
        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.without(contact).withAdded(modifiedContact)));
    }
}