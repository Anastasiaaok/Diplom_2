package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import model.UserGenerator;
import org.junit.Before;
import org.junit.Test;

public class LoginTest extends BaseTest {

    private UserClient userClient;
    private User user;

    @Before
    public void setUpTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();

        // создаем пользователя перед тестами
        userClient.createUser(user);
    }

    @Test
    @DisplayName("Успешный логин")
    @Description("Логин под существующим пользователем")
    public void loginSuccess() {
        userClient.login(user)
                .then()
                .statusCode(200)
                .body("success", org.hamcrest.Matchers.equalTo(true));
    }

    @Test
    @DisplayName("Логин с неверным паролем")
    @Description("Ошибка при неправильном пароле")
    public void loginWrongPassword() {
        User wrongUser = new User(
                user.getEmail(),
                "wrongpassword",
                user.getName()
        );

        userClient.login(wrongUser)
                .then()
                .statusCode(401)
                .body("success", org.hamcrest.Matchers.equalTo(false));
    }

    @Test
    @DisplayName("Логин с неверным email")
    @Description("Ошибка при неправильном email")
    public void loginWrongEmail() {
        User wrongUser = new User(
                "wrong@mail.com",
                user.getPassword(),
                user.getName()
        );

        userClient.login(wrongUser)
                .then()
                .statusCode(401);
    }
}
