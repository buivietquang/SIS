package StudentInformationSystem.entity;

public class RegisterSubjectFee {
	private int idstudent;
	private int idsubject;
	private String subjectcode;
	private String subjectname;
	private String institutename;
	private int creditsubject;
	private int credittuition;
	private int idinstituteOfstudent;
	private int idinstituteOfsubject;
	private String semester;
	
	
	public RegisterSubjectFee(int idstudent, int idsubject, String subjectcode, String subjectname,
			String institutename, int creditsubject, int credittuition, int idinstituteOfstudent,
			int idinstituteOfsubject, String semester) {
		super();
		this.idstudent = idstudent;
		this.idsubject = idsubject;
		this.subjectcode = subjectcode;
		this.subjectname = subjectname;
		this.institutename = institutename;
		this.creditsubject = creditsubject;
		this.credittuition = credittuition;
		this.idinstituteOfstudent = idinstituteOfstudent;
		this.idinstituteOfsubject = idinstituteOfsubject;
		this.semester = semester;
	}

	public RegisterSubjectFee() {
		super();
	}

	public int getIdstudent() {
		return idstudent;
	}

	public void setIdstudent(int idstudent) {
		this.idstudent = idstudent;
	}

	public int getIdsubject() {
		return idsubject;
	}

	public void setIdsubject(int idsubject) {
		this.idsubject = idsubject;
	}

	public int getIdinstituteOfstudent() {
		return idinstituteOfstudent;
	}

	public void setIdinstituteOfstudent(int idinstituteOfstudent) {
		this.idinstituteOfstudent = idinstituteOfstudent;
	}

	public int getIdinstituteOfsubject() {
		return idinstituteOfsubject;
	}

	public void setIdinstituteOfsubject(int idinstituteOfsubject) {
		this.idinstituteOfsubject = idinstituteOfsubject;
	}

	public String getSubjectcode() {
		return subjectcode;
	}

	public void setSubjectcode(String subjectcode) {
		this.subjectcode = subjectcode;
	}

	public String getSubjectname() {
		return subjectname;
	}

	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}

	public String getInstitutename() {
		return institutename;
	}

	public void setInstitutename(String institutename) {
		this.institutename = institutename;
	}

	public int getCreditsubject() {
		return creditsubject;
	}

	public void setCreditsubject(int creditsubject) {
		this.creditsubject = creditsubject;
	}

	public int getCredittuition() {
		return credittuition;
	}

	public void setCredittuition(int credittuition) {
		this.credittuition = credittuition;
	}
	public String getSemester() {
		return semester;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}

}
