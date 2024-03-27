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
        {"name": "John Bonham", "instrument": "Drums", "experience": 12},
        {"name": "John Mayer", "instrument": "Guitar", "experience": 19},
        {"name": "John Scofield", "instrument": "Guitar", "experience": 32},
        {"name": "John Butler", "instrument": "Guitar", "experience": 15},
    ]
)

(
    df.write
    # Create new relationships
    .mode("Append")
    .format("org.neo4j.spark.DataSource")
    # Assign a type to the relationships
    .option("relationship", "PLAYS")
    # Use `keys` strategy
    .option("relationship.save.strategy", "keys")
    .option("relationship.properties", "experience:experience")
    # Create source nodes and assign them a label
    .option("relationship.source.save.mode", "Append")
    .option("relationship.source.labels", ":Musician")
    # Map the DataFrame columns to node properties
    .option("relationship.source.node.properties", "name:name")
    # Create target nodes and assign them a label
    .option("relationship.target.save.mode", "Append")
    .option("relationship.target.labels", ":Instrument")
    # Map the DataFrame columns to node properties
    .option("relationship.target.node.properties", "instrument:name")
    .save()
)
# end::code[]

# tag::check[]
# TODO: add read query to check
# end::check[]
