// Databricks notebook source
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.log4j.{Level,Logger}


// COMMAND ----------

 val fileCsv = "/FileStore/tables/users.csv"

// COMMAND ----------

val df = spark.read
      .option("header", "true")
      .option("inferSchema", "true")
      .csv(fileCsv)



// COMMAND ----------

object UserProcessing {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("User Processing")
      .master("local[*]")
      .getOrCreate()

    import spark.implicits._

        // 1. Filtrer les utilisateurs âgés de 25 ans et plus
    val filteredDf = df.filter($"age" >= 25)

    // 2. Transformer les données pour extraire les noms et les villes
    val transformedDf = filteredDf.select("name", "city")

    // 3. Grouper les utilisateurs par ville
    val groupedDf = transformedDf.groupBy("city").count()

    // Afficher le résultat
    filteredDf.show()
    transformedDf.show()
    groupedDf.show()


  }
}

// COMMAND ----------

UserProcessing.main(Array())

