package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class LogIn {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogIn window = new LogIn();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public LogIn() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(500, 200, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
