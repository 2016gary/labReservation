package org.forten.sample.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_laboratory")
public class Laboratory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;
	@Column(name = "laboratory_name")
	private String laboratoryName;
	@Column(name = "laboratory_address")
	private String laboratoryAddress;
	@Column(name = "order_time")
	private Date orderTime;
	@Column(name = "class_time")
	private String classTime;
	@Column(name = "min_number")
	private int minNumber;
	@Column(name = "max_number")
	private int maxNumber;

	public Laboratory() {
		super();
	}

	public Laboratory(String laboratoryName, String laboratoryAddress, Date orderTime, int minNumber, int maxNumber,
			String classTime) {
		super();
		this.laboratoryName = laboratoryName;
		this.laboratoryAddress = laboratoryAddress;
		this.orderTime = orderTime;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.classTime = classTime;
	}

	public int getId() {
		return id;
	}

	public String getLaboratoryName() {
		return laboratoryName;
	}

	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}

	public String getLaboratoryAddress() {
		return laboratoryAddress;
	}

	public void setLaboratoryAddress(String laboratoryAddress) {
		this.laboratoryAddress = laboratoryAddress;
	}

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	public int getMinNumber() {
		return minNumber;
	}

	public void setMinNumber(int minNumber) {
		this.minNumber = minNumber;
	}

	public int getMaxNumber() {
		return maxNumber;
	}

	public void setMaxNumber(int maxNumber) {
		this.maxNumber = maxNumber;
	}

	public String getClassTime() {
		return classTime;
	}

	public void setClassTime(String classTime) {
		this.classTime = classTime;
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
		Laboratory other = (Laboratory) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Laboratory [id=" + id + ", laboratoryName=" + laboratoryName + ", laboratoryAddress="
				+ laboratoryAddress + ", orderTime=" + orderTime + ", minNumber=" + minNumber + ", maxNumber="
				+ maxNumber + ", classTime=" + classTime + "]";
	}

}
