package api.endpoints;

import api.payload.Order;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class OrderEndPoints {
    static Response response;
    public static Response createOrder(Order payload){
        String post_url = BaseRoutes.getURL().getString("create_order");
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public static Response getOrder(int orderId){
        String get_url = BaseRoutes.getURL().getString("get_order");
        response = given()
                .pathParams("orderId", orderId)
                .when()
                .get(get_url);
        return response;

    }

    public static Response deleteOrder(int orderId){
        String delete_url = BaseRoutes.getURL().getString("delete_order");
        response = given()
                .pathParams("orderId", orderId)
                .when()
                .delete(delete_url);
        return response;

    }


}
