package com.example.demo.register;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class RegisterController {

	@RequestMapping("/")
	public String index()
	{
		return "start/index";
	}
	
	@RequestMapping("/partnerreg")
	public String PartnerIndex()
	{
		return "start/Partindex";
	}
	
	
// request from index page
	@GetMapping("/partnerlogin")
	public String PartnerLogin()
	{
		return "start/PartnerLogin";
	}
	
// request from index page to sign in
	@GetMapping("/partnersignIn")
	public String PartnerSignIn()
	{
		return "start/PartnerSignIn";
	}
	
}
