package Models;

public class UserSingleton {
    private static final UserSingleton userInstance = new UserSingleton();

    private UserSingleton() {}

    public static UserSingleton getInstance() {
        return userInstance;
    }


    User user = new User() {};

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
