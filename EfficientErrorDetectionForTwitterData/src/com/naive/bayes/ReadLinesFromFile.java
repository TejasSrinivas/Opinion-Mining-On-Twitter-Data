package com.naive.bayes;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReadLinesFromFile {
	public static String[] returnListOfListStrings(){
		List<String> listOfLines = new ArrayList<String>();
		try{
		FileInputStream fstream = new FileInputStream("/home/cloudera/Desktop/TwitterApp/stemmedData/afterStemming.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		//Read File Line By Line
		while ((strLine = br.readLine()) != null)   {
		  // Print the content on the console
		 // System.out.println (strLine);
			listOfLines.add(strLine);
		}

		//Close the input stream
		br.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		Object[] listOfObj = listOfLines.toArray();
		String[] listOfStr = new String[listOfObj.length];
		for(int i=0;i<listOfObj.length;i++){
			listOfStr[i]=(String) listOfObj[i];
		}
     return  listOfStr;
	}

}
