package Udemy.RestAssured;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.testng.annotations.Test;

import Udemy.RestAssured.file.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Test10_DataDrivenFromExcel {

	
	@Test 
	public void addBook() throws IOException{
		
		DataDriven2 dd =  new DataDriven2();
		ArrayList<String> al = dd.getData("Data", "TestCases", "DeleteProfile");
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", al.get(1));
		map.put("isbn", al.get(2));
		map.put("aisle", al.get(3));
		map.put("author", al.get(4));
		
		RestAssured.baseURI= "http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		       .body(map)		
		       .when().post("/Library/Addbook.php")		
		       .then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	       	JsonPath js = new JsonPath(response);
	       	String Id =  js.getString("ID");		    
		    System.out.println(Id);		
	}
}
