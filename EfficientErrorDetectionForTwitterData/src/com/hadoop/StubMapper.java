package com.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class StubMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    static String createdDate= null;
    static String tweetedBy=null;
	public StubMapper() {
		System.out.println("StubMapper() call");
	}

	@Override
	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		System.out.println("StubMapper.map(-,-,-)");
		//System.out.println("Line>"+value.toString());
		String s = null;
		String datas[] = value.toString().split(",");
		boolean isNotFetched= true;
        for(String data:datas){
        	if(data.contains("\"text\":\"")){
        	System.out.println("Data>"+data);
        	String txts[] = data.toString().split("\":\""); 
        	try{
        	System.out.println("Message :::"+txts[1]);
        	s = txts[1].substring(0, txts[1].indexOf("\""));
        	}catch(StringIndexOutOfBoundsException e){
        		s = "Retail Sentiment Analysis for Amazon,Flipkart and Snapdeal";
        	}
        	// context.write(new Text(s+":"+createdDate+ ":"+ tweetedBy),new IntWritable(1));
        	} else if(data.contains("\"created_at\"") && isNotFetched){
        		System.out.println("Tweeted Date>"+data);
            	String txts[] = data.toString().split("\":\"");  
            	createdDate = ""+txts[1];
            	isNotFetched = false;
        	}
        	if(data.contains("name\":\"")){
        		System.out.println("Tweeted By>"+data);
            	String txts[] = data.toString().split("\":\""); 
            	if(txts.length>1){
            	tweetedBy = ""+txts[1];
            	}else{
            		tweetedBy="liza_123";
            	}
        	}
		   
        }
        context.write(new Text(s+"qqqqq"+createdDate+ "qqqqq"+ tweetedBy),new IntWritable(1));
	}
}