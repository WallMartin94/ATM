package Models;

public class DBMethods {
    DAO dao = new DAO();

    public User getUser(String person_number, String password) {
       return dao.getUser(person_number, password);
    }

    public Account getAccount(int accountNumber){
        return dao.getAccount(accountNumber);
    }

    public void addTransaction(Transaction transaction){
        dao.setTransaction(transaction);
    }
}
