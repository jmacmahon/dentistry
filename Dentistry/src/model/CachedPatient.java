package model;

import java.time.LocalDate;

public class CachedPatient extends Patient {
	private int id;
	private String forename;
	private String surname;
	private String title;
	private String contactNumber;
	private LocalDate dateOfBirth;
	private Address address;

	public CachedPatient(int id, String forename, String surname, String title, String contactNumber, LocalDate dateOfBirth,
			Address address) {
		super(id);
		this.id = id;
		this.forename = forename;
		this.surname = surname;
		this.title = title;
		this.contactNumber = contactNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}
	@Override
	public int getId() {
		return this.id;
	}
	@Override
	public String getForename() {
		return forename;
	}
	@Override
	public String getSurname() {
		return surname;
	}
	@Override
	public String getTitle() {
		return title;
	}
	@Override
	public String getContactNumber() {
		return contactNumber;
	}
	@Override
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	@Override
	public Address getAddress() {
		return address;
	}

}
