package gui;

import java.util.List;

import model.Person;

public class PersonTableModel extends javax.swing.table.AbstractTableModel {
	
	private static final long serialVersionUID = -3960447426426904371L;
	private List<Person> db;
	private String[] colNames = {"ID", "Name", "CP Number","Occupation","Gender", "Age Category", "Employment Category", "PH Citizen", "Tax ID","note"};
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
		case 3:
			return true;
		case 8:
			return true;
		case 9:
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
		return 10;
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
			return person.getContactNumber();
		case 3:
			return person.getOccupation();
		case 4:
			return person.getGender();
		case 5:
			return person.getAgeCategory();
		case 6:
			return person.getEmpCat();
		case 7:
			return person.isUsCitizen();
		case 8:
			return person.getTaxId();
		case 9:
			return person.getNote();
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
			person.setContactNumber((String) value);
			break;
		case 3:
			person.setOccupation((String) value);
			break;
		case 8:
			person.setTaxId((String) value);
			break;
		case 9:
			person.setNote((String) value);
		default:
			break;
		}
	
	}
}
