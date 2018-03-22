package config.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import config.abst.repositoy.BaseRepositoryJpa;
import config.model.ConfigEntity;


@Repository("configRepository")
@SuppressWarnings("rawtypes")
public class ConfigRepositoryJpa extends BaseRepositoryJpa implements
		ConfigRepository {

	@Override
	@SuppressWarnings("unchecked")
	public List<ConfigEntity> findConfigs() {
		return (List<ConfigEntity>) findByQuery("from ConfigEntity c order by id desc");
	}
	
	

	@Override
	public ConfigEntity getConfig(Integer id) {
		return (ConfigEntity) getByQuery("from ConfigEntity c where c.id=?", id);
	}

}
