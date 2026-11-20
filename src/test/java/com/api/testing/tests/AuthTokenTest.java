package com.api.testing.tests;

import org.testng.annotations.Test;

import com.api.testing.base.BaseTest;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;



public class AuthTokenTest extends BaseTest{

	
	@Test
	
	public void generateAuthToken()
	{
		String authBody =  "{\n" +
                "  \"username\": \"admin\",\n" +
                "  \"password\": \"password123\"\n" +
                "}";
		
		Response response = 
				given()
				.spec(requestSpec)
				.contentType("application/json")
				.body(authBody)
				.log().all()
				
				.when()
				.post("/auth")
				.then()
				.log()
				.all()
				.statusCode(200)
				.body("token",notNullValue())
				.extract()
				.response();
		

        String token = response.jsonPath().getString("token");

        System.out.println("Generated Auth Token: " + token);
	}
}
