package at.fhtw;

import at.fhtw.app.database.DatabaseManager;
import at.fhtw.httpserver.server.Server;
import at.fhtw.httpserver.utils.Router;
import at.fhtw.app.service.session.SessionService;
import at.fhtw.app.service.user.UserService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        /*Server server = new Server(10001, configureRouter());
        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        };*/
        DatabaseManager.getConnection();
    }

    private static Router configureRouter()
    {
        Router router = new Router();
        router.addService("/users", new UserService());
        router.addService("/sessions", new SessionService());

        return router;
    }
}
