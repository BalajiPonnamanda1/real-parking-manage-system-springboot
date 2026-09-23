package com.example.demo.Booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.CustomerData.Customer;

import java.util.List;
import com.example.demo.LandData.Land;
import com.example.demo.Vehicle.Vehicle;



@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

		Booking findByOtp(Integer otp);
		
		
		
		List<Booking> findByCustomer(Customer customer);
		
		Booking findByLandAndCustomerAndVehicle(Land land, Customer customer, Vehicle vehicle);
		
		List<Booking> findByLand(Land land);
		

		void deleteByVehicle(Vehicle vehicle);
		
		
		Booking findByVehicleNumber(String vehicleNumber);
		
// for writing review
		List<Booking> findByCustomerAndOtpIsNull(Customer customer);
		
}
