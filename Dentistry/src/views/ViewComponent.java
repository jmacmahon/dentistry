package views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Appointment;

public abstract class ViewComponent {
	private List<ViewComponent> children;
	public abstract JPanel getPanel();

	protected void addChild(ViewComponent component) {
		if (this.children == null) {
			this.children = new Vector<>();
		}
		children.add(component);
	}

	public void refresh() {
		if (this.children == null) {
			return;
		}
		for (ViewComponent child : children) {
			child.refresh();
		}
	}

	private static HashMap<ViewComponent, JFrame> activeFrames = new HashMap<>();

	public static JFrame spawnInFrame(ViewComponent component, String title) {
		JFrame frame = new JFrame(title);

		frame.getContentPane().add(component.getPanel());
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				activeFrames.remove(component);
			}
		});
		activeFrames.put(component, frame);
		frame.setVisible(true);
		return frame;
	}

	public static void refreshAll() {
		for (ViewComponent component : activeFrames.keySet()) {
			component.refresh();
		}
	}

	public static void closeAppointment(Appointment appointment) {
		Vector<JFrame> toClose = new Vector<>();
		for (Entry<ViewComponent, JFrame> entry : activeFrames.entrySet()) {
			if (entry.getKey() instanceof AppointmentDetail) {
				AppointmentDetail detail = (AppointmentDetail)entry.getKey();
				if (detail.getAppointment() == appointment) {
					toClose.add(entry.getValue());
				}
			}
		}
		for (JFrame frame : toClose) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void closeNewPatient() {
		Vector<JFrame> toClose = new Vector<>();
		for (Entry<ViewComponent, JFrame> entry : activeFrames.entrySet()) {
			if (entry.getKey() instanceof NewPatient) {
				toClose.add(entry.getValue());
			}
		}
		for (JFrame frame : toClose) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}

	public static void closeNewAppointment() {
		Vector<JFrame> toClose = new Vector<>();
		for (Entry<ViewComponent, JFrame> entry : activeFrames.entrySet()) {
			if (entry.getKey() instanceof NewAppointment) {
				toClose.add(entry.getValue());
			}
		}
		for (JFrame frame : toClose) {
			frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		}
	}
}
