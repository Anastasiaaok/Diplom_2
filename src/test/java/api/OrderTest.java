package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.notNullValue;

public class OrderTest extends BaseTest {

    private OrderClient orderClient;

    @Before
    public void setUpTest() {
        orderClient = new OrderClient();
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    @Description("Можно создать заказ без токена")
    public void createOrderWithoutAuth() {

        String body = "{ \"ingredients\": [\"61c0c5a71d1f82001bdaaa6d\", \"61c0c5a71d1f82001bdaaa6f\"] }";

        orderClient.createOrder(body)
                .then()
                .statusCode(200)
                .body("order.number", notNullValue());
    }

    @Test
    @DisplayName("Создание заказа без ингредиентов")
    @Description("Ошибка если не передать ингредиенты")
    public void createOrderWithoutIngredients() {

        String body = "{ \"ingredients\": [] }";

        orderClient.createOrder(body)
                .then()
                .statusCode(400);
    }

    @Test
    @DisplayName("Создание заказа с неверным хешем")
    @Description("Ошибка при невалидном хеше")
    public void createOrderWithWrongHash() {

        String body = "{ \"ingredients\": [\"123456789012345678901234\"] }";

        orderClient.createOrder(body)
                .then()
                .statusCode(400); // ВАЖНО: API реально возвращает 400
    }
}
