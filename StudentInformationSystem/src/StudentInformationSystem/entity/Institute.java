package StudentInformationSystem.entity;

public class Institute {
	private int idInstitute;
	private String instituteName;
	private String phoneSupport;
	private String address;
	/**
	 * 
	 */
	public Institute() {
		super();
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
	 * @return the phoneSupport
	 */
	public String getPhoneSupport() {
		return phoneSupport;
	}
	/**
	 * @return the addess
	 */
	public String getAddress() {
		return address;
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
	/**
	 * @param phoneSupport the phoneSupport to set
	 */
	public void setPhoneSupport(String phoneSupport) {
		this.phoneSupport = phoneSupport;
	}
	/**
	 * @param addess the addess to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
}
