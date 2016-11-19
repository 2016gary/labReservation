package org.forten.sample.vo;

import java.util.Date;

import org.forten.utils.common.DateUtil;

public class NotebookVo {
	private int id;
	private String title;
	private String username;
	private String contentText;
	private Date createTime;
	private int notebookId;

	public NotebookVo() {
		super();
	}

	public NotebookVo(int id, String contentText, int notebookId) {
		super();
		this.id = id;
		this.contentText = contentText;
		this.notebookId = notebookId;
	}

	public NotebookVo(int id, String title, String username, String contentText, Date createTime) {
		super();
		this.id = id;
		this.title = title;
		this.username = username;
		this.contentText = contentText;
		this.createTime = createTime;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateTimeStr() {
		if (this.createTime == null) {
			return "";
		}
		return DateUtil.convertDateToString(this.createTime, "yyyy年MM月dd日 HH:mm:ss");
	}

	@Override
	public String toString() {
		return "NotebookVo [id=" + id + ", title=" + title + ", username=" + username + ", contentText=" + contentText
				+ ", createTime=" + createTime + "]";
	}

	public int getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(int notebookId) {
		this.notebookId = notebookId;
	}
}
