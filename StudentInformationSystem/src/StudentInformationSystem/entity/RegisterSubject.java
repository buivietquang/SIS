package StudentInformationSystem.entity;

import javafx.scene.control.CheckBox;

public class RegisterSubject {
	private String subjectCode;
	private String subjectName;
	private String institute;
	private String stageRegister;
	private int credit;
	private CheckBox select;
	
	public RegisterSubject() {
		
	}
	
	public RegisterSubject(String subjectCode, String subjectName, String institute, 
			String stageRegister, int credit) {
		
		this.subjectCode = subjectCode;
		this.subjectName = subjectName;
		this.institute = institute;
		this.stageRegister = stageRegister;
		this.credit = credit;
		this.select = new CheckBox();
	}

	public CheckBox getSelect() {
		return select;
	}

	public void setSelect(CheckBox select) {
		this.select = select;
	}

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getStageRegister() {
		return stageRegister;
	}

	public void setStageRegister(String stageRegister) {
		this.stageRegister = stageRegister;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	
}
