package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import controller.Controller;

//import com.mysql.cj.xdevapi.Statement;

public class Database {
	private List<Person> people;
	private Connection con;
	private static User user;
	
	public User getUser() {
		return user;
	}

	Controller controller;
	public Database() {
		people = new LinkedList<Person>();
	}

	public void addPerson(Person person) {
		people.add(person);
	}

	public int getPeopleSize() {
		return people.size();
	}

	public void connect() throws Exception {
		if (con != null) {
			return;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new Exception("Driver not found");
		}
		String url = "jdbc:mysql://localhost/persondb";
		con = DriverManager.getConnection(url, "root", "");
	}

	public void disConnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("can't close connection");
			}
		}
	}

	public void save() throws SQLException {
		String insertSql = "INSERT INTO people(id,name,age,contact_number,employment_status,tax_id,ph_citizen,gender,occupation,user_id,note) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		for (Person person : people) {
			int id = person.getId();
			String name = person.getName();
			AgeCategory age = person.getAgeCategory();
			String contactNumber = person.getContactNumber();
			String occupation = person.getOccupation();
			EmploymentCategory emp = person.getEmpCat();
			String tax = person.getTaxId();
			boolean isPH = person.getUsCitezen();
			Gender gender = person.getGender();
			String note = person.getNote();
			int count = searchInql(id);
			int col = 1;
			if (count == 0) {
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, age.name());
				insertStatement.setString(col++, contactNumber);
				insertStatement.setString(col++, emp.name());
				insertStatement.setString(col++, tax);
				insertStatement.setBoolean(col++, isPH);
				insertStatement.setString(col++, gender.name());
				insertStatement.setString(col++, occupation);
				insertStatement.setInt(col++, user.getId());
				insertStatement.setString(col++, note);
				insertStatement.executeUpdate();
			} 
		}
		JOptionPane.showMessageDialog(new JButton(), "Person Successfully Inserted", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
		insertStatement.close();
	}
	
	public void update() throws SQLException {
		String updateSql = "UPDATE people SET name=?,age=?,contact_number=?,employment_status=?,tax_id=?,ph_citizen=?,gender=?,occupation=?,user_id=?,note=? where id=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);
		for (Person person : people) {
			int id = person.getId();
			String name = person.getName();
			AgeCategory age = person.getAgeCategory();
			String contactNumber = person.getContactNumber();
			String occupation = person.getOccupation();
			EmploymentCategory emp = person.getEmpCat();
			String tax = person.getTaxId();
			boolean isPH = person.getUsCitezen();
			Gender gender = person.getGender();
			String note = person.getNote();
			int col = 1;
			int count = searchInql(id);
			if (count == 1) {
				updateStatement.setString(col++, name);
				updateStatement.setString(col++, age.name());
				updateStatement.setString(col++, contactNumber);
				updateStatement.setString(col++, emp.name());
				updateStatement.setString(col++, tax);
				updateStatement.setBoolean(col++, isPH);
				updateStatement.setString(col++, gender.name());
				updateStatement.setString(col++, occupation);
				updateStatement.setInt(col++, user.getId());
				updateStatement.setString(col++, note);
				updateStatement.setInt(col++, id);
				updateStatement.executeUpdate();
			}
		}
		JOptionPane.showMessageDialog(new JButton(), "Information Successfully Updated", "SUCCESS MESSAGE", JOptionPane.INFORMATION_MESSAGE);
		updateStatement.close();
	}

	public void retrieve() throws SQLException {
		people.clear();
		String retrieveSql = "SELECT * FROM people where user_id = ?";
		PreparedStatement selectStatement = con.prepareStatement(retrieveSql);
		selectStatement.setInt(1,user.getId());
		ResultSet results = selectStatement.executeQuery();
		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String age = results.getString("age");
			String emp = results.getString("employment_status");
			String taxid = results.getString("tax_id");
			boolean phCitizen = results.getBoolean("ph_citizen");
			String gender = results.getString("gender");
			String occu = results.getString("occupation");
			String contactNumber = results.getString("contact_number");
			String note = results.getString("note");
			people.add(new Person(id, name, contactNumber,occu, AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), taxid,
					phCitizen, Gender.valueOf(gender),user.getId(),note));
		}
		selectStatement.close();
		results.close();
	}

	public void removePerson(int index) throws SQLException {
		Person person = people.get(index);
		int id = person.getId();
		int count = searchInql(id);
		String deleteSql = "delete from people where id =?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteSql);
		if (count == 1) {
			int deleteConfirm = JOptionPane.showConfirmDialog(new JButton(),
					"Are you sure you want to delete this person?", "Delete person",
					JOptionPane.OK_CANCEL_OPTION | JOptionPane.WARNING_MESSAGE);
			if (deleteConfirm == JOptionPane.OK_OPTION) {
				deleteStatement.setInt(1, id);
				deleteStatement.executeUpdate();
			} else if (deleteConfirm == JOptionPane.CANCEL_OPTION) {
				deleteStatement.close();
				return;
			}
		}
		people.remove(index);
		deleteStatement.close();
	}
	
	private int searchInql(int id) throws SQLException {
		String searchSql = "Select count(*) as count from people where id = ?";
		PreparedStatement searchStatement = con.prepareStatement(searchSql);
		searchStatement.setInt(1, id);
		ResultSet searchResult = searchStatement.executeQuery();
		searchResult.next();
		int count = searchResult.getInt(1);
		searchStatement.close();
		searchResult.close();
		return count;
	}

	public List<Person> getPeople() {
		return Collections.unmodifiableList(people);
	}

	public void SaveToFile(File file) throws IOException {
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Person[] persons = people.toArray(new Person[people.size()]);
		oos.writeObject(persons);
		oos.close();
	}

	public void loadFromFile(File file) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois = new ObjectInputStream(fis);
		try {
			Person[] persons = (Person[]) ois.readObject();
			people.clear();
			people.addAll(Arrays.asList(persons));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		ois.close();
	}

	public boolean register(String firstname, String lastname, String email, String username, String password) throws SQLException {
		boolean registerResult = false;
		String searchSql = "select count(*) as count from user where email = ?";
		PreparedStatement searchStatement = con.prepareStatement(searchSql);
		searchStatement.setString(1, email);
		String insertSql = "insert into user(first_name,last_name,email,username,password) values(?,?,?,?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);
		ResultSet result = searchStatement.executeQuery();
		result.next();
		int resultCount = result.getInt(1);
		if (resultCount == 1) {
			JOptionPane.showMessageDialog(new JButton(), "your email is already in user", "Error Message", JOptionPane.ERROR_MESSAGE);
		} else {
			int col = 1;
			insertStatement.setString(col++,firstname);
			insertStatement.setString(col++,lastname);
			insertStatement.setString(col++,email);
			insertStatement.setString(col++,username);
			insertStatement.setString(col++,password);
			insertStatement.executeUpdate();
			registerResult = true;
			JOptionPane.showMessageDialog(new JButton(), "Account Successfully created", "SUCCESS Message", JOptionPane.INFORMATION_MESSAGE);
		}
		searchStatement.close();
		result.close();
		insertStatement.close();
		return registerResult;
	}

	public boolean logIn(String username, String password) throws SQLException {
		String searchSql = "Select * from user where username = ? and password = ?";
		PreparedStatement searchStatement = con.prepareStatement(searchSql);
		searchStatement.setString(1, username);
		searchStatement.setString(2, password);
		ResultSet result = searchStatement.executeQuery();
		boolean logInResult = false;
		if (result.next()) {
			int id = result.getInt("id");
			String firstname = result.getString("first_name");
			String lastname = result.getString("last_name");
			String email = result.getString("email");
			user = new User(id,firstname,lastname,email,username);
			System.out.println("id: "+user.getId()+" firstname:" + user.getFirstname() + " lastname: "+ user.getLastname()+" email:" + user.getEmail()+" username: " + user.getUsername());
			logInResult = true;
		} else {
			JOptionPane.showMessageDialog(new JButton(), "It's either username or password is wrong", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		result.close();
		searchStatement.close();
		return logInResult;
	}

	public int getMaxId() throws SQLException {
		String maxIdSql = "select max(id) as count from people";
		Statement maxIdStatement = con.createStatement();
		ResultSet maxIdResult = maxIdStatement.executeQuery(maxIdSql);
		maxIdResult.next();
		int result = maxIdResult.getInt(1);
		maxIdStatement.close();
		maxIdResult.close();
		return result;
	}
}
