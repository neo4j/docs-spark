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
df = (
    spark.read.format("org.neo4j.spark.DataSource")
    .option("relationship", "BOUGHT")
    .option("relationship.source.labels", "Person")
    .option("relationship.target.labels", "Product")
    .load()
)

df.show()
# end::code[]

# tag::check[]
# TODO: add read query to check
# end::check[]
