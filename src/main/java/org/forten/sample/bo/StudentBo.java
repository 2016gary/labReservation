package org.forten.sample.bo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.forten.sample.dao.HibernateDao;
import org.forten.sample.dao.JDBCDao;
import org.forten.sample.entity.Student;
import org.forten.sample.entity.StudentLaboratoryRelation;
import org.forten.sample.vo.MessageVo;
import org.forten.sample.vo.StudentLaboratoryRelationVo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("studentBo")
public class StudentBo {
	private static final Logger log = Logger.getLogger(StudentBo.class);

	@Resource
	private HibernateDao dao;
	@Resource
	private JDBCDao jdbcDao;

	@Transactional
	public void register(Student student) {
		try {
			dao.save(student);
		} catch (Exception e) {
			log.error("注册时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
		}
	}

	@Transactional
	public MessageVo doSave(StudentLaboratoryRelation studentLaboratoryRelation) {
		try {
			dao.save(studentLaboratoryRelation);
			return new MessageVo("预约成功！");
		} catch (Exception e) {
			log.error("预约实验室时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("预约失败！");
		}
	}

	@Transactional
	public MessageVo doDelete(int id) {
		try {
			dao.delete(StudentLaboratoryRelation.class, id);
			return new MessageVo("退选成功！");
		} catch (Exception e) {
			log.error("退选预约时出错！", e);
			if (log.isDebugEnabled()) {
				e.printStackTrace();
			}
			return new MessageVo("退选失败！");
		}
	}

	@Transactional(readOnly = true)
	public List<StudentLaboratoryRelationVo> queryAll(Integer adminId) {
		String sql = "SELECT test_student_laboratory_relation.id,test_student_laboratory_relation.laboratory_id,test_student_laboratory_relation.student_id,test_laboratory.laboratory_name,test_laboratory.laboratory_address,test_laboratory.max_number,test_laboratory.min_number,test_laboratory.class_time,test_student_laboratory_relation.ordered_time,(CASE WHEN test_student_laboratory_relation.laboratoryOrderStatus=0 THEN '已预约' WHEN test_student_laboratory_relation.laboratoryOrderStatus=1 THEN '排队中' WHEN test_student_laboratory_relation.laboratoryOrderStatus=2 THEN '预约成功' "
				+ " END)laboratoryOrderStatus FROM test_laboratory JOIN test_student_laboratory_relation ON (test_laboratory.id=test_student_laboratory_relation.laboratory_id) WHERE test_student_laboratory_relation.student_id=:adminId";
		Map<String, Object> params = new HashMap<>();
		params.put("adminId", adminId);
		return jdbcDao.findBy(sql, params, new RowMapper<StudentLaboratoryRelationVo>() {

			@Override
			public StudentLaboratoryRelationVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				StudentLaboratoryRelationVo studentLaboratoryRelationVo = new StudentLaboratoryRelationVo();
				studentLaboratoryRelationVo.setId(rs.getInt("id"));
				studentLaboratoryRelationVo.setLaboratoryId(rs.getInt("laboratory_id"));
				studentLaboratoryRelationVo.setStudentId(rs.getInt("student_id"));
				studentLaboratoryRelationVo.setLaboratoryName(rs.getString("laboratory_name"));
				studentLaboratoryRelationVo.setLaboratoryAddress(rs.getString("laboratory_address"));
				studentLaboratoryRelationVo.setMaxNumber(rs.getInt("max_number"));
				studentLaboratoryRelationVo.setMinNumber(rs.getInt("min_number"));
				studentLaboratoryRelationVo.setClassTime(rs.getString("class_time"));
				studentLaboratoryRelationVo.setOrderedTime(rs.getDate("ordered_time"));
				studentLaboratoryRelationVo.setLaboratoryOrderStatusDesc(rs.getString("laboratoryOrderStatus"));
				return studentLaboratoryRelationVo;
			}
		});
	}
}
