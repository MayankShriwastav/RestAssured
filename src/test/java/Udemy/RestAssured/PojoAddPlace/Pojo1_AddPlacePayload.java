package Udemy.RestAssured.PojoAddPlace;

import java.util.List;

public class Pojo1_AddPlacePayload {
	
	private Pojo2_Location location;
	private List<String> types;
	private int accuracy;
	private String name;
	private String address;
	private String website;
	private String language;
	private String phone_number;
	
	//getter and setter for all variable--
	public Pojo2_Location getLocation() {
		return location;
	}
	public void setLocation(Pojo2_Location location) {
		this.location = location;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	

}
