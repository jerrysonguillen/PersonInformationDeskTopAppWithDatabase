package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.MatteBorder;

import controller.Controller;

public class Register {

	JFrame frmRegister;
	private JPasswordField txtCPassword;
	private JLabel register;
	private JPasswordField txtPassword;
	private Controller controller;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register window = new Register();
					window.frmRegister.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Register() {
		initialize();
	}

	private void initialize() {
		
		controller = new Controller();
		frmRegister = new JFrame();
		frmRegister.getContentPane().setForeground(UIManager.getColor("Button.disabledShadow"));
		frmRegister.getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		frmRegister.setResizable(false);
		frmRegister.setTitle("Sign Up");
		frmRegister.setBounds(280, 100, 848, 468);
		frmRegister.getContentPane().setLayout(null);
		frmRegister.setUndecorated(true);
		frmRegister.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmRegister.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				controller.disConnect();
				frmRegister.dispose();
				System.gc();
			}
		});
		
		JLabel lblFirstname = new JLabel("First Name:");
		lblFirstname.setForeground(UIManager.getColor("Button.darkShadow"));
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblFirstname.setBounds(456, 64, 204, 14);
		frmRegister.getContentPane().add(lblFirstname);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(UIManager.getColor("Button.darkShadow"));
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(456, 175, 204, 14);
		frmRegister.getContentPane().add(lblEmail);
		
		JLabel lblLastname = new JLabel("Last Name:");
		lblLastname.setForeground(UIManager.getColor("Button.darkShadow"));
		lblLastname.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblLastname.setBounds(456, 119, 204, 14);
		frmRegister.getContentPane().add(lblLastname);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(UIManager.getColor("Button.darkShadow"));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(456, 277, 204, 14);
		frmRegister.getContentPane().add(lblPassword);
		
		JLabel lblCPassword = new JLabel("Confirm Password:");
		lblCPassword.setForeground(UIManager.getColor("Button.darkShadow"));
		lblCPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCPassword.setBounds(456, 332, 204, 14);
		frmRegister.getContentPane().add(lblCPassword);
		
		JLabel lblUsername = new JLabel("User Name:");
		lblUsername.setForeground(UIManager.getColor("Button.darkShadow"));
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setBounds(456, 225, 204, 14);
		frmRegister.getContentPane().add(lblUsername);
		
		JTextPane txtFirstname = new JTextPane();
		
		txtFirstname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
		txtFirstname.setBounds(456, 88, 353, 20);
		frmRegister.getContentPane().add(txtFirstname);
		
		JTextPane txtEmail = new JTextPane();
		
		txtEmail.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
		txtEmail.setBounds(456, 194, 353, 20);
		frmRegister.getContentPane().add(txtEmail);
		
		JTextPane txtLastname = new JTextPane();
		txtLastname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
		txtLastname.setBounds(456, 144, 353, 20);
		frmRegister.getContentPane().add(txtLastname);
		
		txtCPassword = new JPasswordField();
		txtCPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
		txtCPassword.setBounds(456, 357, 353, 20);
		frmRegister.getContentPane().add(txtCPassword);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				frmRegister.dispose();
			}
		});
	
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(810, 0, 38, 28);
		frmRegister.getContentPane().add(lblX);
		
		JLabel lblNewLabel_1 = new JLabel("Register");
		lblNewLabel_1.setBackground(UIManager.getColor("Button.darkShadow"));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(423, 0, 435, 39);
		frmRegister.getContentPane().add(lblNewLabel_1);
		
		register = new JLabel("");
		register.setHorizontalAlignment(SwingConstants.CENTER);
		register.setIcon(new ImageIcon(Register.class.getResource("/registericon.png")));
		register.setBounds(65, 88, 300, 288);
		frmRegister.getContentPane().add(register);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 153, 204));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(0, 0, 424, 488);
		frmRegister.getContentPane().add(lblNewLabel);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LogInForm.main(null);
				frmRegister.dispose();
			}
		});
		
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(105, 105, 105));
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnBack.setBounds(661, 401, 144, 26);
		frmRegister.getContentPane().add(btnBack);
		
		JTextPane txtUsername = new JTextPane();
		txtUsername.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
		txtUsername.setBounds(456, 246, 353, 20);
		frmRegister.getContentPane().add(txtUsername);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String firstname = txtFirstname.getText();
				String lastname = txtLastname.getText();
				String email = txtEmail.getText();
				String username = txtUsername.getText();
				String password = new String(txtPassword.getPassword());
				String cPassword = new String(txtCPassword.getPassword());
				connect();
				if(controller.register(firstname,lastname,email,username,password,cPassword)) {
					frmRegister.dispose();
					LogInForm.main(null);
				}
			}

		});
		
		txtCPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
					String firstname = txtFirstname.getText();
					String lastname = txtLastname.getText();
					String email = txtEmail.getText();
					String username = txtUsername.getText();
					String password = new String(txtPassword.getPassword());
					String cPassword = new String(txtCPassword.getPassword());
					connect();
					if(controller.register(firstname,lastname,email,username,password,cPassword)) {
						frmRegister.dispose();
						LogInForm.main(null);
					}
				}
			}
		});
		
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnSubmit.setBackground(SystemColor.controlDkShadow);
		btnSubmit.setBounds(456, 401, 144, 26);
		frmRegister.getContentPane().add(btnSubmit);
		
		txtPassword = new JPasswordField();
		txtPassword.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(0, 153, 255)));
		txtPassword.setBounds(456, 301, 353, 20);
		frmRegister.getContentPane().add(txtPassword);
		
		txtFirstname.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtLastname.requestFocus();
					StringBuilder st = new StringBuilder(txtFirstname.getText());
					st.deleteCharAt(txtFirstname.getText().length());
					String res = new String(st);
					txtFirstname.setText(res);
				}
			}
		});
		
		txtEmail.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtUsername.requestFocus();
					StringBuilder st = new StringBuilder(txtEmail.getText());
					st.deleteCharAt(txtEmail.getText().length());
					String res = new String(st);
					txtEmail.setText(res);
				}
			}
		});
		
		txtLastname.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtEmail.requestFocus();
					StringBuilder st = new StringBuilder(txtLastname.getText());
					st.deleteCharAt(txtLastname.getText().length());
					String res = new String(st);
					txtLastname.setText(res);
				}
			}
		});
		
		txtUsername.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_TAB) {
					txtPassword.requestFocus();
					StringBuilder st = new StringBuilder(txtUsername.getText());
					st.deleteCharAt(txtUsername.getText().length());
					String res = new String(st);
					txtUsername.setText(res);
				}
			}
		});
	}
	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frmRegister, "Unable to connect to database", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
