package org.forten.sample.qo;

import java.util.Date;

public class LaboratoryQo {
	private String laboratoryName;
	private Date fromOrderTime;
	private Date toOrderTime;
	private Integer pageNo;
	private Integer pageSize;
	private String orderByField;
	private String orderByType;

	public LaboratoryQo() {
		super();
		this.pageNo = 1;
		this.pageSize = 5;
		this.orderByField = "id";
		this.orderByType = "DESC";
	}

	public LaboratoryQo(String laboratoryName, Date fromOrderTime, Date toOrderTime) {
		this();
		this.laboratoryName = laboratoryName;
		this.fromOrderTime = fromOrderTime;
		this.toOrderTime = toOrderTime;
	}

	public LaboratoryQo(String laboratoryName, Date fromOrderTime, Date toOrderTime, Integer pageNo, Integer pageSize) {
		this();
		this.laboratoryName = laboratoryName;
		this.fromOrderTime = fromOrderTime;
		this.toOrderTime = toOrderTime;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public LaboratoryQo(String laboratoryName, Date fromOrderTime, Date toOrderTime, Integer pageNo, Integer pageSize,
			String orderByField, String orderByType) {
		super();
		this.laboratoryName = laboratoryName;
		this.fromOrderTime = fromOrderTime;
		this.toOrderTime = toOrderTime;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.orderByField = orderByField;
		this.orderByType = orderByType;
	}

	public String getLaboratoryName() {
		return laboratoryName;
	}

	public void setLaboratoryName(String laboratoryName) {
		this.laboratoryName = laboratoryName;
	}

	public Date getFromOrderTime() {
		return fromOrderTime;
	}

	public void setFromOrderTime(Date fromOrderTime) {
		this.fromOrderTime = fromOrderTime;
	}

	public Date getToOrderTime() {
		return toOrderTime;
	}

	public void setToOrderTime(Date toOrderTime) {
		this.toOrderTime = toOrderTime;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderByField() {
		return orderByField;
	}

	public void setOrderByField(String orderByField) {
		this.orderByField = orderByField;
	}

	public String getOrderByType() {
		return orderByType;
	}

	public void setOrderByType(String orderByType) {
		this.orderByType = orderByType;
	}

	@Override
	public String toString() {
		return "LaboratoryQo [laboratoryName=" + laboratoryName + ", fromOrderTime=" + fromOrderTime + ", toOrderTime="
				+ toOrderTime + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", orderByField=" + orderByField
				+ ", orderByType=" + orderByType + "]";
	}

}
