package at.fhtw.sampleapp.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Session {

    @JsonAlias({"Username"})
    private String username;
    @JsonAlias({"SessionToken"})
    private String sessionToken;

    // Jackson needs the default constructor
    public Session() {}

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Session(String username, String sessionToken) {
        this.username = username;
        this.sessionToken = sessionToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
