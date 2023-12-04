package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserTests {
    User userPayload;
    Response response;

    @BeforeClass
    public void setUpData(){
        userPayload = new User();
    }

    @Test(priority = 1, dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testPostUser(String userId, String userName, String firstName, String lastName, String email, String password, String phone){
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);

    }

    @Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testGetUserByName(String username){

        response = UserEndPoints.retrieveUser(username);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.jsonPath().getString("username"),username);
        softAssert.assertAll();
    }

    @Test(priority = 3, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username){

        response = UserEndPoints.deleteUser(username);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        Response getDeletedUser = UserEndPoints.retrieveUser(username);
        softAssert.assertEquals(getDeletedUser.getStatusCode(),404);
        softAssert.assertAll();
    }


}
