package api.test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UserTests extends BaseTest {
    User userPayload;
    Response response;
    @BeforeClass
    public void setData(){
        userPayload = new User();
    }

    @Test(priority = 1, dataProvider = "UserData", dataProviderClass = DataProviders.class)
    public void testPostUser(String userId, String userName, String firstName, String lastName, String email, String password, String phone){
//       reading users data from Excel sheet
        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(userName);
        userPayload.setFirstName(firstName);
        userPayload.setLastName(lastName);
        userPayload.setEmail(email);
        userPayload.setPassword(password);
        userPayload.setPhone(phone);

        logger.info("....Creating user....");
        response = UserEndPoints.createUser(userPayload);
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("....User is created....");

    }

    @Test(priority = 2, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testGetUserByName(String username){
        logger.info("....Reading user information....");

        response = UserEndPoints.retrieveUser(username);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.jsonPath().getString("username"),username);
        softAssert.assertAll();

        logger.info("....User information is displayed ....");

    }

    @Test(priority = 3, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testUpdateUserByName(String username){
        logger.info("....Update user information....");
        //       updating users data from Excel sheet
        userPayload.setUserStatus(1);
        userPayload.setLastName("ahhmed");
        response = UserEndPoints.updateUser(userPayload,username);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertAll();

        logger.info("....User information is updated ....");

    }

    @Test(priority = 4, dataProvider = "UserNames",dataProviderClass = DataProviders.class)
    public void testDeleteUserByName(String username){
        logger.info("....Deleting user information....");

        response = UserEndPoints.deleteUser(username);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        Response getDeletedUser = UserEndPoints.retrieveUser(username);
        softAssert.assertEquals(getDeletedUser.getStatusCode(),404);
        softAssert.assertAll();
        logger.info("....user is deleted....");

    }


}
