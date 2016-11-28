package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Address {
	private int id;
	private CachedAddress cached;

	public Address(int id) {
		this.id = id;
	}

	private CachedAddress getCachedAddress() {
		if (this.cached == null) {
			try {
				ResultSet results = model.db.Queries.getAddress(this.id);
				results.next();
				Address shouldBeCached = Address.fromResultSet(results, this.id);
				if (shouldBeCached instanceof CachedAddress) {
					this.cached = (CachedAddress) shouldBeCached;
				} else {
					// something went wrong -- throw an exception
					// TODO make this a non-runtime exception
					throw new RuntimeException();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
		}
		return this.cached;
	}

	public int getId() {
		return this.id;
	}
	public int getHouseNumber() {
		return this.getCachedAddress().getHouseNumber();
	}
	public String getStreetName() {
		return this.getCachedAddress().getStreetName();
	}
	public String getDistrictName() {
		return this.getCachedAddress().getDistrictName();
	}
	public String getCityName() {
		return this.getCachedAddress().getCityName();
	}
	public String getPostcode() {
		return this.getCachedAddress().getPostcode();
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

	public static int add(int houseNumber, String streetName, String districtName, String cityName, String postcode) {
		try {
			ResultSet result = model.db.Queries.addAddress(houseNumber, streetName, districtName, cityName, postcode);
			result.next();
			return result.getInt("id");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
}
