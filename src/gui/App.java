package gui;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import controller.Controller;

public class App {

	private JFrame frmPersonInformation;
	private JTextField txtName;
	private JTextField txtOcupation;
	private JTextField txtTaxId;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
	private JDialog prefDialog;
	private JSplitPane splitPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frmPersonInformation.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public App() {
		initialize();
	}

	private void initialize() {
		frmPersonInformation = new JFrame();
		frmPersonInformation.setTitle("Person Information");
		frmPersonInformation.setMinimumSize(new Dimension(600, 500));
		frmPersonInformation.setBounds(100, 100, 600, 500);
		frmPersonInformation.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmPersonInformation.getContentPane().setLayout(new BorderLayout(0, 0));
		frmPersonInformation.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				controller.disConnect();
				frmPersonInformation.dispose();
				System.gc();
			}
			
		});
		
		JTree serverTree = new JTree(createTree());
		serverTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		serverTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) serverTree.getLastSelectedPathComponent();
				Object userObject = node.getUserObject();
				System.out.println(userObject);
				if (userObject instanceof ServerInfo) {
					int id = ((ServerInfo) userObject).getId();
					System.out.println("got the user id of :" + id);
				}
				
			}
		});
		
		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());
		messagePanel.add(new JScrollPane(serverTree),BorderLayout.CENTER);
		
		
		tablePanel = new TablePanel();
		JTextArea txtArea = new JTextArea();
		JTabbedPane tabPane = new JTabbedPane();
		tabPane.addTab("Person Database", tablePanel);
		tabPane.addTab("Messages", messagePanel);
		
		
		JPanel pnlForm = new JPanel();
		pnlForm.setMinimumSize(new Dimension(250, 10));
		
		txtArea.setEditable(true);
