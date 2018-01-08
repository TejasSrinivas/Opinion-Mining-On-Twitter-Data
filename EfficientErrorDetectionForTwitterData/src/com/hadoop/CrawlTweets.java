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
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import com.hadoop.ErrorTO;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;

public class CrawlTweets {
	static BufferedWriter br = null;
	CrawlTweets(){
	 try {
		br = new BufferedWriter(new FileWriter("/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
    public static void main(String[] str){
    	//getTweeterData("amazon", ":(");
    }

    /**
     * @param args
     */
   // public static void main(String[] args) throws Exception {
    public static String getTweeterData(String org){
        /** 
         * Usage: java twitter4j.examples.search.SearchTweets [query] 
         * 
         * @param args search query 
         */ 
        System.out.println("CrawlTweets.getTweeterData() starts...");
   	  String consumerKeyStr1 = "oK3dlZ826nkpdN4e48ahxhXzi";
   		 String consumerSecretStr1 = "R4F7VrQTmucdb0rXYbw2eeuobRoHi2j1JwFNBWLVBk2nwoarFs";
   		 String accessTokenStr1 = "313148724-EBtpGlwWqkgTdOnqFnJ2gNHrK4uHjoiySCNRkBe5";
   		 String accessTokenSecretStr1 = "F5zulF58vBcL4f0rTYV7kIY68at6gyDsELYa2V6827Io5";

   		ConfigurationBuilder cb = new ConfigurationBuilder();
   	    cb.setJSONStoreEnabled(true);
   	    
       	 
            String tweetSearch=org;
            Twitter twitter = new TwitterFactory(cb.build()).getInstance();

   			twitter.setOAuthConsumer(consumerKeyStr1, consumerSecretStr1);
   			AccessToken accessToken = new AccessToken(accessTokenStr1,
   					accessTokenSecretStr1);

   			twitter.setOAuthAccessToken(accessToken);

   			 List<String> toWriteTweets = new ArrayList<String>();
            try { 
                Query query = new Query(tweetSearch); 
                QueryResult result; 
                do { 
                    result = twitter.search(query); 
                    List<Status> tweets = result.getTweets(); 
                    
                    for (Status tweet : tweets) {
                    String json = DataObjectFactory.getRawJSON(tweet);
                    toWriteTweets.add(json);
                       System.out.println(json); 
                      // System.out.println("---------------------------------------------");
                    } 
                } while ((query = result.nextQuery()) != null); 
               // System.exit(0); 
            } catch (TwitterException te) { 
                te.printStackTrace(); 
                System.out.println("Failed to search tweets: " + te.getMessage()); 
                //System.exit(-1); 
            } 
            try {
            	CleanUpHdfs.cleanHdfsDir();
				Thread.sleep(1000);			
				writeDataToFile(toWriteTweets);
				System.out.println("Copied to hdfs:"+CopyFromLocal.copyToHDFS(org));
			//CopyFromLocal.copyToHDFSStemmed(org);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //System.exit(0); 
            try {

				StubDriver.runMe();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("CrawlTweets.getTweeterData() ends...");
            return null;
        } 
    
	public static List<ErrorTO> getDetails(String storename) {
        System.out.println("CrawlTweets.getDetails() starts...");
		String url = null;
		List<ErrorTO> errorList = new ArrayList<ErrorTO>();
		OutputStream file = null;
		System.out.println("Store name is:::::::::::::::"+ storename);
		try {
			if (storename.equalsIgnoreCase("Macys")) {
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
			/*file = new OutputStream(
					"/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt");*/

			IOUtils.copyBytes(in, fs1, 4096, false);
			IOUtils.closeStream(in);
			errorList = readDataFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try{
			File toWrite = null;
			if (storename.equalsIgnoreCase("Snapdeal"))
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
			
			/*FileReader fileReader = new FileReader(toWrite);
			
			BufferedReader br = new BufferedReader(fileReader);
			String noOfRecords = br.readLine();
			int prevRecd = Integer.parseInt(noOfRecords);*/
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
        System.out.println("CrawlTweets.getDetails() ends...");
		return errorList;
	}
	public static List<ErrorTO> readDataFromFile() throws FileNotFoundException, IOException{
        System.out.println("CrawlTweets.readDataFromFile() starts...");
		List<ErrorTO> errorList = new ArrayList<ErrorTO>();
		
		try{
				BufferedReader br = new BufferedReader(new FileReader("/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt"));
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
        System.out.println("CrawlTweets.readDataFromFile() ends...");
		return errorList;
		
	}
	
	public static boolean writeDataToFile(List<String> lines) throws FileNotFoundException, IOException{
		BufferedWriter bw = null;
		FileWriter fw = null;
		StringBuffer content = new StringBuffer();
		for(String line:lines){
			content.append(line+"\n");
		}

		try {

			String contentString = content.toString();

			fw = new FileWriter("/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt");
			bw = new BufferedWriter(fw);
			bw.write(contentString);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return false;
		
	}
}
