package xyz.shanmugavel.bd.wc;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class TokenizerMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	private final static IntWritable ONE = new IntWritable(1);
	private Text word = new Text();
	
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String data = null;
		if (null != value) {
			data = value.toString();
		}
		StringTokenizer tokens = new StringTokenizer(data);
		while (tokens.hasMoreTokens()) {
			word.set(tokens.nextToken());
			context.write(word, ONE);
		}
	}

}
