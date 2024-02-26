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
    private String passport_size_photo;
    private String hallticket_photo;

    
    public StudentEntity() {
    }

    
    
    public StudentEntity(Long id, String name, String roll, String fatherName, String email, String mobile,
            String gender, String dob, String district, String passport_size_photo, String hallticket_photo) {
        this.id = id;
        this.name = name;
        this.roll = roll;
        this.fatherName = fatherName;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.dob = dob;
        this.district = district;
        this.passport_size_photo = passport_size_photo;
        this.hallticket_photo = hallticket_photo;
    }



    public StudentEntity(String name, String roll, String fatherName, String email, String mobile, String gender, String dob,
            String district, String passport_size_photo, String hallticket_photo) {
        this.name = name;
        this.roll = roll;
        this.fatherName = fatherName;
        this.email = email;
        this.mobile = mobile;
        this.gender = gender;
        this.dob = dob;
        this.district = district;
        this.passport_size_photo = passport_size_photo;
        this.hallticket_photo = hallticket_photo;
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
    public String getPassport_size_photo() {
        return passport_size_photo;
    }
    public void setPassport_size_photo(String passport_size_photo) {
        this.passport_size_photo = passport_size_photo;
    }
    public String getHallticket_photo() {
        return hallticket_photo;
    }
    public void setHallticket_photo(String hallticket_photo) {
        this.hallticket_photo = hallticket_photo;
    }
    
}
