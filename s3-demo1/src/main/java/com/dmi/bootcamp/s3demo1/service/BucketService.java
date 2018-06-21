package com.dmi.bootcamp.s3demo1.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.dmi.bootcamp.s3demo1.util.AwsUtils;

public class BucketService {
	private final AmazonS3 client;
	public BucketService() {
		client=AwsUtils.getAmazonS3Client();
	}
	public boolean createBucket(String bucketName) {
		if(client.doesBucketExist(bucketName)) {
			System.out.println("Use a different bucket name ["+bucketName +"]is not available");
			return false;
		}
		else {
			client.createBucket(bucketName);
		}
		return true;
	}

	public List<Bucket> getAllBuckets() {
		return  client.listBuckets();

	}
	public boolean deleteBucket(String bucketName) {
		try {
			client.deleteBucket(bucketName);
		} catch (AmazonServiceException e) {
			System.out.println("Unable to delete["+bucketName +"], "+e.getMessage());
			return false;
		}
		return true;
	}
	public void uploadFile(String bucketName, String key, String path) {
		client.putObject(bucketName, key, new File(path));
	}

	public void listAllFiles(String bucketName) {
		ObjectListing objectListing = client.listObjects(bucketName);
		for(S3ObjectSummary summary : objectListing.getObjectSummaries()) {
		    System.out.println(summary.getKey());
		}
	}
	
	public void dowloadFile(String bucketName,String key, String destinationPath) {
		S3Object s3object = client.getObject(bucketName, key);
		S3ObjectInputStream inputStream = s3object.getObjectContent();
		try {
			FileUtils.copyInputStreamToFile(inputStream, new File(destinationPath));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void deleteFile(String bucketName,String key) {
		client.deleteObject(bucketName,key);
	}
	public void deleteFiles(String bucketName,String[] keys) {
		
		DeleteObjectsRequest deleleteRequest = new DeleteObjectsRequest(bucketName).withKeys(keys);
		client.deleteObjects(deleleteRequest );
	}
	
	public ObjectMetadata getMetadeta(String bucketName,String key) {
		return client.getObjectMetadata(bucketName,key);
	}
	
}
