package com.example.demo.Vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.LandData.Land;
import java.util.List;



@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

	void deleteByVehicleTypeAndLand(String vehicleType, Land land);
	
	List<Vehicle> findByLand(Land land);
	
	List<Vehicle> findByLandCityAndVehicleType(String city, String vehicleType);


	Vehicle findByVehicleTypeAndLand(String vehicleType, Land land);

	void deleteByVehicleId(Integer vehicleId);
}
