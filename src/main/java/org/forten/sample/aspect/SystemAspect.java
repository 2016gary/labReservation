package org.forten.sample.aspect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.taglibs.standard.tei.ForEachTEI;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.forten.sample.dao.JDBCDao;
import org.forten.sample.vo.StudentLaboratoryRelationVo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SystemAspect {
	@Resource
	private JDBCDao jdbcDao;

	@Before("@annotation(org.forten.sample.aspect.SwitchStatus)")
	public void before() {
		String sql = "SELECT id FROM test_laboratory ";
		Map<String, Object> params = new HashMap<>();
		List<Integer> laboratoryIdList = jdbcDao.findBy(sql, params, new RowMapper<Integer>() {

			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int laboratoryId = rs.getInt("id");
				return laboratoryId;
			}
		});
		for (Integer id : laboratoryIdList) {
			String sql1 = "SELECT count(id) FROM test_student_laboratory_relation WHERE test_student_laboratory_relation.laboratory_id=:laboratoryId";
			Map<String, Object> params2 = new HashMap<>();
			params2.put("laboratoryId", id);
			int count = jdbcDao.findSingleObjectBy(sql1, params2, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					int count = rs.getInt("count(id)");
					return count;
				}
			});
			String sqlMaxNumber = "SELECT test_laboratory.max_number FROM test_laboratory WHERE id=:laboratoryId";
			int maxNumber = jdbcDao.findSingleObjectBy(sqlMaxNumber, params2, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					int maxNumber = rs.getInt("max_number");
					return maxNumber;
				}
			});
			String sqlMinNumber = "SELECT test_laboratory.min_number FROM test_laboratory WHERE id=:laboratoryId";
			int minNumber = jdbcDao.findSingleObjectBy(sqlMinNumber, params2, new RowMapper<Integer>() {

				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
					int minNumber = rs.getInt("min_number");
					return minNumber;
				}
			});
			if (!(count < minNumber)) {
				String sql2 = "CREATE TABLE test_demo (SELECT id FROM test_student_laboratory_relation WHERE laboratory_id=:laboratoryId ORDER BY ordered_time LIMIT 0,:maxNumber )";
				params2.put("maxNumber", maxNumber);
				jdbcDao.update(sql2, params2);

				String sql3 = "UPDATE test_student_laboratory_relation SET test_student_laboratory_relation.laboratoryOrderStatus=2 WHERE id IN (SELECT id FROM test_demo)";
				Map<String, Object> params3 = new HashMap<>();
				jdbcDao.update(sql3, params3);
				String sql4 = "UPDATE test_student_laboratory_relation SET test_student_laboratory_relation.laboratoryOrderStatus=1 WHERE id NOT IN (SELECT id FROM test_demo)";
				Map<String, Object> params4 = new HashMap<>();
				jdbcDao.update(sql4, params4);

				String sql5 = "DROP TABLE test_demo";
				Map<String, Object> params5 = new HashMap<>();
				jdbcDao.update(sql5, params5);
			}else{
				String sql6 = "UPDATE test_student_laboratory_relation SET test_student_laboratory_relation.laboratoryOrderStatus=0 WHERE laboratory_id =:id";
				Map<String, Object> params6 = new HashMap<>();
				params6.put("id", id);
				jdbcDao.update(sql6, params6);
			}
		}
	}
}
