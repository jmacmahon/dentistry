package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import controller.Controller;

public class Main {
	public static void main(String[] args) {
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
				Controller.entryPoint();
			}
		});
	}
}