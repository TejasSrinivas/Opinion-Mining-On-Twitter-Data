package com.hadoop;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class CleanUpHdfs {
	public static String cleanHdfsDir() throws MalformedURLException, IOException {
		System.out.println("CleanUpHdfs.cleanHdfsDir() begin");

		String hdfsFolder = "hdfs://quickstart.cloudera:8020/user/cloudera/flume/tweets";
		try {
			// pickup config files off classpath
			Configuration conf = new Configuration();
			// explicitely add other config files
			conf.addResource("/etc/hadoop/conf/core-site.xml");
			// create a FileSystem object needed to load file resources
			// load files and stuff below!

			// FileSystem hdfs =FileSystem.get(new Configuration());
			Path newInput_dir = new Path(hdfsFolder);
			System.out.println("To delete if exist:" + newInput_dir + "\n");
			FileSystem hdfs = FileSystem.get(
					URI.create(newInput_dir.toString()), conf);
			if (hdfs.exists(newInput_dir)) {
				System.out.println("Deleting old input dir from location:"
						+ hdfsFolder + "input/");
				hdfs.delete(newInput_dir, true); // Delete existing Directory
			}
			Thread.sleep(2000);
			System.out.println("Recreating input dir at location:" + hdfsFolder
					+ "input/");
			hdfs.mkdirs(newInput_dir);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Could not remove the file,Manually delete it");
		}

		System.out.println("CleanUp process completed");
		System.out.println("CleanUpHdfs.cleanHdfsDir() end");
		return hdfsFolder;
	}
}
