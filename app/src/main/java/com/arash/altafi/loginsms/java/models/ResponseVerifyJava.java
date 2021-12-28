package com.arash.altafi.loginsms.java.models;

import com.google.gson.annotations.SerializedName;

public class ResponseVerifyJava{

	@SerializedName("message")
	private String message;

	public String getMessage(){
		return message;
	}
}