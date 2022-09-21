import org.apache.spark.sql._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.streaming._
import org.apache.spark.sql.types._

case class PeageData(vehicule: String, position: String, payment: String, today: String, current_date: String)

object Main {
  def main(args: Array[String]): Unit = {

    println("----- Start Program ----")

    val topic_name = "devdata"

    val spark = SparkSession.builder
      .master("local[1]")
      .appName("Stream with Kafka")
      .getOrCreate()

    import spark.implicits._

    val df = spark.readStream
      .format("kafka")
      .option("kafka.bootstrap.servers", "localhost:9092")
      .option("subscribe", topic_name)
      .load()

    // CAST DATA BYTE TO STRING
    val castDF = df.selectExpr("CAST(value as STRING)").as[String]

    // SPLIT DATA WITH ","
    val splitDF = castDF.map((row) => row.split(','))
      .map(line => PeageData(
        line(0),
        line(1),
        line(2),
        line(3),
        line(4)
      ))

    // SHOW DATA ON CONSOLE EVERY 3seconds
    val query = splitDF.writeStream.outputMode("update").format("console").trigger(Trigger.ProcessingTime("3 seconds")).start()

    query.awaitTermination()


    println("----- Fin Program ----")

  }
}