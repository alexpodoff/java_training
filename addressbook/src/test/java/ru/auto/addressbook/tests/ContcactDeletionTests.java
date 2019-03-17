package ru.auto.addressbook.tests;

import org.testng.annotations.*;

public class ContcactDeletionTests extends TestBase {

  @Test
  public void testDeleteContcact() throws Exception {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().selectContactById(660);
    app.getContactHelper().deleteContact();
    app.getNavigationHelper().confirmAlert();
    app.getNavigationHelper().gotoHomePage();
  }
}
