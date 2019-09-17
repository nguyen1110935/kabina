package Kabina.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Model.Bookingtemp;
import Kabina.Repository.BookingtempRepository;
import Kabina.Service.BookingtempService;

@Service
public class BookingtempServiceImpl implements BookingtempService {

	@Autowired
	BookingtempRepository bookingtempRepository;
	
	@Override
	public List<Bookingtemp> findAll() {
		// TODO Auto-generated method stub
		return bookingtempRepository.findAll();
	}

}
