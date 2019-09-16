package Kabina.Model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "floor_unit")
public class Floor_Unit {
	@Id
	private long id;
	
	
	@ManyToOne
	@MapsId("floorId")
	@JoinColumn(name = "floorId")
	private Floor floor;
	
	@ManyToOne
	@MapsId("unitId")
	@JoinColumn(name = "unitId")
	private Unit unit;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public Floor_Unit(long id, Floor floor, Unit unit) {
		super();
		this.id = id;
		this.floor = floor;
		this.unit = unit;
	}

	public Floor_Unit() {
		
	}
	
	
	
}
