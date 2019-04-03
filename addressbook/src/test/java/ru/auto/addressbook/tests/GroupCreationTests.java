package ru.auto.addressbook.tests;

import org.testng.annotations.Test;
import ru.auto.addressbook.model.GroupData;
import ru.auto.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTO().GroupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test111");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt()))));
  }

}
