package mock;

import model.AddressInterface;

public class Address implements AddressInterface {
	public static final Address[] MOCK_DATA = {
			new Address(4, "Privet Drive", "Little Whinging", "Surrey", "GU15 1AE"),
			new Address(12, "Western Bank", "", "Sheffield", "S10 2AA"),
			new Address(34, "Stockport Road", "Longsight", "Manchester", "M19 1AA"),
	};

	private int houseNumber;
	private String streetName;
	private String districtName;
	private String cityName;
	private String postcode;

	public Address(int houseNumber, String streetName, String districtName, String cityName, String postcode) {
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.districtName = districtName;
		this.cityName = cityName;
		this.postcode = postcode;
	}

	@Override
	public int getHouseNumber() {
		return houseNumber;
	}
	@Override
	public String getStreetName() {
		return streetName;
	}
	@Override
	public String getDistrictName() {
		return districtName;
	}
	@Override
	public String getCityName() {
		return cityName;
	}
	@Override
	public String getPostcode() {
		return postcode;
	}
}
