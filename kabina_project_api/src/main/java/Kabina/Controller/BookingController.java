package Kabina.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@RequestMapping(value = "/bookingtemp/{str}", method = RequestMethod.GET)
	public List<Bookingtemp> getAllBookingTemp(@PathVariable String str) {
		if (str.equals("new")) {
			return bookingtempRepository.getNewBooking();
		}
		else { 
			if (str.equals("edit")){
				return bookingtempRepository.getUpdateBooking();
			}
			else {
				return new ArrayList<>();
			}
		}
	}
	
	@RequestMapping(value = "/reject/{id}", method = RequestMethod.DELETE)
	public void rejectBooking(@PathVariable Long id) {
		bookingtempService.deleteById(id);
		System.out.println("The booking including id= "+ id+ " is deleted");
	}
	
	@RequestMapping(value = "/approve/{id}/{isEdit}", method = RequestMethod.POST)
	public void approve(@PathVariable Long id, boolean isEdit ) {
		Optional<Bookingtemp> bookingtemp = bookingtempService.findById(id);
		
		System.out.println("The booking including id= "+ id+ " is detected.");
		Bookingtemp bokiTemp = bookingtemp.get();
		Booking booking = new Booking(bokiTemp.getBookingId(), bokiTemp.getUser(), bokiTemp.getShelf(),
				bokiTemp.getStartDate(), bokiTemp.getEndDate(), bokiTemp.getExpire());
		System.out.println(booking.toString());
		if (isEdit) {
			bookingService.updateEditBooking(booking);
		}
		else {
			bookingService.addNewBooking(booking);
		}
		bookingtempService.deleteById(id);
	}
	
	@RequestMapping(value = "/users/BookingEdit", method = RequestMethod.GET)
	public List<Booking> findUserBookingEdit(@RequestParam long userId){
		return bookingService.findUserBookingEdit(userId);
	}
	
	@RequestMapping(value = "/admin/booking", method = RequestMethod.GET)
	public Map<String, Object> findShelfAvailableAdmin(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate){
		
		return shelfService.findShelfAvailableAdmin(startDate, endDate);
	}
	

}
