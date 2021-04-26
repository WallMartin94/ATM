package Models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    int acc_num = 2478;
    double acc_bal= 200.00;

    Account testAccount = new Account(acc_num,acc_bal);

    @Test
    public void setAccount_balance() {
        double testBal=200.00;
        testAccount.setAccountBalance(100.00);
        Assert.assertEquals(testBal, testAccount.getAccountBalance());


    }

    @Test
    public void getAccount_balance() {
        double testBal=200.00;
        testAccount.setAccountBalance(testBal);
        Assert.assertEquals(testBal, testAccount.getAccountBalance());
    }

    @Test
    public void getAccount_number() {
        Assert.assertEquals(acc_num,testAccount.getAccountNumber());

    }

    @Test
    public void setAccount_number() {
        int testAccNum = 5040;
        testAccount.setAccountNumber(testAccNum);
        Assert.assertEquals(testAccNum, testAccount.getAccountNumber());
    }
}