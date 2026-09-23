package com.example.demo.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.CustomerData.Customer;

@Service
public class reviewService {

	@Autowired
	ReviewRepository repo;
	
	public Review saveData(Review review)
	{
		return repo.save(review);
	}
	
	public List<Review> getAll(Customer customer)
	{
		return repo.findByBookingCustomer(customer);
	}
	
	public void delete(Integer reviewId)
	{
		repo.deleteById(reviewId);
	}
	
	public Review getReview(Integer reviewId)
	{
		
		return repo.findById(reviewId).get();
		
	}
	
}
