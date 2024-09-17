mkdir -p scala-example
cd scala-example

cp ../../../modules/ROOT/examples/scala/build.sbt .
cp ../../../modules/ROOT/examples/example.jsonl .

mkdir -p src/main/scala
cp ../../../modules/ROOT/examples/scala/SparkApp.scala src/main/scala/

sbt package

$SPARK_HOME/bin/spark-submit \
  --packages org.neo4j:neo4j-connector-apache-spark_2.12:5.3.2_for_spark_3 \
  --class SparkApp \
  target/scala-2.12/spark-app_2.12-1.0.jar
