import org.apache.spark.sql.SparkSession

object Connected {
  def main(args: Array[String]): Unit = {

    println("----- Start Program ----")

    val topic_name = "devdata"

    val spark: SparkSession = SparkSession.builder()
      .master("local[1]")
      .appName("Streaming Kafka")
      .getOrCreate()

    val df = spark
      .read
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", topic_name)
      .load()

    df.printSchema()

    println("----- End Program ----")

  }
}
