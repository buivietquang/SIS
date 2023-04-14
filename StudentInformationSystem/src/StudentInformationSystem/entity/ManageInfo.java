package StudentInformationSystem.entity;

public class ManageInfo {
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

	/**
	 * 
	 */
	public ManageInfo() {
		super();
	}

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
	 */
	public ManageInfo(int idAcount, int idPermission, String username, String password, String confirmPassword,
			String salt, int idStudent, String fullname, String address, String phonenumber, String dateOfBirth,
			String email, String position) {
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

}
