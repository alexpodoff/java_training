package ru.auto.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;
import ru.auto.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

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
    public void testContactPhones() {
        app.goTO().homePage();
        Contacts contacts = app.contact().all();
        ContactData contact = contacts.iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getHomephone(), equalTo(cleaned(contactInfoFromEditForm.getHomephone())));
        assertThat(contact.getMobilephone(), equalTo(cleaned(contactInfoFromEditForm.getMobilephone())));
        assertThat(contact.getWorkphone(), equalTo(cleaned(contactInfoFromEditForm.getWorkphone())));
    }

    public String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }
}
