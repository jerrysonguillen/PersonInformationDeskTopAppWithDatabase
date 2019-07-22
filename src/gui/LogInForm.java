package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.Controller;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LogInForm {

	public JFrame frmLogin;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	Controller controller = new Controller();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInForm window = new LogInForm();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LogInForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Log-in");
		frmLogin.getContentPane().setBackground(new Color(0, 153, 204));
		frmLogin.setBounds(450, 200, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frmLogin.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblLogin = new JLabel("LOG IN");
		lblLogin.setForeground(new Color(255, 255, 255));
		lblLogin.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
		lblLogin.setSize(new Dimension(0, 20));
		GridBagConstraints gbc_lblLogin = new GridBagConstraints();
		gbc_lblLogin.anchor = GridBagConstraints.WEST;
		gbc_lblLogin.insets = new Insets(0, 0, 5, 0);
		gbc_lblLogin.gridx = 1;
		gbc_lblLogin.gridy = 0;
		gbc_lblLogin.weighty = 0.2;
		
		frmLogin.getContentPane().add(lblLogin, gbc_lblLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(ImageIcon("/loginicon.png",20,20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		gbc_lblNewLabel.weightx = 1.00;
		frmLogin.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		txtUsername = new JTextField();
		txtUsername.setPreferredSize(new Dimension(100, 20));
		GridBagConstraints gbc_txtUsername = new GridBagConstraints();
		gbc_txtUsername.insets = new Insets(0, 0, 5, 0);
		gbc_txtUsername.anchor = GridBagConstraints.WEST;
		gbc_txtUsername.gridx = 1;
		gbc_txtUsername.gridy = 1;
		frmLogin.getContentPane().add(txtUsername, gbc_txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(ImageIcon("/lock.png",20,20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 2;
		frmLogin.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String username = txtUsername.getText();
					String password = new String(txtPassword.getPassword());
					connect();
					try {
						if (controller.logIn(username,password)) {
							frmLogin.dispose();
							App.main(null);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		txtPassword.setPreferredSize(new Dimension(115, 20));
		txtPassword.setMinimumSize(new Dimension(30, 20));
		GridBagConstraints gbc_txtPassword = new GridBagConstraints();
		gbc_txtPassword.insets = new Insets(0, 0, 5, 0);
		gbc_txtPassword.anchor = GridBagConstraints.WEST;
		gbc_txtPassword.gridx = 1;
		gbc_txtPassword.gridy = 2;
		frmLogin.getContentPane().add(txtPassword, gbc_txtPassword);
		
		JButton btnLogIn = new JButton("Log-in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				connect();
				try {
					if (controller.logIn(username,password)) {
						frmLogin.dispose();
						App.main(null);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnLogIn.setForeground(new Color(255, 255, 255));
		btnLogIn.setBackground(new Color(0, 51, 153));
		GridBagConstraints gbc_btnLogIn = new GridBagConstraints();
		gbc_btnLogIn.anchor = GridBagConstraints.NORTHEAST;
		gbc_btnLogIn.insets = new Insets(0, 0, 0, 5);
		gbc_btnLogIn.gridx = 0;
		gbc_btnLogIn.gridy = 3;
		frmLogin.getContentPane().add(btnLogIn, gbc_btnLogIn);
		
		JButton btnSignup = new JButton("Sign-up");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmLogin.dispose();
				Register.main(null);
			}
		});
		btnSignup.setBackground(new Color(0, 51, 153));
		btnSignup.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_btnSignup = new GridBagConstraints();
		gbc_btnSignup.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnSignup.gridx = 1;
		gbc_btnSignup.gridy = 3;
		gbc_btnSignup.weighty = 0.5;
		frmLogin.getContentPane().add(btnSignup, gbc_btnSignup);
	}
	
	private ImageIcon ImageIcon(String imagePath,int x,int y) {
		ImageIcon Icon = new ImageIcon(LogInForm.class.getResource(imagePath));
		Image Image = Icon.getImage();
		Image newImage = Image.getScaledInstance(x, y, java.awt.Image.SCALE_SMOOTH);
		Icon = new ImageIcon(newImage);
		return Icon;
	}
	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			//e.printStackTrace();
			JOptionPane.showMessageDialog(frmLogin, "Unable to connect to database", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}

}
