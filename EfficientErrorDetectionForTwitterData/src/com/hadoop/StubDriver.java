package com.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class StubDriver {
	
	public static void runMe() throws Exception {
          System.out.println("StubDriver.runMe() starts...");
		  Path input_dir=new Path("hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweets");
		    Path output_dir=new Path("hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/");
		    
		    Configuration conf = new Configuration();
		  /* conf.set("fs.default.name", "hdfs://localhost:54310");
		    conf.set("mapred.job.tracker", "localhost:54311");*/
		    conf.set("fs.defaultFS", "hdfs://quickstart.cloudera:8020");
		    conf.set("mapred.job.tracker", "localhost:8021");
		    
		    //create your own jar of the project,store in in local location and specify that location.This is needed for cluster frun
		    conf.set("mapred.jar","/home/cloudera/Desktop/TwitterApp/SentmentDetection.jar");//Create the jar of your own project and specify it here
			   
		    Job job = new Job(conf, "SentimentAnalysis"); //Configuration is not necessary
		   // job.setJobName("MyWordCountJob");
		   
			//Number doesnot depend on split number
		   job.setNumReduceTasks(13);//cause data to be processed by multiple partitioner,no effect on local mode,can b any figure
			
			job.setJarByClass(StubDriver.class);
			job.setJobName("SentimentAnalysis");
			FileInputFormat.addInputPath(job, input_dir);
			FileOutputFormat.setOutputPath(job, output_dir);
			job.setMapperClass(StubMapper.class);
			
			job.setPartitionerClass(StubPartitioner.class);
			//job.setNumReduceTasks(4);
				
			
			//Reducer code gets executed only at one place,combiner gets executed for all other case
			job.setReducerClass(StubReducer.class);
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);
		    try{	
		 output_dir.getFileSystem(job.getConfiguration()).delete(output_dir,true);
		    }catch(Exception e){
		    	System.out.println("Could not remove the file");
		    }
				
			job.waitForCompletion(true);
	          System.out.println("StubDriver.runMe() ends...");
	  }

  public static void main(String[] args) throws Exception {
	  runMe();

	/*  Path input_dir=new Path("hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweets");
	    Path output_dir=new Path("hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput");
	    
	    Configuration conf = new Configuration();
	   conf.set("fs.default.name", "hdfs://localhost:54310");
	    conf.set("mapred.job.tracker", "localhost:54311");
	    conf.set("fs.defaultFS", "hdfs://quickstart.cloudera:8020");
	    conf.set("mapred.job.tracker", "localhost:8021");
	    
	    //create your own jar of the project,store in in local location and specify that location.This is needed for cluster frun
	    conf.set("mapred.jar","/home/cloudera/Desktop/TwitterApp/SentmentDetection.jar");//Create the jar of your own project and specify it here
		   
	    Job job = new Job(conf, "SentimentAnalysis"); //Configuration is not necessary
	   // job.setJobName("MyWordCountJob");
	   
		//Number doesnot depend on split number
	   job.setNumReduceTasks(13);//cause data to be processed by multiple partitioner,no effect on local mode,can b any figure
		
		job.setJarByClass(StubDriver.class);
		job.setJobName("SentimentAnalysis");
		FileInputFormat.addInputPath(job, input_dir);
		FileOutputFormat.setOutputPath(job, output_dir);
		job.setMapperClass(StubMapper.class);
		
		job.setPartitionerClass(StubPartitioner.class);
		//job.setNumReduceTasks(4);
			
		
		//Reducer code gets executed only at one place,combiner gets executed for all other case
		job.setReducerClass(StubReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
	   // output_dir.getFileSystem(job.getConfiguration()).delete(output_dir,true);
		try{	
			 System.out.println("Cleaning up earlier directory");
			 output_dir.getFileSystem(job.getConfiguration()).delete(output_dir,true);
			    }catch(Exception e){
			    	System.out.println("Could not remove the file");
			    }
			
		System.exit(job.waitForCompletion(true) ? 0 : 1);*/
  }
}

