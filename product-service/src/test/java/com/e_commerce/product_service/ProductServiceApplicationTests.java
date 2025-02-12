//package com.e_commerce.product_service;
//
//
//import io.restassured.RestAssured;
//import org.hamcrest.Matchers;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.testcontainers.containers.MongoDBContainer;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//
//
//class ProductServiceApplicationTests {
//
//@ServiceConnection
//	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
//
//	@LocalServerPort
//	private Integer port;
//	@BeforeEach
//	void setup()
//	{
//		RestAssured.baseURI="http://localhost";
//		RestAssured.port=port;
//	}
//    static{
//		MongoDBContainer.start();
//	}
//	@Test
//	void shouldCreateProduct()  {
//		String requestBody= """
//				{
//				    "name":"cloth",
//				    "description":"shirt",
//				    "price":1000
//				}
//				""";
//		RestAssured.given()
//				.contentType("application/json")
//				.body(requestBody)
//				.when()
//				.post("/api/products")
//				.then()
//				.statusCode(201)
//				.body("id", Matchers.notNullValue())
//				.body("name",Matchers.equalTo("cloth"))
//				.body("description",Matchers.equalTo("shirt"))
//				.body("price",Matchers.equalTo(1000));
//
//	}
//
//
//}


package com.e_commerce.product_service;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import com.e_commerce.product_service.dto.RequestProduct;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {
@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");
	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	@Test
	void shouldCreateProduct() {
		// Create a new product request body
		String requestBody = """
                {
                    "name": "cloth",
                    "description": "shirt",
                    "price": 1000
                }
                """;

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products")
				.then()
				.body("id", Matchers.notNullValue())
				.body("name",Matchers.equalTo("cloth"))
				.body("description",Matchers.equalTo("shirt"))
				.body("price",Matchers.equalTo(1000))
				.statusCode(201); // Ensure the status code is 201 Created
	}

//	@Test
//	void shouldRetrieveAllProducts() {
//		// Retrieve all products from the GET endpoint
//		List<ProductResponse> products = RestAssured.given()
//				.when()
//				.get("/api/products")
//				.then()
//				.statusCode(HttpStatus.OK.value()) // Ensure the status code is 200 OK
//				.extract().body().jsonPath().getList("", ProductResponse.class);
//
//		// Verify that products list is not empty
//		assert products.size() > 0;
//	}
}
