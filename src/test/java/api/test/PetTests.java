package api.test;

import api.endpoints.PetEndPoints;
import api.payload.Pet;
import org.testng.asserts.SoftAssert;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Collections;

public class PetTests extends BaseTest {
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
        logger.info("......Creating Pet......");

        response = PetEndPoints.createPet(petPayload);
        String newPetId = response.jsonPath().getString("id");
        petPayload.setPetId(newPetId);
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("......Pet is created......");

    }

    @Test(dependsOnMethods = "testCreatePet")
    public void testGetPetById(){
        logger.info("......Reading Pet information......");

        response = PetEndPoints.getPetById(petPayload.getPetId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.jsonPath().getString("id"),petPayload.getPetId());
        softAssert.assertAll();
        logger.info("......Pet information is displayed......");

    }

    @Test(dependsOnMethods = "testCreatePet")
    public void testGetPetByStatus(){
        logger.info("......Reading Pets information with specific status......");

        response = PetEndPoints.getPetByStatus(petPayload.getStatus());
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("......Pets information is displayed......");

    }


    @Test(dependsOnMethods = "testGetPetById")
    public void testUpdatePet(){
        logger.info("......Updating Pet information ......");

        petPayload.setStatus("pending");
        petPayload.setPhotoUrls(Collections.singletonList("photo.test"));
        response = PetEndPoints.updatePet(petPayload);
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.jsonPath().getString("status"),petPayload.getStatus());
        softAssert.assertEquals(response.jsonPath().get("photoUrls").toString().trim(),petPayload.getPhotoUrls().toString().trim());
        softAssert.assertAll();

        logger.info("...... Pet information is updated ......");


    }
    @Test(dependsOnMethods = "testUpdatePet")
    public void testUpdatePetWithFormData(){
        logger.info("...... Updating pet information (Name & Status)......");

        petPayload.setStatus("sold");
        petPayload.setName("Souzi");
        response = PetEndPoints.updatePetWithFormData(petPayload.getPetId(),petPayload.getName(), petPayload.getStatus());
        SoftAssert softAssert= new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        Response getUpdatedPet =PetEndPoints.getPetById(petPayload.getPetId());
        softAssert.assertEquals(getUpdatedPet.jsonPath().getString("status"),petPayload.getStatus());
        softAssert.assertEquals(getUpdatedPet.jsonPath().getString("name"),petPayload.getName());
        softAssert.assertAll();

        logger.info("...... Pet information (Name & Status) is updated ......");


    }

    @Test(dependsOnMethods = "testUpdatePetWithFormData")
    public void testDeletePetById(){
        logger.info("...... Deleting pet information ......");

        response = PetEndPoints.deletePetById(petPayload.getPetId());

        Assert.assertEquals(response.getStatusCode(),200);
        Response getDeletedPet =PetEndPoints.getPetById(petPayload.getPetId());
        Assert.assertEquals(getDeletedPet.getStatusCode(),404);

        logger.info("...... Pet information is deleted ......");

    }
}
