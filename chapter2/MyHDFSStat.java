import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import java.net.URI;

public class MyHDFSStat extends Configured implements Tool {
  public int run(String[] args) throws Exception {
    String uri = null;
    if (args.length > 0) {
      uri = args[0];
    }
    Configuration conf = this.getConf();
    FileSystem fs = FileSystem.get(URI.create(uri), conf);

    // Obtain file stat
    FileStatus status = fs.getFileStatus(new Path(uri));
    System.out.printf("path: %s\n", status.getPath());
    System.out.printf("length: %d\n", status.getLen());
    System.out.printf("access: %d\n", status.getAccessTime());
    System.out.printf("modified: %d\n", status.getModificationTime());
    System.out.printf("owner: %s\n",status.getOwner());
    System.out.printf("group: %s\n", status.getGroup());
    System.out.printf("permission: %s\n", status.getPermission());
    System.out.printf("replication: %d\n", status.getReplication());
    return 0;
  }

  public static void main(String[] args) throws Exception {
    int exitCode = ToolRunner.run(new MyHDFSStat(), args);
    System.exit(exitCode);
  }
}
