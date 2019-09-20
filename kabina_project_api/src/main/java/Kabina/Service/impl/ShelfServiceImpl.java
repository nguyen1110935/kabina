package Kabina.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Model.Shelf;
import Kabina.Repository.ShelfRepository;
import Kabina.Service.ShelfService;

@Service
public class ShelfServiceImpl implements ShelfService {
	@Autowired
	ShelfRepository shelfRepository;

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
	};

}
