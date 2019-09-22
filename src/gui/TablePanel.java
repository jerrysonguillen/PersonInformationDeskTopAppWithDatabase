package gui;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import model.Person;

public class TablePanel extends javax.swing.JPanel {

	private static final long serialVersionUID = -3960447426426904371L;
	private JTable table;
	private PersonTableModel tableModel;
	private JPopupMenu popup;
	private PersonTableListener personTableListener;
	
	public TablePanel() {
		tableModel = new PersonTableModel();
		table = new JTable(tableModel);
		setLayout(new BorderLayout());
		add(new JScrollPane(table), BorderLayout.CENTER);
		popup = new JPopupMenu();
		JMenuItem removeItem = new JMenuItem("delete Row");
		popup.add(removeItem);
		table.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				int row = table.rowAtPoint(e.getPoint());
				table.getSelectionModel().setSelectionInterval(row, row);
				if (e.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, e.getX(), e.getY());
				}
			}
		});
		
		removeItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = table.getSelectedRow();
				if (personTableListener != null ) {
					personTableListener.rowDeleted(row);
					tableModel.fireTableDataChanged();
				}
			}
		});
	}
	
	public void setData(List<Person> db) {
		tableModel.setData(db);
	}
	
	public void refresh() {
		tableModel.fireTableDataChanged();
	}

	public void setPersonTableListener(PersonTableListener Listener) {
		this.personTableListener = Listener;
	}
}