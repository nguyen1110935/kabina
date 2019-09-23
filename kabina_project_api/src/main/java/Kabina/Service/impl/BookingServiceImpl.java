package Kabina.Service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import Kabina.Model.Booking;
import Kabina.Model.Bookingtemp;
import Kabina.Model.Floor;
import Kabina.Model.Shelf;
import Kabina.Model.Unit;
import Kabina.Model.User;
import Kabina.Repository.BookingRepository;
import Kabina.Repository.FloorRepository;
import Kabina.Repository.ShelfRepository;
import Kabina.Repository.UnitRepository;
import Kabina.Repository.UsersRepository;
import Kabina.Service.BookingService;
import Kabina.Service.ShelfService;
import javassist.expr.NewArray;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	ShelfService shelfService;
	@Autowired
	FloorRepository floorRepository;
	@Autowired
	UnitRepository unitRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	ShelfRepository shelfRepository;
	@Override
	public List<Booking> findAll() {
		
		return bookingRepository.findAll();
	}
	
	//return object show detail all shelf of all floor
	@Override
	public Map<Integer, Object> getShelfDetailAllFloor() {
		
		List<Floor> floors=floorRepository.findAll();
		Map<Integer, Object> map= new TreeMap<Integer, Object>();
		for(int i=0; i<floors.size(); i++) {
			int floor=(int) floors.get(i).getFloor();
			map.put(floor, getShelfDetailFromFloor(floor));
		}
		return map;
	}
	
	//return map with key =shelfId and value=[name of user used that shelf]
	public Map<String, Object> getMapShelfDetailFromFloor(int floor){
		Map<String, Object>map =new TreeMap<String, Object>();
		List<Shelf> allFreeShelfFromFloor=shelfService.findFreeShelfAprroved(floor);
		List<Booking> allFromFloor=bookingRepository.findAllBookingFromFloor(floor);
		Map<String, Object>rawData =allDetail(allFromFloor);
		for(int i=0; i<allFreeShelfFromFloor.size(); i++) {
			map.put(allFreeShelfFromFloor.get(i).getShelfId(), new String[5]);
		}
		for(String key: rawData.keySet()) {
			map.put(key, rawData.get(key));
		}
		return map;
		
	}
	
	//Return status of all shelf, 0=free, 1=used but not full, 2=full
	public Map<String, Integer> getShelfStatusFromFloor(int floor){
		Map<String, Integer>map =new TreeMap<String, Integer>();
		List<Shelf> allFreeShelfFromFloor=shelfService.findFreeShelfAprroved(floor);
		List<Booking> allFromFloor=bookingRepository.findAllBookingFromFloor(floor);
		Map<String, Object>rawData =allDetail(allFromFloor);
		for(int i=0; i<allFreeShelfFromFloor.size(); i++) {
			map.put(allFreeShelfFromFloor.get(i).getShelfId(), 0);
		}
		for(String key: rawData.keySet()) {
			String[] strings=(String[]) rawData.get(key);
			int status= checkShelfFull(strings)? 2:1;
			map.put(key, status);
		}
		return map;
	}
	
	//Return object show detail and status of all shelf
	@Override
	public Map<String, Object> getMapShelfFromFloor(int floor){
		Map<String, Object>map =new TreeMap<String, Object>();
		Map<String, Object>detailMap=getMapShelfDetailFromFloor(floor);
		Map<String, Integer> statusMap=getShelfStatusFromFloor(floor);
		map.put("Detail", detailMap);
		map.put("Status", statusMap);
		return map;
	}
	
	
	//return object show detail all shelf in that floor
	@Override
	public Map<String, Object> getShelfDetailFromFloor(int floor){
		Map<String, Object>map =new TreeMap<String, Object>();
		List<Shelf> allFreeShelfFromFloor=shelfService.findFreeShelfAprroved(floor);
		List<String> free= new ArrayList<String>();
		for(int i=0; i<allFreeShelfFromFloor.size(); i++) {
			free.add(allFreeShelfFromFloor.get(i).getShelfId());
		}
		List<Booking> allFromFloor=bookingRepository.findAllBookingFromFloor(floor);
		Map<String, Object>rawData =allDetail(allFromFloor);;
		
		Map<String, Object> rawDataMap=usedandFull(rawData);
		for(String key:rawDataMap.keySet()) {
			map.put(key, rawDataMap.get(key));
		}
		map.put("Free", free);
		return map;
	}
	
	//getAllDetail information of shelf had booked, return map<ShelfID, [shortname of user used that shelf]
	public Map<String, Object> allDetail(List<Booking> booking){
		Map<String, Object>rawmap =new TreeMap<String, Object>();
		Map<String, Object>used =new TreeMap<String, Object>();
		for(int i=0; i<booking.size(); i++) {
			int startDateInt=booking.get(i).getStartDate().getDay()-1; //date of week, sunday =0, monday=1
			int endDateInt=booking.get(i).getEndDate().getDay()-1;
			String key=booking.get(i).getShelf().getShelfId(); //key in map
			String userName=booking.get(i).getUser().getShortName();
			if(!rawmap.containsKey(key)) {
				String[] value=parseDataToArray(new String[5],startDateInt, endDateInt, userName);
				rawmap.put(key, value);
			}else {
				String[] value=(String[]) rawmap.get(key);
				String[] newvalue=parseDataToArray(value,startDateInt, endDateInt, userName);
				rawmap.replace(key, newvalue);
			}
		}
		return rawmap;
	}
	
	public Map<String, Object> usedandFull(Map<String, Object> rawmap){
		Map<String, Object>map =new TreeMap<String, Object>();
		Map<String, Object>full =new TreeMap<String, Object>();
		Map<String, Object>used =new TreeMap<String, Object>();
		for(String key: rawmap.keySet()) {
			String [] value=(String[]) rawmap.get(key);
			if(checkShelfFull(value)){
				full.put(key, value);
			} else {
				used.put(key, value);
			}
		}
		map.put("Used",used);
		map.put("Full",full);
		return map;
	}
	
	public boolean checkShelfFull(String[] arr) {
		for(int i=0; i<arr.length; i++) {
			if(arr[i]==null) {
				return false;
			}
		}
		return true;
	}
	public String[] parseDataToArray(String[] array, int start, int end, String userName) {
		for(int i=start; i<end+1; i++) {
			array[i]=userName;
		}
		return array;
	}

	@Override
	public Map<Long, Object> getShelfDetailFromUnit(int unitId) {
		Map<Long, Object> map=new TreeMap<Long, Object>();
		Unit unit=(Unit) unitRepository.findByUnitId(unitId);
		Set<Floor> floors= unit.getFloor();
		for(Floor floor: floors) {
			Map<String, Object> floorMap=getShelfDetailFromFloor((int)floor.getFloor());
			map.put(floor.getFloor(), floorMap);
		}
		return map;
	}
	
	@Override
	public Map<Long, Object> getMapShelfFromUnit(int unitId) {
		Map<Long, Object> map=new TreeMap<Long, Object>();
		Unit unit=(Unit) unitRepository.findByUnitId(unitId);
		Set<Floor> floors= unit.getFloor();
		for(Floor floor: floors) {
			Map<String, Object> floorMap=getMapShelfFromFloor((int)floor.getFloor());
			map.put(floor.getFloor(), floorMap);
		}
		return map;
	}

	@Override
	public Map<Long, Object> getMapShelfAdmin() {
		Map<Long, Object> map=new TreeMap<Long, Object>();
		List<Floor>floors=floorRepository.findAll();
		for(int i=0; i<floors.size(); i++) {
			long floor= floors.get(i).getFloor();
			Map<String, Object> floorMap=getMapShelfFromFloor((int)floor);
			map.put(floor, floorMap);
		}
		return map;
	}

	@Override
	public long findMaxId() {
		// TODO Auto-generated method stub
		return bookingRepository.findMaxId();
	}
	
	public void insertNewBooking(long bookingId, long userId, String shelfId, String startDate, String endDate) {
		User user= usersRepository.findByUserId(userId);
		Shelf shelf=shelfRepository.findByShelfId(shelfId);
		Date start=null;
		Date end=null;
		try {
			start = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			end=new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long expire=0;
		Booking bookingtemp=new Booking(bookingId, user, shelf, start, end, expire);
		bookingRepository.save(bookingtemp);
	}

	@Override
	public List<Booking> findUserBookingHistory(long userId) {
		// TODO Auto-generated method stub
		return bookingRepository.findUserBookingHistory(userId);
	}

	@Override
	public Map<String, Object> findReportData() {
		Map<String, Object> map= new TreeMap<String, Object>();
		map.put("floor", getShelfDetailAllFloor());
		map.put("unit", unitRepository.findAll());
		return map;
	}
	
	@Override
	public Booking addNewBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.save(booking);
		
	}

	@Override
	public Booking updateEditBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.updateEditBooking(booking.getBookingId(), booking.getStartDate(), booking.getEndDate(), booking.getExpire());

	}
	@Override
	public List<Booking> findUserBookingEdit(long userId) {
		return bookingRepository.findUserBookingEdit(userId);
	}

	@Override
	public int checkUserBook(Long userId, String startDate, String endDate) {
		return bookingRepository.checkUserBook(userId, startDate, endDate);
	}

	@Override
	public Map<String, String[]> getBookingByShelfId(String shelfId) {
		List<Booking> list = bookingRepository.getBookingByShelfId(shelfId);
		
		return parseDataToMap(list);
		
	}
	
	@Override
	public Map<Long, String[]> getBookingByUserId(Long userId,String bookingId) {
		List<Booking> list = bookingRepository.getBookingByUserId(userId,bookingId);
		
		return parseDataToMapUser(list,userId);
		
	}
	
	
	public Map<String, String[]> parseDataToMap(List<Booking> booking){
		Map<String, String[]> map = new TreeMap<String, String[]>();
		SimpleDateFormat dt1 = new SimpleDateFormat("EEEE");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");
		
		for (int i = 0; i < booking.size(); i++) {
			if(booking.get(i).getStartDate()==null){
				map.put(booking.get(i).getShelf().getShelfId(), new String[] {"","","","",""});
			}
			else if (!map.containsKey(booking.get(i).getShelf().getShelfId())) {
				String[] strings=new String[] {"","","","",""};
				map.put(booking.get(i).getShelf().getShelfId(), parseDatetoStringArray(strings, booking.get(i)));
			}else {
				String[] strings=map.get(booking.get(i).getShelf().getShelfId());
				map.replace(booking.get(i).getShelf().getShelfId(), parseDatetoStringArray(strings, booking.get(i)));
			}
		}
		return map;
	}
	
	public Map<Long, String[]> parseDataToMapUser(List<Booking> booking,Long userId){
		Map<Long, String[]> map = new TreeMap<Long, String[]>();
		SimpleDateFormat dt1 = new SimpleDateFormat("EEEE");
		SimpleDateFormat dt2 = new SimpleDateFormat("yyyy-MM-dd");
		map.put(userId,  new String[] {"","","","",""});
		
		for (int i = 0; i < booking.size(); i++) {
			if(booking.get(i).getStartDate()==null){
				map.put(booking.get(i).getUser().getUserId(), new String[] {"","","","",""});
			}
			else if (!map.containsKey(booking.get(i).getUser().getUserId())) {
				String[] strings=new String[] {"","","","",""};
				map.put(booking.get(i).getUser().getUserId(), parseDatetoStringArray(strings, booking.get(i)));
			}else {
				String[] strings=map.get(booking.get(i).getUser().getUserId());
				map.replace(booking.get(i).getUser().getUserId(), parseDatetoStringArray(strings, booking.get(i)));
			}
		}
		return map;
	}
	
	public String [] parseDatetoStringArray(String[]string,Booking booking ) {
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(booking.getStartDate());
        int start=calendar.get(Calendar.DAY_OF_WEEK);
        calendar.setTime(booking.getEndDate());
        int end=calendar.get(Calendar.DAY_OF_WEEK); 
        for(int i=start-2; i<end-1; i++) {
        	string[i]=String.valueOf(booking.getUser().getUserId());
        }
		return string;
	}

	@Override
	public int insertBookingTemp(String bookingId,String userId, String shelfId, String startDate, String endDate) {
		return bookingRepository.insertBookingTemp(bookingId,userId,shelfId,startDate,endDate);
	}

	@Override
	public int updateEndDateAndExpire(long bookingId) {
		return bookingRepository.updateEndDateAndExpire(bookingId);
	}

	@Override
	public int updateBooking(String bookingId, String startDate, String endDate) {
		return bookingRepository.updateBooking(bookingId,startDate,endDate);
	}
	
	@Override
	public List<Booking> findAllBookingHistory() {
		return bookingRepository.findAllBookingHistory();
	}

	@Override
	public List<Booking> findAllBookingEdit() {
		return bookingRepository.findAllBookingEdit();
	}

	@Override
	public int deleteBookingByBookingId(long bookingId) {
		return bookingRepository.deleteById(bookingId);
		
	}
}
