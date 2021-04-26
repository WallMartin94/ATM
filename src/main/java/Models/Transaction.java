package Models;

public class Transaction {
    private double amount;
    private Account account;

    public Transaction(Account account){
        this.account = account;
    }

    public boolean deposit(double amount){
        if (amount > 0) {
            this.amount = amount;
            account.setAccountBalance(account.getAccountBalance() + amount);
            return true;
        } else return false;
    }

    public boolean withdraw(double amount){
        if(account.getAccountBalance() >= amount) {
            this.amount = -amount;
            account.setAccountBalance(account.getAccountBalance() - amount);
            return true;
        } else return false;
    }

    public double getAmount() {
        return amount;
    }

    public Account getAccount() {
        return account;
    }
}
