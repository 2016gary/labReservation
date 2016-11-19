package org.forten.sample.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.forten.sample.dao.JDBCDao;
import org.forten.sample.vo.LoginedAdminVo;
import org.forten.sample.vo.MessageVo;
import org.forten.utils.common.StringUtil;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("loginBo")
public class LoginBo {
	private static final Logger log = Logger.getLogger(LoginBo.class);

	@Resource
	private JDBCDao dao;

	@Transactional(readOnly = true)
	public LoginedAdminVo login(LoginedAdminVo loginedAdminVo) {
		String sql = "SELECT id,admin_name,password,role_name FROM test_admin WHERE admin_name=:n AND password=:p ";
		Map<String, Object> params = new HashMap<>();
		params.put("n", loginedAdminVo.getAdminName());
		params.put("p", loginedAdminVo.getPassword());
		/*
		 * return dao.findSingleObjectBy(sql, params, (rs, rowNum) -> {
		 * 
		 * LoginedAdminVo loginedAdmin = new LoginedAdminVo();
		 * loginedAdmin.setId(rs.getInt("id"));
		 * loginedAdmin.setAdminName(rs.getString("admin_name"));
		 * loginedAdmin.setPassword(rs.getString("password"));
		 * loginedAdmin.setRoleName(rs.getString("role_name")); return
		 * loginedAdmin; });
		 */

		LoginedAdminVo loginedAdmin=dao.findSingleObjectBy(sql, params, new RowMapper<LoginedAdminVo>() {

			@Override
			public LoginedAdminVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				LoginedAdminVo loginedAdmin = new LoginedAdminVo();
				loginedAdmin.setId(rs.getInt("Id"));
				loginedAdmin.setAdminName(rs.getString("admin_name"));
				loginedAdmin.setPassword(rs.getString("password"));
				loginedAdmin.setRoleName(rs.getString("role_name"));
				return loginedAdmin;
			}
		});
		
		if(loginedAdmin==null){
			String sql2 = "SELECT id,name,password,role_name FROM test_student WHERE number=:n AND password=:p ";
			Map<String, Object> params2 = new HashMap<>();
			params2.put("n", loginedAdminVo.getAdminName());
			params2.put("p", loginedAdminVo.getPassword());
			return dao.findSingleObjectBy(sql2, params2, new RowMapper<LoginedAdminVo>() {
				
				@Override
				public LoginedAdminVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					LoginedAdminVo loginedAdmin = new LoginedAdminVo();
					loginedAdmin.setId(rs.getInt("id"));
					loginedAdmin.setAdminName(rs.getString("name"));
					loginedAdmin.setPassword(rs.getString("password"));
					loginedAdmin.setRoleName(rs.getString("role_name"));
					return loginedAdmin;
				}
			});
		}
		return loginedAdmin;
	}

	@Transactional
	public MessageVo doUpdate(LoginedAdminVo vo) {
		if(vo.getRoleName().equals("teacher")){
			try {
				int id = vo.getId();
				String adminName = vo.getAdminName();
				String password = vo.getPassword();
				Map<String, Object> params = new HashMap<>();
				params.put("id", id);
				if (!StringUtil.hasLength(adminName) && !StringUtil.hasLength(password)) {
					return new MessageVo("不能设置为空！");
				}
				if (StringUtil.hasLength(adminName)) {
					String sql1 = "UPDATE test_admin SET admin_name='" + adminName + "' WHERE id=:id ";
					dao.update(sql1, params);
				}
				if (StringUtil.hasLength(password)) {
					String sql2 = "UPDATE test_admin SET password='" + password + "' WHERE id=:id ";
					dao.update(sql2, params);
				}
				if (StringUtil.hasLength(adminName) && StringUtil.hasLength(password)) {
					String sql3 = "UPDATE test_admin SET admin_name='" + adminName + "',password='" + password + "' WHERE id=:id ";
					dao.update(sql3, params);
				}
				return new MessageVo("设置成功！");
			} catch (Exception e) {
				log.error("个人设置时出错！", e);
				if (log.isDebugEnabled()) {
					e.printStackTrace();
				}
				return new MessageVo("设置失败！");
			}
		}
		try {
			int id = vo.getId();
			String email = vo.getEmail();
			String password = vo.getPassword();
			Map<String, Object> params = new HashMap<>();
			params.put("id", id);
			if (!StringUtil.hasLength(email) && !StringUtil.hasLength(password)) {
				return new MessageVo("不能设置为空！");
			}
			if (StringUtil.hasLength(email)) {
				String sql1 = "UPDATE test_student SET email='" + email + "' WHERE id=:id ";
				dao.update(sql1, params);
			}
			if (StringUtil.hasLength(password)) {
				String sql2 = "UPDATE test_student SET password='" + password + "' WHERE id=:id ";
				dao.update(sql2, params);
			}
			if (StringUtil.hasLength(email) && StringUtil.hasLength(password)) {
				String sql3 = "UPDATE test_student SET email='" + email + "',password='" + password + "' WHERE id=:id ";
				dao.update(sql3, params);
			}
			return new MessageVo("设置成功！");
		} catch (Exception e) {
			log.error("个人设置时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("设置失败！");
		}
	}
}
