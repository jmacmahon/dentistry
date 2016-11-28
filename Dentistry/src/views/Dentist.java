package views;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Dentist extends ViewComponent {
	private JTabbedPane tabs;

	public Dentist() {
		super();
		this.tabs = new JTabbedPane();

		this.tabs.addTab("Appointments", new JPanel());
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
		this.tabs.setComponentAt(0, Diary.auto().getPanel());
	}
}
