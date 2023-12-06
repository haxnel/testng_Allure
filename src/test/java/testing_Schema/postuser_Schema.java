package testing_Schema;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilitas.endpoint;

import java.io.File;

public class postuser_Schema {
    @Test
    public void testing_post_user(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eve.holt@reqres.in");
        jsonObject.put("password", "pistol");
        File file = new File("src/test/resources/schema/schema_postUsers.json");

        Response response = endpoint.post(jsonObject.toString());

        response.then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));

    }

    @Test
    public void testing_schema_post_error(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "studdentt@reqres.in");
        jsonObject.put("password", "1233445");
        File file = new File("src/test/resources/schema/schema_postusers_error.json");

        Response response = endpoint.post(jsonObject.toString());
        response.then()
                .log().all()
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));

        Assert.assertEquals(response.getStatusCode(),400);
        Assert.assertEquals(response.getBody().jsonPath().get("error"), "Note: Only defined users succeed registration");
    }
}
