package api.tests;

import api.BaseTest;
import api.model.UserModel;
import com.github.javafaker.Faker;
import com.google.gson.Gson;
import io.qameta.allure.AllureId;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

@Tag("api-tests")
public class ReqResTest extends BaseTest {

    final Faker faker = new Faker();
    final Gson gson = new Gson();
    final String pswrd = "password" + faker.number().digits(4);
    final String name = faker.address().firstName();

    @AllureId("2236")
    @DisplayName("GET. User not found")
    @Test
    public void userNotFound() {
        given().spec(requestSpecification)
                .get("usernotfound/100500").then().assertThat().statusCode(HttpStatus.SC_NOT_FOUND).log().body();
    }

    @AllureId("2237")
    @DisplayName("GET. Users list")
    @Test
    public void usersList() {
        given().spec(requestSpecification)
                .when()
                .get("users?page=2")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log().body();
    }

    @AllureId("2238")
    @DisplayName("GET. User by id")
    @Test
    public void userById() {
        given().spec(requestSpecification)
                .when()
                .get("users/2")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("data.id",
                        equalTo(2));
    }

    @AllureId("2239")
    @DisplayName("PUT. Update user")
    @Test
    public void updateUser() {
        String name = "Homer Simpson";
        String job = "Nuclear safety inspector";
        final UserModel userModel = new UserModel(name, "", "", job);
        final String str = gson.toJson(userModel);
        given().spec(requestSpecification)
                .when().body(str)
                .put("users/2")
                .then().assertThat()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }

    @AllureId("2240")
    @DisplayName("POST. Create User")
    @Test
    public void createUser() {
        final String emailAddress = faker.internet().emailAddress();
        UserModel userModel = new UserModel(name, emailAddress, pswrd, "Working class hero");
        final String userRegJson = gson.toJson(userModel);
        given().spec(requestSpecification)
                .when().body(userRegJson)
                .post("api/register")
                .then()
                .statusCode(HttpStatus.SC_CREATED)
                .log().body()
                .body("id",
                        is(notNullValue()));
    }
}
