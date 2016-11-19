package org.forten.sample.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "test_laboratoryName")
public class LaboratoryName implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Column(name = "laboratory_name")
	@NotBlank(message = "实验室名称不能为空")
	@Length(min = 4, max = 12, message = "字符长度为4~12之间")
	private String laboratoryName;

	public LaboratoryName() {
		super();
	}

	public LaboratoryName(String laboratoryName) {
		super();
		this.laboratoryName = laboratoryName;
	}

	public String getLaboratoryName() {
		return laboratoryName;
	}

	public void setLaboratoryName(String LaboratoryName) {
		this.laboratoryName = LaboratoryName;
	}

	public int getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LaboratoryName other = (LaboratoryName) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LaboratoryName [id=" + id + ", LaboratoryName=" + laboratoryName + "]";
	}

}
