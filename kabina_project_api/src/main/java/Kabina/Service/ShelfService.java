package Kabina.Service;

import java.util.List;
import java.util.Map;

import Kabina.Model.Shelf;


public interface ShelfService {
	List<Shelf> findAll();

	List<Shelf> findFreeShelfOfThisWeek(int floor);
	
	List<Shelf> findUsedShelfOfUserRange(String start, String end, int floor);
	
	List<Shelf> findFullShelfOfThisWeek(int floor);
	
	List<Shelf> findFreeShelfAprroved(int floor);

	Map<String, Object>  findShelfAvailableInRange(String startDate, String endDate, int unitId);

	Map<String, Object> findShelfAvailableAdmin(String startDate, String endDate);
}
