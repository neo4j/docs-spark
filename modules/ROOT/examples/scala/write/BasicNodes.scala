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
import spark.implicits._
import scala.util.Random

case class Person(name: String, surname: String, age: Int)

val total = 10
val rand = Random
val df = (1 to total)
  .map(i => {
    Person(
      name = "Andrea " + i,
      "Santurbano " + i,
      rand.nextInt(100)
    )
  })
  .toDF()

df.write
  .format("org.neo4j.spark.DataSource")
  .mode(SaveMode.Append)
  .option("labels", ":Person:Customer")
  .save()
// end::code[]

// tag::check[]
// TODO: add read query to check
// end::check[]