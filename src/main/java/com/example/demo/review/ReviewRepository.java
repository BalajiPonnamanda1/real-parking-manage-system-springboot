package com.example.demo.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Booking.Booking;
import com.example.demo.CustomerData.Customer;
import com.example.demo.LandData.Land;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

	List<Review> findByBookingCustomer(Customer customer);
	
	List<Review> findByBooking(Booking booking);
	
	List<Review> findByBookingLand(Land land);
	

}
