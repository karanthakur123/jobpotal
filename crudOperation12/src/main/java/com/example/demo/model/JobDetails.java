package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="jobDetails")
public class JobDetails {
	 @Id
	   @GeneratedValue(strategy= GenerationType.IDENTITY)

		private Long id;
		private String jobSpecification;
		private String jobDescription;
		private String jobTitle;
		private String  jobWebsite;
		private String  createdOn;
		private String  username;
		private String hireStatus;
		
		
		
		
		public String getCreated_on() {
			return createdOn;
		}
		public String gethireStatus() {
			return hireStatus;
		}
		public String getUsername() {
			return username;
		}

		public void setCreated_on(String createdOn) {
			this.createdOn = createdOn;
		}




		public Long getId() {
			return id;
		}




		public String getJobSpecification() {
			return jobSpecification;
		}




		public String getJobDescription() {
			return jobDescription;
		}




		public String getJobTitle() {
			return jobTitle;
		}




	



		public String getJobWebsite() {
			return jobWebsite;
		}




		public void setJobWebsite(String jobWebsite) {
			this.jobWebsite = jobWebsite;
		}




		public void setId(Long id) {
			this.id = id;
		}




		public void setJobSpecification(String jobSpecification) {
			this.jobSpecification = jobSpecification;
		}




		public void setJobDescription(String jobDescription) {
			this.jobDescription = jobDescription;
		}




		public void setJobTitle(String jobTitle) {
			this.jobTitle = jobTitle;
		}

		public void setUsername(String username) {
			this.username = username;
		}
		public void setHireStatus(String hireStatus) {
			this.hireStatus = hireStatus;
		}


		
		




		@Override
		public String toString() {
			return "JobDetails [jobSpecification=" + jobSpecification + ", jobDescription=" + jobDescription + ", jobTitle=" + jobTitle + ", jobWebsite=" + jobWebsite + ", createdOn=" + createdOn + ", username=" + username + ", hireStatus=" + hireStatus + "]";
		}
}
