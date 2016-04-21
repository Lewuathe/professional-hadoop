# professional-hadoop
Professional Hadoop source code repository. This repository includes the source code written in chapter2 and chapter3.

## Chapter2

|class name|description|
|:-----:|:-----|
|MyHDFSCat|HDFS version of `cat` command|
|MyHDFSStat|Display stat info of HDFS file|


## Chapter3

|class name|description|
|:-----:|:-----|
|CountSumReducer|A reducer used by WordCount job which counts actual count of each word.|
|TokenizerMapper|A mapper used by WordCount job which tokenize input text.|
|WordCount|A mapreduce job which counts words in given text.|
|WordCountTool|A mapreduce job almost same to `WordCount but using `ToolRunner`.
