package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO {
    private ResultSet resultSet = null;
    private User user;
    private Account account;
    private double accountBalance;



    private User retrieveUser(String query, String sSN, String pass) {

        user = null;
        try {
            if (!Database.dbConnection.isClosed()) {
                resultSet = retrieveSet(query, sSN, pass);
                if (resultSet != null) {
                    if (resultSet.next()) {
                        return user = createUserObject(resultSet);
                    }
                } else {
                    System.out.println("Empty resultSet");
                    return user;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return user;
        }
    }
    public User getUser(String personNumber, String password) {
        User temp = null;
        String query = "SELECT * FROM User where SSN = ? and password = ?;";
        try {
            temp = retrieveUser(query, personNumber, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    private User createUserObject(ResultSet resultSet) throws Exception {
        String sSN = resultSet.getString("SSN");
        String name = resultSet.getString("name");
        int account_number = resultSet.getInt("accountNumber");
        String password = resultSet.getString("password");
        user = new User(sSN, name, account_number);
        user.setPassword(password);
        return user;
    }

    protected ResultSet retrieveSet(String queryString, String... params) {
        try {
            if (!Database.dbConnection.isClosed()) {
                PreparedStatement prepStmt = Database.getConnection().prepareStatement(queryString);
                for (int i = 0; i < params.length; i++) {
                    prepStmt.setString(i+1, params[i]);
                }
                return resultSet = prepStmt.executeQuery();
            }
        } catch (SQLException | NullPointerException ex) {
            System.out.println("Error when executing statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resultSet;
    }

    public Account getAccount(int account_number) {
        Account temp = null;
        String query = "SELECT * FROM Account where id = ?;";
        try {
            temp = retrieveAccount(query, account_number);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return temp;
    }

    private Account retrieveAccount(String query, int account_number) {
        account = null;
        try {
            if (!Database.dbConnection.isClosed()) {
                resultSet = retrieveSet(query, String.valueOf(account_number));
                if (resultSet != null) {
                    if (resultSet.next()) {
                        return account = createAccountObject(resultSet);
                    }
                } else {
                    System.out.println("Empty resultSet");
                    return account;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with ResultSet!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                assert resultSet != null;
                resultSet.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return account;
        }
    }

    private Account createAccountObject(ResultSet resultSet) throws Exception {
        account = new Account(resultSet.getInt(1), resultSet.getDouble(2));
        return account;
    }

    private void updateBalance(Account account) {
        try {
            if (!Database.dbConnection.isClosed()) {
                if (account != null) {
                    accountBalance = account.getAccountBalance();
                    String query = "UPDATE `Account` SET `accountBalance` = ? WHERE (`id` = ?);";
                    PreparedStatement prepStmt = Database.getConnection().prepareStatement(query);
                    prepStmt.setDouble(1, accountBalance);
                    prepStmt.setInt(2, account.getAccountNumber());
                    prepStmt.executeUpdate();
                    prepStmt.close();

                } else {
                    throw new NullPointerException("The user object is null");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setTransaction(Transaction transaction) {
        try {
            if (!Database.dbConnection.isClosed()) {
                if (transaction.getAmount() != 0) {
                    String queryString = "INSERT INTO `Transaction` (`amount`, `accountNumber`) VALUES (?, ?);";
                    PreparedStatement prepStmt = Database.getConnection().prepareStatement(queryString);
                    prepStmt.setDouble(1, transaction.getAmount());
                    prepStmt.setInt(2, transaction.getAccount().getAccountNumber());
                    prepStmt.executeUpdate();
                    prepStmt.close();
                    updateBalance(transaction.getAccount());
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error while working with statement!");
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
