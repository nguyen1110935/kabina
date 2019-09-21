package Kabina.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Kabina.Model.Booking;
import Kabina.Model.Bookingtemp;
import Kabina.Model.Shelf;
import Kabina.Service.ShelfService;
import Kabina.Service.impl.BookingServiceImpl;
import Kabina.Service.impl.BookingtempServiceImpl;

@RestController
@RequestMapping(value = "/control")
public class BookingController {
	
	@Autowired
	ShelfService shelfService;
	
	@Autowired
	private BookingtempServiceImpl bookingtempService;
	
	@Autowired
	private BookingServiceImpl bookingService;
	
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
	
	@RequestMapping(value = "/reject/{id}", method = RequestMethod.DELETE)
	public void rejectBooking(@PathVariable Long id) {
		bookingtempService.deleteById(id);
		System.out.println("The booking including id= "+ id+ " is deleted");
	}
	
	@RequestMapping(value = "/approve/{id}", method = RequestMethod.POST)
	public void approve(@PathVariable Long id) {
		Optional<Bookingtemp> bookingtemp = bookingtempService.findById(id);
		System.out.println("The booking including id= "+ id+ " is detected.");
		Bookingtemp bokiTemp = bookingtemp.get();
		Booking booking = new Booking(bokiTemp.getBookingId(), bokiTemp.getUser(), bokiTemp.getShelf(),
				bokiTemp.getStartDate(), bokiTemp.getEndDate(), bokiTemp.getExpire());
		System.out.println(booking.toString());
		bookingService.addNewBooking(booking);
		bookingtempService.deleteById(id);
	}
}
