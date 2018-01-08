package com.stemmer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hadoop.ErrorTO;

public class StemmerFeeder {
   public Map<String,String> getstemmedList(List<ErrorTO> list){
	   Map<String,String> stemmed = new HashMap<String,String>();
	   writeToUnstemmedList(list);
	   try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	   Stemming.createStemmedFile();
	   List<String> stemmedLines = getLinesFromStemmedFile();
	   Object[] objUnstemmed = list.toArray();
	   Object[] objStemmed = stemmedLines.toArray();
	   for(int i=0;i<list.size();i++){
		   if(i<stemmedLines.size()){
			   System.out.println("Unstemmed:"+objUnstemmed.toString()+"Stemmed:"+objStemmed.toString());
			   stemmed.put(list.get(i).getTweet(), stemmedLines.get(i));
		   }
	   }
	   return stemmed;
   }
   public boolean writeToUnstemmedList(List<ErrorTO> list){
	try {
	   File fout = new File("/home/cloudera/Desktop/TwitterApp/stemmedData/beforeStemming.txt");
		FileOutputStream fos;

			fos = new FileOutputStream(fout);

	 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
	 
		for (ErrorTO data:list) {
			bw.write(data.getTweet());
			bw.newLine();
		}
	 
		bw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return true;
   }
   public List<String> getLinesFromStemmedFile(){
	   List<String> lines = new ArrayList<>();
	   try{
	   File fin = new File("/home/cloudera/Desktop/TwitterApp/stemmedData/afterStemming.txt");
	   FileInputStream fis = new FileInputStream(fin);
	   
		//Construct BufferedReader from InputStreamReader
		BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	 
		String line = null;
		while ((line = br.readLine()) != null) {
			//System.out.println(line);
			lines.add(line);
		}
	 
		br.close();
	   }catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return lines;
   }
}
