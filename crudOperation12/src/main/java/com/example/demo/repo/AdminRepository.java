package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Admin;

public interface AdminRepository extends JpaRepository<Admin,Long> {
	   public List<Admin> findByusernameAndPassword(String Name,String password);


}
