package views;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Secretary extends ViewComponent {
	private JTabbedPane tabs;

	public Secretary() {
		super();
		this.tabs = new JTabbedPane();

		this.tabs.addTab("Dentist appointments", new JPanel());
		this.tabs.addTab("Hygienist appointments", new JPanel());
		this.tabs.addTab("Patients", new JPanel());
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		refresh();
		panel.add(this.tabs);
		return panel;
	}

	@Override
	public void refresh() {
		this.tabs.setComponentAt(0, Diary.partnerAuto(1).getPanel());
		this.tabs.setComponentAt(1, Diary.partnerAuto(2).getPanel());
		this.tabs.setComponentAt(2, Patients.auto().getPanel());
	}
}
