package Udemy.RestAssured;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import Udemy.RestAssured.PojoAddPlace.Pojo1_AddPlacePayload;
import Udemy.RestAssured.PojoAddPlace.Pojo2_Location;


public class Test8_PojoSerialization {
	
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
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		String response = given().log().all().queryParam("key", "qaclick123")
		.body(pl)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
		
		
					
	}
}
