package Kabina.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import Kabina.Model.Booking;
import Kabina.Model.Bookingtemp;


public interface BookingService {
	List<Booking> findAll();
	
	List<Booking> findUserBookingHistory(long userId);
	
	Map<String, Object> getShelfDetailFromFloor(int floor);
	
	Map<Integer, Object> getShelfDetailAllFloor();

	Map<Long, Object> getShelfDetailFromUnit(int unitId);

	Map<String, Object> getMapShelfFromFloor(int floor);

	Map<Long, Object> getMapShelfFromUnit(int unitId);

	Map<Long, Object> getMapShelfAdmin();
	
	long findMaxId();
	
	void insertNewBooking(long bookingId, long userId, String shelfId, String startDate, String endDate);

	Map<String, Object> findReportData();
	
	Booking addNewBooking(Booking booking);

	Booking updateEditBooking(Booking booking);
	
}
