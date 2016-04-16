import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.io.InputStream;
import java.net.URI;

public class MyHDFSCat extends Configured implements Tools {
  pubilc int run(String[] args) throws Exception {
    String uri = null;
    // Target URI is given as first argument
    if (args.length > 0) {
      uri = args[0];
    }

    // Get the default configuration put on your HDFS clusters
    Configuration conf = this.getConf();
    FileSystem fs = FileSystem.get(URI.create(uri), conf); InputStream in = null;
    try {
      in = fs.open(new Path(uri));
      IOUtils.copyBytes(in, System.out, 4096, false);
    } finally {
        IOUtils.closeStream(in); }
        return 0;
    }

    public static void main(String[] args) throws Exception {
      int exitCode = ToolRunner.run(new MyHDFSCat(), args);
      System.exit(exitCode);
    }
  }
}
