package Kabina.Service;

import java.util.List;

import Kabina.Model.Shelf;

public interface ShelfService {
	List<Shelf> findAll();

	List<Shelf> findFreeShelfOfThisWeek(int floor);
	
	List<Shelf> findUsedShelfOfUserRange(String start, String end, int floor);
	
	List<Shelf> findFullShelfOfThisWeek(int floor);
	
	List<Shelf> findFreeShelfAprroved(int floor);
}
