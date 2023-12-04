package api.endpoints;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

public class UserEndPoints {
    public static Response response;


    public static Response createUser(User payload){
        String post_url = BaseRoutes.getURL().getString("post_url");
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public static Response retrieveUser(String userName){
        String get_url = BaseRoutes.getURL().getString("get_url");

        response = given()
                .pathParams("username",userName)
                .when()
                .get(get_url);
        return response;
    }

    public static Response updateUser(User payload,String userName){
        String update_url = BaseRoutes.getURL().getString("update_url");

        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParams("username",userName)
                .body(payload)
                .when()
                .put(update_url);
        return response;
    }
    public static Response deleteUser(String userName){
        String delete_url = BaseRoutes.getURL().getString("delete_url");

         response = given()
                .pathParams("username",userName)
                .when()
                .delete(delete_url);
        return response;
    }

}
