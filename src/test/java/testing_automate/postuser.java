package testing_automate;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilitas.endpoint;

public class postuser {
    //Positive Testing Register users
    long max = 30000;
    @Test
    public void testing_post_user(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eve.holt@reqres.in");
        jsonObject.put("password", "pistol");

        Response response = endpoint.post(jsonObject.toString());

        long latency = response.getTime(); // latensi post dalam milidetik

        response.then().log().all()
                .assertThat().statusCode(200)
                .assertThat().body("$", Matchers.hasKey("id"))
                .assertThat().body("$", Matchers.hasKey("token"))
                .assertThat().time(Matchers.lessThan(max));

        System.out.println("Latensi: "+ latency + " ms");
    }

    //Negative Testing Register users
    @Test
    public void testing_negative_post_user2 (){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "eve.holt@reqres.in");

        Response response = endpoint.post(jsonObject.toString());
        String error = response.jsonPath().getString("error");
        long latency = response.getTime(); // latensi post dalam milidetik

        response.then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("$", Matchers.hasKey("error"))
                .assertThat().time(Matchers.lessThan(max));

        System.out.println("Latensi: "+ latency + " ms");
        System.out.println(error + "\n");
    }

    //Negative Testing Register users dengan email lain
    @Test
    public void testing_negative2_post_user (){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email", "studdentt@reqres.in");
        jsonObject.put("password", "1233445");

        Response response = endpoint.post(jsonObject.toString());
        String error = response.jsonPath().getString("error");
        long latency = response.getTime(); // latensi post dalam milidetik
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),400);
        Assert.assertEquals(response.getBody().jsonPath().get("error"), "Note: Only defined users succeed registration");
        Assert.assertTrue(max > latency);

        System.out.println("Latensi: "+ latency + " ms");
        System.out.println(error + "\n");
    }
}
