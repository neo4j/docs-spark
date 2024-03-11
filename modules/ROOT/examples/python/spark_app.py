from pyspark.sql import SparkSession

spark = SparkSession.builder.getOrCreate()

# Replace with the actual connection URI and credentials
url = "neo4j://localhost:7687"
username = "neo4j"
password = "password"

ds = (spark.read.format("org.neo4j.spark.DataSource")
    .option("url", url)
    .option("authentication.basic.username", username)
    .option("authentication.basic.password", password)
    .option("labels", "Person")
    .load())

ds.show()
