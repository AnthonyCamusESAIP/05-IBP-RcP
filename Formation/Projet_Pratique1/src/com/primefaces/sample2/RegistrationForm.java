package com.primefaces.sample2;

import java.io.Serializable;
import java.util.Date;



	public class RegistrationForm implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private Integer userId;
		private String userName;
		private String password;
		private String firstName;
		private String lastName;
		private String email;
		private String phone;
		private Date dob;
		private boolean ibpRecette;
		
		public RegistrationForm() {
		
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
		}

	
		

		public boolean AddToIbpRecette() {
			return ibpRecette;
		}

		public void AddToIbpRecette(boolean ibpRecette) {
			this.ibpRecette = ibpRecette;
		}

		// setters/getters

		
	}