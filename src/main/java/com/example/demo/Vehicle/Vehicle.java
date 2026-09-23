package com.example.demo.Vehicle;

import com.example.demo.LandData.Land;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Vehicle {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer vehicleId;

	    private String vehicleType;          // Bike, Car, Bus
	    private Integer totalSlots;
	    private Integer availableSlots;
	    private Double pricePerHour;

	    @ManyToOne
	    @JoinColumn(name="land_id")
	    private Land land;

		public Vehicle(Integer vehicleId, String vehicleType, Integer totalSlots, Integer availableSlots, Double pricePerHour,
				Land land) {
			super();
			this.vehicleId = vehicleId;
			this.vehicleType = vehicleType;
			this.totalSlots = totalSlots;
			this.availableSlots = availableSlots;
			this.pricePerHour = pricePerHour;
			this.land = land;
		}

		public Vehicle() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getVehicleId() {
			return this.vehicleId;
		}

		public void setVehicleId(Integer vehicleId) {
			this.vehicleId = vehicleId;
		}

		public String getVehicleType() {
			return vehicleType;
		}

		public void setVehicleType(String vehicleType) {
			this.vehicleType = vehicleType;
		}

		public Integer getTotalSlots() {
			return totalSlots;
		}

		public void setTotalSlots(Integer totalSlots) {
			this.totalSlots = totalSlots;
		}

		public Integer getAvailableSlots() {
			return availableSlots;
		}

		public void setAvailableSlots(Integer availableSlots) {
			this.availableSlots = availableSlots;
		}

		public Double getPricePerHour() {
			return pricePerHour;
		}

		public void setPricePerHour(Double pricePerHour) {
			this.pricePerHour = pricePerHour;
		}

		public Land getLand() {
			return land;
		}

		public void setLand(Land land) {
			this.land = land;
		}
	    
	    
	
	
}
