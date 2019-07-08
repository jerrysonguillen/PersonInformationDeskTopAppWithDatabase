import java.sql.SQLException;

import model.AgeCategory;
import model.Database;
import model.EmploymentCategory;
import model.Gender;
import model.Person;

public class DatabaseTest {
	public static void main(String[] args) {
		Database db = new Database();
		try {
			db.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		db.addPerson(new Person("sophie", "Manager Software Engineer", AgeCategory.adult, EmploymentCategory.employed, "1111", true, Gender.female));
		db.addPerson(new Person("jerryson", "Software Engineer", AgeCategory.adult, EmploymentCategory.employed, "1131", true, Gender.male));
		db.addPerson(new Person("marilyn", "Actress", AgeCategory.adult, EmploymentCategory.employed, "9632", true, Gender.female));
		db.addPerson(new Person("marilyn", "Actress", AgeCategory.adult, EmploymentCategory.employed, "9632", true, Gender.female));
		try {
			db.save();
			//db.retrieve();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		db.disConnect();
		
	}
}
