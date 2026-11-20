package com.api.testing.base;


import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpaceBuilder;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;


public class BaseTest {
	
	protected RequestSpecification requestSpec;
	
	@BeforeClass
	
	public void setup()
	{
		requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://restful-booker.herokuapp.com")
				.build();
		
		System.out.println("API Framework Setup Completed");
		
	}

}
