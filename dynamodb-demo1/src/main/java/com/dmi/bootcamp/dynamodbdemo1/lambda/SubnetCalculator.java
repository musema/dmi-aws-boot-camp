package com.dmi.bootcamp.dynamodbdemo1.lambda;

import java.util.LinkedHashMap;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.dmi.bootcamp.dynamodbdemo1.model.LambdaCFNRequest;
import com.dmi.bootcamp.dynamodbdemo1.model.LambdaCFNResponse;
import com.dmi.bootcamp.dynamodbdemo1.service.SubnetService;

public class SubnetCalculator implements RequestHandler<LinkedHashMap<String,Object>, LambdaCFNResponse> {
	private LambdaLogger logger;
	
	 SubnetService subService=new SubnetService();
	 
	private LambdaCFNRequest request=new LambdaCFNRequest();
	private LambdaCFNResponse response=new LambdaCFNResponse();
	
    @Override
    public LambdaCFNResponse handleRequest(LinkedHashMap<String,Object> input, Context context) {
       logger=context.getLogger();
       
       this.request=transformInputToCFNRequest(input);
       String networkAdress=request.getResourceProperties().get("vpc");
       if(networkAdress==null){
    	   this.response.setStatus("Failure");
    	   this.response.getData().put("Reason", "Invalid or no network address is provided");
    	   
    	   logger.log("\nUnable to calculate the subnet without knowing the network address\n");
    	   return null;
       }
  
       if(request.getRequestType().equalsIgnoreCase("Create")){
    	   this.response.setData(subService.createSubnets(networkAdress, 4,request.getStackId()));
    	   populateCommonCFNResponse();
    	   this.response.setStatus("Success");
    	   return this.response;
       }
       if(request.getRequestType().equalsIgnoreCase("Delete")){
    	   subService.deleteSubnetsByStackId(this.request.getStackId());
    	   populateCommonCFNResponse();
    	   this.response.setStatus("Success");
    	   return this.response;
       }
       if(request.getRequestType().equalsIgnoreCase("Update")){
    	   //update operation
    	   populateCommonCFNResponse();
    	   this.response.setStatus("Success");
    	   return this.response;
       }
       this.response.setStatus("Failure");
	   this.response.getData().put("Reason", "Unknown");
       return this.response;
    }
    @SuppressWarnings("unchecked")
	public LambdaCFNRequest transformInputToCFNRequest(LinkedHashMap<String,Object> input){
    	for(Map.Entry<String, Object> en:input.entrySet()){
    		 if(en.getKey().equalsIgnoreCase("ResourceProperties"))
      		   {
    			 this.request.setResourceProperties((Map<String, String>)en.getValue());
    			 continue;
      		   }
    		 this.request.setValue(en.getKey(), en.getValue());
         }
    	return this.request;
    }
    public void populateCommonCFNResponse(){
    	
    }
}
