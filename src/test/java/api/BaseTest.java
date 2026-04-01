package api;


import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.Before;


public class BaseTest {


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://stellarburgers.education-services.ru";




        RestAssured.filters(new AllureRestAssured());
    }
}

