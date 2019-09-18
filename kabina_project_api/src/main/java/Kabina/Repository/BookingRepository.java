package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Booking;

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
	List<Booking> findUserBookingHistory(int userId);

	//get booking history of the system, use in admin screen
	@Query(
			value = "Select* From Booking where EndDate <= CURDATE() ORDER BY StartDate DESC",
			nativeQuery = true
		)
	List<Booking> findAllBookingHistory(int userId);
	
	//approve edit 
	
	

}
