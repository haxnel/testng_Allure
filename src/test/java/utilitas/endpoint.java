package utilitas;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class endpoint {
    static String HOST = "https://reqres.in"; //URL API Utama
    public static Response get(String api) {
        return RestAssured.get(HOST+api);
    }

    public static Response post(String body) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(body)
                .post(HOST+"/api/register");
    }

    public static Response put(String body, String val) {
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(body)
                .put(HOST+"/api/users/"+val);
    }

    public static Response patch(String body, String val){
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .log().all()
                .body(body)
                .patch(HOST+"/api/users/"+val);
    }

    public static Response delete(String val) {
        return RestAssured.delete(HOST+"/api/users/"+val);
    }
}
