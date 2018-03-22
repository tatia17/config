package config.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import config.abst.repositoy.MainEntityUtil;
import config.exception.ObjectNotFoundException;
import config.model.ConfigEntity;
import config.model.DateUtil;
import config.repository.ConfigRepository;


@Service("configService")
@SuppressWarnings("rawtypes")
public class ConfigService {
	@Autowired
	private ConfigRepository configRepository;

	@Transactional(readOnly = true)
	public List<ConfigEntity> findConfigs() {
		return configRepository.findConfigs();
	}

	@Transactional(readOnly = true)
	public ConfigEntity getConfigEntity(Integer id) {
		return (ConfigEntity) configRepository.getConfig(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Integer saveConfig(ConfigEntity config) {
		System.out.println(config.getConfName());
		config.setCreateDate(DateUtil.currentTime());
		return MainEntityUtil.saveOrUpdateEntity(configRepository, config);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updateConfig(ConfigEntity config) {


		ConfigEntity original = (ConfigEntity) configRepository.getById(ConfigEntity.class, config.getId());
		original.setConfName(config.getConfName());
		original.setActive(config.isActive());
		
		original.setModifyDate(DateUtil.currentTime());
		configRepository.update(original);

	}
	

	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void activeConfig(Integer confId) {

	
		ConfigEntity original = getConfigEntity(confId);
		if (original == null) {
			throw new ObjectNotFoundException(confId);
		}

			original.setActive(true);
			
			MainEntityUtil.saveOrUpdateEntity(configRepository, original);
		

	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeConfig(Integer id) {

		ConfigEntity original=(ConfigEntity) configRepository.getById(ConfigEntity.class, id);
		MainEntityUtil.remove(configRepository, original);

	}
}
