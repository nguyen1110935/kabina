package Kabina.Controller;

import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Kabina.Model.Booking;
import Kabina.Model.Floor;
import Kabina.Model.Shelf;
import Kabina.Model.Unit;
import Kabina.Model.User;
import Kabina.Repository.BookingRepository;
import Kabina.Repository.FloorRepository;
import Kabina.Repository.UnitRepository;
import Kabina.Repository.UsersRepository;
import Kabina.Service.BookingService;
import Kabina.Service.UnitService;

@RestController
@RequestMapping(value = "/control")

public class Admin_User_Controller {
	@Autowired
	BookingService bookingService;
	
	@Autowired
	UnitRepository unitRepository;
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	FloorRepository floorRepository;
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	UsersRepository usersRepository;
	
	@RequestMapping(value = "/admin/shelvesReport", method = RequestMethod.GET)
	public Map<String, Object> findReportData(){
		return bookingService.findReportData();
	}
	
	@RequestMapping(value = "/findAllBookingDetailFromFloor", method = RequestMethod.GET)
	public Map<Integer, Object> findAllBookingDetailFromFloor(){
		return bookingService.getShelfDetailAllFloor();
	}
	
	@RequestMapping(value = "/findAllUnit", method = RequestMethod.GET)
	public List<Unit> findAllUnit(){
		return unitService.findAll();
	}
	
	@RequestMapping(value = "/findAllBookingDetailFromUnit", method = RequestMethod.GET)
	public Map<Long, Object> findAllBookingDetailFromUnit(@RequestParam int unitId){
		return bookingService.getShelfDetailFromUnit(unitId);
	}
	
	@RequestMapping(value = "/getMapShelfFromUnit", method = RequestMethod.GET)
	public Map<Long, Object> getMapShelfFromUnit(@RequestParam int unitId){
		return bookingService.getMapShelfFromUnit(unitId);
	}
	@RequestMapping(value = "/getMapShelfAdmin", method = RequestMethod.GET)
	public Map<Long, Object> getMapShelfAdmin(){
		return bookingService.getMapShelfAdmin();
	}
	@RequestMapping(value = "/unitne", method = RequestMethod.GET)
	public Unit unit(@RequestParam int unitId){
		return unitRepository.findByUnitId(unitId);
	}
	
	@RequestMapping(value = "/floorne", method = RequestMethod.GET)
	public List<Floor> floor(){
		return floorRepository.findAll();
	}
	
	@RequestMapping(value = "/historyne", method = RequestMethod.GET)
	public List<Booking> history(){
		int userId=1;
		return bookingRepository.findUserBookingHistory(1);
	}
	
	@RequestMapping(value = "/testUser", method = RequestMethod.GET)
	public User user(){
		long userId=1;
		return usersRepository.findByUserId(userId);
	}
	
}
