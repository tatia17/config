package config.abst.repositoy;

import config.model.DateUtil;
import config.model.MainEntity;

public class MainEntityUtil {

	public static Integer saveOrUpdateEntity(BaseRepository baseRepository,MainEntity mainEntity){

		if (mainEntity.getId() == null){
			mainEntity.setCreateDate(DateUtil.currentTime());
			baseRepository.save(mainEntity);			
		} else {
			mainEntity.setModifyDate(DateUtil.currentTime());
			baseRepository.update(mainEntity);			
		}		
		return mainEntity.getId();
	}

	
	public static void remove(BaseRepository baseRepository,MainEntity mainEntity){
	
		baseRepository.remove(mainEntity);	
	}
	
	
}