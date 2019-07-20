package gui;

import java.util.List;

import model.Person;

public class PersonTableModel extends javax.swing.table.AbstractTableModel {
	
	private static final long serialVersionUID = -3960447426426904371L;
	private List<Person> db;
	private String[] colNames = {"ID", "Name", "Occupation","Gender", "Age Category", "Employment Category", "PH Citizen", "Tax ID"};
	public PersonTableModel() {
	}
	
	public List<Person> getDb() {
		return db;
	}

	public void setDb(List<Person> db) {
		this.db = db;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		switch (col) {
		case 1:
			return true;
		case 2:
			return true;
		case 7:
			return true;
		default:
			return false;
		}
	}

	@Override
	public String getColumnName(int column) {
		return colNames[column];
	}

	public void setData(List<Person> db) {
		this.db = db;
	}

	public int getColumnCount() {
		return 8;
	}

	@Override
	public int getRowCount() {
		return db.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Person person = db.get(row);
		
		switch (col) {
		case 0:
			return person.getId();
		case 1:
			return person.getName();
		case 2:
			return person.getOccupation();
		case 3:
			return person.getGender();
		case 4:
			return person.getAgeCategory();
		case 5:
			return person.getEmpCat();
		case 6:
			return person.isUsCitizen();
		case 7:
			return person.getTaxId();
		}
		return null;
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		Person person = db.get(row);
		switch (col) {
		case 1:
			person.setName((String) value);
			break;
		case 2:
			person.setOccupation((String) value);;
			break;
		case 7:
			person.setTaxId((String) value);
			break;
		default:
			break;
		}
	
	}
}
