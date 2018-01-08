package com.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class StubPartitioner extends Partitioner<Text, IntWritable> {
	public StubPartitioner() {
		System.out.println("ErrorDetection Partitioner()");
	}

	@Override
	public int getPartition(Text key, IntWritable value, int numReduceTasks) {
		// Default logic of HashPartitioner
		// return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;
		System.out.println(key.hashCode() + ":" + Integer.MAX_VALUE + ":"
				+ numReduceTasks);

		System.out.println("StubPartitioner.getPartition(-,-,-)");

		System.out.println(key + ":: " + value + ":" + numReduceTasks);
		//NullPointerException, nullpointerexception, Null Pointer Exception, null pointer exception, nullpointer exception, Nullpointerexception,
		
		/*if(containsIgnoreCase(key.toString(),":) flipkart")||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),"good"))||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),"nice"))||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),"very good"))||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),":)"))) return 0;
		else if(containsIgnoreCase(key.toString(),":( flipkart")||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),"worst"))||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),"bad"))||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),"very bad"))||(containsIgnoreCase(key.toString(),"flipkart") && containsIgnoreCase(key.toString(),":("))) return 1;
		else if(containsIgnoreCase(key.toString(),"flipkart")) return 2;
		else if(containsIgnoreCase(key.toString(),":) amazon")||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),"good"))||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),"nice"))||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),"very good"))||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),":)"))) return 3;
		else if(containsIgnoreCase(key.toString(),":( amazon")||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),"worst"))||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),"bad"))||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),"very bad"))||(containsIgnoreCase(key.toString(),"amazon") && containsIgnoreCase(key.toString(),":("))) return 4;
		else if(containsIgnoreCase(key.toString(),"amazon")) return 5;
		else if(containsIgnoreCase(key.toString(),":) snapdeal")||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),"good"))||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),"nice"))||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),"very good"))||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),":)"))) return 6;
		else if(containsIgnoreCase(key.toString(),":( snapdeal")||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),"worst"))||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),"bad"))||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),"very bad"))||(containsIgnoreCase(key.toString(),"snapdeal") && containsIgnoreCase(key.toString(),":("))) return 7;
		else if(containsIgnoreCase(key.toString(),"snapdeal")) return 8;	
		else{
			return 9;
		}*/
		if(containsIgnoreCase(key.toString(),"flipkart")) return 0;
		else if(containsIgnoreCase(key.toString(),"snapdeal")) return 1;
		else if(containsIgnoreCase(key.toString(),"amazon")) return 2;	
		else if(containsIgnoreCase(key.toString(),"ebay")) return 3;
		else if(containsIgnoreCase(key.toString(),"myntra")) return 4;
		else{
			return 5;
		}
	//	return 0;
	   }
	public static boolean containsIgnoreCase(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        int len = searchStr.length();
        int max = str.length() - len;
        for (int i = 0; i <= max; i++) {
            if (str.regionMatches(true, i, searchStr, 0, len)) {
                return true;
            }
        }
        return false;
    }
}