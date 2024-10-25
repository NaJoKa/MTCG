package at.fhtw.sampleapp.service.user;

import at.fhtw.sampleapp.model.User;
import java.util.ArrayList;
import java.util.List;

public class UserDummyDAL {
    private List<User> userData;

    public UserDummyDAL() {
        userData = new ArrayList<>();
        userData.add(new User("alexander","hallo","Europe"));
        userData.add(new User("lisa","ciao","Europe"));
        userData.add(new User("johanna","ja","Asia"));
    }

    // GET /weather/:id
    public User getUser(String username) {
        User foundWaether = userData.stream()
                .filter(user -> username == user.getUsername())
                .findAny()
                .orElse(null);

        return foundWaether;
    }

    // GET /weather
    public List<User> getUsers() {
        return userData;
    }

    // POST /weather
    public void addUser(User user) {
        userData.add(user);
    }
}
