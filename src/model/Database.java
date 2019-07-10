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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.sql.Statement;

//import com.mysql.cj.xdevapi.Statement;

public class Database {
	private List<Person> people;
	private Connection con;

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

		System.out.println("connected to database :" + con);
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

		String insertSql = "INSERT INTO people(id,name,age,employment_status,tax_id,ph_citizen,gender,occupation) values(?,?,?,?,?,?,?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSql);

		String query = "select count(*) as count from people where id = ?";
		PreparedStatement countStatement = con.prepareStatement(query);

		String updateSql = "UPDATE people SET name=?,age=?,employment_status=?,tax_id=?,ph_citizen=?,gender=?,occupation=? where id=?";
		PreparedStatement updateStatement = con.prepareStatement(updateSql);

		for (Person person : people) {
			int id = person.getId();
			String name = person.getName();
			AgeCategory age = person.getAgeCategory();
			String occupation = person.getOccupation();
			EmploymentCategory emp = person.getEmpCat();
			String tax = person.getTaxId();
			boolean isPH = person.getUsCitezen();
			Gender gender = person.getGender();

			countStatement.setInt(1, id);
			ResultSet countResult = countStatement.executeQuery();
			countResult.next();

			int col = 1;
			int count = countResult.getInt(1);
			if (count == 0) {
				insertStatement.setInt(col++, id);
				insertStatement.setString(col++, name);
				insertStatement.setString(col++, age.name());
				insertStatement.setString(col++, emp.name());
				insertStatement.setString(col++, tax);
				insertStatement.setBoolean(col++, isPH);
				insertStatement.setString(col++, gender.name());
				insertStatement.setString(col++, occupation);
				insertStatement.executeUpdate();
				System.out.println("Inserting the person with id: " + id);
			} else {
				updateStatement.setString(col++, name);
				updateStatement.setString(col++, age.name());
				updateStatement.setString(col++, emp.name());
				updateStatement.setString(col++, tax);
				updateStatement.setBoolean(col++, isPH);
				updateStatement.setString(col++, gender.name());
				updateStatement.setString(col++, occupation);
				updateStatement.setInt(col++, id);
				updateStatement.executeUpdate();
				System.out.println("Updating the person name: " + name + " with id: " + id);
			}

		}
		JOptionPane.showMessageDialog(null, "Successfully Updated");
		updateStatement.close();
		insertStatement.close();
		countStatement.close();
	}

	public void retrieve() throws SQLException {
		people.clear();
		String retrieveSql = "SELECT * FROM people";
		Statement selectStatement = con.createStatement();
		ResultSet results = selectStatement.executeQuery(retrieveSql);
		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String age = results.getString("age");
			String emp = results.getString("employment_status");
			String taxid = results.getString("tax_id");
			boolean phCitizen = results.getBoolean("ph_citizen");
			String gender = results.getString("gender");
			String occu = results.getString("occupation");
			people.add(new Person(id, name, occu, AgeCategory.valueOf(age), EmploymentCategory.valueOf(emp), taxid,
					phCitizen, Gender.valueOf(gender)));
		}
		results.close();
		selectStatement.close();
	}

	public void removePerson(int index) throws SQLException {

		Person person = people.get(index);
		int id = person.getId();

		String searchSql = "Select count(*) as count from people where id = ?";
		PreparedStatement searchStatement = con.prepareStatement(searchSql);
		searchStatement.setInt(1, id);
		ResultSet searchResult = searchStatement.executeQuery();

		String deleteSql = "delete from people where id =?";
		PreparedStatement deleteStatement = con.prepareStatement(deleteSql);

		searchResult.next();
		int count = searchResult.getInt(1);

		if (count == 1) {
			int deleteConfirm = JOptionPane.showConfirmDialog(new JButton(),
					"Are you sure you want to delete this person?", "Delete person",
					JOptionPane.OK_CANCEL_OPTION | JOptionPane.WARNING_MESSAGE);
			if (deleteConfirm == JOptionPane.OK_OPTION) {
				deleteStatement.setInt(1, id);
				deleteStatement.executeUpdate();
			} else if (deleteConfirm == JOptionPane.CANCEL_OPTION) {
				searchStatement.close();
				searchResult.close();
				deleteStatement.close();
				return;
			}
		}
		
		people.remove(index);
		searchStatement.close();
		searchResult.close();
		deleteStatement.close();
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
}
