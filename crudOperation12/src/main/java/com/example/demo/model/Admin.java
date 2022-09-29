package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="admin")
public class Admin {
	   @Id
	    @GeneratedValue(strategy= GenerationType.IDENTITY)

		private long id;
		private String username;
		private String password;
		private String mobile;
		private String email;
		public long getId() {
			return id;
		}
		public String getUsername() {
			return username;
		}
		public String getPassword() {
			return password;
		}
		public String getMobile() {
			return mobile;
		}
		public String getEmail() {
			return email;
		}
		public void setId(long id) {
			this.id = id;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		
		@Override
		public String toString() {
			return "Admin [username=" + username + ", password=" + password + ", mobile=" + mobile + ", email=" + email + "]";
		}
		

}
