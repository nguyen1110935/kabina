package Kabina.Service;

import java.util.List;
import java.util.Map;

import Kabina.Model.Booking;

public interface BookingService {
	List<Booking> findAll();
	
	Map<String, Object> getShelfDetailFromFloor(int floor);
	
	Map<Integer, Object> getShelfDetailAllFloor();

	Map<Long, Object> getShelfDetailFromUnit(int unitId);
	
	Booking addNewBooking(Booking booking);

}
