package Kabina.Service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import Kabina.Model.Booking;
import Kabina.Repository.BookingRepository;
import Kabina.Service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	BookingRepository bookingRepository;
	
	@Override
	public List<Booking> findAll() {
		
		return bookingRepository.findAll();
	}

	@Override
	public List<Booking> findAllBookingFromFloor(int floor) {
		
		return bookingRepository.findAllBookingFromFloor(floor);
	}

}
