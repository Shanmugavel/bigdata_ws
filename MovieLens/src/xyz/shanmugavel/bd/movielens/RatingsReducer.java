package xyz.shanmugavel.bd.movielens;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class RatingsReducer extends Reducer<Text, IntWritable, Text, LongWritable> {

	private LongWritable count = new LongWritable();
	
	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		long sum = 0;
		for (IntWritable value : values) {
			sum += value.get();
		}
		count.set(sum);
		context.write(key, count);
	}

}
