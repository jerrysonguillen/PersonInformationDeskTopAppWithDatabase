package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
	
	public void connect() throws Exception{
		db.connect();
	}
	
	public void retrieve() throws SQLException {
		db.retrieve();
	}
	
	public void disConnect() {
		db.disConnect();
	}
	
	public void removePerson(int index) {
		db.removePerson(index);
	}
	
	public void addPerson(String name,String occupation,int age,String employment,String gender,boolean isUs,String taxId) {
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
		
		int id = db.numberOfPerson();
		Person person = new Person(id,name, occupation, ageCategory, empCat, taxId, isUs, genderCat);
		
		db.addPerson(person);
	}
	
	public void saveToFile(File file) throws IOException {
		db.SaveToFile(file);
	}
	
	public void loadFromFile(File file) throws IOException {
		db.loadFromFile(file);
	}
	
}
