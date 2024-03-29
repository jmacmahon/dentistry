package mock;

import java.time.LocalDate;

import model.Address;

public class Patient extends model.CachedPatient {
	public Patient(String forename, String surname, String title, LocalDate dateOfBirth,
			Address address) {
		super(0, forename, surname, title, "01234567890", dateOfBirth, address);
	}

	public static final Patient[] MOCK_DATA = {
			new Patient("Joe", "Bloggs", "Mr", LocalDate.of(1992, 9, 17), mock.Address.MOCK_DATA[0]),
			new Patient("Laura", "Phipps", "Ms", LocalDate.of(2000, 07, 02), mock.Address.MOCK_DATA[0]),
			new Patient("Eliza", "Juniper", "Dr", LocalDate.of(1974, 10, 12), mock.Address.MOCK_DATA[1]),
			new Patient("Mary", "O'Kane", "Mx", LocalDate.of(1988, 12, 24), mock.Address.MOCK_DATA[1]),
			new Patient("Brad", "Chapman", "Mx", LocalDate.of(1995, 5, 1), mock.Address.MOCK_DATA[2]),
	};
}
