# tag::setup[]
from pyspark.sql import SparkSession

# Replace with the actual connection URI and credentials
url = "neo4j://localhost:7687"
username = "neo4j"
password = "password"

spark = (
    SparkSession.builder.config("neo4j.url", url)
    .config("neo4j.authentication.basic.username", username)
    .config("neo4j.authentication.basic.password", password)
    .getOrCreate()
)
# end::setup[]

# tag::code[]
# Create example DataFrame
df = spark.createDataFrame(
    [
        {"name": "John", "surname": "Doe", "age": 42},
        {"name": "Jane", "surname": "Doe", "age": 40},
    ]
)

(
    df.write.format("org.neo4j.spark.DataSource")
    .mode("Append")
    .option("labels", ":Person:Customer")
    .save()
)
# end::code[]

# tag::check[]
# TODO: add read query to check
# end::check[]
