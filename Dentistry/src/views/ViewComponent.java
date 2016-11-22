package views;

import javax.swing.JFrame;
import javax.swing.JPanel;

public interface ViewComponent {
	public JPanel getPanel();

	public static JFrame spawnInFrame(ViewComponent v, String title) {
		JFrame frame = new JFrame(title);

		frame.getContentPane().add(v.getPanel());
		frame.pack();
		frame.setVisible(true);

		return frame;
	}
}
