package com.example.demo.CustomerData;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Booking.Booking;
import com.example.demo.Booking.BookingRepository;
import com.example.demo.LandData.Land;
import com.example.demo.PartnerData.Partner;
import com.example.demo.Vehicle.Vehicle;
import com.example.demo.Vehicle.VehicleRepository;
import com.example.demo.review.Review;
import com.example.demo.review.ReviewRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	ReviewRepository repo;
	
	@Autowired
	BookingRepository br;
	
	@Autowired
	VehicleRepository vhrepo;
	
	@RequestMapping("/")
	public String Welcome()
	{
		return "start/custIndex";
	}
	
	@GetMapping("/login")
	public String login()
	{
		return "start/custLogin";
	}
	
	@GetMapping("/signIn")
	public String signIn()
	{
		return "start/custsignIn";
	}
	
	@Autowired
	CustomerService ser;
	
	@PostMapping("/loginData")
	public String login(long mobileNumber, String password, HttpSession session,Model model)
	{
		Customer p = ser.findCust(mobileNumber, password);
		if(p == null)
		{
			model.addAttribute("error", "Invalid mobile number and password");
			return "start/custLogin";
		}
		else
		{
			session.setAttribute("cust", p);
			return "Customer/CustHome";
		}
	}
	
	
	@PostMapping("/signData")
	public String signIn(@ModelAttribute Customer part,HttpSession session, Model model)
	{
		
		Customer cu = ser.findByMobile(part.getMobileNumber());

		if(cu != null)
		{
			model.addAttribute("error","the mobile number is already exist");
			return "start/custsignIn";
		}
			
		Customer p = ser.save(part);
		if(p == null)
			return "start/PartIndex";
		else
		{
			session.setAttribute("cust", p);
			return "Customer/CustHome";
		}
	}
	
	@RequestMapping("/home")
	public String home()
	{
		return "Customer/CustHome";
	}
	
	@GetMapping("/booking")
	public String Booking()
	{
		return "Customer/book";
	}
	
	@PostMapping("/land")
	public String GetLand(@RequestParam String city, @RequestParam String vehicleType  ,Model model)
	{
		List<Vehicle> vh = ser.getLand(city, vehicleType);
		model.addAttribute("data", vh);
		return "Customer/AllLand";
	}
	
	
	@GetMapping("/slot/{id}")
	public String bookSlot(@PathVariable Integer id,HttpSession session, Model model) {
		model.addAttribute("vehicleId", id);
	    System.out.println(id);

	    return "Customer/VehNum";
	}
	
	@GetMapping("/reviews/{id}")
	public String review(@PathVariable Integer id,HttpSession session, Model model) {
		
		Vehicle vh = vhrepo.getById(id);
		Customer cus = (Customer) session.getAttribute("cust");
		Land l = vh.getLand();
		List<Review> rr = repo.findByBookingLand(l);
		model.addAttribute("data", rr);
		
	    return "Customer/review";
	}

	
	@GetMapping("/cancelSlot")
	public String cancel()
	{
		return "Customer/cancelSlot";
	}
	
	@PostMapping("/cancelled")
	public String Enter(@RequestParam Integer otp, HttpSession session,Model model)
	{
		Customer cus = (Customer) session.getAttribute("cust");
		Booking bk = br.findByOtp(otp);
		if(bk != null && bk.getCustomer().getMobileNumber() == cus.getMobileNumber()) {
			
			br.deleteById(bk.getBookingId());
			
		return "Valid/confirm";
		}
		else
		{
			model.addAttribute("error","otp is invalid");
			return "Customer/cancelSlot";
		}
	}
	
}
