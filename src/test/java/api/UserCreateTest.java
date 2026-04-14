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
    @DisplayName("Создание без email")
    @Description("Ошибка при отсутствии email")
    public void createUserWithoutEmail() {
        User u = new User(null, user.getPassword(), user.getName());

        userClient.createUser(u)
                .then()
                .statusCode(403);
    }

    @Test
    @DisplayName("Создание без пароля")
    @Description("Ошибка при отсутствии пароля")
    public void createUserWithoutPassword() {
        User u = new User(user.getEmail(), null, user.getName());

        userClient.createUser(u)
                .then()
                .statusCode(403);
    }

    @Test
    @DisplayName("Создание без имени")
    @Description("Ошибка при отсутствии имени")
    public void createUserWithoutName() {
        User u = new User(user.getEmail(), user.getPassword(), null);

        userClient.createUser(u)
                .then()
                .statusCode(403);
    }
}