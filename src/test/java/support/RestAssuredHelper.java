package support;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredHelper {

    public static RequestSpecification request;
    public static Response response;

    public void setBaseURI(String url) {
        RestAssured.baseURI = url;
        request = RestAssured
                .given()
                .relaxedHTTPSValidation()
                .urlEncodingEnabled(false)
                .log().all();
    }

    public void setContentType(String contentType) {
        request.header("Content-Type", contentType);
    }

    public void setAuthToken(String token) {
        request.header("Authorization", "Bearer " + token);
    }

    public void setRequestBody(String requestBody) {
        request.body(requestBody);
    }

    public Response postRequest(String endpoint) {
        response = request.post(endpoint);
        return response;
    }

    public Response getRequest(String endpoint) {
        response = request.get(endpoint);
        return response;
    }
}