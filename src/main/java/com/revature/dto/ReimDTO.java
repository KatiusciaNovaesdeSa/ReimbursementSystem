package com.revature.dto;

import com.revature.model.Reimbursement;

public class ReimDTO {
	private int reimid;
	private String username;
	private double amount;
	private String submitted; 
	//private String resolved;  
	private String description;
	private String authorFullName;
	private String status;
	private String type;
	
	public ReimDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReimDTO(Reimbursement reim) {
		this.reimid = reim.getId();
		this.username = reim.getAuthor().getUsername();
		this.amount = reim.getAmount();
		this.submitted = reim.getSubmitted().toString();
		

		//this.resolved = reim.getResolved().toString();
		
		this.description = reim.getDescription();
		this.authorFullName = reim.getAuthor().getFirstName() + " " + reim.getAuthor().getLastName();
		this.status = reim.getStatus().getStatus();
		this.type = reim.getType().getType();
	}


	public int getReimid() {
		return reimid;
	}

	public void setReimid(int reimid) {
		this.reimid = reimid;
	}






	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public String getAuthorFullName() {
		return authorFullName;
	}


	public void setAuthorFullName(String authorFullName) {
		this.authorFullName = authorFullName;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	

//
//	public String getResolved() {
//		return resolved;
//	}
//
//
//	public void setResolved(String resolved) {
//		this.resolved = resolved;
//	}
	

}
