package Kabina.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Kabina.Model.Booking;
import Kabina.Service.BookingService;

@RestController
@RequestMapping(value = "/update")
public class UpdateController {

	@Autowired
	BookingService bookingService;
	
	@RequestMapping(value = "/findAllBookingEdit", method = RequestMethod.GET)
	public List<Booking> findAllBookingHistory(){
		return bookingService.findAllBookingEdit();
	}
	
	@RequestMapping(value = "/deleteBookingEdit", method = RequestMethod.POST)
	public int deleteBookingEdit(@RequestParam(value = "bookingId") long bookingId){
		System.out.println(bookingId);
		 return bookingService.deleteBookingByBookingId(bookingId) + bookingService.updateEndDateAndExpire(bookingId);
	}
	
	@RequestMapping(value = "/getBookingByShelfId", method = RequestMethod.POST)
	public Map<String, String[]> getBookingByShelfId(@RequestParam(value = "shelfId") String shelfId){
		System.out.println(shelfId);
		 return bookingService.getBookingByShelfId(shelfId);
	}
	
	
	@RequestMapping(value = "/insertBookingTemp", method = RequestMethod.POST)
	public int insertBookingTemp(
			@RequestParam("bookingId") String bookingId,
			@RequestParam("userId") String userId,
			@RequestParam("shelfId") String shelfId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate){
		System.out.println(userId);
		System.out.println(shelfId);
		System.out.println(startDate);
		System.out.println(endDate);
		 return bookingService.insertBookingTemp(bookingId,userId,shelfId,startDate,endDate);
	}
	
	
	@RequestMapping(value = "/updateBooking", method = RequestMethod.POST)
	public int updateBooking(
			@RequestParam("bookingId") String bookingId,
			@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate){
		System.out.println(bookingId);
		System.out.println(startDate);
		System.out.println(endDate);
		
		 return bookingService.updateBooking(bookingId,startDate,endDate);
	}
	
	@RequestMapping(value = "/getBookingByUserId", method = RequestMethod.POST)
	public Map<Long, String[]> getBookingByUserId(
			@RequestParam(value = "userId") Long userId,
			@RequestParam(value = "bookingId") String bookingId
			){
		System.out.println(userId);
		 return bookingService.getBookingByUserId(userId,bookingId);
	}
	

	
}
