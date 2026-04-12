package api;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.User;
import model.UserGenerator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserCreateTest extends BaseTest {

    private UserClient userClient;
    private User user;

    @Before
    public void setUpTest() {
        userClient = new UserClient();
        user = UserGenerator.getRandomUser();
    }

    @Test
    @DisplayName("Успешное создание пользователя")
    @Description("Создание уникального пользователя")
    public void createUserSuccess() {
        userClient.createUser(user)
                .then()
                .statusCode(200)
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание существующего пользователя")
    @Description("Нельзя создать пользователя дважды")
    public void createExistingUser() {
        userClient.createUser(user);

        userClient.createUser(user)
                .then()
                .statusCode(403)
                .body("message", equalTo("User already exists"));
    }

    @Test
    @DisplayName("Создание без обязательного поля")
    @Description("Ошибка при отсутствии email")
    public void createUserWithoutEmail() {
        User userWithoutEmail = new User(
                null,
                user.getPassword(),
                user.getName()
        );

        userClient.createUser(userWithoutEmail)
                .then()
                .statusCode(403);
    }
}