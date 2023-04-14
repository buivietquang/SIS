package StudentInformationSystem.entity;

public class Subject {
	private int idSubject;
	private String subjectCode;
	private String subjectName;
	private int creditSubject;
	private int creditTuition;
	private String institute ;
	private float weight;
	/**
	 * 
	 */
	public Subject() {
		super();
	}
	/**
	 * @return the idSubject
	 */
	public int getIdSubject() {
		return idSubject;
	}
	/**
	 * @return the subjectCode
	 */
	public String getSubjectCode() {
		return subjectCode;
	}
	/**
	 * @return the subjectName
	 */
	public String getSubjectName() {
		return subjectName;
	}
	/**
	 * @return the creditSubject
	 */
	public int getCreditSubject() {
		return creditSubject;
	}
	/**
	 * @return the creditTuition
	 */
	public int getCreditTuition() {
		return creditTuition;
	}
	/**
	 * @return the institute
	 */
	public String getInstitute() {
		return institute;
	}
	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}
	/**
	 * @param idSubject the idSubject to set
	 */
	public void setIdSubject(int idSubject) {
		this.idSubject = idSubject;
	}
	/**
	 * @param subjectCode the subjectCode to set
	 */
	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}
	/**
	 * @param subjectName the subjectName to set
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	/**
	 * @param creditSubject the creditSubject to set
	 */
	public void setCreditSubject(int creditSubject) {
		this.creditSubject = creditSubject;
	}
	/**
	 * @param creditTuition the creditTuition to set
	 */
	public void setCreditTuition(int creditTuition) {
		this.creditTuition = creditTuition;
	}
	/**
	 * @param institute the institute to set
	 */
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	
}
