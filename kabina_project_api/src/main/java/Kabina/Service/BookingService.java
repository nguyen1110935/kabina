package Kabina.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import Kabina.Model.Booking;
import Kabina.Model.Bookingtemp;
import Kabina.Model.Shelf;
import Kabina.Model.User;

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
}
