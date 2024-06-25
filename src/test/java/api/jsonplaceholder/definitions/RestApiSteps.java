package api.jsonplaceholder.definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class RestApiSteps {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
    private Response response;

    @Given("The REST API is up and running")
    public void theRestAPIIsUpAndRunning() {
        RestAssured.baseURI = BASE_URL;
    }

    @When("I send a GET request to the API")
    public void iSendAGETRequestToTheAPI() {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.get().then().log().all().extract().response();
        System.out.println("Response: " + response);
    }

    @Then("The status code of the response should be {int}")
    public void theStatusCodeOfTheResponseShouldBe(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }


    @When("I send a POST request to the API")
    public void iSendAPOSTRequestToTheAPI() {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        String jsonBody = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
        response = httpRequest.body(jsonBody).post().then().log().all().extract().response();
        System.out.println("POST Response: " + response);
    }

    @When("I send a POST request to the API {string} {string} {string}")
    public void iSendAPOSTRequestToTheAPI(String title, String body, String userId) {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        String jsonBody = "{\"title\":\""+title+"\",\"body\":\""+body+"\",\"userId\":"+userId+"}";
        response = httpRequest.body(jsonBody).post().then().log().all().extract().response();
        System.out.println("------------------------------------------------");
    }

    @When("I send a GET request to the API for user id {string}")
    public void iSendAGETRequestToTheAPIForUserId(String userId) {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.get("/" + userId).then().log().all().extract().response();
    }
    @Then("I compare GET response with POST request {string} {string} {string}")
    public void iCompareGETResponseToPOSTRequest(String userId, String title, String body){
        String actualUserId = response.jsonPath().getString("id");
        String actualTitle = response.jsonPath().getString("title");
        String actualBody = response.jsonPath().getString("body");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualUserId, userId, "Compare UserID");
        softAssert.assertEquals(actualTitle, title,"Compare Title");
        softAssert.assertEquals(actualBody, body, "Compare Body");
        softAssert.assertAll("Compare result: ");
    }

    @When("I send a PUT request to the API for user id {string} with {string} {string}")
    public void iSendAPUTRequestToTheAPIForUserId(String userId, String title, String body ) {
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        String jsonBody = "{\"username\":\""+title+"\",\"body\":\""+body+"\"}";
        response = httpRequest.body(jsonBody).put("/" + userId).then().log().all().extract().response();
        System.out.println("------------------------------------------------");
    }

    @When("I send a DELETE request to the API for user id {string}")
    public void iSendADELETERequestToTheAPIForUserId(String userId){
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.delete("/" + userId).then().log().all().extract().response();
        System.out.println("------------------------------------------------");
    }
}
