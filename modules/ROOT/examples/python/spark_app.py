from pyspark.sql import SparkSession

spark = SparkSession.builder.getOrCreate()

# Replace with the actual connection URI and credentials
url = "neo4j://localhost:7687"
username = "neo4j"
password = "password"

data = spark.read.json("example.jsonl")

(data.write.format("org.neo4j.spark.DataSource")
    .mode("Overwrite")
    .option("url", url)
    .option("authentication.basic.username", username)
    .option("authentication.basic.password", password)
    .option("labels", "Person")
    .option("node.keys", "name,surname")
    .save())

ds = (spark.read.format("org.neo4j.spark.DataSource")
    .option("url", url)
    .option("authentication.basic.username", username)
    .option("authentication.basic.password", password)
    .option("labels", "Person")
    .load())

ds.show()
