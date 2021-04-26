package Models;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    String pers_num = "333333";
    String name = "Ivan Åkesson";
    int acc_num = 32015782;

    User testUser = new User(pers_num, name, acc_num);

    @Test
    public void getPerson_number() {
        Assert.assertEquals(pers_num, testUser.getString());
    }

    @Test
    public void setPerson_number() {
        String pn = "00000";
        testUser.setString(pn);
        Assert.assertEquals(pn, testUser.getString());
    }

    @Test
    public void getName() {
        Assert.assertEquals(name, testUser.getName());
    }

    @Test
    public void setName() {
        String nm = "name";
        testUser.setName(nm);
        Assert.assertEquals(nm, testUser.getName());
    }

    @Test
    public void getPassword() {
        String pass = "secret";
        testUser.setPassword(pass);
        Assert.assertEquals(pass, testUser.getPassword());
    }

    @Test
    public void getAccount_number() {
        Assert.assertEquals(acc_num, testUser.getAccountNumber());
    }

    @Test
    public void setAccount_number() {
        int acc = 22222;
        testUser.setAccountNumber(acc);
        Assert.assertEquals(acc, testUser.getAccountNumber());
    }
}