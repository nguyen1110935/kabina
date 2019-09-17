package Kabina.Service;

import java.util.List;

import Kabina.Model.Booking;

public interface BookingService {
	List<Booking> findAll();
	
	List<Booking> findAllBookingFromFloor(int floor);
}
