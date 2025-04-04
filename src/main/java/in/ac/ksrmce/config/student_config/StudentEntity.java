package in.ac.ksrmce.config.student_config;

public class StudentEntity {
	private Long id;
	private String name;
	private String roll;
	private String fatherName;
	private String email;
	private String mobile;
	private String gender;
	private String dob;
	private String district;
	private String photo;
	private String signature;
	private String referenceNumber;
	private String hall_ticket_number;

	public StudentEntity() {
	}

	public StudentEntity(Long id, String name, String roll, String fatherName, String email, String mobile,
			String gender, String dob, String district, String photo, String signature, String referenceNumber,
			String hall_ticket_number) {
		super();
		this.id = id;
		this.name = name;
		this.roll = roll;
		this.fatherName = fatherName;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.dob = dob;
		this.district = district;
		this.photo = photo;
		this.signature = signature;
		this.referenceNumber = referenceNumber;
		this.hall_ticket_number = hall_ticket_number;
	}

	public StudentEntity(Long id, String name, String roll, String fatherName, String email, String mobile,
			String gender, String dob, String district, String photo, String signature, String referenceNumber) {
		super();
		this.id = id;
		this.name = name;
		this.roll = roll;
		this.fatherName = fatherName;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.dob = dob;
		this.district = district;
		this.photo = photo;
		this.signature = signature;
		this.referenceNumber = referenceNumber;
	}

	public StudentEntity(String name, String roll, String fatherName, String email, String mobile, String gender,
			String dob, String district, String photo, String signature, String referenceNumber) {
		super();
		this.name = name;
		this.roll = roll;
		this.fatherName = fatherName;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.dob = dob;
		this.district = district;
		this.photo = photo;
		this.signature = signature;
		this.referenceNumber = referenceNumber;
	}

	public StudentEntity(String name, String roll, String fatherName, String email, String mobile, String gender,
			String dob, String district, String photo, String signature, String referenceNumber,
			String hall_ticket_number) {
		super();
		this.name = name;
		this.roll = roll;
		this.fatherName = fatherName;
		this.email = email;
		this.mobile = mobile;
		this.gender = gender;
		this.dob = dob;
		this.district = district;
		this.photo = photo;
		this.signature = signature;
		this.referenceNumber = referenceNumber;
		this.hall_ticket_number = hall_ticket_number;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoll() {
		return roll;
	}

	public void setRoll(String roll) {
		this.roll = roll;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getReferenceNumber() {
		return referenceNumber;
	}

	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	public String getHall_ticket_number() {
		return hall_ticket_number;
	}

	public void setHall_ticket_number(String hall_ticket_number) {
		this.hall_ticket_number = hall_ticket_number;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", name=" + name + ", roll=" + roll + ", fatherName=" + fatherName
				+ ", email=" + email + ", mobile=" + mobile + ", gender=" + gender + ", dob=" + dob + ", district="
				+ district + ", photo=" + photo + ", signature=" + signature + ", referenceNumber=" + referenceNumber
				+ ", hall_ticket_number=" + hall_ticket_number + "]";
	}

	//
	// public void setPassport_size_photo(String passport_size_photo) {
	// // TODO Auto-generated method stub
	//
	// }

}
