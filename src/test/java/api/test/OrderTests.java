package api.test;

import api.endpoints.OrderEndPoints;
import api.payload.Order;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class OrderTests {
    Order orderPayload;
    Response response;

    @BeforeClass
    public void setData(){
        orderPayload = new Order();
        orderPayload.setId(5);
        orderPayload.setPetId("9223372036854765462");
        orderPayload.setQuantity(6);
        orderPayload.setShipDate("2023-12-03T18:44:56.399+0000");

    }

    @Test(priority = 1)
    public void testPostOrder(){
        response = OrderEndPoints.createOrder(orderPayload);
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2)
    public void testGetOrderById(){
        response = OrderEndPoints.getOrder(orderPayload.getId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        softAssert.assertEquals(response.jsonPath().getInt("id"),orderPayload.getId());
        softAssert.assertEquals(response.jsonPath().getString("shipDate"),orderPayload.getShipDate());
        softAssert.assertEquals(response.jsonPath().getString("status"),orderPayload.getStatus());
        softAssert.assertEquals(response.jsonPath().getInt("quantity"),orderPayload.getQuantity());
        softAssert.assertAll();
    }
    @Test(priority = 3)
    public void testDeleteOrderById(){
        response = OrderEndPoints.deleteOrder(orderPayload.getId());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response.getStatusCode(),200);
        Response getDeletedOrder = OrderEndPoints.getOrder(orderPayload.getId());
        softAssert.assertEquals(getDeletedOrder.getStatusCode(),404);
        softAssert.assertAll();
    }
}
