package org.forten.sample.entity.enums;

public enum LaboratoryOrderStatus {
	SUBMIT("已预约"), QUEUE("排队中"), SUCCESS("预约成功");
	private String desc;

	private LaboratoryOrderStatus(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

	public int getIndex() {
		return ordinal();
	}
}
