// tag::setup[]
import org.apache.spark.sql.{SaveMode, SparkSession}

// Replace with the actual connection URI and credentials
val url = "neo4j://localhost:7687"
val username = "neo4j"
val password = "password"

val spark = SparkSession.builder
  .config("neo4j.url", url)
  .config("neo4j.authentication.basic.username", username)
  .config("neo4j.authentication.basic.password", password)
  .getOrCreate()
// end::setup[]

// tag::code[]
val df = spark.read
  .format("org.neo4j.spark.DataSource")
  .option("labels", ":Person")
  .load()

df.show()
// end::code[]

// tag::check[]
// TODO: add read query to check
// end::check[]
