EXAMPLES_ROOT=../../../../modules/ROOT/examples
CONNECTOR_VERSION=$(grep -o "exact-connector-version: .\+" ../../../antora.yml | grep -o "\d\+\.\d\+\.\d\+")
SCALA_VERSION=$(grep -o "scala-version: .\+" ../../../antora.yml | grep -o "\d\+\.\d\+")

mkdir -p scala-example
cd scala-example

cp $EXAMPLES_ROOT/scala/build.sbt .
cp $EXAMPLES_ROOT/example.jsonl .

mkdir -p src/main/scala
cp $EXAMPLES_ROOT/scala/SparkApp.scala src/main/scala/

sbt package

$SPARK_HOME/bin/spark-submit \
  --packages org.neo4j:neo4j-connector-apache-spark_${SCALA_VERSION}:${CONNECTOR_VERSION}_for_spark_3 \
  --class SparkApp \
  target/scala-2.12/spark-app_2.12-1.0.jar
