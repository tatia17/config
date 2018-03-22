package config.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class MainEntity {

	@Column(name = "CREATE_DATE", columnDefinition= "DATETIME",updatable=false)
	private Date createDate;

	@Column(name = "MODIFY_DATE", columnDefinition= "DATETIME")
	private Date modifyDate;




	public MainEntity() {
	}

	public abstract Integer getId();		
		
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}


}