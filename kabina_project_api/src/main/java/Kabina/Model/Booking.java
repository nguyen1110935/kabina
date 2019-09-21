package Kabina.Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Booking")
public class Booking {
	@Id
	@GeneratedValue(generator = "bookingId", strategy = GenerationType.IDENTITY)
	private long bookingId;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="shelfId",nullable = false)
	private Shelf shelf;
	
	@Column(name= "startDate",nullable = false)
	private Date startDate;
	
	@Column(name= "endDate",nullable = false)
	private Date endDate;
	
	@Column(name = "expire")
	private long expire;
	
	public Booking() {
		
	}
	
	public Booking(long bookingId, @NotBlank User user, Shelf shelf, Date startDate, Date endDate, long expire) {
		super();
		this.bookingId = bookingId;
		this.user = user;
		this.shelf = shelf;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expire = expire;
	}

	public long getBookingId() {
		return bookingId;
	}

	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public long getExpire() {
		return expire;
	}

	public void setExpire(long expire) {
		this.expire = expire;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", user=" + user + ", shelf=" + shelf + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", expire=" + expire + "]";
	}
	

}
