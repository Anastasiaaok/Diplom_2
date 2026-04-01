package api;


import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;




public class OrderTest extends BaseTest {


    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Проверка, что заказ можно создать без авторизации")
    public void createOrderWithoutAuth() {


        String body = "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\"] }";


        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(body)
                .post("/api/orders")
                .then()
                .statusCode(200)
                .body("order.number", notNullValue());
    }
}