//		frame.getContentPane().add(txtArea, BorderLayout.CENTER);
		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,pnlForm,tablePanel);
		splitPane.setOneTouchExpandable(true);
		frmPersonInformation.getContentPane().add(splitPane, BorderLayout.CENTER);

		JToolBar toolBar = new JToolBar();
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		toolBar.setBorder(BorderFactory.createEtchedBorder());
		frmPersonInformation.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		fileChooser = new JFileChooser();

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connect();
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frmPersonInformation, "Unable to save in database", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		toolBar.add(btnSave);
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connect();
				try {
					controller.retrieve();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(frmPersonInformation, "Unable to retrieve data in database", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				tablePanel.refresh();
			}
		});
		
		toolBar.add(btnRefresh);

		
		pnlForm.setPreferredSize(new Dimension(250, 10));
		Border innerBorder = BorderFactory.createTitledBorder("Add Person");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		pnlForm.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		GridBagLayout gbl_pnlForm = new GridBagLayout();
		gbl_pnlForm.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_pnlForm.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_pnlForm.columnWeights = new double[] { 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pnlForm.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pnlForm.setLayout(gbl_pnlForm);

		////////////// first row///////////////
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 0;
		pnlForm.add(lblName, gbc_lblName);

		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(5, 20));
		txtName.setSize(new Dimension(10, 0));
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 0;
		pnlForm.add(txtName, gbc_txtName);
		txtName.setColumns(10);

		////////////////////// next row/////////////

		JLabel lblOcupation = new JLabel("Ocupation:");
		GridBagConstraints gbc_lblOcupation = new GridBagConstraints();
		gbc_lblOcupation.anchor = GridBagConstraints.EAST;
		gbc_lblOcupation.insets = new Insets(0, 0, 5, 5);
		gbc_lblOcupation.gridx = 1;
		gbc_lblOcupation.gridy = 1;
		pnlForm.add(lblOcupation, gbc_lblOcupation);

		txtOcupation = new JTextField();
		GridBagConstraints gbc_txtOcupation = new GridBagConstraints();
		gbc_txtOcupation.insets = new Insets(0, 0, 5, 0);
		gbc_txtOcupation.anchor = GridBagConstraints.WEST;
		gbc_txtOcupation.gridx = 2;
		gbc_txtOcupation.gridy = 1;
		pnlForm.add(txtOcupation, gbc_txtOcupation);
		txtOcupation.setColumns(10);

		JLabel lblAge = new JLabel("Age:");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 1;
		gbc_lblAge.gridy = 2;
		pnlForm.add(lblAge, gbc_lblAge);

		JList<Object> list = new JList<Object>();
		list.setPreferredSize(new Dimension(110, 66));
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		list.setAlignmentY(Component.TOP_ALIGNMENT);
		list.setAlignmentX(Component.LEFT_ALIGNMENT);
		list.setModel(new AbstractListModel<Object>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] { "Under 18", "18 to 65", "65 or over" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc_list.insets = new Insets(0, 0, 5, 0);
		gbc_list.weightx = 1.0;
		gbc_list.weighty = 0.1;
		gbc_list.gridx = 2;
		gbc_list.gridy = 2;
		pnlForm.add(list, gbc_list);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		JLabel lblEmployment = new JLabel("Employment:");
		GridBagConstraints gbc_lblEmployment = new GridBagConstraints();
		gbc_lblEmployment.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployment.anchor = GridBagConstraints.EAST;
		gbc_lblEmployment.gridx = 1;
		gbc_lblEmployment.gridy = 3;
		pnlForm.add(lblEmployment, gbc_lblEmployment);

		JComboBox cmbBox = new JComboBox();
		cmbBox.setModel(new DefaultComboBoxModel(new String[] { "Employed", "self-Employed", "unEmployed" }));
		cmbBox.setEditable(true);
		GridBagConstraints gbc_cmbBox = new GridBagConstraints();
		gbc_cmbBox.insets = new Insets(0, 0, 5, 0);
		gbc_cmbBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_cmbBox.gridx = 2;
		gbc_cmbBox.gridy = 3;
		pnlForm.add(cmbBox, gbc_cmbBox);

		JLabel lblPHCitizen = new JLabel("PH Citizen:");
		GridBagConstraints gbc_lblPHCitizen = new GridBagConstraints();
		gbc_lblPHCitizen.anchor = GridBagConstraints.EAST;
		gbc_lblPHCitizen.insets = new Insets(0, 0, 5, 5);
		gbc_lblPHCitizen.gridx = 1;
		gbc_lblPHCitizen.gridy = 4;
		pnlForm.add(lblPHCitizen, gbc_lblPHCitizen);

		JCheckBox chckbxUsCitizen = new JCheckBox("");
		GridBagConstraints gbc_chckbxUsCitizen = new GridBagConstraints();
		gbc_chckbxUsCitizen.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxUsCitizen.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUsCitizen.gridx = 2;
		gbc_chckbxUsCitizen.gridy = 4;
		pnlForm.add(chckbxUsCitizen, gbc_chckbxUsCitizen);

		JLabel lblTaxId = new JLabel("Tax ID:");
		lblTaxId.setEnabled(false);
		GridBagConstraints gbc_lblTaxId = new GridBagConstraints();
		gbc_lblTaxId.anchor = GridBagConstraints.EAST;
		gbc_lblTaxId.insets = new Insets(0, 0, 5, 5);
		gbc_lblTaxId.gridx = 1;
		gbc_lblTaxId.gridy = 5;
		pnlForm.add(lblTaxId, gbc_lblTaxId);

		txtTaxId = new JTextField();
		txtTaxId.setEnabled(false);
		GridBagConstraints gbc_txtTaxId = new GridBagConstraints();
		gbc_txtTaxId.anchor = GridBagConstraints.WEST;
		gbc_txtTaxId.insets = new Insets(0, 0, 5, 0);
		gbc_txtTaxId.gridx = 2;
		gbc_txtTaxId.gridy = 5;
		pnlForm.add(txtTaxId, gbc_txtTaxId);
		txtTaxId.setColumns(10);
		///////////// next row///////////////
		JLabel lblGender = new JLabel("Gender:");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 1;
		gbc_lblGender.gridy = 6;
		ButtonGroup genderGroup = new ButtonGroup();

		pnlForm.add(lblGender, gbc_lblGender);
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		rdbtnMale.setActionCommand("Male");
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnMale.gridx = 2;
		gbc_rdbtnMale.gridy = 6;
		pnlForm.add(rdbtnMale, gbc_rdbtnMale);

		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setActionCommand("Female");
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnFemale.gridx = 2;
		gbc_rdbtnFemale.gridy = 7;
		genderGroup.add(rdbtnMale);
		genderGroup.add(rdbtnFemale);
		pnlForm.add(rdbtnFemale, gbc_rdbtnFemale);

		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnOk.insets = new Insets(0, 0, 0, 0);
		gbc_btnOk.weightx = 1.0;
		gbc_btnOk.weighty = 2.0;
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = 8;
		pnlForm.add(btnOk, gbc_btnOk);
		
		controller = new Controller();
		
		btnOk.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String name = txtName.getText();
				String occupation = txtOcupation.getText();
				int age = list.getLeadSelectionIndex();
				String employment = (String) cmbBox.getSelectedItem();
				String gender = genderGroup.getSelection().getActionCommand();
				boolean isPH = chckbxUsCitizen.isSelected();
				String taxId = txtTaxId.getText();
				controller.addPerson(name, occupation, age, employment, gender, isPH, taxId);
				tablePanel.refresh();
				//System.out.println(name + " " + occupation + " :" + age + ", " + employment + " gener: " + gender + "\n");
				
			}
		});
		
		new PersonTableModel();
		tablePanel.setData(controller.getPeople());
		
		
		tablePanel.setPersonTableListener(new PersonTableListener() {
			public void rowDeleted(int row) {
			try {
					controller.removePerson(row);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
		
		chckbxUsCitizen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isTicked = chckbxUsCitizen.isSelected();
				lblTaxId.setEnabled(isTicked);
				txtTaxId.setEnabled(isTicked);
			}
		});

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem exportItem = new JMenuItem("Export Data...");
		
		exportItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(exportItem) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(new JButton(), "Could not save data to the file", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		JMenuItem importItem = new JMenuItem("Import Data...");
		importItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		exportItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		importItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (fileChooser.showOpenDialog(new JButton()) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						tablePanel.refresh();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(new JButton(), "Could not load the data from the file", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		JMenuItem exitFile = new JMenuItem("Exit");
		
		exitFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action =JOptionPane.showConfirmDialog(new JButton(), "Do you really want to exit this application", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] windowListeners = frmPersonInformation.getWindowListeners();
					for (WindowListener listener: windowListeners) {
						listener.windowClosing(new WindowEvent(frmPersonInformation,0));
					}
					
					
				}
			}
		});
		
		fileMenu.add(exportItem);
		fileMenu.add(importItem);
		fileMenu.addSeparator();
		fileMenu.add(exitFile);
		menuBar.add(fileMenu);
		
		prefDialog = new JDialog();
		prefDialog.setTitle("Preferences");
		prefDialog.setSize(400, 300);
		prefDialog.setLocationRelativeTo(frmPersonInformation);
		
		JButton btnPrefOk = new JButton("OK");
		JButton btnPrefCancel = new JButton("Cancel");
		SpinnerNumberModel spinnerModel = new SpinnerNumberModel(3306,0,9999,1);
		JSpinner portSpinner = new JSpinner(spinnerModel);
		
		JTextField txtUsername = new JTextField(10);
		JLabel lblUser = new JLabel("User name:");
		JLabel lblPassword = new JLabel("Password:");
		JPasswordField txtPassword = new JPasswordField(10);
		prefDialog.getContentPane().setLayout(new GridBagLayout());
		txtPassword.setEchoChar('*');
		
		int y = 0;
		//////first row/////////////////
		GridBagConstraints userGc = new GridBagConstraints();
		userGc.gridy = y;
		userGc.gridx = 0;
		userGc.weightx = 1;
		userGc.weighty = 1;
		userGc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(lblUser,userGc);
		
		GridBagConstraints txtUserGc = new GridBagConstraints();
		txtUserGc.gridy = y++;
		txtUserGc.gridx = 1;
		txtUserGc.weightx = 1;
		txtUserGc.weighty = 1;
		txtUserGc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(txtUsername,txtUserGc);
		//////next row//////////////////////
		GridBagConstraints lblPasswordgc = new GridBagConstraints();
		lblPasswordgc.gridy = y;
		lblPasswordgc.gridx = 0;
		lblPasswordgc.weightx = 1;
		lblPasswordgc.weighty = 1;
		lblPasswordgc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(lblPassword,lblPasswordgc);
		
		GridBagConstraints txtPasswordGc = new GridBagConstraints();
		txtPasswordGc.gridy = y++;
		txtPasswordGc.gridx = 1;
		txtPasswordGc.weightx = 1;
		txtPasswordGc.weighty = 1;
		txtPasswordGc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(txtPassword,txtPasswordGc);
		//////next row//////////////////////
		GridBagConstraints portgc = new GridBagConstraints();
		portgc.gridy = y;
		portgc.gridx = 0;
		portgc.weightx = 1;
		portgc.weighty = 1;
		portgc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(new JLabel("Port"),portgc);
		
		GridBagConstraints portSpinnerGc = new GridBagConstraints();
		portSpinnerGc.gridy = y++;
		portSpinnerGc.gridx = 1;
		portSpinnerGc.weightx = 1;
		portSpinnerGc.weighty = 1;
		portSpinnerGc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(portSpinner,portSpinnerGc);
		/////////next row///////////
		GridBagConstraints btnOkGc = new GridBagConstraints();
		btnOkGc.gridy = y;
		btnOkGc.gridx = 0;
		btnOkGc.weightx = 1;
		btnOkGc.weighty = 1;
		btnOkGc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(btnPrefOk,btnOkGc);
		
		GridBagConstraints btnCancelGc = new GridBagConstraints();
		btnCancelGc.gridy = y++;
		btnCancelGc.gridx=1;
		btnCancelGc.weightx = 2;
		btnCancelGc.weighty = 1;
		btnCancelGc.fill = GridBagConstraints.NONE;
		prefDialog.getContentPane().add(btnPrefCancel,btnCancelGc);
		
		btnPrefOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = txtUsername.getText();
				char[] password = txtPassword.getPassword();
				System.out.println(username +" pass: " + new String(password));
				prefDialog.setVisible(false);
			}
		});
		
		btnPrefCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefDialog.setVisible(false);
			}
		});
		
		JMenu windowMenu = new JMenu("Window");
		JMenu showItem = new JMenu("Show");
		JCheckBoxMenuItem showItemForm = new JCheckBoxMenuItem("PersonForm");
		JMenuItem prefsItem = new JMenuItem("Preferences");
		showItemForm.setSelected(true);
		showItem.add(showItemForm);
		windowMenu.add(showItem);
		//windowMenu.add(prefsItem);
		menuBar.add(windowMenu);
		
		
		prefsItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				prefDialog.setVisible(true);
			}
		});

		frmPersonInformation.setJMenuBar(menuBar);

		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitFile.setMnemonic(KeyEvent.VK_X);
		exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		showItemForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				if (menuItem.isSelected()) {
					splitPane.setDividerLocation((int)pnlForm.getMinimumSize().getWidth());
				}
				pnlForm.setVisible(menuItem.isSelected());
			}
		});
		
	}
	public DefaultMutableTreeNode createTree() {
		DefaultMutableTreeNode top = new DefaultMutableTreeNode("Server");
		
		DefaultMutableTreeNode branch1 = new DefaultMutableTreeNode("UK");
		DefaultMutableTreeNode server1 = new DefaultMutableTreeNode(new ServerInfo("London",1));
		DefaultMutableTreeNode server2 = new DefaultMutableTreeNode(new ServerInfo("Edinburgh",2));
		
		branch1.add(server1);
		branch1.add(server2);
		
		DefaultMutableTreeNode branch2 = new DefaultMutableTreeNode("USA");
		DefaultMutableTreeNode server3 = new DefaultMutableTreeNode(new ServerInfo("UK",3));
		DefaultMutableTreeNode server4 = new DefaultMutableTreeNode(new ServerInfo("London",4));
		branch2.add(server3);
		branch2.add(server4);
		
		top.add(branch1);
		top.add(branch2);
		return top;
	}
	
	public void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmPersonInformation, "Unable to connect to database", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
}
