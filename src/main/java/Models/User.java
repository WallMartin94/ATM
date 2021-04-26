package Models;

public class User {
    private String string;
    private String name;
    private String password;
    private int accountNumber;

    public User(){}

    public User(String string, String name, int accountNumber){
        setString(string);
        setName(name);
        setAccountNumber(accountNumber);

    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

}
