package api.endpoints;

import api.payload.Pet;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class PetEndPoints {
    static Response response;

    public static Response createPet(Pet payload){
        String post_url = BaseRoutes.getURL().getString("create_pet");
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return response;
    }

    public static Response getPetById(String petId){
        String get_url = BaseRoutes.getURL().getString("get_pet");
        response = given()
                .pathParams("petId",petId)
                .when()
                .get(get_url);
        return response;
    }
    public static Response getPetByStatus(String status){
        String get_url = BaseRoutes.getURL().getString("get_pet_by_status");
        response = given()
                .queryParam("status",status)
                .when()
                .get(get_url);
        return response;
    }

    public static Response updatePet(Pet payload){
        String update_url = BaseRoutes.getURL().getString("update_pet");
        response = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .put(update_url);
        return response;
    }

    public static Response updatePetWithFormData(String petId, String name, String status ){
        String update_url = BaseRoutes.getURL().getString("update_pet_with_form_data");
        response = given()
                .header("Content-Type","application/x-www-form-urlencoded")
                .accept(ContentType.JSON)
                .pathParams("petId",petId)
                .body("name="+name+"&status="+status)
                .when()
                .post(update_url);
        return response;
    }

    public static Response deletePetById(String petId){
        String delete_url = BaseRoutes.getURL().getString("delete_pet");

        response = given()
                .pathParams("petId",petId)
                .when()
                .delete(delete_url);
        return response;
    }
}
