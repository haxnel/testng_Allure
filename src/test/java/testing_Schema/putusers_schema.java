package testing_Schema;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utilitas.endpoint;

import java.io.File;

public class putusers_schema {
    @Test
    public void schema_put_user(){
        String nama= "morpheus";
        String job = "zion resident";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", nama);
        jsonObject.put("job", job);

        Response response = endpoint.put(jsonObject.toString(), "2");
        File file = new File("src/test/resources/schema/schema_putusers.json");

        response.then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file));
    }
}
