package org.forten.sample.vo;

import java.util.Date;

import org.forten.sample.entity.enums.LaboratoryOrderStatus;

public class StudentLaboratoryRelationVo {
	private int id;
	private int laboratoryId;
	private String laboratoryName;
	private String laboratoryAddress;
	private Date orderedTime;
	private int minNumber;
	private int maxNumber;
	private String classTime;
	private String laboratoryOrderStatusDesc;
	private LaboratoryOrderStatus laboratoryOrderStatus;
	private int studentId;

	public StudentLaboratoryRelationVo() {
		super();
	}

	public StudentLaboratoryRelationVo(int id, int laboratoryId, String laboratoryName, String laboratoryAddress,
			Date orderedTime, int minNumber, int maxNumber, String classTime, String laboratoryOrderStatusDesc,
			LaboratoryOrderStatus laboratoryOrderStatus, int studentId) {
		super();
		this.id = id;
		this.laboratoryId = laboratoryId;
		this.laboratoryName = laboratoryName;
		this.laboratoryAddress = laboratoryAddress;
		this.orderedTime = orderedTime;
		this.minNumber = minNumber;
		this.maxNumber = maxNumber;
		this.classTime = classTime;
		this.laboratoryOrderStatusDesc = laboratoryOrderStatusDesc;
		this.laboratoryOrderStatus = laboratoryOrderStatus;
		this.studentId = studentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLaboratoryId() {
		return laboratoryId;
	}

	public void setLaboratoryId(int laboratoryId) {
		this.laboratoryId = laboratoryId;
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

	public Date getOrderedTime() {
		return orderedTime;
	}

	public void setOrderedTime(Date orderedTime) {
		this.orderedTime = orderedTime;
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

	public String getLaboratoryOrderStatusDesc() {
		return laboratoryOrderStatusDesc;
	}

	public void setLaboratoryOrderStatusDesc(String laboratoryOrderStatusDesc) {
		this.laboratoryOrderStatusDesc = laboratoryOrderStatusDesc;
	}

	public LaboratoryOrderStatus getLaboratoryOrderStatus() {
		return laboratoryOrderStatus;
	}

	public void setLaboratoryOrderStatus(LaboratoryOrderStatus laboratoryOrderStatus) {
		this.laboratoryOrderStatus = laboratoryOrderStatus;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		return "StudentLaboratoryRelationVo [id=" + id + ", laboratoryId=" + laboratoryId + ", laboratoryName="
				+ laboratoryName + ", laboratoryAddress=" + laboratoryAddress + ", orderedTime=" + orderedTime
				+ ", minNumber=" + minNumber + ", maxNumber=" + maxNumber + ", classTime=" + classTime
				+ ", laboratoryOrderStatusDesc=" + laboratoryOrderStatusDesc + ", laboratoryOrderStatus="
				+ laboratoryOrderStatus + ", studentId=" + studentId + "]";
	}

}
