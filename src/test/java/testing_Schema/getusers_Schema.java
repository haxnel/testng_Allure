package testing_Schema;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilitas.endpoint;

import java.io.File;

public class getusers_Schema {
    @Test
    public void testing_getuser_Schema(){
        Response response = endpoint.get("/api/users?page=1");
        Response response2 = endpoint.get("/api/users?page=2");
        Response response3 = endpoint.get("/api/users/3");
        File file = new File("src/test/resources/schema/schema_getusers.json");
        File file2 = new File("src/test/resources/schema/schema_getsingle_users.json");

        response.then()
                .log().all()
                        .assertThat().statusCode(200)
                        .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));

        response2.then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));

        response3.then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file2));
    }

}
