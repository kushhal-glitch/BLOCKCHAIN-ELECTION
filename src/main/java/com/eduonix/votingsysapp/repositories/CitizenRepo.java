package com.eduonix.votingsysapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eduonix.votingsysapp.entity.Citizen;

@Repository
public interface CitizenRepo extends JpaRepository<Citizen,Integer> {

	public Citizen findByAadharid(String aadharid);
	
	//@Query("FROM citizens As citizen WHERE lower(citizen.aadharid) = lower(:aadharid)")
	//public Citizen findCitizen(@Param("aadharid") String aadharid);
}
