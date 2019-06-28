package kh.spring.dto;

public class BoardDTO {
	private int no;
	private String id;
	private String title;
	private String text;
	private int click;
	
	public BoardDTO() {
		super();
	}

	public BoardDTO(int no, String id, String title, String text, int click) {
		super();
		this.no = no;
		this.id = id;
		this.title = title;
		this.text = text;
		this.click = click;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getClick() {
		return click;
	}

	public void setClick(int click) {
		this.click = click;
	}

	
}
