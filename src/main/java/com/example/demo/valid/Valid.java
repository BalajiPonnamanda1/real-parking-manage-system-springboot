package com.example.demo.valid;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Booking.Booking;
import com.example.demo.Booking.BookingRepository;
import com.example.demo.PartnerData.Partner;
import com.example.demo.Vehicle.Vehicle;
import com.example.demo.Vehicle.VehicleRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/valid")
public class Valid {

	@Autowired
	BookingRepository bkp;
	
	@Autowired
	VehicleRepository vhrepo;
	
	@GetMapping("/")
	public String Valid()
	{
		return "Valid/Check";
	}
	
	@GetMapping("/in")
	public String In()
	{
		return "Valid/enter";
	}
	
	@PostMapping("/validate")
	public String Enter(@RequestParam Integer otp, HttpSession session,Model model)
	{
		Partner pt = (Partner) session.getAttribute("partner");
		Booking bk = bkp.findByOtp(otp);
		
		
		if(bk != null && bk.getLand().getPartner().getMobileNumber() == pt.getMobileNumber()) {
		bk.setInTime(LocalDateTime.now());
		Vehicle vh = bk.getVehicle();
		vh.setAvailableSlots(vh.getAvailableSlots() - 1);
		
		vhrepo.save(vh);
		bk.setOtp(null);
		bk.setStatus("booked");
		bk = bkp.save(bk);
		session.setAttribute("book", bk);
		return "Valid/confirm";
		}
		else
		{
			model.addAttribute("error","otp is invalid");
			return "Valid/enter";
		}
	}
	
	
	@GetMapping("/out")
	public String goout()
	{
		return "Valid/checkOut";
	}
	
	
	@GetMapping("/checking")
	public String Exit(@RequestParam String vehicleNumber, Model model)
	{
		
		Booking bk = bkp.findByVehicleNumber(vehicleNumber);
		bk.setOutTime(LocalDateTime.now());
		bk.setStatus("open");

		Vehicle vh = bk.getVehicle();
		vh.setAvailableSlots(vh.getAvailableSlots() + 1);
		vhrepo.save(vh);
		
		Duration duration = Duration.between(bk.getInTime(),bk.getOutTime());

		long totalMinutes = duration.toMinutes();

		double amount = Math.round( (totalMinutes / 60.0) * bk.getVehicle().getPricePerHour());
		bk.setAmount(amount);
		bk = bkp.save(bk);
		model.addAttribute("data", bk); 
		return "Valid/exit";
	}
}
