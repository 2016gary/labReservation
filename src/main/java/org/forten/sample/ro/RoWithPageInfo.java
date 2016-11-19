package org.forten.sample.ro;

import java.util.List;

import org.forten.sample.vo.LoginedAdminVo;
import org.forten.utils.collection.CollectionUtil;
import org.forten.utils.system.PageInfo;

public class RoWithPageInfo<T> {
	private List<T> dataList;
	private PageInfo pageInfo;
	private LoginedAdminVo loginedAdmin;

	public RoWithPageInfo() {
		super();
	}

	public RoWithPageInfo(List<T> dataList, PageInfo pageInfo) {
		super();
		this.dataList = dataList;
		this.pageInfo = pageInfo;
	}

	public RoWithPageInfo(List<T> dataList, PageInfo pageInfo, LoginedAdminVo loginedAdmin) {
		super();
		this.dataList = dataList;
		this.pageInfo = pageInfo;
		this.loginedAdmin = loginedAdmin;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	public LoginedAdminVo getLoginedAdmin() {
		return loginedAdmin;
	}

	public void setLoginedAdmin(LoginedAdminVo loginedAdmin) {
		this.loginedAdmin = loginedAdmin;
	}

	public boolean isEmptyData() {
		return CollectionUtil.isEmpty(dataList);
	}

	@Override
	public String toString() {
		return "RoWithPageInfo [dataList=" + dataList + ", pageInfo=" + pageInfo + ", loginedAdmin=" + loginedAdmin
				+ "]";
	}

}
