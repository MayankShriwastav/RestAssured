package Udemy.RestAssured;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test5_JiraApi {
	
	@Test
	public void jiraApi(){		
		RestAssured.baseURI ="http://localhost:8080";
		
	//Login Scenario
	// by using the SessionFilter class session id is stored in it. and we can use any where.
		SessionFilter session = new SessionFilter();
		
		String response =given().header("Content-Type","application/json")
		.body("{ \"username\": \"mayanks028\", \"password\": \"Mayank_123\" }").log().all().filter(session)
		.when().post("/rest/auth/1/session")
		.then().log().all().extract().response().asString();
		
		//Add Comment ---
		String sendComment ="hye, how are you..?" ;
		String addCommentResponse = given().pathParam("key", "10007").log().all().header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"body\": \""+sendComment+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}")
		
		      .filter(session).when().post("rest/api/2/issue/{key}/comment")
		      .then().log().all().assertThat().statusCode(201).extract().response().asString();
		
		JsonPath js  = new JsonPath(addCommentResponse);
		String commentId = js.getString("id");
		
		//Add Attachment ---
		given().header("X-Atlassian-Token"," no-check").filter(session).pathParam("key", "10007")
		.header("Content-Type","multipart/form-data")
		.multiPart("file",new File("jira.txt"))
		.when().post("/rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//Get issue --> get only comment value from bug using query paramenter 
		
		String issueDetails = given().filter(session).pathParam("key", "10007")
		.queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{key}")
		.then().log().all().extract().response().asString();
	
		
		JsonPath js1  = new JsonPath(issueDetails);
		int numberOfCommnet = js1.getInt("fields.comment.comments.size()");
		
		for(int i=0 ; i<numberOfCommnet ; i++){
			String commentId2 = js1.get("fields.comment.comments["+i+"].id").toString();
			
			if(commentId.equalsIgnoreCase(commentId2)){
				String getComment = js1.get("fields.comment.comments["+i+"].body").toString();
				System.out.println("get commend : "+getComment);
				Assert.assertEquals(getComment, sendComment);
			}
		}
		
		
	}
	


}
