package views;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class ViewComponent {
	public abstract JPanel getPanel();

	public static HashMap<ViewComponent, JFrame> activeFrames = new HashMap<>();

	public static JFrame spawnInFrame(ViewComponent component, String title) {
		JFrame frame = new JFrame(title);

		frame.getContentPane().add(component.getPanel());
		frame.pack();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				activeFrames.remove(component);
				printFrameTitles();
			}
		});
		activeFrames.put(component, frame);
		printFrameTitles();
		frame.setVisible(true);
		return frame;
	}

	private static void refreshFrame(ViewComponent component, JFrame frame) {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(component.getPanel());
		frame.pack();
		frame.setVisible(true);
	}

	public static void refreshAllFrames() {
		for (Entry<ViewComponent, JFrame> e : activeFrames.entrySet()) {
			refreshFrame(e.getKey(), e.getValue());
		}
	}

	private static void printFrameTitles() {
		for (Entry<ViewComponent, JFrame> e : activeFrames.entrySet()) {
			System.out.println(e.getValue().getTitle());
		}
		System.out.println("---");
	}
}
