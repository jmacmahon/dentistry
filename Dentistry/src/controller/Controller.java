package controller;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import main.Config;
import mock.Patient;
import model.AppointmentInterface;
import model.PatientInterface;
import model.db.Database;
import views.Login;
import views.ViewComponent;

public class Controller {
	private static Database db;

	// Static class for the moment until we need objects.  To be refactored later.
	public static List<PatientInterface> getPatients() {
		Vector<PatientInterface> patients = new Vector<PatientInterface>();
		for (PatientInterface patient : Patient.MOCK_DATA) {
			patients.add(patient);
		}
		return patients;
	}

	public static void initDB() {
		db = new Database(Config.DB_URL, Config.DB_USER, Config.DB_PASS);
		db.init();
	}

	public static void entryPoint() {
		initDB();
		ViewComponent.spawnInFrame(new Login(), "Dentistry").setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void deleteAppointment(AppointmentInterface appointment) {
		AppointmentInterface.delete(appointment);
		ViewComponent.closeAppointment(appointment);
		ViewComponent.refreshAll();
	}

	public static List<PatientInterface> searchPatients(Integer houseNumber, String postcode) {
		Vector<PatientInterface> patients = new Vector<>();
		patients.add(Patient.MOCK_DATA[0]);
		return patients;
	}
}
