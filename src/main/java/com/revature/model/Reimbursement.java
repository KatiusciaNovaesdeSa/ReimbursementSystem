package com.revature.model;

import java.sql.Timestamp;

public class Reimbursement {
	
	 private int id;
	 private double amount;
	 private Timestamp submitted; //Timestamp(SYSTEM.currentMillitime)
	 private Timestamp resolved;
	 private String description;
	 private User author;
	 private User resolver;
	 private Status status;

    // private int status;
	 private Type type;
	    
		 //private Receipt receipt;
	 
	 
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int id, double amount, Timestamp submitted, Timestamp resolved, User author, String description, Status status, Type type) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.author = author;
		this.description = description;
		this.status = status;
		this.type = type;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public Timestamp getSubmitted() {
		return submitted;
	}


	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}


	public Timestamp getResolved() {
		return resolved;
	}


	public void setResolved(Timestamp resolved) {
		this.resolved = resolved;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public User getAuthor() {
		return author;
	}


	public void setAuthor(User author) {
		this.author = author;
	}


	public User getResolver() {
		return resolver;
	}


	public void setResolver(User resolver) {
		this.resolver = resolver;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public Type getType() {
		return type;
	}


	public void setType(Type type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", author=" + author + ", resolver=" + resolver + ", status="
				+ status + ", type=" + type + "]";
	}
		 
	
	    
}
