package api;


import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import model.User;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;




public class UserCreateTest extends BaseTest {


    @Test
    @DisplayName("Успешное создание пользователя")
    @Description("Проверка регистрации нового пользователя")
    public void createUserSuccess() {


        User user = new User(
                "test" + System.currentTimeMillis() + "@mail.com",
                "123456",
                "TestUser"
        );


        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/auth/register")
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }
}
