package model;

public class Address {
	private int houseNumber;
	private String streetName;
	private String districtName;
	private String cityName;
	private String postcode;

	public Address(int houseNumber, String streetName, String districtName, String cityName, String postcode) {
		super();
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.districtName = districtName;
		this.cityName = cityName;
		this.postcode = postcode;
	}

	public int getHouseNumber() {
		return houseNumber;
	}
	public String getStreetName() {
		return streetName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public String getCityName() {
		return cityName;
	}
	public String getPostcode() {
		return postcode;
	}
}
