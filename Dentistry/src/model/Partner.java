package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import main.Config;

public class Partner {
	private int id;
	private String forename;
	private String surname;
	private String title;
	public Partner(int id, String forename, String surname, String title) {
		super();
		this.id = id;
		this.forename = forename;
		this.surname = surname;
		this.title = title;
	}
	public int getId() {
		return id;
	}
	public String getForename() {
		return forename;
	}
	public String getSurname() {
		return surname;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
		return title + " " + surname + ", " + forename;
	}

	public static Partner fromResultSet(ResultSet result) {
		try {
			return new Partner(
					result.getInt("partner.id"),
					result.getString("partner.forename"),
					result.getString("partner.surname"),
					result.getString("partner.title"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static List<Partner> getAllPartners() {
		ResultSet results;
		try {
			results = model.db.Queries.getAllPartners();
			return allFromResultSet(results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static List<Partner> allFromResultSet(ResultSet results) {
		if (Config.MOCK) {
			return new Vector<Partner>();
		} else {
			Vector<Partner> partners = new Vector<>();
			try {
				while (results.next()) {
					partners.add(fromResultSet(results, results.getInt("partner.id")));
				}
				return partners;
			} catch (SQLException e) {
				// TODO maybe throw a ModelError here?
				e.printStackTrace();
				return null;
			}
		}
	}

	public static Partner fromResultSet(ResultSet results, int id) {
		try {
			return new Partner(
					results.getInt("partner.id"),
					results.getString("partner.forename"),
					results.getString("partner.surname"),
					results.getString("partner.title"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
