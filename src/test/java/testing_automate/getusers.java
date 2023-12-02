package utilitas;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class getusers {
    @Test
    public void testing_positive_getuser(){
        Response response = endpoint.get("https://reqres.in/api/users?page=1");
        Assert.assertEquals(response.statusCode(),200);
        // Format the response body as JSON
        JSONObject jsonObject = new JSONObject(response.body().asString());
        String formattedJson = jsonObject.toString(4);
        System.out.println(formattedJson);
    }
}
