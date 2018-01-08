package com.hadoop;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import com.stemmer.Stemming;

//Assume hdfs://localhost:54310/user/hduser/data/abc.txt has some data

public class CopyFromLocal {

	public static boolean copyToHDFS(String fileName)
			throws MalformedURLException, IOException {
        System.out.println("CopyFromLocal.copyToHDFS() starts...");
		String localPath = "/home/cloudera/Desktop/TwitterApp/TempRecordReader.txt";
		String hdfsPath = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweets/"
				+ fileName + ".txt";

		Configuration conf = new Configuration();

		InputStream in = new BufferedInputStream(new FileInputStream(localPath));

		// Pointer to HDFS
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		// Create a file called fruit1.txt in hdfs and will be reference by out
		OutputStream out = fs.create(new Path(hdfsPath));
		IOUtils.copyBytes(in, out, 4096, true);
        System.out.println("CopyFromLocal.copyToHDFS() ends...");
		return true;
	}

	public static boolean copyToHDFSStemmed(String fileName)
			throws MalformedURLException, IOException, InterruptedException {
        System.out.println("CopyFromLocal.copyToHDFSStemmed() starts...");
		Stemming.createStemmedFile();
		Thread.sleep(2000);
		String localPath ="/home/cloudera/Desktop/TwitterApp/TempStemmedRecordReader.txt";
		String hdfsPath = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweets/"
				+ fileName + ".txt";

		Configuration conf = new Configuration();

		InputStream in = new BufferedInputStream(new FileInputStream(localPath));

		// Pointer to HDFS
		FileSystem fs = FileSystem.get(URI.create(hdfsPath), conf);
		// Create a file called fruit1.txt in hdfs and will be reference by out
		OutputStream out = fs.create(new Path(hdfsPath));
		IOUtils.copyBytes(in, out, 4096, true);
        System.out.println("CopyFromLocal.copyToHDFSStemmed() ends...");
		return true;
	}
}
