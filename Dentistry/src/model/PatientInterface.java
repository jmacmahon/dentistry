package model;

import java.time.LocalDate;

public interface PatientInterface {
	public String getForename();

	public String getSurname();

	public String getTitle();

	public String getContactNumber();

	public LocalDate getDateOfBirth();

	public AddressInterface getAddress();
}
