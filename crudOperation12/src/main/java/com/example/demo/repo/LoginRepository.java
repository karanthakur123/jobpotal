package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Login;

public interface LoginRepository extends JpaRepository <Login,Long>{

	   public List<Login> findByusernameAndPassword(String Name,String password);

	   

}
