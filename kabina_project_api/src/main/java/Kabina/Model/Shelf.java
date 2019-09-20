package Kabina.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "Shelf")
public class Shelf {
	@Id
	private Long shelfId;
	
	@ManyToOne
	@JoinColumn(name="floor")
	private Floor floor;

	
	public String getShelfId() {
		return Long.toString(shelfId);
	}
	public void setShelfId(Long shelfId) {
		this.shelfId = shelfId;
	}
	public Floor getFloor() {
		return floor;
	}
	public void setFloor(Floor floor) {
		this.floor = floor;
	}
	public Shelf() {
		
	}
	public Shelf(Long shelfId, Floor floor) {
		super();
		this.shelfId = shelfId;
		this.floor = floor;
	}
	

	

}
