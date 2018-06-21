package com.dmi.bootcamp.dynamodbdemo1.model;

import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Subnet")
public class Subnet {
	
	@DynamoDBHashKey(attributeName="networkAddress")
	private String networkAddress;
	
	//@DynamoDBRangeKey(attributeName="stackId")
	@DynamoDBAttribute
	private String stackId;
	
	@DynamoDBAttribute
	private String availabilityZone;
	
	@DynamoDBAttribute
	private List<String> tags;
	
	public String getAvailabilityZone() {
		return availabilityZone;
	}
	public void setAvailabilityZone(String availabilityZone) {
		this.availabilityZone = availabilityZone;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Subnet(){}
	public String getNetworkAddress() {
		return networkAddress;
	}
	public void setNetworkAddress(String networkAddress) {
		this.networkAddress = networkAddress;
	}
	public String getStackId() {
		return stackId;
	}
	public void setStackId(String stackId) {
		this.stackId = stackId;
	}
	
}
