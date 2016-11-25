package views;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Secretary extends ViewComponent {

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		JTabbedPane tabs = new JTabbedPane();

		tabs.addTab("Appointments", Diary.auto().getPanel());
		tabs.addTab("Patients", Patients.auto().getPanel());

		panel.add(tabs);
		return panel;
	}

}
