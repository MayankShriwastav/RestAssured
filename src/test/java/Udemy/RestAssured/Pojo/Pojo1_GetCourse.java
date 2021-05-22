package Udemy.RestAssured.Pojo;

public class Pojo1_GetCourse {
	
	private String url;
	private String services;
	private String instructor;
	private String expertise;
	private Pojo2_Courses courses;
	private String linkedIn;
	
	//getter and setter for all variable--
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServices() {
		return services;
	}
	public void setServices(String services) {
		this.services = services;
	}
	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	public String getExpertise() {
		return expertise;
	}
	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}
	public Pojo2_Courses getCourses() {
		return courses;
	}
	public void setCourses(Pojo2_Courses courses) {
		this.courses = courses;
	}
	public String getLinkedIn() {
		return linkedIn;
	}
	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}
	

}
