package controller;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JFrame;

import main.Config;
import model.Address;
import model.Appointment;
import model.Partner;
import model.Patient;
import model.db.Database;
import views.Login;
import views.ViewComponent;

public class Controller {
	private static Database db;

	public static void initDB() {
		Config.db = new Database(Config.DB_URL, Config.DB_USER, Config.DB_PASS);
		Config.db.init();
	}

	public static void entryPoint() {
		initDB();
		ViewComponent.spawnInFrame(new Login(), "Dentistry").setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void deleteAppointment(Appointment appointment) {
		Appointment.delete(appointment);
		ViewComponent.closeAppointment(appointment);
		ViewComponent.refreshAll();
	}

	public static void newPatient(String title, String forename, String surname,
			String contact, LocalDate dateOfBirth, int houseNumber,
			String streetName, String districtName, String cityName,
			String postcode) {
		int addressId = Address.add(houseNumber, streetName, districtName, cityName, postcode);
		Patient.add(title, forename, surname, contact, dateOfBirth, addressId);
		ViewComponent.closeNewPatient();
		ViewComponent.refreshAll();
	}

	public static void newAppointment(Patient patient, Partner partner, LocalDate date, LocalTime time,
			Integer duration) {
		Appointment.add(patient.getId(), partner.getId(), date.atTime(time), duration.intValue());
		ViewComponent.closeNewAppointment();
		ViewComponent.refreshAll();
	}
}
