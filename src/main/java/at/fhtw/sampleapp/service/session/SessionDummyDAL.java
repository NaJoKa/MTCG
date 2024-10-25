package at.fhtw.sampleapp.service.session;

import at.fhtw.sampleapp.model.Session;
import java.util.ArrayList;
import java.util.List;

public class SessionDummyDAL {
    private List<Session> sessionData;

    public SessionDummyDAL() {
        sessionData = new ArrayList<>();
        sessionData.add(new Session("alexander","alexander-mtcgToken"));
        sessionData.add(new Session("lisa","lisa-mtcgToken"));
        sessionData.add(new Session("johanna","johanna-mtcgToken"));
    }

    // GET /weather/:id
    public Session getSession(String username) {
        Session foundWaether = sessionData.stream()
                .filter(user -> username == user.getUsername())
                .findAny()
                .orElse(null);

        return foundWaether;
    }

    // GET /weather
    public List<Session> getSessionIDs() {
        return sessionData;
    }

    // POST /weather
    public void addSession(Session session) {
        sessionData.add(session);
    }
}
