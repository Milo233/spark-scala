package com.milo.spark

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *  spark streaming  处理Socket数据
  *  netcat提供数据 https://www.jianshu.com/p/3beea7de5d16
  *  windows 启动netcat： .\nc -l -p 6789
  *  也可以在虚拟机里使用netcat
  */
object NetworkWordCount {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")

    //创建StreamingContext需要两个参数：SparkConf和batch interval
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    val lines = ssc.socketTextStream("192.168.1.103", 6789)

    // 按行读取数据，并按空格split，对得到的单词计数，不累积
    val result = lines.flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_)

    result.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
