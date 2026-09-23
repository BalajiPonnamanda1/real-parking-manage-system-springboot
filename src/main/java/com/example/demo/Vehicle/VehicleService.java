package com.example.demo.Vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Booking.BookingRepository;
import com.example.demo.LandData.Land;

import jakarta.transaction.Transactional;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository repo;
	
	@Autowired
	BookingRepository bkp;
	
// to add the vehicle information
	public Vehicle added(Vehicle veh)
	{
		try {
			return repo.save(veh);
		}
		catch(Exception e)
		{
			return null;
		}
	}
	
// to delete the vehicle 
	@Transactional
	public void deleted(Vehicle vehicle)
	{
		
		bkp.deleteByVehicle(vehicle);
		repo.deleteByVehicleId(vehicle.getVehicleId());
	}
	
	
// to get all land vehicle details
	public List<Vehicle> retriew(Land land)
	{
		return repo.findByLand(land);
	}
	
// to find the vehicle based on vehicle Id
	public Vehicle findById(Integer id)
	{
		return repo.findById(id).orElse(null);
	}
	
// to find the vehicle based on type and land
	public Vehicle findVehicle(String vehicleType,Land land)
	{
		return repo.findByVehicleTypeAndLand(vehicleType, land);
	}
}
