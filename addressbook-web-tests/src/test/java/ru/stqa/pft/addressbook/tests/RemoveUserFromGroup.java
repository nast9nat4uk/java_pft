package ru.stqa.pft.addressbook.tests;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import ru.stqa.pft.addressbook.model.Users;

/**
 * Created by Настя on 30.03.2017.
 */
public class RemoveUserFromGroup extends  TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().users().size() ==0) {
            app.goTo().userPage();
            app.user().create(new UserData().withName("nameTest").withLastName("Name2Test")
                    .withAddress("addressTest").withHomePhone( "1234567").withEmail( "m@m.com"));
        }

        if (app.db().groups().size() ==0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("g1").withHeader("h1").withFooter("f1"));
        }
        app.goTo().userPage();
    }

    @Test
    public void testRemoveUserFromGroup(){
        Users before = app.db().users();
        UserData selectedUser = before.iterator().next();
        String targetGroup = app.user().getTargetGroup();
        app.user().addUserToGroup(selectedUser);
        app.goTo().targetGroup(targetGroup);
        app.user().selectUserById(selectedUser.getId());
        app.user().removeUserFromTargetGroup();
        app.goTo().targetGroup(targetGroup);
        app.user().assertUserIsRemovedFromGroup(selectedUser);

    }
}
