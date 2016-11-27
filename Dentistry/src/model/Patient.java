package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import main.Config;

public class Patient {
	private int id;
	private CachedPatient cached;

	public Patient(int id) {
		this.id = id;
	}

	private CachedPatient getCached() {
		if (this.cached == null) {
			try {
				ResultSet results = model.db.Queries.getPatient(this.id);
				results.next();
				Patient shouldBeCached = Patient.fromResultSet(results, this.id);
				if (shouldBeCached instanceof CachedPatient) {
					this.cached = (CachedPatient) shouldBeCached;
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
	public String getForename() {
		return this.getCached().getForename();
	}
	public String getSurname() {
		return this.getCached().getSurname();
	}
	public String getTitle() {
		return this.getCached().getTitle();
	}
	public String getContactNumber() {
		return this.getCached().getContactNumber();
	}
	public LocalDate getDateOfBirth() {
		return this.getCached().getDateOfBirth();
	}
	public Address getAddress() {
		return this.getCached().getAddress();
	}

	public static List<Patient> getAllPatients() {
		ResultSet results;
		try {
			results = model.db.Queries.getAllPatients();
			return allFromResultSet(results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public static List<Patient> allFromResultSet(ResultSet results) {
		if (Config.MOCK) {
			return new Vector<Patient>(Arrays.asList(mock.Patient.MOCK_DATA));
		} else {
			Vector<Patient> patients = new Vector<>();
			try {
				while (results.next()) {
					patients.add(fromResultSet(results, results.getInt("patient.id")));
				}
				return patients;
			} catch (SQLException e) {
				// TODO maybe throw a ModelError here?
				e.printStackTrace();
				return null;
			}
		}
	}

	public static Patient fromResultSet(ResultSet results, int id) {
		try {
			return new CachedPatient(
					results.getInt("patient.id"),
					results.getString("patient.forename"),
					results.getString("patient.surname"),
					results.getString("patient.title"),
					results.getString("patient.contactNumber"),
					results.getDate("patient.dateOfBirth").toLocalDate(),
					Address.fromResultSet(results, results.getInt("patient.addressId")));
		} catch (SQLException e) {
			return new Patient(id);
		}
	}

	public static List<Patient> search(String forename, String surname, Integer houseNumber, String postcode) {
		ResultSet results;
		try {
			results = model.db.Queries.searchPatients(forename, surname, houseNumber, postcode);
			return allFromResultSet(results);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
