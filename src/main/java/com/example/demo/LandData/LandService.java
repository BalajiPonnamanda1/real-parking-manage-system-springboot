package com.example.demo.LandData;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.PartnerData.Partner;

@Service
public class LandService {

	@Autowired
	LandRepositary repo;
	
// land added into data base
	
	public Land Added(Land land)
	{
		return repo.save(land);
	}
// land deleted
	public void deleteLand(String location, Partner partner)
	{
		repo.deleteByLandNameAndPartner(location, partner);
	}
	
// update Land
	public List<Land> retriew(Partner partner)
	{
		return repo.findByPartner(partner);
	}
	
	public void delete(Integer id)
	{
		repo.deleteById(id);
	}
	
	public Land findLand(Integer id)
	{
		return repo.findById(id).get();
	}
}
