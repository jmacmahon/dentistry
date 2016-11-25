package main;

import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;
import mock.Patient;
import model.PatientInterface;
import model.db.Database;
import views.Patients;
import views.ViewComponent;

public class Main {

	private static void createAndShowPatients() {
		Vector<PatientInterface> patients = new Vector<PatientInterface>();
		for (PatientInterface patient : Patient.MOCK_DATA) {
			patients.add(patient);
		}
		Patients patientsPanel = new Patients(patients);

		JFrame frame = ViewComponent.spawnInFrame(patientsPanel, "Patients");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		Database db = new Database(Config.DB_URL, Config.DB_USER, Config.DB_PASS);
		db.init();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Controller.secretaryEntryPoint();
			}
		});
	}
}