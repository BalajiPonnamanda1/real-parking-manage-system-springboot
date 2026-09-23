package com.example.demo.CustomerData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	Customer findByMobileNumberAndPassword(long mobileNumber, String password);
	
	Customer findByMobileNumber(long mobileNumber);
}
