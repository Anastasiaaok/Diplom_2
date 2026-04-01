package api;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import model.UserGenerator;
import model.User;
import org.junit.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;




public class LoginTest extends BaseTest {


    @Test
    @DisplayName("Успешный логин пользователя")
    @Description("Создание пользователя и успешный логин")
    public void loginSuccess() {


        User user = UserGenerator.getRandomUser();


        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/auth/register");


        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }


    @Test
    @DisplayName("Логин с неверным паролем")
    @Description("Проверка ошибки при неправильном пароле")
    public void loginWrongPassword() {


        User user = UserGenerator.getRandomUser();


        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(user)
                .post("/api/auth/register");


        User wrongUser = new User(
                user.getEmail(),
                "wrongpassword",
                user.getName()
        );


        given()
                .filter(new AllureRestAssured())
                .contentType(ContentType.JSON)
                .body(wrongUser)
                .post("/api/auth/login")
                .then()
                .statusCode(401);
    }
}
