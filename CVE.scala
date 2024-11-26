package fr.umontpellier.polytech.ig5;

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession, functions => F}

object CVE extends App {
  val startTime = System.nanoTime()

  // Set logging level to reduce unnecessary logs
  Logger.getLogger("org").setLevel(Level.ERROR)

  // Define the years to process
  val years = (2002 to 2024).toList

  // Initialize Spark session
  val spark = SparkSession.builder
    .appName("CVE Application")
    .master("local[*]")
    .getOrCreate()

  spark.sparkContext.setLogLevel("ERROR")

  def processJsonFile(filePath: String): DataFrame = {
    val df = spark.read.option("multiline", "true").json(filePath)

    // Explode the CVE_Items array and extract relevant fields
    df.select(F.explode(F.col("CVE_Items")).alias("cve"))
      .select(
        F.col("cve.cve.CVE_data_meta.ID").alias("ID"),
        F.expr("cve.cve.description.description_data[0].value").alias("Description"),
        F.col("cve.impact.baseMetricV3.cvssV3.baseScore").alias("baseScore"),
        F.col("cve.impact.baseMetricV3.cvssV3.baseSeverity").alias("baseSeverity"),
        F.col("cve.impact.baseMetricV3.exploitabilityScore").alias("exploitabilityScore"),
        F.col("cve.impact.baseMetricV3.impactScore").alias("impactScore")
      )
  }

  // Process all JSON files and combine the results into a single DataFrame
  val allTransformedData: DataFrame = years
    .map(year => s"data-in/nvdcve-1.1-$year.json")
    .map(processJsonFile)
    .reduce(_ union _)

  println("Give Transformed Data Schema:")
  allTransformedData.printSchema()

  println("Sample Transformed Data:")
  allTransformedData.show(5, truncate = false)

  val outputFile = "data-out/transformed_data.json"
  allTransformedData
    .coalesce(1)
    .write
    .mode("overwrite")
    .json(outputFile)

  val endTime = System.nanoTime()
  println(s"Program executed in ${(endTime - startTime) / 1e9} seconds")
}
