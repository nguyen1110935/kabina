package Kabina.Repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Booking;
import Kabina.Model.Bookingtemp;

@Repository
public interface BookingRepository extends CrudRepository<Booking, String>, JpaRepository<Booking, String> {
	List<Booking> findAll();
	
	//get booking in booking table if the booking still unexpired
	@Query(
			value = "select * from " + 
					"( select * from booking where StartDate BETWEEN (SELECT DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)) " + 
					"AND (SELECT DATE_ADD(CURDATE(), INTERVAL + 4 - WEEKDAY(CURDATE()) DAY))) as b left join shelf as s " + 
					"on b.shelfId=s.shelfId " +
					"where s.floor= ?1",
			nativeQuery = true
		)
	List<Booking> findAllBookingFromFloor(int floor);
	
	//get booking in booking table with the enddate before today, default it's history
	@Query(
			value = "Select* From Booking where (UserId= ?1 and EndDate <= CURDATE()) ORDER BY StartDate DESC ",
			nativeQuery = true
		)
	List<Booking> findUserBookingHistory(long userId);

	//get booking history of the system, use in admin screen
	@Query(
			value = "Select* From Booking where EndDate <= CURDATE() ORDER BY StartDate DESC",
			nativeQuery = true
		)
	List<Booking> findAllBookingHistory(long userId);
	
	//get current max id from table booking and booking temp
	@Query(
		value = "select if(t.maxT>b.maxB, t.maxT,b.maxB) as id " + 
				"from " + 
				"(select IF((select COUNT(*) FROM bookingtemp)>0 ,Max(BookingId),0) as maxT from bookingtemp) as t, " + 
				"(select max(bookingId) as maxB from booking) as b",
				nativeQuery = true
		)
	long findMaxId();
	
	//Check user book in new range or not, for user cannot book 2 shelf in 1 day, return number booking user booking range input
	@Query(
			value = "select Count(*) from (select * from booking UNION select * from bookingtemp) as b where b.USERID=?1 " + 
					"and (b.StartDate between ?2 and ?3 or b.EndDate between ?2 and ?3 or ?2 between b.startdate and b.enddate or ?3 between b.startdate and b.enddate)",
					nativeQuery = true
			)
	int  checkUserBook(Long userId, String startDate, String endDate);

	@Query(
			value = "UPDATE booking SET  startDate = ?2,  endDate = ?3 WHERE bookingId = ?1;",
			nativeQuery = true
		)
	Booking updateEditBooking(long id, Date startDate, Date endDate, long expire);
	
	
	

}
