package Udemy.RestAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Udemy.RestAssured.file.Payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class Test3_DynamicJson {
	
	@Test (dataProvider = "BooksData")
	public void addBook(String isbn , String aisle ){
		RestAssured.baseURI= "http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		       .body(Payload.add(isbn,aisle))		
		       .when().post("/Library/Addbook.php")		
		       .then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	       	JsonPath js = new JsonPath(response);
	       	String Id =  js.getString("ID");		    
		    System.out.println(Id);		
	}
	
	@Test (dataProvider = "BooksData")
	public void deleteBook(String isbn , String aisle ){
		String id =isbn.concat(aisle);  //id is created  using isbn+aisle
		RestAssured.baseURI= "http://216.10.245.166";
		
		String response = given().header("Content-Type","application/json")
		       .body(Payload.delete(id))		
		       .when().delete("/Library/DeleteBook.php")		
		       .then().log().all().assertThat().statusCode(200).extract().response().asString();
		
	       	JsonPath js = new JsonPath(response);
	       	String msg =  js.getString("msg ");		    
		    System.out.println(msg);		
	}
	
	@DataProvider (name = "BooksData")
	public Object[][] getData(){	
		return new Object [][] { {"Shini100","111"}, {"Shini200","222"}, {"Shini300","333"} };
		
	}

}
