package Udemy.RestAssured;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Udemy.RestAssured.Pojo.Pojo1_GetCourse;
import Udemy.RestAssured.Pojo.Pojo3_WebAutomation;

public class Test7_PojoDeserialization {

	// below Code is same as "Test6_Oauth class" nothing change. 
	//only change is, the response we are getting from REST API, we are storing in java pojo class (line number 54-56). and get value from that java class. 
	
	@Test
	public void PojoDeserialization() throws InterruptedException{		
			
//link -->		https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
//hit the above link, and get Code URL, and pass to below string url (we need to perform manually. as google has stop to give codeurl  through automation)
	
		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AY0e-g74QE1JZbQH9pE-DdXK8UOruJ22x15odxSFIRNpSSE9FzUrgfv-Cn_AkdY8ymxhMw&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		System.out.println("code  : "+code);
			
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")		
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		System.out.println("accesstocken"+accessTokenResponse);
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		

		//***********below 3 lines of code is just getting response as Json  and stored into java object i.e.Pojo classes.
		
		Pojo1_GetCourse gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(Pojo1_GetCourse.class);


		//**************below code is just for getting data from pojo class that is called Deserialization************************
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		//verify Protractor course is available or not in WebAutomation module
		String expectedCourseTtl ="Protractor";
		List<Pojo3_WebAutomation> webAtmCources =gc.getCourses().getWebAutomation();
		
		for(int i=0 ; i< webAtmCources.size() ; i++){
			String actualCourseTtl = webAtmCources.get(i).getCourseTitle();
			if(actualCourseTtl.equalsIgnoreCase(expectedCourseTtl)){
				System.out.println(webAtmCources.get(i).getPrice());				
				Assert.assertEquals(expectedCourseTtl, actualCourseTtl);
				break;
			}
		}
		
		//print all the courses under webAutomation
		for(int i=0 ; i< webAtmCources.size() ; i++){
		 System.out.println(webAtmCources.get(i).getCourseTitle());
		}

		//compare all cources under webAutomation 
		String [] expArr = {"Selenium Webdriver Java","Cypress","Protractor"};
		ArrayList<String> actualCourse = new ArrayList<String>();
		for(int i=0 ; i< webAtmCources.size() ; i++){
			actualCourse.add(webAtmCources.get(i).getCourseTitle());
			}		
		List<String> expList = Arrays.asList(expArr);
		Assert.assertTrue(expList.equals(actualCourse));
		
	}
}
