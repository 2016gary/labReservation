package org.forten.sample.ro;

import java.util.List;

import org.forten.sample.vo.LaboratoryNameVo;
import org.forten.sample.vo.LoginedAdminVo;

public class RoForLaboratoryName {
	private List<LaboratoryNameVo> nameVoList;
	private LoginedAdminVo loginedAdmin;

	public RoForLaboratoryName() {
		super();
	}

	public RoForLaboratoryName(List<LaboratoryNameVo> nameVoList, LoginedAdminVo loginedAdmin) {
		super();
		this.nameVoList = nameVoList;
		this.loginedAdmin = loginedAdmin;
	}

	public List<LaboratoryNameVo> getNameVoList() {
		return nameVoList;
	}

	public void setNameVoList(List<LaboratoryNameVo> nameVoList) {
		this.nameVoList = nameVoList;
	}

	public LoginedAdminVo getLoginedAdmin() {
		return loginedAdmin;
	}

	public void setLoginedAdmin(LoginedAdminVo loginedAdmin) {
		this.loginedAdmin = loginedAdmin;
	}

	@Override
	public String toString() {
		return "RoForLaboratoryName [nameVoList=" + nameVoList + ", loginedAdmin=" + loginedAdmin + "]";
	}

}
