package testing_automate;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.annotations.Test;
import utilitas.endpoint;
public class patchuser {
    long max = 30000;
    @Test
    public void testing_positive(){
        String nama= "morpheus";
        String job = "zion resident";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", nama);
        jsonObject.put("job", job);

        Response response = endpoint.patch(jsonObject.toString(), "2");

        response.then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("name", Matchers.equalTo(nama))
                .assertThat().body("job", Matchers.equalTo(job))
                .assertThat().body("$", Matchers.hasKey("updatedAt"))
                .assertThat().time(Matchers.lessThan(max));
        System.out.println("Latensi: "+ response.getTime()+ " ms\n");
    }

    @Test
    public void testing_negative(){
        String nama= "testes";
        String job = "guru";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", nama);
        jsonObject.put("job", job);

        Response response = endpoint.patch(jsonObject.toString(), "200");

        response.then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().time(Matchers.lessThan(max));
        System.out.println("Latensi: "+ response.getTime()+ " ms\n");
    }
}
