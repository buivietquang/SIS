 	package StudentInformationSystem.entity;

public class Grade {
	private String semester;
	private String subjectCode;
	private String subjectName;
	private int credit;
	private double middleGrade;
	private double finalGrade;
	private String letterGrade;
	
	public Grade() {
		
	}

	public Grade( String semester,String subjectCode, String subjectName, int credit,
			double middleGrade, double finalGrade, String letterGrade) {
		
		this.semester = semester;
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.credit = credit;
		this.middleGrade = middleGrade;
		this.finalGrade= finalGrade;
		this.letterGrade = letterGrade;
	}

	public double getMiddleGrade() {
		return middleGrade;
	}

	public void setMiddleGrade(double middleGrade) {
		this.middleGrade = middleGrade;
	}

	public double getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(double finalGrade) {
		this.finalGrade = finalGrade;
	}

	public String getLetterGrade() {
		return letterGrade;
	}

	public void setLetterGrade(String letterGrade) {
		this.letterGrade = letterGrade;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
}
