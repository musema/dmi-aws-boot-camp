package com.dmi.bootcamp.s3demo1;

import java.util.List;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dmi.bootcamp.s3demo1.service.BucketService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello S3..." );
        
        System.out.println("==============Creating S3 bucket=========");
        
        String bucketName="com.dmi.musema.bootcamp12";
        String key="index.html";
        BucketService bucketService=new BucketService();
        
       boolean status= bucketService.createBucket(bucketName);
       
       System.out.println("Bucket Created:"+status);
        
        List<Bucket> buckets=bucketService.getAllBuckets();
       for(Bucket bucket : buckets) {
		    System.out.println(bucket.getName());
		}
       
       System.out.println("==============Check expiration date========="); 
       
       bucketName="ladyhanu.com";
       ObjectMetadata metadata=bucketService.getMetadeta(bucketName, key);
       
       System.out.println("Expiration date:"+metadata.getExpirationTime());
       
    }
}
