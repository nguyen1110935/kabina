package Kabina.Service.impl;

import java.util.List;
import java.util.Optional;

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
	
	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		bookingtempRepository.deleteById(id);
	}

	@Override
	public Optional<Bookingtemp> findById(Long id) {
		// TODO Auto-generated method stub
		return bookingtempRepository.findById(id);
	}
}
