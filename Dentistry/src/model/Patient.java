package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public abstract class Patient {
	private String forename;
	private String surname;
	private String title;
	private String contactNumber;
	private LocalDate dateOfBirth;
	private Address address;

	public Patient(String forename, String surname, String title, String contactNumber, LocalDate dateOfBirth,
			Address address) {
		super();
		this.forename = forename;
		this.surname = surname;
		this.title = title;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
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
		return new Vector<Patient>(Arrays.asList(mock.Patient.MOCK_DATA));
	}
}
