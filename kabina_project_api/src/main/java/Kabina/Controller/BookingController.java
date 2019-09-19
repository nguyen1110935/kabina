package Kabina.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import Kabina.Model.Booking;
import Kabina.Model.Bookingtemp;
import Kabina.Model.Shelf;
import Kabina.Repository.BookingRepository;
import Kabina.Repository.BookingtempRepository;
import Kabina.Service.BookingService;
import Kabina.Service.BookingtempService;
import Kabina.Service.ShelfService;

@RestController
@RequestMapping(value = "/control")
public class BookingController {
	
	@Autowired
	ShelfService shelfService;
	
	@Autowired
	BookingtempService bookingtempService;
	
	@Autowired
	BookingService bookingService;
	
	@Autowired
	BookingtempRepository bookingtempRepository;
	
	
	
	@RequestMapping(value = "/findFreeShelfOfThisWeek", method = RequestMethod.GET)
	public List<Shelf> findAllFreeShelf(){
		return shelfService.findFreeShelfOfThisWeek(9); 
	}
	
	//find shelf used but still available in the range day user input
	@RequestMapping(value = "/findUsedShelfOfUserRange", method = RequestMethod.GET)
	public List<Shelf> findUsedShelfOfUserRange(){
		return shelfService.findUsedShelfOfUserRange("2019-09-19", "2019-09-19", 9) ;
	}
	
	@RequestMapping(value = "/findFullShelfOfThisWeek", method = RequestMethod.GET)
	public List<Shelf> findFullShelfOfThisWeek(){
		return shelfService.findFullShelfOfThisWeek(9) ;
	}
	
	@RequestMapping(value = "/users/booking", method = RequestMethod.GET)
	public Map<String, Object> findShelfAvailableInRange(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("unitId") int unitId){
		System.out.println(startDate+" "+endDate+" "+ unitId);
		return shelfService.findShelfAvailableInRange(startDate, endDate, unitId);
	}
	
	@PostMapping("/users/requesebooking")
	@ResponseStatus(code=HttpStatus.CREATED)
	public int requestBooking(
			@RequestParam(value = "userId") long userId,
			@RequestParam(value = "shelfId") String shelfId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate
			) {
		long bookingId= bookingService.findMaxId()+1;
		return bookingtempService.checkAndInsertNewBooking(bookingId, userId, shelfId, startDate, endDate);
	}
	
	@RequestMapping(value = "/users/BookingUnapprove", method = RequestMethod.GET)
	public List<Bookingtemp> findUserBookingUnapprove(@RequestParam long userId){
		return bookingtempService.findByUserId(userId);
	}
	
	@RequestMapping(value = "/users/BookingHistory", method = RequestMethod.GET)
	public List<Booking> findUserBookingHistory(@RequestParam long userId){
		return bookingService.findUserBookingHistory(userId);
	}
	
//	@RequestMapping(value = "/users/test", method = RequestMethod.GET)
//	public Long findShelfAvailableInRange(){
//		return BookingRepository.findMaxId();
//	}
	
	@RequestMapping(value = "/admin/booking", method = RequestMethod.GET)
	public Map<String, Object> findShelfAvailableAdmin(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		
		return shelfService.findShelfAvailableAdmin(startDate, endDate);
	}
	
	@PostMapping("/admin/requesebooking")
	@ResponseStatus(code=HttpStatus.CREATED)
	public int requestBookingAdmin(
			@RequestParam(value = "userId") long userId,
			@RequestParam(value = "shelfId") String shelfId,
			@RequestParam(value = "startDate") String startDate,
			@RequestParam(value = "endDate") String endDate
			) {
		long bookingId= bookingService.findMaxId()+1;
		return bookingtempService.insertNewBooking(bookingId, userId, shelfId, startDate, endDate);
	}
	
}
