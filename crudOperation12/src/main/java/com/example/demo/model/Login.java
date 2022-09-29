package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="login")
public class Login {
	   @Id
	   @GeneratedValue(strategy= GenerationType.IDENTITY)

		private Long id;
		private String username;
		private String password;
		private String email;
		private String mobile;

		
		public Long getId() {
			return id;
		}
		public String getUsername() {
			return username;
		}
		public String email() {
			
			return email;
		}public String mobile() {
			
			return mobile;
		}
		public String getPassword() {
			return password;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public void setUsename(String username) {
			this.username = username;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setmobile(String mobile) {
			this.mobile = mobile;
		}
		public void setemail(String email) {
			this.email = email;
		}
		@Override
		public String toString() {
			return "Login [id=" + id + ",username=" + username + ", password=" + password + ", email=" + email + ", mobile=" + mobile + "]";
		}
}
