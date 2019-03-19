package ru.auto.addressbook.tests;

import org.testng.annotations.Test;
import ru.auto.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testModifyGroup() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test6", "test66", "test666"));
        }
        app.getGroupHelper().modifyGroup(new GroupData("test11", "test12", "test13"));
    }
}
