package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.UserData;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class UserCreationTest extends TestBase {


    @Test
    public void testAddUser() {

        List<UserData> before = app.getUserHelper().getUserList();
        UserData user = new UserData("nameTest333", "Name2Test",null, null, null, "test1");

        app.getUserHelper().createUser(user);
        List<UserData> after = app.getUserHelper().getUserList();
        Assert.assertEquals(after.size(), before.size()+1);



        user.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(user);
        Comparator<? super UserData> byId = (u1, u2) -> Integer.compare(u1.getId(), u2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before,after);

    }

}
