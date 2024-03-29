package views;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.Config.User;

public class Login extends ViewComponent {
	public User getUser(JPanel panel) {
		String[] options = {
				"Dentist",
				"Hygienist",
				"Secretary",
		};
		int choice = JOptionPane.showOptionDialog(
				panel,
				"Please select which user you are.",
				"Login",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[0]
				);
		switch (choice) {
		case JOptionPane.YES_OPTION:
			return User.DENTIST;
		case JOptionPane.NO_OPTION:
			return User.HYGIENIST;
		default:
			return User.SECRETARY;
		}
	}

	@Override
	public JPanel getPanel() {
		JPanel panel = new JPanel();
		switch(getUser(panel)) {
		case DENTIST:
			Dentist dentist = new Dentist();
			this.addChild(dentist);
			panel.add(dentist.getPanel());
			break;
		case HYGIENIST:
			Hygienist hygienist = new Hygienist();
			this.addChild(hygienist);
			panel.add(hygienist.getPanel());
			break;
		case SECRETARY:
			Secretary secretary = new Secretary();
			this.addChild(secretary);
			panel.add(secretary.getPanel());
			break;
		}
		return panel;
	}
}
