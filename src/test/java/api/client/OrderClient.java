package api.client;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {

    private static final String ORDER = "/api/orders";

    @Step("Создание заказа")
    public Response createOrder(String body) {
        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .post(ORDER);
    }
}