package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndPoints {

	public static Response CreatePlaceOrder(Store payload)
	{
		Response response=given()
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
			.body(payload)
		.when()
			.post(Routes.post_url_store);
			
		return response;
	}
	
	public static Response readOrder(int orderId)
	{
		Response response=given()
				.pathParam("orderId", orderId)
		.when()
			.get(Routes.get_url_store);
			
		return response;
	}
	
}
