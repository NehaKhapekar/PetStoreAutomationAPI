package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
import api.endpoints.StoreEndPoints;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests {

	Faker faker;
	Store storePayload;
	
	public Logger logger; // for logs
	
	@BeforeClass
	public void setup()
	{
		faker=new Faker();
		storePayload=new Store();
		
	   storePayload.setId(faker.idNumber().hashCode());
	   storePayload.setPetId(faker.number().randomNumber());
		storePayload.setQuantity(faker.number().numberBetween(1, 100));
		storePayload.setStatus(faker.options().option("Placed", "approved", "delivered"));
		//storePayload.setComplete(faker.options().option("complete", "incomplete"));
		storePayload.setComplete(faker.bool().bool());
		
		
		//logs
		logger= LogManager.getLogger(this.getClass());
		
		logger.debug("debugging.....");
		
	}
	
	@Test(priority=1)
	public void testPlaceOrder()
	{
		logger.info("********** Creating user  ***************");
		Response response=StoreEndPoints.CreatePlaceOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********User is creatged  ***************");
	
		System.out.println(response.getBody().asString());
		
	}
	
	@Test(priority=2)
	public void testGetOrderByPetID()
	{
		logger.info("********** Reading User Info ***************");
		
		Response response=StoreEndPoints.readOrder(this.storePayload.getId());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		
		logger.info("**********User info  is displayed ***************");
		
	}
	
	
	
}
