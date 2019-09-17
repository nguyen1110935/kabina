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
	
	@Query(
			value = "select * from " + 
					"( select * from booking where StartDate BETWEEN (SELECT DATE_ADD(CURDATE(), INTERVAL - WEEKDAY(CURDATE()) DAY)) " + 
					"AND (SELECT DATE_ADD(CURDATE(), INTERVAL + 4 - WEEKDAY(CURDATE()) DAY))) as b left join shelf as s " + 
					"on b.shelfId=s.shelfId " +
					"where s.floor= ?1",
			nativeQuery = true
		)
	List<Booking> findAllBookingFromFloor(int floor);

}
