package Kabina.Service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Model.Floor;
import Kabina.Model.Shelf;
import Kabina.Model.Unit;
import Kabina.Repository.FloorRepository;
import Kabina.Repository.ShelfRepository;
import Kabina.Service.ShelfService;
import Kabina.Service.UnitService;

@Service
public class ShelfServiceImpl implements ShelfService {
	@Autowired
	ShelfRepository shelfRepository;
	
	@Autowired 
	UnitService unitService;
	
	@Autowired
	FloorRepository floorRepository;

	@Override
	public List<Shelf> findAll() {
		// TODO Auto-generated method stub
		return shelfRepository.findAll();
	}
	
	@Override
	public List<Shelf> findFreeShelfOfThisWeek(int floor) {
		// TODO Auto-generated method stub
		return shelfRepository.findFreeShelfOfThisWeek(floor);
	}
	
	//get list shelf used bit still free in user range
	@Override
	public List<Shelf> findUsedShelfOfUserRange(String start, String end, int floor){
		return shelfRepository. findUsedShelfOfUserRange(start,end,floor);
	}

	@Override
	public List<Shelf> findFullShelfOfThisWeek(int floor) {
		return shelfRepository.findFullShelfOfThisWeek(floor);
	}

	@Override
	public List<Shelf> findFreeShelfAprroved(int floor) {
		// TODO Auto-generated method stub
		return shelfRepository.findFreeShelfAprroved(floor);
	}

	@Override
	public Map<String, Object> findShelfAvailableInRange(String startDate, String endDate, int unitId) {
		Map<String, Object> map=new TreeMap<String, Object>();
		Unit units=(Unit) unitService.findByUnitId(unitId);
		List<Shelf> frees=new ArrayList<Shelf>();
		List<Shelf> used=new ArrayList<Shelf>();
		Set<Floor> floors=units.getFloor();
		for(Floor floor: floors) {
			long fl=floor.getFloor();
			frees.addAll(findFreeShelfOfThisWeek((int) fl));
			used.addAll(findUsedShelfOfUserRange(startDate, endDate, (int) fl));
		}
		map.put("Free", frees);
		map.put("Used", used);
		return map;
	}

	@Override
	public Map<String, Object> findShelfAvailableAdmin(String startDate, String endDate) {
		List<Floor> floors=floorRepository.findAll();
		Map<String, Object> map=new TreeMap<String, Object>();
		List<Shelf> frees=new ArrayList<Shelf>();
		List<Shelf> used=new ArrayList<Shelf>();;
		for(int i=0; i<floors.size(); i++) {
			long fl=floors.get(i).getFloor();
			frees.addAll(findFreeShelfOfThisWeek((int) fl));
			used.addAll(findUsedShelfOfUserRange(startDate, endDate, (int) fl));
		}
		map.put("Free", frees);
		map.put("Used", used);
		return map;
	};

}
