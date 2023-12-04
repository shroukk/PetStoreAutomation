package api.test;

import api.endpoints.UserEndPointsUsingRoutesClass;
import api.payload.User;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTestsUsingFakerLib {
    Faker faker;
    User userPayload;
    Response response;

    @BeforeClass
    public void setUpData(){
        faker = new Faker();
        userPayload = new User();
        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

    }

    @Test(priority = 1)
    public void testPostUser(){
        response = UserEndPointsUsingRoutesClass.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2)
    public void testGetUser(){
        response = UserEndPointsUsingRoutesClass.retrieveUser(userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3)
    public void testUpdateUserByName(){
//        update data
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());

        response = UserEndPointsUsingRoutesClass.updateUser(userPayload,userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

//        check data after update
        Response updatedResponse = UserEndPointsUsingRoutesClass.retrieveUser(userPayload.getUsername());
        Assert.assertEquals(updatedResponse.getStatusCode(),200);
    }

    @Test(priority = 4)
    public void testDeleteUserByName(){
        response = UserEndPointsUsingRoutesClass.deleteUser(userPayload.getUsername());
        Assert.assertEquals(response.getStatusCode(),200);
    }
}
