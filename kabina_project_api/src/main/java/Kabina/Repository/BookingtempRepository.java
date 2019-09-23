package Kabina.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import Kabina.Model.Booking;
import Kabina.Model.Bookingtemp;

@Repository
public interface BookingtempRepository extends CrudRepository<Bookingtemp, Long>, JpaRepository<Bookingtemp, Long> {
	List<Bookingtemp> findAll();
	
	@Query(
			value = "Select * From Bookingtemp where userId=?1",
			nativeQuery = true
		)
	List<Bookingtemp> findByUserId(long userId);
	
	//get NEW booking unapprove 
	@Query(
			value = "Select * From Bookingtemp where BookListId not IN (SELECT BookListId FROM booklist)",
			nativeQuery = true
		)
	List<Bookingtemp> findAllNewBookingUnapprove();
	
	//get EDIT  unapprove 
	@Query(
			value = "Select * From Bookingtemp where BookListId IN (SELECT BookListId FROM booklist)",
			nativeQuery = true
		)
	List<Bookingtemp> findAllEditUnapprove();
	
	@Query(
			value = "REPLACE into BookingTemp (bookingId, UserId, ShelfId, StartDate, EndDate, Expire) value (?1,?2,?3,?4,?5,false)",
					nativeQuery = true
			)
	
	Integer replaceBookingtemp(int bookingId, Long userId, String shelfId, String startDate, String endDate);
	
	void deleteById(Long id);
	
	// Nguyen
	@Query(
			value = "Select * From BookingTemp where BookingId not IN (SELECT BookingId FROM booking)",
					nativeQuery = true
			)
	List<Bookingtemp> getNewBooking();
	
	@Query(
			value = "Select * From BookingTemp where BookingId IN (SELECT BookingId FROM booking)",
					nativeQuery = true
			)
	List<Bookingtemp> getUpdateBooking();
}
