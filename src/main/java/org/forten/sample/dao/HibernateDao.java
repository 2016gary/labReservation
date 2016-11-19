package org.forten.sample.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository("hibernateDao")
public class HibernateDao {
	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public <T> void save(T entity) {
		getSession().save(entity);
	}

	public <T> void update(T entity) {
		getSession().update(entity);
	}

	public <T> void delete(T entity) {
		getSession().delete(entity);
	}

	public <T> T findByGet(Class<T> clazz, Serializable id) {
		return getSession().get(clazz, id);
	}

	public <T> T findByLoad(Class<T> clazz, Serializable id) {
		return getSession().load(clazz, id);
	}

	public int executeUpdate(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		// 命名参数的绑定
		for (Entry<String, Object> param : params.entrySet()) {
			String key = param.getKey();
			Object value = param.getValue();
			if (value.getClass().isArray()) {
				query.setParameterList(key, (Object[]) value);
			} else if (value instanceof Collection) {
				query.setParameterList(key, (Collection<?>) value);
			} else {
				query.setParameter(key, value);
			}
		}

		return query.executeUpdate();
	}

	public <T> int delete(Class<T> entityClass, Serializable id) {
		String hql = "DELETE FROM " + entityClass.getSimpleName() + " WHERE id=:id";
		Query query = getSession().createQuery(hql);
		query.setParameter("id", id);
		return query.executeUpdate();
	}

	public int executeUpdate(String hql) {
		Query query = getSession().createQuery(hql);
		return query.executeUpdate();
	}

	public <T> List<T> findBy(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		// 命名参数的绑定
		for (Entry<String, Object> param : params.entrySet()) {
			String key = param.getKey();
			Object value = param.getValue();
			if (value.getClass().isArray()) {
				query.setParameterList(key, (Object[]) value);
			} else if (value instanceof Collection) {
				query.setParameterList(key, (Collection<?>) value);
			} else {
				query.setParameter(key, value);
			}
		}

		List<T> list = query.list();
		return list;
	}

	public <T> T findObjectBy(String hql, Map<String, Object> params) {
		Query query = getSession().createQuery(hql);
		// 命名参数的绑定
		for (Entry<String, Object> param : params.entrySet()) {
			String key = param.getKey();
			Object value = param.getValue();
			if (value.getClass().isArray()) {
				query.setParameterList(key, (Object[]) value);
			} else if (value instanceof Collection) {
				query.setParameterList(key, (Collection<?>) value);
			} else {
				query.setParameter(key, value);
			}
		}

		return (T) query.uniqueResult();
	}

	public <T> List<T> findBy(String hql) {
		Query query = getSession().createQuery(hql);
		query.setCacheable(true);
		return query.list();
	}

	public <T> T findObjectBy(String hql) {
		Query query = getSession().createQuery(hql);
		return (T) query.uniqueResult();
	}

	public <T> List<T> findBy(String hql, Map<String, Object> params, int first, int max) {
		Query query = getSession().createQuery(hql);
		// 命名参数的绑定
		for (Entry<String, Object> param : params.entrySet()) {
			String key = param.getKey();
			Object value = param.getValue();
			if (value.getClass().isArray()) {
				query.setParameterList(key, (Object[]) value);
			} else if (value instanceof Collection) {
				query.setParameterList(key, (Collection<?>) value);
			} else {
				query.setParameter(key, value);
			}
		}

		query.setFirstResult(first);
		query.setMaxResults(max);

		return query.list();
	}

	public <T> List<T> findBy(String hql, int first, int max) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(max);

		return query.list();
	}
}
