package controller;

import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import main.Config;
import model.Appointment;
import model.Patient;
import model.db.Database;
import views.Login;
import views.ViewComponent;

public class Controller {
	private static Database db;

	// Static class for the moment until we need objects.  To be refactored later.
	public static List<Patient> getPatients() {
		Vector<Patient> patients = new Vector<Patient>();
		for (Patient patient : mock.Patient.MOCK_DATA) {
			patients.add(patient);
		}
		return patients;
	}

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
}
