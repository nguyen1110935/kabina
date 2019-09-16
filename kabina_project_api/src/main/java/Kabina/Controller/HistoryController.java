package Kabina.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Kabina.Model.Booking;
import Kabina.Model.Shelf;
import Kabina.Service.BookingService;

@RestController
@RequestMapping(value = "/history")
public class HistoryController {
	
	@Autowired
	BookingService bookingService;
	
	@RequestMapping(value = "/findAllBookingHistory", method = RequestMethod.GET)
	public List<Booking> findAllBookingHistory(){
		return bookingService.findAllBookingHistory();
	}

}
