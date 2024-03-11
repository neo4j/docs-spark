name := "Spark App"

version := "1.0"

scalaVersion := "2.12.18"

val sparkVersionMajor = "3"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.1"
libraryDependencies += "org.neo4j" %% "neo4j-connector-apache-spark" % s"5.3.0_for_spark_${sparkVersionMajor}"