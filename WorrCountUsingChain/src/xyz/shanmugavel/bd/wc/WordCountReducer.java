package xyz.shanmugavel.bd.wc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	private final static IntWritable SUM = new IntWritable(0);

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {
		int total = 0;
		for (IntWritable value : values) {
			total += value.get();
		}
		SUM.set(total);
		context.write(key,  SUM);
	}

}
