package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CachedAddress extends Address {
	private int id;
	private int houseNumber;
	private String streetName;
	private String districtName;
	private String cityName;
	private String postcode;

	public CachedAddress(int id, int houseNumber, String streetName, String districtName, String cityName, String postcode) {
		super(id);
		this.id = id;
		this.houseNumber = houseNumber;
		this.streetName = streetName;
		this.districtName = districtName;
		this.cityName = cityName;
		this.postcode = postcode;
	}
	@Override
	public int getId() {
		return this.id;
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

	public static Address fromResultSet(ResultSet results, int id) {
		try {
			return new CachedAddress(
					results.getInt("address.id"),
					results.getInt("address.houseNumber"),
					results.getString("address.streetName"),
					results.getString("address.districtName"),
					results.getString("address.cityName"),
					results.getString("address.postcode"));
		} catch (SQLException e) {
			return new Address(id);
		}
	}
}
