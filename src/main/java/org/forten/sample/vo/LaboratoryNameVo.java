package org.forten.sample.vo;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class LaboratoryNameVo {
	private int id;
	@NotBlank(message = "实验室名称不能为空")
	@Length(min = 4, max = 12, message = "字符长度为4~12之间")
	private String laboratoryName;

	public LaboratoryNameVo() {
		super();
	}

	public LaboratoryNameVo(int id, String laboratoryName) {
		super();
		this.id = id;
		this.laboratoryName = laboratoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLaboratoryName() {
		return laboratoryName;
	}

	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}

	@Override
	public String toString() {
		return "LaboratoryNameVo [id=" + id + ", laboratoryName=" + laboratoryName + "]";
	}

}
