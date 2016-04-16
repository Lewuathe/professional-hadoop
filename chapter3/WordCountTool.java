import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

class WordCountTool extends Configured implements Tool {
  public int run(String[] strings) throws Exception {
    Configuration conf = this.getConf();

    // Obtain input path and output path from command line options
    String inputPath = conf.get("input_path", "/input");
    String outputPath = conf.get("output_path", "/output");
    Job job = Job.getInstance(conf, conf.get("app_name"));
    
    job.setJarByClass(WordCount.class);
    job.setMapperClass(TokenizerMapper.class);
    job.setCombinerClass(CountSumReducer.class);
    job.setReducerClass(CountSumReducer.class);
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(IntWritable.class);
    FileInputFormat.addInputPath(job, new Path(inputPath));
    FileOutputFormat.setOutputPath(job, new Path(outputPath));
    return job.waitForCompletion(true) ? 0 : 1;
  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new WordCountTool(), args);
    System.exit(exitCode);
  }
}
