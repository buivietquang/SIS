package StudentInformationSystem.entity;

public class Notice {
	private int idnotice;
	private String title;
	private String content;
	private String datetime;
	
	public Notice(int idnotice, String title, String content, String datetime) {
 		super();
 		this.idnotice = idnotice;
 		this.title = title;
 		this.content = content;
		this.datetime = datetime;
 	}
	
	public Notice() {
		super();
	}

	public int getIdnotice() {
		return idnotice;
	}

	public void setIdnotice(int idnotice) {
		this.idnotice = idnotice;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getDatetime() {
		return datetime;
	}
	
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
}
