package Kabina.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Kabina.Model.Shelf;
import Kabina.Service.ShelfService;

@RestController
@RequestMapping(value = "/control")
public class BookingController {
	
	@Autowired
	ShelfService shelfService;
	
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
}
