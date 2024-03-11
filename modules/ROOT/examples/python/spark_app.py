from pyspark.sql import SparkSession

spark = SparkSession.builder.getOrCreate()

# Replace with the actual connection URI and credentials
url = "neo4j://localhost:7687"
username = "neo4j"
password = "password"

df = spark.createDataFrame([
    {"name": "John", "surname": "Doe", "age": 32},
    {"name": "Jane", "surname": "Doe", "age": 42}
])

(df.write.format("org.neo4j.spark.DataSource")
    .mode("Overwrite")
    .option("url", url)
    .option("authentication.basic.username", username)
    .option("authentication.basic.password", password)
    .option("labels", ":Person")
    .option("node.keys", "name,surname")
    .save())
