package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.User;

import static io.restassured.RestAssured.given;

public class UserClient {

    private static final String CREATE = "/api/auth/register";
    private static final String LOGIN = "/api/auth/login";

    @Step("Создание пользователя")
    public Response createUser(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(CREATE);
    }

    @Step("Логин пользователя")
    public Response login(User user) {
        return given()
                .contentType(ContentType.JSON)
                .body(user)
                .post(LOGIN);
    }
}
