package com.naive.bayes;

import java.util.HashMap;
import java.util.Map;

public class GetConfusionMatrix {

	public static void main(String[] args){
		GetConfusionMatrix gcm = new GetConfusionMatrix();
		gcm.getMatrixParams();
	}
	public Map<String,Integer> getMatrixParams() {
	    Map<String,Integer> ratio = new HashMap<String,Integer>();
		TextClassifier1 tcf =  new TextClassifier1();
		String  matrix = tcf.getConfusionMatrix();
        System.out.println("===================");
        System.out.println(matrix);
        String[] arr = matrix.split("\n");
        System.out.println("===================");
        System.out.println("1"+arr[0]+"2"+arr[1]+"3"+arr[2]);
        System.out.println("satisfied:"+arr[3].substring(0, 2));
        System.out.println("neutral:"+arr[4].substring(2, 4));
        System.out.println("dissatisfied:"+arr[5].substring(4, 6));
        ratio.put("satisfied",Integer.valueOf(arr[3].substring(1, 2)));
        ratio.put("neutral",Integer.valueOf(arr[4].substring(3, 4)));
        ratio.put("dissatisfied",Integer.valueOf(arr[5].substring(5, 6)));
        return ratio;
	}

}
