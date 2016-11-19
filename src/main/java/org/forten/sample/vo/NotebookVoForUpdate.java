package org.forten.sample.vo;

public class NotebookVoForUpdate {
	private int id;
	private String title;
	private String username;
	private String contentText;

	public NotebookVoForUpdate() {
		super();
	}

	public NotebookVoForUpdate(int id, String title, String username, String contentText) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.contentText = contentText;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	@Override
	public String toString() {
		return "NotebookVoForUpdate [id=" + id + ", title=" + title + ", username=" + username + ", contentText="
				+ contentText + "]";
	}
}
