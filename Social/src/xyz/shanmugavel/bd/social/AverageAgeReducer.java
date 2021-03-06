package xyz.shanmugavel.bd.social;

import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class AverageAgeReducer extends Reducer<IntWritable, IntWritable, IntWritable, FloatWritable> {

	public void reduce(IntWritable key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		float totalFriends = 0;
		FloatWritable avgFriends = new FloatWritable();
		int noOfSamples = 0;
		for (IntWritable value : values) {
			totalFriends += value.get();
			noOfSamples+=1;
		}
		avgFriends.set(totalFriends/noOfSamples);
		context.write(key, avgFriends);

	}

}
