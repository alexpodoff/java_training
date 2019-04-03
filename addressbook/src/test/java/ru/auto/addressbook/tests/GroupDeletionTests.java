package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.GroupData;
import ru.auto.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTO().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test6").withHeader("test66").withFooter("test666"));
    }
  }

  @Test
  public void testDeleteGroup() {
    Groups before = app.group().all();
    GroupData deletedGroup = before.iterator().next();
    app.group().deleteById(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(before.without(deletedGroup)));

  }
}
