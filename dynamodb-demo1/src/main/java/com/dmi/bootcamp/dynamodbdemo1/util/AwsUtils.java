package com.dmi.bootcamp.dynamodbdemo1.util;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

public class AwsUtils {
	
public static AmazonDynamoDB getAwsClient() {
    AmazonDynamoDB client=AmazonDynamoDBClientBuilder.standard().withRegion(Regions.fromName("us-east-1")).build();
    return client;
}

}
