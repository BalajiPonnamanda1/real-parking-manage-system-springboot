package com.example.demo.LandData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.PartnerData.Partner;
import com.example.demo.PartnerData.PartnerService;
import com.example.demo.review.Review;
import com.example.demo.review.ReviewRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/land")
public class LandController {
	
	@Autowired
	LandService ser;
	
	@Autowired
	PartnerService prtser;
	
	@Autowired
	ReviewRepository revr;
	
	@GetMapping("/lands")
	public String lands()
	{
		return "Land/Land";
	}
	
	@GetMapping("/add")
	public String AddLand()
	{
		return "Land/AddLand";
	}
	
	@PostMapping("/added")
	public String addLand(@ModelAttribute Land land,
	                      HttpSession session, Model model)
	{

	    Integer partnerId =(Integer)session.getAttribute("pid");

	    Partner partner = prtser.findwithId(partnerId);

	    land.setPartner(partner);

	    ser.Added(land);

	    model.addAttribute("msg","land added sucessfully");
		return "Land/msg";
	}
	
// delete Land
	@GetMapping("/remove")
	public String deleteLa()
	{
		return "Land/deleteLand";
	}
	
	@GetMapping("/delete")
	public String DeleteLand(@RequestParam Integer id, HttpSession session, Model model)
	{
		ser.delete(id);
		 model.addAttribute("msg","land deleted sucessfully");
		return "Land/msg";
	}
// update Land
	@GetMapping("/update")
	public String updatePage(@RequestParam Integer id, Model model)
	{
	    Land land = ser.findLand(id);

	    model.addAttribute("data", land);

	    return "Land/updateLand";
	}
	
	@PostMapping("/updateLand")
	public String update(@ModelAttribute Land land, HttpSession session,Model model)
	{
		Partner p =prtser.findwithId((Integer)session.getAttribute("pid"));
		land.setPartner(p);
	    ser.Added(land);

	    model.addAttribute("msg","land Updated sucessfully");
		return "Land/msg";
	}
	
	
	@GetMapping("/show")
	public String Retriew(Model model, HttpSession session)
	{
		Partner p = prtser.findwithId((Integer)session.getAttribute("pid"));
		List<Land> al = ser.retriew(p);
		model.addAttribute("data", al);
		return "Land/retriew";
	}
	
	@GetMapping("/review")
	public String review(@RequestParam Integer id,Model model)
	{
		
		Land land = ser.findLand(id);
		List<Review> rv = revr.findByBookingLand(land);
		model.addAttribute("data", rv);
		return "Review/LandReview";
	}
	
}
