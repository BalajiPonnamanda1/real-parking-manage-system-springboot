package com.example.demo.Vehicle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.LandData.Land;
import com.example.demo.LandData.LandRepositary;
import com.example.demo.LandData.LandService;
import com.example.demo.PartnerData.Partner;
import com.example.demo.PartnerData.PartnerService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/vehicle")
public class VehicleController {

	@Autowired
	VehicleService ser;

	@Autowired
	LandRepositary landrepo;
	
	@Autowired
	LandService landser;
	
	@Autowired
	PartnerService prtser;

// default page for vehicles
	@GetMapping("/")
	public String start(HttpSession session, Model model)
	{
		Partner pt  = prtser.findwithId((Integer)session.getAttribute("pid"));
		List<Land> al = landser.retriew(pt);
		model.addAttribute("data", al);
		return "Vehicle/showLand";
	}

// getting the current land page
	@GetMapping("/Land")
	public String GetLand(@RequestParam("id") Integer landId,HttpSession session)
	{
		Land l = (Land)landser.findLand(landId);
		session.setAttribute("place", l.getLandId());
		
		return "Vehicle/park";
	}

// move to land places
	@GetMapping("/park")
	public String park()
	{
		return "Vehicle/Park";
	}
	
// add information of vehcle
	@GetMapping("/add")
	public String AddVeh()
	{
		return "Vehicle/addVeh";
	}

// to integrate the vehicle details
	@PostMapping("/added")
	public String addedVeh(@ModelAttribute Vehicle veh,HttpSession session, Model model)
	{
		
		Land l = landser.findLand((Integer) session.getAttribute("place"));
		System.out.print("this is land = "+l);
		veh.setLand(l);
		Vehicle vh = ser.findVehicle(veh.getVehicleType(), l);
		
		if(vh != null)
		{
			model.addAttribute("error","check your data once , the vehicle type already exists ");
			return "Vehicle/addVeh";
		}
		else
		{
			ser.added(veh);
			session.setAttribute("park", vh);
			model.addAttribute("msg","vehicle added sucessfully");
			return "Vehicle/msg";
		}
	}
	
//	@GetMapping("/update")
//	public String UpdateVeh()
//	{
//		return "Vehicle/updateVeh";
//	}
//	
//	@PostMapping("/updated")
//	public String Updated(@ModelAttribute Vehicle veh, HttpSession session)
//	{
//		Vehicle v = (Vehicle)session.getAttribute("park");
//		
//		v.setAvailableSlots(veh.getAvailableSlots());
//		v.setPricePerHour(veh.getPricePerHour());
//		v.setTotalSlots(veh.getTotalSlots());
//		v.setVehicleType(veh.getVehicleType());
//		
//		Vehicle vh = ser.added(v);
//		if(vh == null)
//		{
//			return "Vehicle/addVeh";
//		}
//		else
//		{
//			session.setAttribute("park", vh);
//			return "redirect:/partner/Home";
//		}
//	}
//	
	

// update the vehicle details
	@GetMapping("/update")
	public String updatePage(@RequestParam Integer id, Model model)
	{
	    Vehicle vehicle = ser.findById(id);

	    model.addAttribute("data", vehicle);

	    return "Vehicle/updateVeh";
	}
	
	
// integrated with database	
	@PostMapping("/updated")
	public String updateVehicle(@ModelAttribute Vehicle vehicle, HttpSession session,Model model)
	{
		Land l = landser.findLand((Integer) session.getAttribute("place"));
		vehicle.setLand(l);
		Vehicle vh = ser.findById(vehicle.getVehicleId());
		if( !vh.getVehicleType().equals( vehicle.getVehicleType()))
		{
			Vehicle v1 = ser.findVehicle(vehicle.getVehicleType(), l);
			if(v1 != null)
			{
				model.addAttribute("error","check your data once , the vehicle type already exists ");
				model.addAttribute("data", vehicle);
				return "Vehicle/updateVeh";
			}
		}
		ser.added(vehicle);
		model.addAttribute("msg","vehicle updated sucessfully");
	    return "Vehicle/msg";
	}
	
	
	
	
// to delete vehicle
	@GetMapping("/delete")
	public String deleteVeh()
	{
		return "Vehicle/deleteVeh";
	}
	
// to integrated with database
	@GetMapping("/deleted")
	public String deletedVeh(@RequestParam String vehicleType, HttpSession session, Model model)
	{
		Land l = landser.findLand((Integer) session.getAttribute("place"));
		Vehicle vh = ser.findVehicle(vehicleType, l);
		if(vh != null)
		{
			ser.deleted(vh);
			model.addAttribute("msg", "vehicle is deleted sucessfully");
			return "Vehicle/msg";
			
		}
		else {
			model.addAttribute("error", "your data is not matching");
			return "Vehicle/deleteVeh";
		}
	}
	
// to show all vehicles respective land
	@GetMapping("/show")
	public String showData(Model model, HttpSession session)
	{
		Land l = landser.findLand((Integer) session.getAttribute("place"));
		List<Vehicle> al = ser.retriew(l);
		model.addAttribute("data", al);
		return "Vehicle/retriew";
	}
	
	
}
