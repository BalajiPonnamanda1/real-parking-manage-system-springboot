package com.example.demo.PartnerData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PartnerRepository extends JpaRepository<Partner, Integer> {
	
	Partner findByMobileNumberAndPassword(long mobileNumber, String password);
	
	Partner findByMobileNumber(long mobileNumber);
}
