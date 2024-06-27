name := "Spark App"
version := "1.0"
scalaVersion := "2.12.18"

libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.5.1"
libraryDependencies += "org.neo4j" %% "neo4j-connector-apache-spark" % "5.3.1_for_spark_3"