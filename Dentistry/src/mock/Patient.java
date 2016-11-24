package mock;

import java.time.LocalDate;

import model.AddressInterface;
import model.PatientInterface;

public class Patient implements PatientInterface {
	public static final Patient[] MOCK_DATA = {
			new Patient("Joe", "Bloggs", "Mr", LocalDate.of(1992, 9, 17), Address.MOCK_DATA[0]),
			new Patient("Laura", "Phipps", "Ms", LocalDate.of(2000, 07, 02), Address.MOCK_DATA[0]),
			new Patient("Eliza", "Juniper", "Dr", LocalDate.of(1974, 10, 12), Address.MOCK_DATA[1]),
			new Patient("Mary", "O'Kane", "Mx", LocalDate.of(1988, 12, 24), Address.MOCK_DATA[1]),
			new Patient("Brad", "Chapman", "Mx", LocalDate.of(1995, 5, 1), Address.MOCK_DATA[2]),
	};

	private String forename;
	private String surname;
	private String title;
	private LocalDate dateOfBirth;
	private AddressInterface address;

	public Patient(String forename, String surname, String title, LocalDate dateOfBirth, AddressInterface address) {
		this.forename = forename;
		this.surname = surname;
		this.title = title;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
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
		return "01234567890";
	}
	@Override
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	@Override
	public AddressInterface getAddress() {
		return address;
	}

}
