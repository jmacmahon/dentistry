package main;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mock.Appointment;
import views.Diary;
 
public class Main {
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Dentistry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        LocalDate thisMonday = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        Diary d = new Diary(new Vector<AppointmentInterface>(Arrays.asList(Appointment.MOCK_DATA)), thisMonday);
        JPanel panel = d.getPanel();
        frame.getContentPane().add(panel);
 
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}