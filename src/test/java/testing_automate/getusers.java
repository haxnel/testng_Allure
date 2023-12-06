package testing_automate;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilitas.endpoint;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class getusers {
    long max = 60000;
    //Cara 1 :)
    @Test
    public void testing_positive_getuser1(){
        Response response = endpoint.get("/api/users?page=1");
        long latency = response.getTime(); // dalam milidetik
        String page = response.jsonPath().getString("page");
        List <String> data = response.jsonPath().getList("data.id");//Menyimpan data pada data.id

        // Membuat Format response body seperti JSON
        JSONObject jsonObject = new JSONObject(response.body().asString());
        String formattedJson = jsonObject.toString(4);

        System.out.println(formattedJson + "\n");
        System.out.println("Latency: " + latency + " milliseconds");
        System.out.println("halaman: " + page);

        //Validasi
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(page, "1"); //Cek page yang diakses benar
        Assert.assertEquals(data.size(), 6);// Cek data harus mempunyai jumlah total 6
        Assert.assertTrue(latency < max);//Cek latensi harus kurang dari max latensi yang ditentikan
    }

    //Cara 2 dengan BDD format
    @Test
    public void testing_positive_getuser2(){
        Response response = endpoint.get("/api/users?page=2");
        response.then()
                .log().all()
                .assertThat().time(Matchers.lessThan(max))
                .assertThat().body("data", Matchers.instanceOf(List.class))
                .assertThat().body("page", Matchers.equalTo(2))
                .assertThat().body("data.id", Matchers.hasSize(6))
                .assertThat().statusCode(200);
    }

    @Test
    public void testing_negative_getuser(){
        Response response = endpoint.get("/api/users/22");
        long max_negative = 1000;
        response.then()
                .log().all() //Mendapatkan kembalian data dan menampilkannya
                .assertThat().time(Matchers.lessThan(max_negative))// Waktu respon tidak boleh melewati waktu max_in
                .assertThat().statusCode(404) // Harus mengeluarkan status code 404
                .assertThat().body("page", Matchers.nullValue()); //Tidak terdapat value pada path page
    }
}
