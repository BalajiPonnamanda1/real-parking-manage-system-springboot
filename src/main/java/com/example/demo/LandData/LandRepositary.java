package com.example.demo.LandData;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.PartnerData.Partner;

@Repository
public interface LandRepositary extends JpaRepository<Land, Integer>{

	 List<Land> findByPartner(Partner partner);
	 void deleteByLandNameAndPartner(String location, Partner partner);
	 
	 List<Land> findByPartnerAndCity(Partner partner, String city);
	 
	 long countByPartner(Partner partner);
	 
	 
}
