package at.fhtw.app.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class User {
    @JsonAlias({"Username"})
    private String username;
    @JsonAlias({"Password"})
    private String password;
    @JsonAlias({"region"})
    private String region;

    // Jackson needs the default constructor
    public User() {}

    public User(String username,String password, String region) {
        this.username = username;
        this.password = password;
        this.region = region;

    }

    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
