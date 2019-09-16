package Kabina.Model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "floor")
public class Floor {
	@Id
	private long floor;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "floor")
	Set<Unit> unit;
	
	public Set<Unit> getUnit() {
		return unit;
	}

	public void setUnit(Set<Unit> unit) {
		this.unit = unit;
	}

	public long getFloor() {
		return floor;
	}

	public void setFloor(long floor) {
		this.floor = floor;
	}
	
	

	public Floor() {
		
	}
	public Floor(long floor) {
		super();
		this.floor = floor;
	}
	
	
	
	

}
