package api.test;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Collections;

public class PetTests {
    Pet petPayload;
    Response response;
    @BeforeClass
    public void setData() {

        petPayload = new Pet();
        petPayload.setPetId("13212131");
        petPayload.setName("Russie");
        petPayload.setStatus("available");
        petPayload.setCategory(5,"Catss");
        petPayload.setPhotoUrls(Collections.singletonList("photo.testy"));
        petPayload.setTags(6, "my catties");

    }

    @Test(priority = 1)
    public void testCreatePet(){
        response = PetEndPoints.createPet(petPayload);
        String newPetId = response.jsonPath().getString("id");
        petPayload.setPetId(newPetId);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2)
    public void testGetPetById(){
        response = PetEndPoints.getPetById(petPayload.getPetId());
        response.then().log().all();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.jsonPath().getString("id"),petPayload.getPetId());
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void testGetPetByStatus(){
        response = PetEndPoints.getPetByStatus(petPayload.getStatus());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }


    @Test(priority = 4)
    public void testUpdatePet(){

        petPayload.setStatus("pending");
        petPayload.setPhotoUrls(Collections.singletonList("photo.test"));
        response = PetEndPoints.updatePet(petPayload);
        response.then().log().all();
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.jsonPath().getString("status"),petPayload.getStatus());
        softAssert.assertEquals(response.jsonPath().get("photoUrls").toString().trim(),petPayload.getPhotoUrls().toString().trim());
        softAssert.assertAll();

    }
    @Test(priority = 5)
    public void testUpdatePetWithFormData(){
        petPayload.setStatus("sold");
        petPayload.setName("souzi");
        response = PetEndPoints.updatePetWithFormData(petPayload.getPetId(),petPayload.getName(), petPayload.getStatus());
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        Response getUpdatedPet =PetEndPoints.getPetById(petPayload.getPetId());
        softAssert.assertEquals(getUpdatedPet.jsonPath().getString("status"),petPayload.getStatus());
        softAssert.assertEquals(getUpdatedPet.jsonPath().getString("name"),petPayload.getName());
        softAssert.assertAll();

    }

    @Test(priority = 6)
    public void testDeletePetById(){
        response = PetEndPoints.deletePetById(petPayload.getPetId());

        Assert.assertEquals(response.getStatusCode(),200);
        Response getDeletedPet =PetEndPoints.getPetById(petPayload.getPetId());
        Assert.assertEquals(getDeletedPet.getStatusCode(),404);

    }
}
