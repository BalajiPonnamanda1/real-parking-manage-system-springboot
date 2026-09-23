package com.example.demo.PartnerData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/partner")
public class PartnerController {

	@Autowired
	PartnerService ser;
// login checking	
	
	@PostMapping("/loginData")
	public String login(long mobileNumber, String password, HttpSession session, Model model)
	{
		
		Partner p = ser.findPartner(mobileNumber, password);
		if(p == null)
		{
			model.addAttribute("error","mobile number and password does not matched");
			return "start/PartIndex";
		}
		else
		{
			session.setAttribute("pid", p.getPartnerId());
			return "PartnerProfile/PartnerPage";
		}
	}
// sign up details
	@PostMapping("/signData")
	public String signIn(@ModelAttribute Partner part,HttpSession session, Model model)
	{
		Partner p = ser.save(part);
		if(p == null)
			return "start/PartIndex";
		else
		{
			session.setAttribute("pid", p.getPartnerId());
			
			return "PartnerProfile/PartnerPage";
		}
	}
	
	@GetMapping("/Home")
	public String Home()
	{
		return "PartnerProfile/PartnerPage";
	}
	
	@GetMapping("/profile")
	public String Profile()
	{
		return "PartnerProfile/profile";
	}
	
// profile showing
	@GetMapping("/details")
	public String profileShow(Model model,HttpSession session)
	{
		Partner p = ser.findwithId((Integer) session.getAttribute("pid"));
		long count = ser.getLandCount(p);
		model.addAttribute("data", p);
		model.addAttribute("count", count);
		return "PartnerProfile/PartProfile";
	}

// profile update 
	
	@GetMapping("/update")
	public String updatePage(Model model, HttpSession session) {

	    Partner partner = ser.findwithId((Integer)session.getAttribute("pid"));

	    model.addAttribute("data", partner);

	    return "PartnerProfile/UpdatePartner";
	}
	
	@PostMapping("/updateData")
	public String Updated(@ModelAttribute Partner partner,HttpSession session, Model model)
	{
		if(ser.Update(partner))
		{
			model.addAttribute("msg","Updated sucessfully");
			return "PartnerProfile/msg";
		}
		else
		{
			model.addAttribute("error", "phone number already exists");
			return "PartnerProfile/UpdatePartner";
		}
	}
	
	@GetMapping("/delete")
	public String delete(HttpSession session, Model model)
	{
		ser.deleteAccount((Integer)session.getAttribute("partner"));
		model.addAttribute("msg","login sucessfull");
		return "PartnerProfile/msg";
	}
	
	
}
