import org.apache.spark.sql.SparkSession

object SparkApp {
    def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
        
        val ds = spark.read.format("org.neo4j.spark.DataSource")
                .option("url", "neo4j://localhost:7687")
                .option("authentication.basic.username", "neo4j")
                .option("authentication.basic.password", "password")
                .option("labels", "Customer")
                .load

        ds.show
    }
}