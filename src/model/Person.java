package model;

import java.io.Serializable;

public class Person implements Serializable {

	private static final long serialVersionUID = -3960447426426904371L;
	private static int count = 1;
	private int id;
	private String name;
	private String contactNumber;
	private String occupation;
	private EmploymentCategory empCat;
	private String taxId;
	private boolean PHCitizen;
	private Gender gender;
	private AgeCategory ageCategory;
	private int userId;
	private String note;

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Person(String name, String occupation, AgeCategory ageCategory, EmploymentCategory empCat, String taxId,
			boolean usCitizen, Gender gender) {
		this.name = name;
		this.occupation = occupation;
		this.ageCategory = ageCategory;
		this.empCat = empCat;
		this.taxId = taxId;
		this.PHCitizen = usCitizen;
		this.gender = gender;
		this.id = count;
		count++;
	}

	public Person(int id, String name, String contactNumber, String occupation, AgeCategory ageCategory, EmploymentCategory empCat,
			String taxId, boolean usCitizen, Gender gender, int userId,String note) {
		this(name, occupation, ageCategory, empCat, taxId, usCitizen, gender);
		
		this.id = id;
		this.contactNumber = contactNumber;
		this.userId = userId;
		this.note = note;
	}

	public boolean isPHCitizen() {
		return PHCitizen;
	}

	public void setPHCitizen(boolean pHCitizen) {
		PHCitizen = pHCitizen;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public AgeCategory getageCategory() {
		return getageCategory();
	}

	public void setageCategory(AgeCategory ageCategory) {
		this.setAgeCategory(ageCategory);
	}

	public EmploymentCategory getEmpCat() {
		return empCat;
	}

	public void setEmpCat(EmploymentCategory empCat) {
		this.empCat = empCat;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public boolean isUsCitizen() {
		return PHCitizen;
	}

	public void setUsCitizen(boolean usCitizen) {
		this.PHCitizen = usCitizen;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public AgeCategory getAgeCategory() {
		return ageCategory;
	}

	public void setAgeCategory(AgeCategory ageCategory) {
		this.ageCategory = ageCategory;
	}

	public boolean getUsCitezen() {
		return PHCitizen;

	}

}
