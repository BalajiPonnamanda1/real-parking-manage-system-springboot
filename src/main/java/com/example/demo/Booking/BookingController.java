package com.example.demo.Booking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.CustomerData.Customer;
import com.example.demo.Vehicle.Vehicle;
import com.example.demo.Vehicle.VehicleRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/slotValid")
public class BookingController {

	@Autowired
	BookingService ser;
	
	@Autowired
	VehicleRepository repo;
	
	@PostMapping("/otp")
	public String add(@RequestParam String vehicleNumber,@RequestParam Integer vehicleId,Model model, 
			 @RequestParam LocalDate bookingDate,
             @RequestParam LocalTime bookingTime,HttpSession session)
	{
		LocalDateTime bookingDateTime = LocalDateTime.of(bookingDate, bookingTime);
		
		Random rd = new Random();
		int otp = 10000 + rd.nextInt(90000);
		Booking book = new Booking();
		book.setVehicleNumber(vehicleNumber);
		book.setOtp(otp);
		book.setBookTime(bookingDateTime);
		Customer cu = (Customer) session.getAttribute("cust");
		book.setCustomer(cu);
		Vehicle vh = repo.getById(vehicleId);
		book.setLand(vh.getLand());
		book.setVehicle(vh);
		book.setStatus("booked");
		ser.add(book);
		model.addAttribute("data",book);
		return "Customer/booked";
	}
	
	
}
