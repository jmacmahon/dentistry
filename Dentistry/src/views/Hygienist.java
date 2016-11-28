package views;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Hygienist extends ViewComponent {
	private JTabbedPane tabs;

	public Hygienist() {
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
		this.tabs.setComponentAt(0, DiaryDay.single(2).getPanel());
	}
}
