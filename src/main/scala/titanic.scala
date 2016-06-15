package main.scala

/**
  * Created by lhommet on 6/14/16.
  */

import org.apache.spark.{SparkConf, SparkContext}


object titanic {
  def main(args: Array[String]) {
    val logFile = "/Users/lhommet/Downloads/spark-1.6.1-bin-hadoop2.6/README.md"
    val conf = new SparkConf().setAppName("Simple Application").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val logData = sc.textFile(logFile, 2).cache()
    val numA = logData.filter(line => line.contains("a")).count()
    val numB = logData.filter(line => line.contains("b")).count()
    println("Lines with a: %s, Lines with b: %s".format(numA, numB))
    println("DONE")
  }
}
