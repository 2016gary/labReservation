package org.forten.sample.vo;

public class LoginedAdminVo {
	private int id;
	private String adminName;
	private String password;
	private String roleName;
	private String email;

	public LoginedAdminVo() {
		super();
	}

	public LoginedAdminVo(int id, String adminName, String password) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.password = password;
	}

	public LoginedAdminVo(int id, String adminName, String password, String roleName) {
		super();
		this.id = id;
		this.adminName = adminName;
		this.password = password;
		this.roleName = roleName;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "LoginedAdminVo [id=" + id + ", adminName=" + adminName + ", roleName=" + roleName + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
