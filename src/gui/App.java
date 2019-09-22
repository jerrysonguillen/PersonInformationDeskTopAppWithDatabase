package gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import controller.Controller;

public class App {

	private JFrame frmPersonInformation;
	private JTextField txtName;
	private JTextField txtNote;
	private JTextField txtCNumber;
	private JTextField txtOcupation;
	private JTextField txtTaxId;
	private JFileChooser fileChooser;
	private Controller controller;
	private TablePanel tablePanel;
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
		controller = new Controller();
		frmPersonInformation.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent arg0) {
				controller.disConnect();
				frmPersonInformation.dispose();
				System.gc();
			}
		});

		JPanel messagePanel = new JPanel();
		messagePanel.setLayout(new BorderLayout());

		tablePanel = new TablePanel();
		JPanel pnlForm = new JPanel();
		pnlForm.setMinimumSize(new Dimension(250, 10));

		splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnlForm, tablePanel);
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
					System.out.println(e);
					JOptionPane.showMessageDialog(frmPersonInformation, "Unable to save in database", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		toolBar.add(btnSave);
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connect();
				try {
					controller.update();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});

		toolBar.add(btnUpdate);
		JButton btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				connect();
				try {
					controller.retrieve();
				} catch (SQLException e) {
					System.out.println(e);
					JOptionPane.showMessageDialog(frmPersonInformation, "Unable to retrieve data in database",
							"Error Message", JOptionPane.ERROR_MESSAGE);
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
		int gridY = 0;
		////////////// first row///////////////
		JLabel lblName = new JLabel("Name:");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = gridY;
		pnlForm.add(lblName, gbc_lblName);

		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(5, 20));
		txtName.setSize(new Dimension(10, 0));
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 0);
		gbc_txtName.anchor = GridBagConstraints.WEST;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = gridY++;
		pnlForm.add(txtName, gbc_txtName);
		txtName.setColumns(10);
		////////////////////// next row /////////////
		JLabel lblContactNumber = new JLabel("Contact Number:");
		GridBagConstraints gbc_lblContactNumber = new GridBagConstraints();
		gbc_lblContactNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblContactNumber.anchor = GridBagConstraints.EAST;
		gbc_lblContactNumber.gridx = 1;
		gbc_lblContactNumber.gridy = gridY;
		pnlForm.add(lblContactNumber, gbc_lblContactNumber);

		txtCNumber = new JTextField();
		txtCNumber.setPreferredSize(new Dimension(5, 20));
		txtCNumber.setSize(new Dimension(10, 0));
		GridBagConstraints gbc_txtCNumber = new GridBagConstraints();
		gbc_txtCNumber.insets = new Insets(0, 0, 5, 0);
		gbc_txtCNumber.anchor = GridBagConstraints.WEST;
		gbc_txtCNumber.gridx = 2;
		gbc_txtCNumber.gridy = gridY++;
		pnlForm.add(txtCNumber, gbc_txtCNumber);
		txtCNumber.setColumns(10);
		////////////////////// next row/////////////
		JLabel lblOcupation = new JLabel("Ocupation:");
		GridBagConstraints gbc_lblOcupation = new GridBagConstraints();
		gbc_lblOcupation.anchor = GridBagConstraints.EAST;
		gbc_lblOcupation.insets = new Insets(0, 0, 5, 5);
		gbc_lblOcupation.gridx = 1;
		gbc_lblOcupation.gridy = gridY;
		pnlForm.add(lblOcupation, gbc_lblOcupation);

		txtOcupation = new JTextField();
		GridBagConstraints gbc_txtOcupation = new GridBagConstraints();
		gbc_txtOcupation.insets = new Insets(0, 0, 5, 0);
		gbc_txtOcupation.anchor = GridBagConstraints.WEST;
		gbc_txtOcupation.gridx = 2;
		gbc_txtOcupation.gridy = gridY++;
		pnlForm.add(txtOcupation, gbc_txtOcupation);
		txtOcupation.setColumns(10);

		JLabel lblAge = new JLabel("Age:");
		GridBagConstraints gbc_lblAge = new GridBagConstraints();
		gbc_lblAge.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblAge.insets = new Insets(0, 0, 5, 5);
		gbc_lblAge.gridx = 1;
		gbc_lblAge.gridy = gridY;
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
		gbc_list.gridy = gridY++;
		pnlForm.add(list, gbc_list);
		//////////////////// next row///////////////////////////
		JLabel lblNote = new JLabel("Note:");
		GridBagConstraints gbc_lblNote = new GridBagConstraints();
		gbc_lblNote.insets = new Insets(0, 0, 5, 5);
		gbc_lblNote.anchor = GridBagConstraints.EAST;
		gbc_lblNote.gridx = 1;
		gbc_lblNote.gridy = gridY;
		pnlForm.add(lblNote, gbc_lblNote);

		txtNote = new JTextField();
		txtNote.setPreferredSize(new Dimension(5, 20));
		txtNote.setSize(new Dimension(10, 0));
		GridBagConstraints gbc_txtNote = new GridBagConstraints();
		gbc_txtNote.insets = new Insets(0, 0, 5, 0);
		gbc_txtNote.anchor = GridBagConstraints.WEST;
		gbc_txtNote.gridx = 2;
		gbc_txtNote.gridy = gridY++;
		pnlForm.add(txtNote, gbc_txtNote);
		txtNote.setColumns(10);
		///////////////////// next row///////////////
		JButton btnOk = new JButton("OK");
		JLabel lblEmployment = new JLabel("Employment:");
		GridBagConstraints gbc_lblEmployment = new GridBagConstraints();
		gbc_lblEmployment.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmployment.anchor = GridBagConstraints.EAST;
		gbc_lblEmployment.gridx = 1;
		gbc_lblEmployment.gridy = gridY;
		pnlForm.add(lblEmployment, gbc_lblEmployment);

		JComboBox cmbBox = new JComboBox();
		cmbBox.setModel(new DefaultComboBoxModel(new String[] { "Employed", "self-Employed", "unEmployed" }));
		cmbBox.setEditable(true);
		GridBagConstraints gbc_cmbBox = new GridBagConstraints();
		gbc_cmbBox.anchor = GridBagConstraints.WEST;
		gbc_cmbBox.insets = new Insets(0, 0, 5, 0);
		gbc_cmbBox.gridx = 2;
		gbc_cmbBox.gridy = gridY++;
		pnlForm.add(cmbBox, gbc_cmbBox);
		////////////////// next row///////////////////////////////
		JLabel lblPHCitizen = new JLabel("PH Citizen:");
		GridBagConstraints gbc_lblPHCitizen = new GridBagConstraints();
		gbc_lblPHCitizen.anchor = GridBagConstraints.EAST;
		gbc_lblPHCitizen.insets = new Insets(0, 0, 5, 5);
		gbc_lblPHCitizen.gridx = 1;
		gbc_lblPHCitizen.gridy = gridY;
		pnlForm.add(lblPHCitizen, gbc_lblPHCitizen);

		JCheckBox chckbxUsCitizen = new JCheckBox("");
		GridBagConstraints gbc_chckbxUsCitizen = new GridBagConstraints();
		gbc_chckbxUsCitizen.anchor = GridBagConstraints.NORTHWEST;
		gbc_chckbxUsCitizen.insets = new Insets(0, 0, 5, 0);
		gbc_chckbxUsCitizen.gridx = 2;
		gbc_chckbxUsCitizen.gridy = gridY++;
		pnlForm.add(chckbxUsCitizen, gbc_chckbxUsCitizen);
		////////////////////// next row//////////////////////
		JLabel lblTaxId = new JLabel("Tax ID:");
		lblTaxId.setEnabled(false);
		GridBagConstraints gbc_lblTaxId = new GridBagConstraints();
		gbc_lblTaxId.anchor = GridBagConstraints.EAST;
		gbc_lblTaxId.insets = new Insets(0, 0, 5, 5);
		gbc_lblTaxId.gridx = 1;
		gbc_lblTaxId.gridy = gridY;
		pnlForm.add(lblTaxId, gbc_lblTaxId);

		txtTaxId = new JTextField();
		txtTaxId.setEnabled(false);
		GridBagConstraints gbc_txtTaxId = new GridBagConstraints();
		gbc_txtTaxId.anchor = GridBagConstraints.WEST;
		gbc_txtTaxId.insets = new Insets(0, 0, 5, 0);
		gbc_txtTaxId.gridx = 2;
		gbc_txtTaxId.gridy = gridY++;
		pnlForm.add(txtTaxId, gbc_txtTaxId);
		txtTaxId.setColumns(10);
		///////////// next row///////////////
		JLabel lblGender = new JLabel("Gender:");
		GridBagConstraints gbc_lblGender = new GridBagConstraints();
		gbc_lblGender.anchor = GridBagConstraints.EAST;
		gbc_lblGender.insets = new Insets(0, 0, 5, 5);
		gbc_lblGender.gridx = 1;
		gbc_lblGender.gridy = gridY;
		ButtonGroup genderGroup = new ButtonGroup();

		pnlForm.add(lblGender, gbc_lblGender);
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setSelected(true);
		rdbtnMale.setActionCommand("Male");
		GridBagConstraints gbc_rdbtnMale = new GridBagConstraints();
		gbc_rdbtnMale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnMale.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnMale.gridx = 2;
		gbc_rdbtnMale.gridy = gridY++;
		pnlForm.add(rdbtnMale, gbc_rdbtnMale);
		//////////////////// next row////////////////
		JRadioButton rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setActionCommand("Female");
		GridBagConstraints gbc_rdbtnFemale = new GridBagConstraints();
		gbc_rdbtnFemale.anchor = GridBagConstraints.WEST;
		gbc_rdbtnFemale.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnFemale.gridx = 2;
		gbc_rdbtnFemale.gridy = gridY++;
		genderGroup.add(rdbtnMale);
		genderGroup.add(rdbtnFemale);
		pnlForm.add(rdbtnFemale, gbc_rdbtnFemale);
		/////////////////// next row//////////////////
		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnOk.insets = new Insets(0, 0, 0, 0);
		gbc_btnOk.weightx = 1.0;
		gbc_btnOk.weighty = 2.0;
		gbc_btnOk.gridx = 2;
		gbc_btnOk.gridy = gridY;
		pnlForm.add(btnOk, gbc_btnOk);
		btnOk.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				String name = txtName.getText();
				String contactNumber = txtCNumber.getText();
				String occupation = txtOcupation.getText();
				int age = list.getLeadSelectionIndex();
				String employment = (String) cmbBox.getSelectedItem();
				String gender = genderGroup.getSelection().getActionCommand();
				String note = txtNote.getText();
				boolean isPH = chckbxUsCitizen.isSelected();
				String taxId = txtTaxId.getText();
				connect();
				controller.addPerson(name, contactNumber, occupation, age, employment, gender, isPH, taxId, note);
				tablePanel.refresh();
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
						JOptionPane.showMessageDialog(new JButton(), "Could not save data to the file", "Error",
								JOptionPane.ERROR_MESSAGE);
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
						JOptionPane.showMessageDialog(new JButton(), "Could not load the data from the file", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		JMenuItem exitFile = new JMenuItem("Exit");
		exitFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int action = JOptionPane.showConfirmDialog(new JButton(), "Do you really want to exit this application",
						"Confirm Exit", JOptionPane.OK_CANCEL_OPTION);
				if (action == JOptionPane.OK_OPTION) {
					WindowListener[] windowListeners = frmPersonInformation.getWindowListeners();
					for (WindowListener listener : windowListeners) {
						listener.windowClosing(new WindowEvent(frmPersonInformation, 0));
					}
				}
			}
		});

		fileMenu.add(exportItem);
		fileMenu.add(importItem);
		fileMenu.addSeparator();
		fileMenu.add(exitFile);
		menuBar.add(fileMenu);

		JMenu windowMenu = new JMenu("Window");
		JMenu showItem = new JMenu("Show");
		JCheckBoxMenuItem showItemForm = new JCheckBoxMenuItem("PersonForm");
		showItemForm.setSelected(true);
		showItem.add(showItemForm);
		windowMenu.add(showItem);
		menuBar.add(windowMenu);
		frmPersonInformation.setJMenuBar(menuBar);
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitFile.setMnemonic(KeyEvent.VK_X);
		exitFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		showItemForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) ev.getSource();
				if (menuItem.isSelected()) {
					splitPane.setDividerLocation((int) pnlForm.getMinimumSize().getWidth());
				}
				pnlForm.setVisible(menuItem.isSelected());
			}
		});
	}

	public void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(frmPersonInformation, "Unable to connect to database", "Error Message",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
