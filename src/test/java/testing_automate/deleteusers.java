package testing_automate;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import utilitas.endpoint;
public class deleteusers {
    long max = 30000;
    @Test
    public void testing_positive(){
        Response response = endpoint.delete("2");
        response.then()
                .log().all()
                .assertThat().time(Matchers.lessThan(max))
                .assertThat().body(Matchers.equalTo(""))
                .assertThat().statusCode(204);
    }

    @Test
    public void testing_negative(){
        Response response = endpoint.delete("2222");
        response.then()
                .log().all()
                .assertThat().time(Matchers.lessThan(max))
                .assertThat().body(Matchers.equalTo(""))
                .assertThat().statusCode(204);
    }
}
