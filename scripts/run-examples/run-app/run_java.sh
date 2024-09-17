EXAMPLES_ROOT=../../../../modules/ROOT/examples
CONNECTOR_VERSION=$(grep -o "exact-connector-version: .\+" ../../../antora.yml | grep -o "\d\+\.\d\+\.\d\+")
SCALA_VERSION=$(grep -o "scala-version: .\+" ../../../antora.yml | grep -o "\d\+\.\d\+")

mkdir -p java-example
cd java-example

cp $EXAMPLES_ROOT/java/pom.xml .
cp $EXAMPLES_ROOT/example.jsonl .

mkdir -p src/main/java
cp $EXAMPLES_ROOT/java/SparkApp.java src/main/java/

mvn package

$SPARK_HOME/bin/spark-submit \
  --packages org.neo4j:neo4j-connector-apache-spark_${SCALA_VERSION}:${CONNECTOR_VERSION}_for_spark_3 \
  --class SparkApp \
  target/spark-app-1.0.jar
