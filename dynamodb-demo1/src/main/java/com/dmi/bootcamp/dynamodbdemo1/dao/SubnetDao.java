package com.dmi.bootcamp.dynamodbdemo1.dao;

import java.util.List;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.dmi.bootcamp.dynamodbdemo1.model.Subnet;
import com.dmi.bootcamp.dynamodbdemo1.util.AwsUtils;

public class SubnetDao {
	private final DynamoDBMapper mapper;
	private final AmazonDynamoDB client;
	public SubnetDao(){
		this.client=AwsUtils.getAwsClient();
		this.mapper=new DynamoDBMapper(client);
	}
	public void createSubnet(Subnet sub){
		mapper.save(sub);
	}
	
	public Subnet getSubnetByNetworkAddress(String networkAddress){
		return mapper.load(Subnet.class,networkAddress);
	}
	
	public Subnet getSubnet(Subnet subnet) {
		return mapper.load(subnet);
	}
	
	public List<Subnet> getSubnets(DynamoDBQueryExpression<Subnet> query){
		return mapper.query(Subnet.class, query);
	}
	public List<Subnet> getAllSubnets(){
		DynamoDBScanExpression scan=new DynamoDBScanExpression();
		
		return mapper.scan(Subnet.class, scan);
	}
	public void deleteSubnet(Subnet subnet){
		mapper.delete(subnet);
	}
}
