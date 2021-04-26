package Models;

public class Account {
    private double accountBalance;
    private int accountNumber;

    public Account(int accountNumber, double accountBalance){
        setAccountBalance(accountBalance);
        setAccountNumber(accountNumber);
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
