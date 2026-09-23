package com.example.demo.Booking;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;

import com.example.demo.CustomerData.Customer;
import com.example.demo.LandData.Land;
import com.example.demo.Vehicle.Vehicle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Booking {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer bookingId;

	    private String vehicleNumber;

	    private Integer otp;


	    private LocalDateTime bookTime;
	    private LocalDateTime inTime;

	    private LocalDateTime outTime;

	    private Double amount;

	    private String status;

	    @ManyToOne
	    @JoinColumn(name = "customer_id")
	    private Customer customer;

	    @ManyToOne
	    @JoinColumn(name = "land_id")
	    private Land land;

	    @ManyToOne
	    @JoinColumn(name = "vehicle_id")
	    private Vehicle vehicle;

		public Booking(Integer bookingId, String vehicleNumber, Integer otp, LocalDateTime inTime,
				LocalDateTime bookingTime,
				LocalDateTime outTime, Double amount, String status, Customer customer, Land land, Vehicle vehicle) {
			super();
			this.bookingId = bookingId;
			this.vehicleNumber = vehicleNumber;
			this.otp = otp;
			this.inTime = inTime;
			this.outTime = outTime;
			this.amount = amount;
			this.status = status;
			this.customer = customer;
			this.land = land;
			this.vehicle = vehicle;
			this.bookTime = bookingTime;
		}

		public Booking() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Integer getBookingId() {
			return bookingId;
		}

		public void setBookingId(Integer bookingId) {
			this.bookingId = bookingId;
		}

		public LocalDateTime getBookTime() {
			return bookTime;
		}

		public void setBookTime(LocalDateTime bookTime) {
			this.bookTime = bookTime;
		}

		public String getVehicleNumber() {
			return vehicleNumber;
		}

		public void setVehicleNumber(String vehicleNumber) {
			this.vehicleNumber = vehicleNumber;
		}

		public Integer getOtp() {
			return otp;
		}

		public void setOtp(Integer otp) {
			this.otp = otp;
		}

		public LocalDateTime getInTime() {
			return inTime;
		}

		public void setInTime(LocalDateTime inTime) {
			this.inTime = inTime;
		}

		public LocalDateTime getOutTime() {
			return outTime;
		}

		public void setOutTime(LocalDateTime outTime) {
			this.outTime = outTime;
		}

		public Double getAmount() {
			return amount;
		}

		public void setAmount(Double amount) {
			this.amount = amount;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public Land getLand() {
			return land;
		}

		public void setLand(Land land) {
			this.land = land;
		}

		public Vehicle getVehicle() {
			return vehicle;
		}

		public void setVehicle(Vehicle vehicle) {
			this.vehicle = vehicle;
		}
	    
}
