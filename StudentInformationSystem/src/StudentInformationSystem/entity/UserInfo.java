package StudentInformationSystem.entity;

public class UserInfo {
	private int idAcount;
	private int idPermission;
	private String username;
	private String password;
	private String confirmPassword;
	private String salt;
	private int idStudent;
	private String fullname;
	private String address;
	private String phonenumber;
	private String dateOfBirth;
	private String email;
	private String position;
	private String gender;
	private String classUser;
	private String course;
	private String majors;
	private String identityCardNumber;
	private String dateIssue;
	private String issuePlace;
	private int idInstitute;
	private String instituteName;
	

	

	/**
	 * @param idAcount
	 * @param idPermission
	 * @param username
	 * @param password
	 * @param confirmPassword
	 * @param salt
	 * @param idStudent
	 * @param fullname
	 * @param address
	 * @param phonenumber
	 * @param dateOfBirth
	 * @param email
	 * @param position
	 * @param gender
	 * @param classUser
	 * @param course
	 * @param majors
	 * @param identityCardNumber
	 * @param dateIssue
	 * @param issuePlace
	 * @param idInstitute
	 * @param instituteName
	 */
	public UserInfo(int idAcount, int idPermission, String username, String password, String confirmPassword,
			String salt, int idStudent, String fullname, String address, String phonenumber, String dateOfBirth,
			String email, String position, String gender, String classUser, String course, String majors,
			String identityCardNumber, String dateIssue, String issuePlace, int idInstitute, String instituteName) {
		super();
		this.idAcount = idAcount;
		this.idPermission = idPermission;
		this.username = username;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.salt = salt;
		this.idStudent = idStudent;
		this.fullname = fullname;
		this.address = address;
		this.phonenumber = phonenumber;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.position = position;
		this.gender = gender;
		this.classUser = classUser;
		this.course = course;
		this.majors = majors;
		this.identityCardNumber = identityCardNumber;
		this.dateIssue = dateIssue;
		this.issuePlace = issuePlace;
		this.idInstitute = idInstitute;
		this.instituteName = instituteName;
	}

	/**
	 * 
	 */
	public UserInfo() {
		super();
	}

	/**
	 * @return the idAcount
	 */
	public int getIdAcount() {
		return idAcount;
	}

	/**
	 * @return the idPermission
	 */
	public int getIdPermission() {
		return idPermission;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @return the idStudent
	 */
	public int getIdStudent() {
		return idStudent;
	}

	/**
	 * @return the fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}

	/**
	 * @return the dateOfBirth
	 */
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * @param idAcount
	 *            the idAcount to set
	 */
	public void setIdAcount(int idAcount) {
		this.idAcount = idAcount;
	}

	/**
	 * @param idPermission
	 *            the idPermission to set
	 */
	public void setIdPermission(int idPermission) {
		this.idPermission = idPermission;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param confirmPassword
	 *            the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @param salt
	 *            the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}

	/**
	 * @param idStudent
	 *            the idStudent to set
	 */
	public void setIdStudent(int idStudent) {
		this.idStudent = idStudent;
	}

	/**
	 * @param fullname
	 *            the fullname to set
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param phonenumber
	 *            the phonenumber to set
	 */
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	/**
	 * @param dateOfBirth
	 *            the dateOfBirth to set
	 */
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the classUser
	 */
	public String getClassUser() {
		return classUser;
	}

	/**
	 * @return the course
	 */
	public String getCourse() {
		return course;
	}

	/**
	 * @return the majors
	 */
	public String getMajors() {
		return majors;
	}

	/**
	 * @return the identityCardNumber
	 */
	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	/**
	 * @return the dateIssue
	 */
	public String getDateIssue() {
		return dateIssue;
	}

	/**
	 * @return the issuePlace
	 */
	public String getIssuePlace() {
		return issuePlace;
	}

	/**
	 * @param gender
	 *            the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @param classUser
	 *            the classUser to set
	 */
	public void setClassUser(String classUser) {
		this.classUser = classUser;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(String course) {
		this.course = course;
	}

	/**
	 * @param majors
	 *            the majors to set
	 */
	public void setMajors(String majors) {
		this.majors = majors;
	}

	/**
	 * @param identityCardNumber
	 *            the identityCardNumber to set
	 */
	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	/**
	 * @param dateIssue
	 *            the dateIssue to set
	 */
	public void setDateIssue(String dateIssue) {
		this.dateIssue = dateIssue;
	}

	/**
	 * @param issuePlace
	 *            the issuePlace to set
	 */
	public void setIssuePlace(String issuePlace) {
		this.issuePlace = issuePlace;
	}

	/**
	 * @return the idInstitute
	 */
	public int getIdInstitute() {
		return idInstitute;
	}

	/**
	 * @return the instituteName
	 */
	public String getInstituteName() {
		return instituteName;
	}

	/**
	 * @param idInstitute the idInstitute to set
	 */
	public void setIdInstitute(int idInstitute) {
		this.idInstitute = idInstitute;
	}

	/**
	 * @param instituteName the instituteName to set
	 */
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	
	

}
