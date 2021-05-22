package Udemy.RestAssured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import Udemy.RestAssured.file.Payload;

public class Test1_Basic {

	public static void main(String[] args) {
	
		//given -->all input details
		//When --> Submit the API - resource , http method
		//then --> validate the response
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		 .body(Payload.addPlace())
		        
		       .when().post("maps/api/place/add/json")
		
		       .then().assertThat().statusCode(200)
		       .body("scope", equalTo("APP"))
		       .header("server", "Apache/2.4.18 (Ubuntu)")		       
		       .extract().response().asString();		
		       System.out.println(response);
		     
		     JsonPath js = new JsonPath(response);
		    String placeId =  js.getString("place_id");		    
		    System.out.println(placeId);
		    
		    
		  //using Http method as - put  for updating Address
		    String newAddress  = "70 Nagpur walk, Rasra";
		    given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		    .body("{\r\n" + 
		    		"\"place_id\":\""+placeId+"\",\r\n" + 
		    		"\"address\":\""+newAddress+"\",\r\n" + 
		    		"\"key\":\"qaclick123\"\r\n" + 
		    		"}\r\n" + 
		    		"")
		    
		    .when().put("/maps/api/place/update/json")
		    .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		    
		    
		  //using Http method as - get  for getting updated Address
		    String getPalceResponse = given().log().all().queryParam("key", "qaclick123")
		    .queryParam("place_id", placeId)
		    .when().get("/maps/api/place/get/json")
		    .then().log().all().assertThat().statusCode(200).extract().response().asString();
		    
		     JsonPath js1 = new JsonPath(getPalceResponse);
			 String actualAddress =  js1.getString("address");		    
			 System.out.println("actual :"+actualAddress);
			 System.out.println("expected :"+newAddress);
			 
			 Assert.assertEquals(newAddress, actualAddress);
	}

}
