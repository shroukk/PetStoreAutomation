package api.endpoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserEndPointsUsingRoutesClass {
    public static Response response;
    public static Response createUser(User payload){
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.post_url);
        return response;
    }

    public static Response retrieveUser(String userName){
         response = given()
                .pathParams("username",userName)
                .when()
                .get(Routes.get_url);
        return response;
    }

    public static Response updateUser(User payload,String userName){
         response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username",userName)
                .body(payload)
                .when()
                .put(Routes.update_url);
        return response;
    }
    public static Response deleteUser(String userName){
         response = given()
                .pathParams("username",userName)
                .when()
                .delete(Routes.delete_url);
        return response;
    }

}
