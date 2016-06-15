package main.scala

import com.cra.figaro.algorithm.sampling.Importance

import com.cra.figaro.language.Constant
import org.apache.spark.{SparkConf, SparkContext}
/**
  * Created by lhommet on 6/14/16.
  */
object testSetup {

  def main(args: Array[String]) {
    println("Test Figaro")
    val HelloFigaro = Constant("Hello Figaro")
    val inferenceSampler = Importance(1000, HelloFigaro)
    inferenceSampler.start()
    inferenceSampler.probability(HelloFigaro, "Hello Figaro")

    println("Test Figaro OK")

    println("Test Spark")
    val conf = new SparkConf()
      .setMaster("local[*]")
      .setAppName("Test Spark")
      .set("spark.executor.memory","2g")
    val sc = new SparkContext(conf)

    val lines = sc.parallelize(Seq("this is first line", "this is seconf line", "this is third line"))
    val counts = lines.flatMap(line => line.split(" "))
      .map(word => (word,1))
      .reduceByKey(_+_)

    println("Test Spark OK")
    println("Done!")
  }
}
