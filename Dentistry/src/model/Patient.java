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
	private String forename;
	private String surname;
	private String title;
	private String contactNumber;
	private LocalDate dateOfBirth;
	private Address address;

	public Patient(int id, String forename, String surname, String title, String contactNumber, LocalDate dateOfBirth,
			Address address) {
		super();
		this.id = id;
		this.forename = forename;
		this.surname = surname;
		this.title = title;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	public int getId() {
		return this.id;
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
	public String getContactNumber() {
		return contactNumber;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public Address getAddress() {
		return address;
	}

	public static List<Patient> getAllPatients() {
		if (Config.MOCK) {
			return new Vector<Patient>(Arrays.asList(mock.Patient.MOCK_DATA));
		} else {
			Vector<Patient> patients = new Vector<>();
			try {
				ResultSet results = model.db.Queries.getAllPatients();
				while (results.next()) {
					patients.add(fromResultSet(results));
				}
				return patients;
			} catch (SQLException e) {
				// TODO maybe throw a ModelError here?
				e.printStackTrace();
				return null;
			}
		}
	}

	public static Patient fromResultSet(ResultSet results) {
		try {
			return new Patient(
					results.getInt("patient.id"),
					results.getString("patient.forename"),
					results.getString("patient.surname"),
					results.getString("patient.title"),
					results.getString("patient.contactNumber"),
					results.getDate("patient.dateOfBirth").toLocalDate(),
					CachedAddress.fromResultSet(results, results.getInt("patient.addressId")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
