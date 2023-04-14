package StudentInformationSystem.entity;

public class GradeTotal {
	private String semester;
	private double GPA;
	private double CPA;
	private int creditPass;
	private int creditTotal;
	private int creditDebt;
	private int creditRegister;
	private String warning;

	public GradeTotal(String semester, double gPA, double cPA, int creditPass, int creditTotal, int creditDebt,
			int creditRegister, String warning) {
		super();
		this.semester = semester;
		GPA = gPA;
		CPA = cPA;
		this.creditPass = creditPass;
		this.creditTotal = creditTotal;
		this.creditDebt = creditDebt;
		this.creditRegister = creditRegister;
		this.warning = warning;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public double getGPA() {
		return GPA;
	}

	public void setGPA(double gPA) {
		GPA = gPA;
	}

	public double getCPA() {
		return CPA;
	}

	public void setCPA(double cPA) {
		CPA = cPA;
	}

	public int getCreditPass() {
		return creditPass;
	}

	public void setCreditPass(int creditPass) {
		this.creditPass = creditPass;
	}

	public int getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(int creditTotal) {
		this.creditTotal = creditTotal;
	}

	public int getCreditDebt() {
		return creditDebt;
	}

	public void setCreditDebt(int creditDebt) {
		this.creditDebt = creditDebt;
	}

	public String getWarning() {
		return warning;
	}

	public void setWarning(String warning) {
		this.warning = warning;
	}

	public int getCreditRegister() {
		return creditRegister;
	}

	public void setCreditRegister(int creditRegister) {
		this.creditRegister = creditRegister;
	}

}
