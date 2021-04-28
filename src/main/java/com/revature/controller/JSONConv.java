package com.revature.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.model.Reimbursement;
import com.revature.model.User;

import java.io.IOException;

public class JSONConv {
    public Reimbursement getReimbursement(String json) throws IOException {
        return new ObjectMapper().readValue(json, Reimbursement.class);
    }
    public User getUser(String json) throws IOException {
        return new ObjectMapper().readValue(json, User.class);
    }

    public String getJSON(Reimbursement reimbursement) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(reimbursement);
    }
    public String getJSON(User user) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(user);
    }
}