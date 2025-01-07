package at.fhtw.app.service.user;

import at.fhtw.httpserver.http.ContentType;
import at.fhtw.httpserver.http.HttpStatus;
import at.fhtw.httpserver.server.Request;
import at.fhtw.httpserver.server.Response;
import at.fhtw.app.controller.Controller;
import at.fhtw.app.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;


public class UserController extends Controller {
    private UserDummyDAL userDAL;

    public UserController() {

        // Nur noch für die Dummy-JUnit-Tests notwendig. Stattdessen ein RepositoryPattern verwenden.
        this.userDAL = new UserDummyDAL();
    }

    // GET /weather(:id
    public Response getUser(String username)
    {
        try {
            User userData = this.userDAL.getUser(username);
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String userDataJSON = this.getObjectMapper().writeValueAsString(userData);

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
    // GET /weather
    public Response getUsers() {
        try {
            List userData = this.userDAL.getUsers();
            // "[ { \"id\": 1, \"city\": \"Vienna\", \"temperature\": 9.0 }, { ... }, { ... } ]"
            String userDataJSON = this.getObjectMapper().writeValueAsString(userData);

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
    public Response addUser(Request request) {
        try {

            // request.getBody() => "{ \"id\": 4, \"city\": \"Graz\", ...
            User user = this.getObjectMapper().readValue(request.getBody(), User.class);
            this.userDAL.addUser(user);

            return new Response(
                    HttpStatus.CREATED,
                    ContentType.JSON,
                    "{ message: \"Success\" }"
            );
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
