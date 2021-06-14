package com.eduonix.votingsysapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eduonix.votingsysapp.entity.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate,Integer> {

	public Candidate findById(int id);
}
