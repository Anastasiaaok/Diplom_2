package model;


public class UserGenerator {


    public static User getRandomUser() {
        String email = "test" + System.currentTimeMillis() + "@mail.com";
        String password = "123456";
        String name = "TestUser";


        return new User(email, password, name);
    }
}
