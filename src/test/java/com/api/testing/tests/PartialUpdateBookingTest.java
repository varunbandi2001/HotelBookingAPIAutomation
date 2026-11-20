package com.api.testing.tests;



import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.testing.base.BaseTest;


public class PartialUpdateBookingTest extends BaseTest {
	
	@Test
	
	public void partialUpdateBooking()
	{
		
		String createBody =
				"{"
		                + "\"firstname\":\"John\","
		                + "\"lastname\":\"Smith\","
		                + "\"totalprice\":1500,"
		                + "\"depositpaid\":true,"
		                + "\"bookingdates\":{"
		                + "\"checkin\":\"2026-12-10\","
		                + "\"checkout\":\"2026-12-15\""
		                + "},"
		                + "\"additionalneeds\":\"Breakfast\""
		                + "}";
		
		int bookingId = 
				
				given()
				
				.spec(requestSpec)
				.contentType("application/json")
				.body(createBody)
				.when()
				.post("/booking")
				.then()
				.statusCode(200)
				.extract()
				.path("bookingid");
		
System.out.println("Created Booking ID:" + bookingId);

String authBody = 
"{"
        + "\"username\":\"admin\","
        + "\"password\":\"password123\""
        + "}";

      
		String token = 
				given()
				.spec(requestSpec)
				.contentType("application/json")
				.body(authBody)
				.when()
				.post("/auth")
				.then()
				.statusCode(200)
				.extract()
				.path("token");
		System.out.println("Authentication token generated");
		
		
		String patchBody = "{"
                + "\"firstname\":\"Varun\""
                + "}";
		
		given()
		
		.spec(requestSpec)
		.contentType("application/json")
		.cookie("token",token)
		.pathParam("id",bookingId)
		.body(patchBody)
		.when()
		.patch("/booking/{id}")
		.then()
		.log().all()
		.statusCode(200)
		.body("firstname",equalTo("Varun"))
		.body("lastname", equalTo("Smith"))
		.body("totalprice",equalTo(1500));
		
		
		System.out.println("Partial Update Successful");
				
				

	}

}
