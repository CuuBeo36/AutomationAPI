package api.sample;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostApiTest {
    Response response;
    public HashMap<Object,Object> map=new HashMap<Object,Object>();

    @Given("^the valid endpoint with payload to create user$")
    public void setupEndpointAndPostData()
    {
        RestAssured.baseURI="https://reqres.in/";
        RestAssured.basePath="/api/users";

        map.put("name","john");
        map.put("job", "Software Developer");
    }


    @When("^the request is send to the server$")
    public void sendRequest()
    {
        response = given()
                .contentType(ContentType.JSON)
                .body(map)
                .when()
                .post()
                .then()
                .statusCode(201).contentType(ContentType.JSON).
                extract().response();
    }


    @Then("^the new user must be created with name as \"([^\"]*)\"$")
    public void validateResponse(String name)
    {
        String userName = response.path("name");
        Assert.assertEquals(userName, name);
    }
}
