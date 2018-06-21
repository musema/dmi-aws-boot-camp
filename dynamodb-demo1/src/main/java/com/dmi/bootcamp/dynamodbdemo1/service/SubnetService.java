package com.dmi.bootcamp.dynamodbdemo1.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.dmi.bootcamp.dynamodbdemo1.dao.SubnetDao;
import com.dmi.bootcamp.dynamodbdemo1.model.Subnet;

public class SubnetService {
	private SubnetDao subnetDao;
	public SubnetService() {
		this.subnetDao=new SubnetDao();
	}
	public void createSubnets(Subnet subnet) {
		subnetDao.createSubnet(subnet);
	}
	
	public Subnet getSubnetByNetworkAddressAndStackId(String networkAddress, String stackId) {
		Subnet subnet=new Subnet();
		subnet.setNetworkAddress(networkAddress);
		subnet.setStackId(stackId);
		return subnetDao.getSubnet(subnet);
	}
	
	public List<Subnet> getSubnetsByStackId(String stackId){
		DynamoDBQueryExpression<Subnet> query=new DynamoDBQueryExpression<Subnet>()
				.withRangeKeyCondition(stackId, 
						new Condition()
						.withComparisonOperator(ComparisonOperator
								.EQ)
						.withAttributeValueList(new AttributeValue().withS(stackId)));
		return subnetDao.getSubnets(query);
	}
	public void getSubnetByNetworkAddress(String networkAddress) {

	}
	public List<Subnet> getAllSubnets(){
		return subnetDao.getAllSubnets();
	}
	public void deleteSubnetsByStackId(String stackId){
		
		List<Subnet> subnets= getSubnetsByStackId(stackId);

		String networkAddress="";
		for(int i=0;i<subnets.size();i++){
			networkAddress=subnets.get(i).getNetworkAddress();
			Subnet s=new Subnet();
			s.setNetworkAddress(networkAddress);
			s.setStackId(stackId);
			subnetDao.deleteSubnet(s);
		}

	}
	
	public Map<String,String> createSubnets(String networkAddress,int noOfSubnetsRequired, String stackId){
		Map<String,String> subnets=new HashMap<String,String>(); 
		
		for(int i=1;i<=noOfSubnetsRequired;i++){
			String subId=getFreeSubnet(networkAddress,stackId);
			Subnet sub= new Subnet();
			sub.setNetworkAddress(subId);
			sub.setStackId(stackId);
			subnetDao.createSubnet(sub);
			subnets.put("subnet-"+i, subId);	
		}
		return subnets;
		
	}
	
	public String getFreeSubnet(String networkAddress,String stackId){
		String[] temp=networkAddress.split("\\.",0);
		StringBuilder subnet=new StringBuilder();
		for(int i=8;i<=255;i++){
			subnet=new StringBuilder();
			subnet.append(temp[0]+".").append(temp[1]+"."+i+"."+temp[3]);
			if(subnetDao.getSubnetByNetworkAddress(subnet.toString())==null){
				return subnet.toString();
			}	
		}
		return subnet.toString();
	}
	

}
