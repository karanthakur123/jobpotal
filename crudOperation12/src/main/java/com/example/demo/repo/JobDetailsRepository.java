package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Book;
import com.example.demo.model.JobDetails;


public interface JobDetailsRepository extends JpaRepository<JobDetails,Long> {
	
	   public List<JobDetails> findByUsername(String username);
	   
	   @Modifying
	   @Transactional
	   @Query( value = "Update job_details set hire_status='Hired' where id = :id", nativeQuery = true)
	   void updateHiredStatus(String id);
	   
	

}
