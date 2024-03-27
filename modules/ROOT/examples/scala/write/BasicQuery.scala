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
case class Person(name: String, surname: String, age: Int)

// Create an example DataFrame
val df = Seq(
  Person("John", "Doe", 42),
  Person("Jane", "Doe", 40)
).toDF()

// Define the Cypher query to use in the write
val query = "CREATE (n:Person {fullName: event.name + ' ' + event.surname})"

df.write
  .format("org.neo4j.spark.DataSource")
  .option("query", query)
  .mode(SaveMode.Overwrite)
  .save()
// end::code[]

// tag::check[]
// TODO: add read query to check
// end::check[]
