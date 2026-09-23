package com.example.demo.Booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

	@Autowired
	private BookingRepository repo;
	
	public void add(Booking book)
	{
		repo.save(book);
	}
}
