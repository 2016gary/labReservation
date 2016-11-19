package org.forten.sample.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdbcDao")
public class JDBCDao implements InitializingBean {
	private DataSource dataSource;
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public <T> T findSingleObjectBy(String sql, Map<String, Object> params, RowMapper<T> mapper) {
		try {
			return jdbcTemplate.queryForObject(sql, params, mapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public <T> List<T> findBy(String sql, Map<String, Object> params, RowMapper<T> mapper) {
		return jdbcTemplate.query(sql, params, mapper);
	}

	public int update(String sql, Map<String, Object> params) {
		return jdbcTemplate.update(sql, params);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (dataSource == null) {
			throw new BeanCreationException("数据源未被注入到" + JDBCDao.class.getName());
		}
		if (jdbcTemplate == null) {
			throw new BeanCreationException("JDBC模板被注入到" + JDBCDao.class.getName());
		}
	}
}
