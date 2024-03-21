// tag::setup[]
import org.apache.spark.sql.{SaveMode, SparkSession}

// Replace with the actual connection URI and credentials
val url = "neo4j://localhost:7687"
val username = "neo4j"
val password = "password"
// end::setup[]

import spark.implicits._
import scala.util.Random

case class Point3d(`type`: String = "point-3d",
                   srid: Int,
                   x: Double,
                   y: Double,
                   z: Double)

case class Person(name: String, surname: String, age: Int, livesIn: Point3d)

val total = 10
val rand = Random
val df = (1 to total)
  .map(i => {
    Person(name = "Andrea " + i, "Santurbano " + i, rand.nextInt(100),
    Point3d(srid = 4979, x = 12.5811776, y = 41.9579492, z = 1.3))
  }).toDF()

df.write
  .format("org.neo4j.spark.DataSource")
  .mode(SaveMode.Append)
  .option("labels", ":Person:Customer")
  .save()
