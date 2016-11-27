package mock;

public class Address extends model.Address {
	public Address(int houseNumber, String streetName, String districtName, String cityName, String postcode) {
		super(houseNumber, streetName, districtName, cityName, postcode);
	}

	public static final Address[] MOCK_DATA = {
			new Address(4, "Privet Drive", "Little Whinging", "Surrey", "GU15 1AE"),
			new Address(12, "Western Bank", "", "Sheffield", "S10 2AA"),
			new Address(34, "Stockport Road", "Longsight", "Manchester", "M19 1AA"),
	};
}
