package Udemy.RestAssured;

import org.testng.Assert;

import Udemy.RestAssured.file.Payload;
import io.restassured.path.json.JsonPath;

public class Test2_Basic2 {

	public static void main(String[] args) {
		
		 JsonPath js = new JsonPath(Payload.jsonStub()); 
		 
		//1. Print No of courses returned by API
		 		int jsonSize  = js.getInt("courses.size()");
		 		System.out.println(jsonSize);

		 //2.Print Purchase Amount
		 		int amount  = js.getInt("dashboard.purchaseAmount");
		 		System.out.println(amount);

		// 3. Print Title of the first course
		 		String firstCourseTitle  =js.get("courses[0].title");
		 		System.out.println(firstCourseTitle);

		 //4. Print All course titles and their respective Prices
		 		System.out.println("****Print All course titles and their respective Prices*******");		 		
		 		for(int i=0; i<jsonSize ; i++){
		 			String Title  =js.get("courses["+i+"].title");
		 			int price  =js.getInt("courses["+i+"].price");
		 			System.out.println("course :"+Title+"  :: Price :" +price);
		 		}

		 //5. Print no of copies sold by RPA Course
		 		System.out.println("****Print no of copies sold by RPA Course*******");	
		 		for(int i=0; i<jsonSize ; i++){
		 			String Title  =js.get("courses["+i+"].title");
		 			 if(Title.equalsIgnoreCase("Cypress")){
		 				int copies  =js.getInt("courses["+i+"].copies");
		 				System.out.println("No. of Copies Sold  : "+copies);
		 			 }
		 		}
		 		
		 //6. Verify if Sum of all Course prices matches with Purchase Amount
		 		System.out.println("****Verify if Sum of all Course prices matches with Purchase Amount*******");	
		 		int amt =0;
		 		for(int i=0; i<jsonSize ; i++){
		 			int price  =js.getInt("courses["+i+"].price");
		 			int copies  =js.getInt("courses["+i+"].copies");
		 			amt +=price*copies;
		 		}
		 		System.out.println("purchase Amount is  : "+amt);
		 		
		 		int ExpectedAmt  =js.getInt("dashboard.purchaseAmount");
		 		Assert.assertEquals(amt, ExpectedAmt);
	}

}
