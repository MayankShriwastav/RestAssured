package Udemy.RestAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import Udemy.RestAssured.PojoAddPlace.Pojo1_AddPlacePayload;
import Udemy.RestAssured.PojoAddPlace.Pojo2_Location;


public class Test9_SpacBuilder {
	
	@Test
	public void PojoSerialization(){		
		
		Pojo1_AddPlacePayload pl = new Pojo1_AddPlacePayload();
		pl.setAccuracy(50);
		pl.setName("mayank Srivastava");
		pl.setAddress("Frontline house");
		pl.setWebsite("http://google.com");
		pl.setLanguage("French-IN");
		pl.setPhone_number("859639999");
		
		List<String> mylist = new  ArrayList<String>();
		mylist.add("shoe park");
		mylist.add("shop");
		pl.setTypes(mylist);
		
		Pojo2_Location location = new Pojo2_Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		pl.setLocation(location);

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				                                           .addQueryParam("key", "qaclick123")
		                                                    .setContentType(ContentType.JSON).build();
				
		ResponseSpecification respo = new ResponseSpecBuilder().expectStatusCode(200)
				                                                .expectContentType(ContentType.JSON).build();
				
		RequestSpecification res = given().spec(req).body(pl);
		
		Response response = res.when().post("/maps/api/place/add/json")
	                                         	.then().spec(respo).extract().response();	
		
		String responseString = response.asString();
		System.out.println(responseString);
	
		
//below code convert to more generic way -- using RequestSpecBuilder and ResponseSpecBuilder
		
/*
        RestAssured.baseURI= "https://rahulshettyacademy.com";		
        String response = given().log().all().queryParam("key", "qaclick123")
		.body(pl)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
*/
				
					
	}
}
