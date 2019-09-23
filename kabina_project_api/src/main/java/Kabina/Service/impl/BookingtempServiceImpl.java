package Kabina.Service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Kabina.Model.Bookingtemp;
import Kabina.Model.Shelf;
import Kabina.Model.User;
import Kabina.Repository.BookingRepository;
import Kabina.Repository.BookingtempRepository;
import Kabina.Repository.ShelfRepository;
import Kabina.Repository.UsersRepository;
import Kabina.Service.BookingtempService;

@Service
public class BookingtempServiceImpl implements BookingtempService {

	@Autowired
	BookingtempRepository bookingtempRepository;
	@Autowired
	BookingRepository bookingRepository;
	@Autowired
	UsersRepository usersRepository;
	@Autowired
	ShelfRepository shelfRepository;
	
	@Override
	public List<Bookingtemp> findAll() {
		// TODO Auto-generated method stub
		return bookingtempRepository.findAll();
	}

	@Override
	public int checkAndInsertNewBooking(long bookingId, long userId, String shelfId, String startDate, String endDate) {
		if(bookingRepository.checkUserBook(userId, startDate, endDate)!=0) {
			return 0;
		}else {
			return insertNewBooking(bookingId, userId, shelfId, startDate, endDate);
		}
	}
	
	@Override
	public int insertNewBooking(long bookingId, long userId, String shelfId, String startDate, String endDate) {
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
		Bookingtemp bookingtemp=new Bookingtemp(bookingId, user, shelf, start, end, expire);
		bookingtempRepository.save(bookingtemp);
		return 1;
	}

	@Override
	public List<Bookingtemp> findByUserId(long userId) {
		// TODO Auto-generated method stub
		return bookingtempRepository.findByUserId(userId);
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
	
	@Override
	public List<Bookingtemp> getAllBookingtemp() {
		// TODO Auto-generated method stub
		return bookingtempRepository.findAll();
	}

}
