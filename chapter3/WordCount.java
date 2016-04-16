import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCount {
  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "Word Count");
    job.setJarByClass(WordCount.class);
    // Setup Map task class
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(CountSumReducer.class);
    
    // Setup Reduce task class
    job.setReducerClass(CountSumReducer.class);

    // This is for output of reduce task
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    // Set input path of an application
    FileInputFormat.addInputPath(job, new Path("/input"));
    // Set output path of an application
    FileOutputFormat.setOutputPath(job, new Path("/output"));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
 }
}
