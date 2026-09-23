package com.example.demo.CustomerData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Vehicle.Vehicle;
import com.example.demo.Vehicle.VehicleRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repo;
	
	@Autowired
	VehicleRepository vehRepo;
	
	public Customer findCust(long mobileNumber, String password)
	{
		return repo.findByMobileNumberAndPassword(mobileNumber, password);
	}
	
	public Customer save(Customer cust)
	{
		return repo.save(cust);
	}
	
	public List<Vehicle> getLand(String city,String vehicleType)
	{
		return vehRepo.findByLandCityAndVehicleType(city, vehicleType);
	}
	
	public Customer findByMobile(long mobileNumber)
	{
		return repo.findByMobileNumber(mobileNumber);
	}
}
