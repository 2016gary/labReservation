package org.forten.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_notebook_response")
public class NotebookResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Column(name = "notebook_id")
	private int notebookId;
	@Column(name = "context_text")
	private String contentText;

	public NotebookResponse() {
		super();
	}

	public NotebookResponse(int notebookId, String contentText) {
		super();
		this.notebookId = notebookId;
		this.contentText = contentText;
	}

	public int getNotebookId() {
		return notebookId;
	}

	public void setNotebookId(int notebookId) {
		this.notebookId = notebookId;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public int getId() {
		return id;
	}

}
