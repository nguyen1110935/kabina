package Kabina.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import Kabina.Model.Booking;
import Kabina.Model.Floor;
import Kabina.Model.Shelf;
import Kabina.Model.Unit;
import Kabina.Model.User;
import Kabina.Repository.BookingRepository;
import Kabina.Repository.FloorRepository;
import Kabina.Repository.UnitRepository;
import Kabina.Service.BookingService;
import Kabina.Service.ShelfService;

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
//	public Map<String, Object> getMapShelfDetailFromFloor(int floor){
//		Map<String, Object>map =new TreeMap<String, Object>();
//		List<Shelf> allFreeShelfFromFloor=shelfService.findFreeShelfAprroved(floor);
//		List<Booking> allFromFloor=bookingRepository.findAllBookingFromFloor(floor);
//		Map<String, Object>rawData =allDetail(allFromFloor);;
//		
//		return map;
//		
//	}
//	
	
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
	
	//getAllDetail information of shelf had booked
	public Map<String, Object> allDetail(List<Booking> booking){
		Map<String, Object>rawmap =new TreeMap<String, Object>();
		Map<String, Object>map =new TreeMap<String, Object>();
		Map<String, Object>full =new TreeMap<String, Object>();
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
	public Booking addNewBooking(Booking booking) {
		// TODO Auto-generated method stub
		return bookingRepository.save(booking);
		
	}

}
