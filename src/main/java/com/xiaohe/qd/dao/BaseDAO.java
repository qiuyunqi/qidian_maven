package com.xiaohe.qd.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@SuppressWarnings("all")
@Repository
public class BaseDAO<T, ID extends Serializable> {
	private static Log log = LogFactory.getLog(BaseDAO.class);

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private HibernateTemplate hibernateTemplate;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Class<T> entityClass;

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public BaseDAO() {
		this.entityClass = (Class<T>) getParameterClass(this.getClass());
	}

	public Class<?> getParameterClass(Class<?> clazz) {
		Class<?> returnClazz = getParameterClazz(clazz);
		if (returnClazz == null) {
			clazz = clazz.getSuperclass();
			while (clazz != null) {
				returnClazz = getParameterClazz(clazz);
				if (returnClazz != null) {
					return returnClazz;
				}
				clazz = clazz.getSuperclass();
			}
		}
		return returnClazz;
	}

	private Class<?> getParameterClazz(Class<?> clazz) {
		if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
			ParameterizedType type = (ParameterizedType) clazz.getGenericSuperclass();
			return (Class<?>) type.getActualTypeArguments()[0];
		}
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void reload(T entity) {
		getHibernateTemplate().lock(entity, LockMode.NONE);
	}

	public T merge(T entity) {
		return (T) getHibernateTemplate().merge(entity);
	}

	public T get(ID id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	public void save(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().saveOrUpdate(entity);
		log.debug("save or update entity: " + entity);
	}

	public void update(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().update(entity);
		log.debug("update entity: " + entity);
	}

	public void delete(T entity) {
		Assert.notNull(entity);
		getHibernateTemplate().delete(entity);
		log.debug("delete entity: " + entity);
	}

	public void delete(ID id) {
		Assert.notNull(id);
		delete(get(id));
	}

	public int countQueryResult(String hql, List<Object> valueList) {
		Object[] objArray = new Object[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			objArray[i] = valueList.get(i);
		}
		String fromClause = hql.substring(hql.indexOf("from"));
		String countHql = "select count(*) " + fromClause;
		return ((Long) getHibernateTemplate().find(countHql, objArray).get(0)).intValue();
	}

	public int countQueryResult(String hql) {
		String fromClause = hql.substring(hql.indexOf("from"));
		String countHql = "select count(*) " + fromClause;
		return ((Long) getHibernateTemplate().find(countHql).get(0)).intValue();
	}

	public int countSqlQueryResult(String sql, Object... values) {
		String fromClause = sql.substring(sql.indexOf("from"));
		String countSql = "select count(*) " + fromClause;
		return ((Long) getHibernateTemplate().find(countSql, values).get(0)).intValue();
	}

	public void saveOrUpdateAll(final Collection<T> collection) {
		if (collection != null) {
			for (T t : collection) {
				getHibernateTemplate().saveOrUpdate(t);
			}
		}
	}

	public List<T> findListByDetachedCriteria(DetachedCriteria detachedCriteria) {
		if (getHibernateTemplate().findByCriteria(detachedCriteria).size() > 0) {
			return getHibernateTemplate().findByCriteria(detachedCriteria);
		} else {
			return null;
		}
	}

	public List<String> findCollectionByHql(String hql) {
		if (getHibernateTemplate().find(hql).size() > 0) {
			return getHibernateTemplate().find(hql);
		} else {
			return null;
		}
	}

	public List<T> findAll() {
		if (getHibernateTemplate().loadAll(entityClass).size() > 0) {
			return getHibernateTemplate().loadAll(entityClass);
		} else {
			return null;
		}
	}

	public List<T> findAllByHQL(String hql) {
		if (getHibernateTemplate().find(hql).size() > 0) {
			return getHibernateTemplate().find(hql);
		} else {
			return null;
		}
	}

	public List<T> findAllByHQL(String hql, Object value) {
		if (getHibernateTemplate().find(hql, value).size() > 0) {
			return getHibernateTemplate().find(hql, value);
		} else {
			return null;
		}
	}

	public List<T> findAllByHQL(String hql, Object... values) {
		if (getHibernateTemplate().find(hql, values).size() > 0) {
			return getHibernateTemplate().find(hql, values);
		} else {
			return null;
		}
	}

	public List<T> findAllByHQL(String hql, List<Object> valueList) {
		Object[] objArray = new Object[valueList.size()];
		for (int i = 0; i < valueList.size(); i++) {
			objArray[i] = valueList.get(i);
		}
		if (getHibernateTemplate().find(hql, objArray).size() > 0) {
			return getHibernateTemplate().find(hql, objArray);
		} else {
			return null;
		}
	}

	public T findUniqueByHQL(String hql, Object... values) {
		if (getHibernateTemplate().find(hql, values).size() > 0) {
			return (T) getHibernateTemplate().find(hql, values).get(0);
		} else {
			return null;
		}
	}

	public List<T> findListByHQL(String hql, Object... values) {
		if (getHibernateTemplate().find(hql, values).size() > 0) {
			return getHibernateTemplate().find(hql, values);
		} else {
			return null;
		}
	}

	public List<T> findListByHQL(int fromIndex, int size, String hql, List<Object> valueList) {
		Query query = (Query) getSession().createQuery(hql);
		if (valueList != null) {
			for (int i = 0; i < valueList.size(); i++) {
				query.setParameter(i, valueList.get(i));
			}
		}
		query.setFirstResult(fromIndex);
		query.setMaxResults(size);
		return query.list();
		// if (query.list().size() > 0) {
		// return query.list();
		// } else {
		// return null;
		// }
	}

	public List<Object> findParamListByHQL(String hql, Object... values) {
		if (getHibernateTemplate().find(hql, values).size() > 0) {
			return getHibernateTemplate().find(hql, values);
		} else {
			return null;
		}
	}

	public List<Object[]> findParamsListByHQL(String hql, Object... values) {
		if (getHibernateTemplate().find(hql, values).size() > 0) {
			return getHibernateTemplate().find(hql, values);
		} else {
			return null;
		}
	}

	/**
	 * 取得对象的主键名,辅助函数.
	 */
	public String getIdName(Class clazz) {
		Assert.notNull(clazz);
		ClassMetadata meta = getSessionFactory().getClassMetadata(clazz);
		Assert.notNull(meta, "Class " + clazz + " not define in hibernate session factory.");
		String idName = meta.getIdentifierPropertyName();
		Assert.hasText(idName, clazz.getSimpleName() + " has no identifier property define.");
		return idName;
	}

	/**
	 * 通过sql，根据list类型的条件参数 查询对象数组集合
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */

	public List<Object[]> findBySqlGetArray(final String sql, final List paramList) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlquery = session.createSQLQuery(sql);
				if (paramList != null) {
					for (int i = 0; i < paramList.size(); i++) {
						sqlquery.setParameter(i, paramList.get(i));
					}
				}
				return sqlquery.list();
			}
		});
	}

	public List<Object[]> findListBySql(final int fromIndex, final int size, final String sql, final List paramList) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {

			public List<Object[]> doInHibernate(Session session) throws HibernateException, SQLException {
				SQLQuery sqlquery = session.createSQLQuery(sql);
				if (paramList != null) {
					for (int i = 0; i < paramList.size(); i++) {
						sqlquery.setParameter(i, paramList.get(i));
					}
				}
				sqlquery.setFirstResult(fromIndex);
				sqlquery.setMaxResults(size);
				return sqlquery.list();
			}
		});
	}

}
