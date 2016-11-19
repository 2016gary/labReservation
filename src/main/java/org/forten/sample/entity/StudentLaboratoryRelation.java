package org.forten.sample.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.forten.sample.entity.enums.LaboratoryOrderStatus;

@Entity
@Table(name = "test_student_laboratory_relation")
public class StudentLaboratoryRelation implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Column(name = "laboratory_id")
	private int laboratoryId;
	@Column(name = "student_id")
	private int studentId;
	@Column(name = "ordered_time")
	private Date orderedTime;
	@Column
	@Enumerated
	private LaboratoryOrderStatus laboratoryOrderStatus;

	public StudentLaboratoryRelation() {
		super();
		this.orderedTime = new Date();
		this.laboratoryOrderStatus = LaboratoryOrderStatus.SUBMIT;
	}

	public StudentLaboratoryRelation(int laboratoryId) {
		this();
		this.laboratoryId = laboratoryId;
	}

	public StudentLaboratoryRelation(int id, int laboratoryId, int studentId) {
		this();
		this.id = id;
		this.laboratoryId = laboratoryId;
		this.studentId = studentId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(int laboratoryId) {
		this.laboratoryId = laboratoryId;
	}

	public Date getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
	}

	public LaboratoryOrderStatus getLaboratoryOrderStatus() {
		return laboratoryOrderStatus;
	}

	public void setLaboratoryOrderStatus(LaboratoryOrderStatus laboratoryOrderStatus) {
		this.laboratoryOrderStatus = laboratoryOrderStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		StudentLaboratoryRelation other = (StudentLaboratoryRelation) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "StudentLaboratoryRelation [id=" + id + ", laboratoryId=" + laboratoryId + ", studentId=" + studentId
				+ ", orderedTime=" + orderedTime + ", laboratoryOrderStatus=" + laboratoryOrderStatus + "]";
	}

}
