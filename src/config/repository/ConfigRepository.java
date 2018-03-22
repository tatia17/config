package config.repository;

import java.util.List;

import config.abst.repositoy.BaseRepository;
import config.model.ConfigEntity;



@SuppressWarnings("rawtypes")
public interface ConfigRepository extends BaseRepository{
	
	public List<ConfigEntity> findConfigs() ;

	public ConfigEntity getConfig(Integer id);

}
