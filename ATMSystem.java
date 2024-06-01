import java.util.HashMap;
import java.util.Map;

public class ATMSystem {
    private Map<String, User> users;

    public ATMSystem() {
        this.users = new HashMap<>();
        // Adding some dummy users for testing
        users.put("user1", new User("user1", "1234"));
        users.put("user2", new User("user2", "5678"));
    }

    public User authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public User findUserById(String userId) {
        return users.get(userId);
    }

    public static void main(String[] args) {
        ATMSystem atmSystem = new ATMSystem();
        ATM atm = new ATM(atmSystem);
        atm.start();
    }
}
