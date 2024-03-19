import org.apache.spark.sql.{SaveMode, SparkSession}

object SparkApp {
    def main(args: Array[String]): Unit = {
        val spark = SparkSession.builder.appName("Simple Application").getOrCreate

        // Replace with the actual connection URI and credentials
        val url = "neo4j://localhost:7687"
        val username = "neo4j"
        val password = "password"

        val data = spark.read.json("example.jsonl")

        data.write.format("org.neo4j.spark.DataSource")
            .mode(SaveMode.Overwrite)
            .option("url", url)
            .option("authentication.basic.username", username)
            .option("authentication.basic.password", password)
            .option("labels", "Person")
            .option("node.keys", "name,surname")
            .save
        
        val ds = spark.read.format("org.neo4j.spark.DataSource")
            .option("url", url)
            .option("authentication.basic.username", username)
            .option("authentication.basic.password", password)
            .option("labels", "Person")
            .load

        ds.show
    }
}