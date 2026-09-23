package com.example.demo.PartnerData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.autoconfigure.ServerProperties.Reactive.Session;
import org.springframework.stereotype.Service;

import com.example.demo.LandData.LandRepositary;

import jakarta.servlet.http.HttpSession;

@Service
public class PartnerService {

	@Autowired
	PartnerRepository repo;
	
	@Autowired
	LandRepositary landrepo;
// sign in process	
	public Partner save(Partner partner)
	{
		return repo.save(partner);
	}
// to login checking
	public Partner findPartner(long mobileNumber,String password)
	{
		return repo.findByMobileNumberAndPassword(mobileNumber, password);
		
	}
//to find by partner with Id
	public Partner findwithId(Integer partnerId)
	{
		return repo.findById(partnerId).get();
	}
// to update the values
	public boolean Update(Partner part)
	{
		try {
			Partner p = findwithId(part.getPartnerId());
			Partner p1 = repo.findByMobileNumber(part.getMobileNumber());
			if(p1 == null || p.getMobileNumber() == part.getMobileNumber())
			{
				p.setMobileNumber(part.getMobileNumber());
				p.setPartnerName(part.getPartnerName());
				p.setPassword(part.getPassword());
				repo.save(p);
				return true;
			}
		}
		catch(Exception e)
		{
			return false;
		}
		return false;
	}
// to delete the account
	public void deleteAccount(Integer partnerId)
	{
		repo.deleteById(partnerId);
	}
	
// to get land counts 
	public long getLandCount(Partner partner)
	{
		return landrepo.countByPartner(partner);
	}
}
