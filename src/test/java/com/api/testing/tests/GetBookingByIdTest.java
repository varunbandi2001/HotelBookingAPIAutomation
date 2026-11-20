package com.api.testing.tests;

import org.testng.annotations.Test;
import com.api.testing.base.BaseTest;


import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetBookingByIdTest  extends BaseTest{
	
	@Test
	
	public void verifyGetBookingById()
	{
		
		 String requestBody = "{\n" +
	                "  \"firstname\": \"Varun\",\n" +
	                "  \"lastname\": \"Kumar\",\n" +
	                "  \"totalprice\": 1500,\n" +
	                "  \"depositpaid\": true,\n" +
	                "  \"bookingdates\": {\n" +
	                "    \"checkin\": \"2026-12-10\",\n" +
	                "    \"checkout\": \"2026-12-15\"\n" +
	                "  },\n" +
	                "  \"additionalneeds\": \"Breakfast\"\n" +
	                "}";
		 
		 
		 Response createResponse = 
				 
				 given()
				.spec(requestSpec)
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/booking")
				
				.then()
				.log().all()
				.statusCode(200)
				.extract()
				.response();
		 
		 int bookingId = createResponse.jsonPath().getInt("bookingid");
		 
		 System.out.println("Created Booking ID:" + bookingId);
		 
		 
		 given()
		 .spec(requestSpec)
		 .pathParam("id", bookingId)
		 .when()
		 .get("/booking/{id}")
		 .then()
		 .log().all()
		 .statusCode(200)
		
		 
		 // STEP 5: Validate response
         .body("firstname", equalTo("Varun"))
         .body("lastname", equalTo("Kumar"))
         .body("totalprice", equalTo(1500))
         .body("depositpaid", equalTo(true))
         .body("bookingdates.checkin", equalTo("2026-12-10"))
         .body("bookingdates.checkout", equalTo("2026-12-15"))
         .body("additionalneeds", equalTo("Breakfast"));
	}

}
