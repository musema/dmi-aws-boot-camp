package com.dmi.bootcamp.dynamodbdemo1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dmi.bootcamp.dynamodbdemo1.model.Subnet;
import com.dmi.bootcamp.dynamodbdemo1.service.SubnetService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("App is started...");
        SubnetService subService=new SubnetService();
        Map<String,String> subnets=subService.createSubnets("10.0.10.0/24", 4, "stack1");
        for(Map.Entry<String, String> entry:subnets.entrySet()) {
        	System.out.println(entry.getValue());
        }
        
        List<String> tags=new ArrayList<String>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        Subnet subnet1=new Subnet();
        subnet1.setNetworkAddress("10.0.12.0/24");
        subnet1.setTags(tags);
        
        subService.createSubnets(subnet1);
    }
}
