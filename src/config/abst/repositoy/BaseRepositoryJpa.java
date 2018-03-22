package config.abst.repositoy;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import config.model.DateUtil;



@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class BaseRepositoryJpa implements BaseRepository {

	@PersistenceContext
    protected EntityManager entityManager;

	public BaseRepositoryJpa() {
	}


	

	
	public final String toSQLServerDate(String dateString) {
		return DateUtil.dateToString(DateUtil.stringToDate(dateString),"yyyy-MM-dd");
	}

	public final String toSQLServerDateCurrent() {
		return DateUtil.dateToString(DateUtil.currentDate(),"yyyy-MM-dd");
	}

	public final String toSQLServerMonthCurrent() {
		return DateUtil.dateToString(DateUtil.getMonthFirstDate(DateUtil.currentDate()),"yyyy-MM-dd");
	}
	
	public final void save(final Object entity) {
		entityManager.persist(entity);
	}

	public final void remove(final Object entity) {
		entityManager.remove(entity);
	}

	public final void update(final Object entity) {
		try{
			
			entityManager.merge(entity);
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
	}

	public final void refresh(final Object entity) {
		entityManager.refresh(entity);
	}

	public final Object getById(final Class cls, Integer id) {
		return entityManager.find(cls, id);
	}

	public final Object getByQuery(final String query,final Object... parameters) {

		Query q = entityManager.createQuery(query);
		setParameters(q, parameters);
		List list = q.getResultList();
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	public final Object getByQueryCached(final String query,final Object... parameters) {

		Query q = entityManager.createQuery(query);
		setParameters(q, parameters);
		q.setHint("org.hibernate.cacheable", Boolean.TRUE);
		List list = q.getResultList();
		return list != null && list.size() > 0 ? list.get(0) : null;
	}

	
	public final Object getByQuery(final String query,final Map<String,?> parameters) {

		Query q = entityManager.createQuery(query);
		setNamedParameters(q,parameters);
		List list = q.getResultList();
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
	
	public final Object flush(final Object entity) {
		entityManager.flush();
		return entity;
	}

	public final List<?> findAll(final Class cls) {
		Query q = entityManager.createQuery("SELECT h FROM " + cls.getName() + " h");
		return q.getResultList();
	}

	public final List<?> findAllWithOrder(final Class cls,final String orderBy) {
		Query q;
		if (orderBy != null && !orderBy.equals("")) {
			q = entityManager.createQuery("SELECT h FROM " + cls.getName() + " h" + " order by h." + orderBy);
		} else {
			q = entityManager.createQuery("SELECT h FROM " + cls.getName());
		}
		return q.getResultList();
	}

	public final List<?> findRangeWithOrder(final Class cls,final String orderBy,final int start,final int limit) {

		Query q = entityManager.createQuery("SELECT h FROM " + cls.getName() + " h" + " order by h." + orderBy);
		q.setFirstResult(start);
		q.setMaxResults(limit);
		return q.getResultList();
	}

	public final List<?> findRange(final Class cls, int start, int limit) {

		Query q = entityManager.createQuery("SELECT h FROM " + cls.getName() + " h");
		q.setFirstResult(start);
		q.setMaxResults(limit);
		return q.getResultList();
	}

	public final List<?> findByQuery(final String query,final Map<String, ?> parameters) {

		Query q = entityManager.createQuery(query);
		setNamedParameters(q,parameters);
		return q.getResultList();
	}

	public final List<?> findByQuery(final String query,final Object... parameters) {

		Query q = entityManager.createQuery(query);
		setParameters(q, parameters);
		return q.getResultList();
	}

	public final List<?> findRangeByQuery(final String query, final int start,final int limit,final Object... parameters) {

		Query q = entityManager.createQuery(query);
		q.setFirstResult(start);
		q.setMaxResults(limit);
		setParameters(q, parameters);
		return q.getResultList();
	}

	public final long countAll(final Class cls) {

		Query q = entityManager.createQuery("SELECT count(*) FROM " + cls.getName() + " h");
		return Long.valueOf(q.getSingleResult().toString());
	}

	public final long countByQuery(final String query) {

		Query q = entityManager.createQuery("SELECT count(*) " + query);
		return Long.valueOf(q.getSingleResult().toString());
	}

	public final long countByQuery(final String query,final Object... parameters) {

		Query q = entityManager.createQuery("SELECT count(*) " + query);
		setParameters(q, parameters);
		return Long.valueOf(q.getSingleResult().toString());
	}

	public final Object max(Class cls, String attribute) {

		Query q = entityManager.createQuery("SELECT max(h." + attribute + ") FROM " + cls.getName() + " h");
		return q.getSingleResult();
	}

	public final BigDecimal sum(final String attribute,final String query,final Object... parameters) {
		
		Query q = entityManager.createQuery("SELECT SUM(" + attribute + ") " + query); 
		setParameters(q, parameters);
		return new BigDecimal(q.getSingleResult().toString());
	}

	public final Integer removeAll(final Class cls) {

		Query q = entityManager.createQuery("DELETE FROM " + cls.getName() + " h");
		return q.executeUpdate();
	}

	public final Integer removeByQuery(String query,final Object... parameters) {

		if (query.indexOf("delete") == -1) {
			query = "delete " + query;
		}

		Query q = entityManager.createQuery(query);
		setParameters(q, parameters);
		return q.executeUpdate();
	}

	protected void setParameters(Query q, Object[] values) {
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				q.setParameter(i + 1, values[i]);
			}
		}	
	}
	
	protected void setNamedParameters(Query q, Map<String,?> values) {
		if (values != null) {
			for (Map.Entry<String, ?> entry : values.entrySet()) {
				q.setParameter(entry.getKey(), entry.getValue());
			};
		 }
	}

}