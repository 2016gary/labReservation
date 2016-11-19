package org.forten.sample.vo;

import java.util.Date;

import org.forten.utils.common.DateUtil;

public class LaboratoryVo {
	private int id;
	private String laboratoryName;
	private String laboratoryAddress;
	private Date orderTime;
	private int minNumber;
	private int maxNumber;
	private String classTime;

	public LaboratoryVo() {
		super();
	}

	public LaboratoryVo(int id, String laboratoryName, String laboratoryAddress, Date orderTime, int minNumber,
			int maxNumber, String classTime) {
		super();
		this.id = id;
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

	public void setId(int id) {
		this.id = id;
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

	public String getOrderTimeStr() {
		if (orderTime == null) {
			return "";
		} else {
			return DateUtil.convertDateToString(orderTime, "yyyy年MM月dd日");
		}
	}
}
