package xyz.shanmugavel.bd.wc;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LowerCaseMapper extends Mapper<Text, IntWritable, Text, IntWritable> {

	private Text word = new Text();

	public void map(Text key, IntWritable value, Context context) throws IOException, InterruptedException {
		String data = null;
		if (null != key) {
			data = key.toString();
			word.set(data.toLowerCase());
			context.write(word, value);
		}
	}

}
