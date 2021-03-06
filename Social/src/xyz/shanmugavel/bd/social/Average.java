/**
 * 
 */
package xyz.shanmugavel.bd.social;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
public class Average {

	private static final String JOB_NAME = "AVERAGE_FRIENDS_FINDER";

	// ./bin/hadoop jar ~/Desktop/social.jar xyz.shanmugavel.bd.social.Average ~/Workarea/Workspace/data/social/fakefriends.csv  ~/Documents/bigdata_ws/Social/result/

	
	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, JOB_NAME);
		job.setJarByClass(Average.class);
		job.setMapperClass(AverageAgeMapper.class);
		job.setReducerClass(AverageAgeReducer.class);
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : -1);
	}

}
