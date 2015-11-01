package xyz.shanmugavel.bd.social;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AverageAgeMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable> {
	
	private static final int TOTAL_NO_OF_ELEMENTS = 4;
	private IntWritable ageKey = new IntWritable();
	private IntWritable noOfFriends = new IntWritable();

	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String data = null;
		if (null != value) {
			data = value.toString();
		}
		StringTokenizer tokens = new StringTokenizer(data, ",");
		if(TOTAL_NO_OF_ELEMENTS == tokens.countTokens()) {
			String userId = tokens.nextToken();
			String userName = tokens.nextToken();
			int age = Integer.parseInt(tokens.nextToken());
			int friends = Integer.parseInt(tokens.nextToken());
			
			ageKey.set(age);
			noOfFriends.set(friends);
			context.write(ageKey, noOfFriends);
		} else {
			System.err.println("Invalid Input, skipping the record");
		}
	}

}
