package xyz.shanmugavel.bd.movielens;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class RatingsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private static final IntWritable ONE = new IntWritable(1);
	private static final int TOTAL_NO_OF_ELEMENTS = 4;
	private Text ratingsKey = new Text();
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String data = null;
		if (null != value) {
			data = value.toString();
		}
		StringTokenizer tokens = new StringTokenizer(data);
		if(TOTAL_NO_OF_ELEMENTS == tokens.countTokens()) {
			String userId = tokens.nextToken();
			String itemId = tokens.nextToken();
			String rating = tokens.nextToken();
			String timestamp = tokens.nextToken();
			
			ratingsKey.set(rating);
			context.write(ratingsKey, ONE);
		} else {
			System.err.println("Invalid Input, skipping the record");
		}

	}

}
