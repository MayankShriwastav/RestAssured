package Udemy.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test4_JsonPassFromExternal {

	@Test
	public void addLocation() throws IOException{
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		 .body(new String (Files.readAllBytes(Paths.get("D:\\My_Projects\\RestAssured\\Request.json"))))
		        
		  .when().post("maps/api/place/add/json")		
		  .then().assertThat().statusCode(200)      
		   .extract().response().asString();		
		    System.out.println(response);
		     
		     JsonPath js = new JsonPath(response);
		    String placeId =  js.getString("place_id");		    
		    System.out.println(placeId);
	}
	
}
