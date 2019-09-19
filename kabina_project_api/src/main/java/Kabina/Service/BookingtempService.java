package Kabina.Service;

import java.util.List;

import Kabina.Model.Bookingtemp;

public interface BookingtempService {
	List<Bookingtemp> findAll();
	int insertNewBooking(long bookingId, long userId, String shelfId, String startDate, String endDate);
	
	int checkAndInsertNewBooking(long bookingId, long userId, String shelfId, String startDate, String endDate);
	
	List<Bookingtemp> findByUserId(long userId);


}
