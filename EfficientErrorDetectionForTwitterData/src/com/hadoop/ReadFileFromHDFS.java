package com.hadoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class ReadFileFromHDFS {
	public static void main(String[] args) throws MalformedURLException,
			IOException {

		ReadFileFromHDFS r = new ReadFileFromHDFS();
	//	r.getDetails("Snapdeal");

	}

/*	public static List<ErrorTO> getDetails(String storename) {
        System.out.println("ReadFileFromHDFS.getDetails() starts...");
		String url = null;
		List<ErrorTO> errorList = new ArrayList<ErrorTO>();
		OutputStream file = null;
		System.out.println("Store name is:::::::::::::::"+ storename);
		try {
			if (storename.equalsIgnoreCase("Snapdeal")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00009";
			} else if (storename.equalsIgnoreCase("Amazon")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00010";
			} else if (storename.equalsIgnoreCase("Wallmart")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00011";
			} else {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00012";
			}
			// reads all the property from configuration files to enable user
			// interacting with HDFS
			Configuration conf = new Configuration();

			// accepts url and configuraation and creates a FileSystem Out of it
			FileSystem fs = FileSystem.get(URI.create(url), conf);

			// Create an inputStream pointing to the file represented by Path
			// File f=new File("abc.txt"); Java Path p=new Path("abc.txt");
			// //hadoop
			InputStream in = fs.open(new Path(url)); // return FSDataInputStream
														// containing
														// DFSDataInputStreaming
			FileOutputStream fs1 = new FileOutputStream(new File("/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt"));
			file = new OutputStream(
					"/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt");

			IOUtils.copyBytes(in, fs1, 4096, false);
			IOUtils.closeStream(in);
			errorList = readDataFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			File toWrite = null;
			if (storename.equalsIgnoreCase("Macys"))
			{
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/snapdeal.txt");
			}
			else if (storename.equalsIgnoreCase("Amazon"))
			{
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/amozon.txt");
			}
			else if (storename.equalsIgnoreCase("Wallmart"))
			{
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/wallmart.txt");
			}
			else{
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/other.txt");
			}
			
			FileReader fileReader = new FileReader(toWrite);
			
			BufferedReader br = new BufferedReader(fileReader);
			String noOfRecords = br.readLine();
			int prevRecd = Integer.parseInt(noOfRecords);
			int currRecord = errorList.size();
			//currRecord = currRecord + prevRecd;
			System.out.println("numbr of records="+currRecord);
			FileWriter fileWriter = new FileWriter(toWrite);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(new Integer(currRecord).toString());
			bufferedWriter.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
        System.out.println("ReadFileFromHDFS.getDetails() ends...");
		return errorList;
	}*/
	public static List<ErrorTO> getDetailsSentiment(String storename) {
        System.out.println("ReadFileFromHDFS.getDetails(storename,category) starts...");
		String url = null;
		System.out.println("Store name is:::::::::::::::"+ storename);
		List<ErrorTO> errorList = new ArrayList<ErrorTO>();
		FileOutputStream file = null;
		try {
			if (storename.equalsIgnoreCase("flipkart")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00000";
			}else if (storename.equalsIgnoreCase("snapdeal")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00001";
			}
			else if (storename.equalsIgnoreCase("amazon")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00002";
			} else if (storename.equalsIgnoreCase("ebay")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00003";
			}else if (storename.equalsIgnoreCase("myntra")) {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00004";
			}
			else {
				url = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweetsoutput/part-r-00005";
			}
			
			
			
			// reads all the property from configuration files to enable user
			// interacting with HDFS
			Configuration conf = new Configuration();

			// accepts url and configuraation and creates a FileSystem Out of it
			FileSystem fs = FileSystem.get(URI.create(url), conf);

			// Create an inputStream pointing to the file represented by Path
			// File f=new File("abc.txt"); Java Path p=new Path("abc.txt");
			// //hadoop
			InputStream in = fs.open(new Path(url)); // return FSDataInputStream
														// containing
														// DFSDataInputStreaming
														// DFSDataInputStreaming
			FileOutputStream fs1 = new FileOutputStream(new File("/home/cloudera/Desktop/TwitterApp/TempReducedRecordReader.txt"));
			/*file = new OutputStream(
					"/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt");*/

			IOUtils.copyBytes(in, fs1, 4096, false);
			IOUtils.closeStream(in);
			errorList = readDataFromFile();
			System.out.println("Number Of records Fetched"+errorList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			File toWrite = null;
			if (storename.equalsIgnoreCase("flipkart"))
			{
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/ForGraph/flipkart.txt");
			}
			else if(storename.equalsIgnoreCase("snapdeal") ){
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/ForGraph/snapdeal.txt");
			}
			else if(storename.equalsIgnoreCase("amazon")){
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/ForGraph/amazon.txt");
			}
			else if (storename.equalsIgnoreCase("ebay"))
			{
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/ForGraph/ebay.txt");
			}
			else if(storename.equalsIgnoreCase("myntra")){
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/ForGraph/myntra.txt");
			}
			else{
				 toWrite = new File("/home/cloudera/Desktop/TwitterApp/ForGraph/other.txt");
			}
			
			/*FileReader fileReader = new FileReader(toWrite);
			
			BufferedReader br = new BufferedReader(fileReader);
			String noOfRecords = br.readLine();
			int prevRecd = Integer.parseInt(noOfRecords);*/
			int currRecord = errorList.size();
			//currRecord = currRecord + prevRecd;
			
			FileWriter fileWriter = new FileWriter(toWrite);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(new Integer(currRecord).toString());
			bufferedWriter.close();
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
        System.out.println("ReadFileFromHDFS.getDetails(storename,category) ends...");
		return errorList;
	}
	
	public static List<ErrorTO> readDataFromFile() throws FileNotFoundException, IOException{
		List<ErrorTO> errorList = new ArrayList<ErrorTO>();
		
		try{
				BufferedReader br = new BufferedReader(new FileReader("/home/cloudera/Desktop/TwitterApp/TempReducedRecordReader.txt"));
		    String line=null;
		   // System.out.println("Lines to be printed:::::::::::");
		    while ((line = br.readLine()) != null) {
		    	ErrorTO  errorobj = new ErrorTO();
		      // System.out.println(line);
		       String datas[] = line.toString().split("qqqqq");
		      // StringTokenizer str = new StringTokenizer(line, "$$$$$");
		       //while(str.hasMoreTokens())
		       try{
			       errorobj.setTweet(datas[0]);
			       StringTokenizer str = new StringTokenizer(datas[1],"\"");
			       errorobj.setTweetedOn(str.nextToken());
			       StringTokenizer str1 = new StringTokenizer(datas[2],"\"");
		           errorobj.setTweetedBy(str1.nextToken());
		       }catch(Exception e){
		    	   e.printStackTrace();
		       }
		       errorList.add(errorobj);
		    }
		}catch(Exception e){
			e.printStackTrace();
		}
		return errorList;
		
	}
}
