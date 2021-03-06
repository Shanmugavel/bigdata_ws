/**
 * 
 */
package xyz.shanmugavel.bd.movielens;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
public class Ratings {

	private static final String JOB_NAME = "RATINGS_FINDER";

	// ./bin/hadoop jar ~/Desktop/ratings.jar xyz.shanmugavel.bd.movielens.Ratings ~/Workarea/Workspace/data/ml-100k/u.data  ~/Documents/bigdata_ws/MovieLens/result/

	
	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, JOB_NAME);
		job.setJarByClass(Ratings.class);
		job.setMapperClass(RatingsMapper.class);
		job.setReducerClass(RatingsReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : -1);
	}

}
