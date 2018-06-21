package com.dmi.bootcamp.s3demo1.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsUtils {
	public static AWSCredentials getCredentials() {
		AWSCredentials credentials = new BasicAWSCredentials("<AWS accesskey>", "<AWS secretkey>");
		return credentials;
	}
	public static AmazonS3 getAmazonS3Client() {
		AWSCredentials credentials=getCredentials();
		AmazonS3 client = AmazonS3ClientBuilder
				  .standard()
				 // .withCredentials(new AWSStaticCredentialsProvider(credentials))
				  .withRegion(Regions.US_EAST_1)
				  .build();
		
		return client;
	}
	
}
