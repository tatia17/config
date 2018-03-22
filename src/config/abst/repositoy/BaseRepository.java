package config.abst.repositoy;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface BaseRepository {

	public void save(final Object entity);

	public void remove(final Object entity);

	public void update(final Object entity);

	public void refresh(final Object entity);

	public Object getById(final Class<?> cls, Integer id);

	public Object getByQuery(final String query, final Object... parameters);

	public Object getByQuery(final String query, final Map<String, ?> parameters);

	public Object flush(final Object entity);

	public List<?> findAll(final Class<?> cls);

	public List<?> findAllWithOrder(final Class<?> cls, final String orderBy);

	public List<?> findRangeWithOrder(final Class<?> cls, final String orderBy, final int start, final int limit);

	public List<?> findRange(final Class<?> cls, int start, int limit);

	public List<?> findByQuery(final String query, final Map<String, ?> parameters);

	public List<?> findByQuery(final String query, final Object... parameters);

	public List<?> findRangeByQuery(final String query, final int start, final int limit, final Object... parameters);

	public long countAll(final Class<?> cls);

	public long countByQuery(final String query);

	public long countByQuery(final String query, final Object... parameters);

	public Object max(Class<?> cls, String attribute);

	public BigDecimal sum(final String attribute, final String query, final Object... parameters);

	public Integer removeAll(final Class<?> cls);

	public Integer removeByQuery(final String query, final Object... parameters);

}