package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import mock.Patient;

public interface PatientInterface {
	public String getForename();

	public String getSurname();

	public String getTitle();

	public String getContactNumber();

	public LocalDate getDateOfBirth();

	public AddressInterface getAddress();

	public static List<PatientInterface> getAllPatients() {
		return new Vector<PatientInterface>(Arrays.asList(Patient.MOCK_DATA));
	}
}
