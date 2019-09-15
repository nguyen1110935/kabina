package Kabina.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Unit")
public class Unit {
	@Id
	@GeneratedValue(generator = "unitId", strategy = GenerationType.IDENTITY)
	private long unitId;
	
	@Column(name = "unitName")
	private String unitName;
	
	@Column(name = "leaderId")
	private long leaderId;
	
	public long getUnitId() {
		return unitId;
	}

	public void setUnitId(long unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public long getLeaderId() {
		return leaderId;
	}

	public void setLeaderId(long leaderId) {
		this.leaderId = leaderId;
	}
}
