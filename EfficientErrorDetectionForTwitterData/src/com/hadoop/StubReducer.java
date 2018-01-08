package com.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class StubReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	public StubReducer() {
		System.out.println("StubReducer()");
	}

	@Override
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		System.out.println("StubReducer.reduce(-,-,-)");
		System.out.println("Key=" + key);
		System.out.println("Values...");
		int maxValue = Integer.MIN_VALUE;
		int sum = 0;
		for (IntWritable value : values) {
			System.out.println("" + value.get());
			sum = sum + value.get();
			maxValue = Math.max(maxValue, sum);
		}
		context.write(key, new IntWritable(sum));
	}
}