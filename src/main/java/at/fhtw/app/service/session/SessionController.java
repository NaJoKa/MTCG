package at.fhtw.app.service.session;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.app.controller.Controller;
import at.fhtw.app.model.Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;

public class SessionController extends Controller {
    private SessionDummyDAL sessionDAL;

    public SessionController() {

        // Nur noch für die Dummy-JUnit-Tests notwendig. Stattdessen ein RepositoryPattern verwenden.
        this.sessionDAL = new SessionDummyDAL();
    }

    // GET /weather(:id
    public Response getSession(String username)
    {
        try {
            Session sessionData = this.sessionDAL.getSession(username);
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String weatherDataJSON = this.getObjectMapper().writeValueAsString(sessionData);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    weatherDataJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }
    // GET /weather
    public Response getSessions() {
        try {
            List sessionData = this.sessionDAL.getSessionIDs();
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String userDataJSON = this.getObjectMapper().writeValueAsString(sessionData);

            return new Response(
                    HttpStatus.OK,
                    ContentType.JSON,
                    userDataJSON
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return new Response(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    ContentType.JSON,
                    "{ \"message\" : \"Internal Server Error\" }"
            );
        }
    }

    // POST /weather
    public Response addSession(Request request) {
        try {

            // request.getBody() => "{ \"id\": 4, \"city\": \"Graz\", ...
            Session session = this.getObjectMapper().readValue(request.getBody(), Session.class);
            this.sessionDAL.addSession(session);

            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    "{ message: \"Success\", \"usertoken: " + session.getSessionToken() + "\"}"         );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return new Response(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ContentType.JSON,
                "{ \"message\" : \"Internal Server Error\" }"
        );
    }

    // GET /weather
    // gleich wie "public Response getWeather()" nur mittels Repository
}
