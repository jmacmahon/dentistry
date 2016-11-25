package controller;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;

import main.Config;
import mock.Appointment;
import mock.Patient;
import model.AppointmentInterface;
import model.PatientInterface;
import model.db.Database;
import views.Diary;
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

	public static List<AppointmentInterface> getAppointments(LocalDateTime from, LocalDateTime to) {
		return new Vector<AppointmentInterface>(Arrays.asList(Appointment.MOCK_DATA));
	}

	public static List<AppointmentInterface> getAppointments() {
		return new Vector<AppointmentInterface>(Arrays.asList(Appointment.MOCK_DATA));
	}

	public static void initDB() {
		db = new Database(Config.DB_URL, Config.DB_USER, Config.DB_PASS);
		db.init();
	}

	public static void secretaryEntryPoint() {
		LocalDate thisMonday = LocalDate.of(2016, 11, 25).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		ViewComponent diary = new Diary(getAppointments(), thisMonday);
		ViewComponent.spawnInFrame(diary, "Diary").setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
