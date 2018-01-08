package com.stemmer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.tartarus.martin.Stemmer;

public class Stemming {
	
	public static void createStemmedFile() {
		System.out.println("Stemming.createStemmedFile begin..");
		 char[] w = new char[501];
	      Stemmer s = new Stemmer();
	      try
	      {
	         FileInputStream in = new FileInputStream("/home/cloudera/Desktop/TwitterApp/stemmedData/beforeStemming.txt");
	         File toWrite = new File("/home/cloudera/Desktop/TwitterApp/stemmedData/afterStemming.txt");
	         FileWriter fileWriter = new FileWriter(toWrite);
	     	 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
	         try
	         { while(true)

	           {  int ch = in.read();
	              if (Character.isLetter((char) ch))
	              {
	                 int j = 0;
	                 while(true)
	                 {  ch = Character.toLowerCase((char) ch);
	                    w[j] = (char) ch;
	                    if (j < 500) j++;
	                    ch = in.read();
	                    if (!Character.isLetter((char) ch))
	                    {
	                       /* to test add(char ch) */
	                       for (int c = 0; c < j; c++) s.add(w[c]);

	                       /* or, to test add(char[] w, int j) */
	                       /* s.add(w, j); */

	                       s.stem();
	                       {  String u;

	                          /* and now, to test toString() : */
	                          u = s.toString();

	                          /* to test getResultBuffer(), getResultLength() : */
	                          /* u = new String(s.getResultBuffer(), 0, s.getResultLength()); */

	                         // System.out.print(u);
	                          bufferedWriter.write(u);
	                       }
	                       break;
	                    }
	                 }
	              }
	              if (ch < 0) break;
	             // System.out.print((char)ch);
	              bufferedWriter.write((char)ch);
	           }
	         }
	         catch (IOException e)
	         {  System.out.println("error reading \"/home/cloudera/Desktop/TwitterApp/stemmedData/beforeStemming.txt\"");
	         }
	         bufferedWriter.close();
	      }
	      catch (FileNotFoundException e)
	      {  System.out.println("file \"/home/cloudera/Desktop/TwitterApp/stemmedData/beforeStemming.txt\" not found");
	      }
	      catch (IOException e1) {
				e1.printStackTrace();
			}
	      System.out.println("Stemming.createStemmedFile end..");
	}
}
