/**
 * 
 */
package xyz.shanmugavel.bd.wc;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.chain.ChainMapper;
import org.apache.hadoop.mapreduce.lib.chain.ChainReducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * @author shanmugavelsundaramoorthy
 *
 */
public class WordCountUsingChain {
	
	private static final String JOB_NAME = "WORD_COUNT_USING_CHAIN";

	// ./bin/hadoop jar ~/Desktop/wcusingchain.jar xyz.shanmugavel.bd.wc.WordCountUsingChain ~/Workarea/Workspace/data/word_count/book.txt ~/Documents/bigdata_ws/WorrCountUsingChain/result/


	/**
	 * @param args
	 * @throws IOException 
	 * @throws InterruptedException 
	 * @throws ClassNotFoundException 
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, JOB_NAME);
		job.setJarByClass(WordCountUsingChain.class);
		
		Path outputPath = new Path(args[1]);
        FileSystem  fs = FileSystem.get(new URI(outputPath.toString()), conf);
        //It will delete the output directory if it already exists. don't need to delete it  manually  
        fs.delete(outputPath, true);
        
        JobConf tokenizerMapConf = new JobConf(false);
		ChainMapper.addMapper(job, TokenizerMapper.class, LongWritable.class, Text.class, Text.class, IntWritable.class, tokenizerMapConf);
		
        JobConf caseMapConf = new JobConf(false);
		ChainMapper.addMapper(job, LowerCaseMapper.class, Text.class, IntWritable.class, Text.class, IntWritable.class, caseMapConf);
		
        JobConf reducerMapConf = new JobConf(false);
		ChainReducer.setReducer(job, WordCountReducer.class, Text.class, IntWritable.class, Text.class, IntWritable.class, reducerMapConf);
	
		//add other mappers here if required to run after reducer.
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : -1);

	}

}
