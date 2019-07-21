package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gui.App;
import gui.LogInForm;
import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class Controller {
	Database db = new Database();
	public void setDb(Database db) {
		this.db = db;
	}

	public List<Person> getPeople() {
		return db.getPeople();
	}
	
	public void save() throws SQLException {
		db.save();
	}
	
	public void update() throws SQLException {
		db.update();
	}
	
	public void connect() throws Exception{
		db.connect();
	}
	
	public void retrieve() throws SQLException {
		db.retrieve();
	}
	
	public void disConnect() {
		db.disConnect();
	}
	
	public void removePerson(int index) throws SQLException {
		db.removePerson(index);
	}
	
	public void addPerson(String name,String contactNumber,String occupation,int age,String employment,String gender,boolean isUs,String taxId,String note) {
		AgeCategory ageCategory = null;
		switch (age) {
		case 0:
			ageCategory = AgeCategory.child;
			break;
		case 1:
			ageCategory = AgeCategory.adult;
			break;
		case 2:
			ageCategory = AgeCategory.senior;
		default:
			break;
		}
		
		EmploymentCategory empCat;
		if (employment.equals("Employed")) {
			empCat = EmploymentCategory.employed;
		} else if (employment.equals("self-Employed")) {
			empCat = EmploymentCategory.selfEmployed;
		} else if (employment.equals("unEmployed")) {
			empCat = EmploymentCategory.unemployed;
		} else {
			empCat = EmploymentCategory.other;
		}
		Gender genderCat = null;
		switch (gender) {
		case "Male":
			genderCat = Gender.male;
			break;
		case "Female":
			genderCat = Gender.female;
		default:
			break;
		}
		
		ArrayList<Integer> personId = new ArrayList<Integer>();
		for (Person person: db.getPeople()) {
			personId.add(person.getId());
		}
		int id=1;
		try {
			id = db.getMaxId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Collections.sort(personId);
		if (db.getPeopleSize()>0) {
			if (personId.get(db.getPeopleSize()-1) <= id) {
				id++;
			} else {
				id = personId.get(db.getPeopleSize()-1)+1;
			}
		}else {
			id++;
		}
		Person person = new Person(id,name,contactNumber, occupation, ageCategory, empCat, taxId, isUs, genderCat,db.getUser().getId(),note);
		db.addPerson(person);
	}
	
	public void saveToFile(File file) throws IOException {
		db.SaveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}

	public boolean register(String firstname, String lastname, String email, String username, String password,
			String cPassword) {
		boolean registerResult = false;
		System.out.println("this is controller");
		if (firstname.length() == 0 || lastname.length() == 0 || email.length() == 0 || username.length() == 0 ||password.length() == 0) {
			JOptionPane.showMessageDialog(new JButton(), "please fill up all fields", "Error Message", JOptionPane.ERROR_MESSAGE);
		}else {
			if (!(username.length() <= 30 && username.length() >=8)) {
				JOptionPane.showMessageDialog(new JButton(), "Your username must be 8 to 30 characters", "Error Message", JOptionPane.ERROR_MESSAGE);
			} else {
				if (!(password.length() <=30 && password.length() >= 8)) {
					JOptionPane.showMessageDialog(new JButton(), "Your password must be 8 to 30 chracters", "Error Message", JOptionPane.ERROR_MESSAGE);
				}else {
					if (!password.equals(cPassword)) {
						JOptionPane.showMessageDialog(new JButton(), "password didnt match", "Error Message", JOptionPane.ERROR_MESSAGE);
					} else {
						try {
							registerResult = db.register(firstname,lastname,email,username,password);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return registerResult;
	}

	public boolean logIn(String username, String password) throws SQLException {
		boolean result = false;
		if (username.length() == 0 || password.length() == 0) {
			JOptionPane.showMessageDialog(new JButton(), "please fill up all fields", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else {
			result = db.logIn(username,password);
		}
		return result;
	}

	
	
}
