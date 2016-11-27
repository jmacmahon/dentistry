package model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

public abstract class Patient {
	public abstract String getForename();

	public abstract String getSurname();

	public abstract String getTitle();

	public abstract String getContactNumber();

	public abstract LocalDate getDateOfBirth();

	public abstract Address getAddress();

	public static List<Patient> getAllPatients() {
		return new Vector<Patient>(Arrays.asList(mock.Patient.MOCK_DATA));
	}
}
