mkdir -p java-example
cd java-example

cp ../../../modules/ROOT/examples/java/pom.xml .
cp ../../../modules/ROOT/examples/example.jsonl .

mkdir -p src/main/java
cp ../../../modules/ROOT/examples/java/SparkApp.java src/main/java/

mvn package

$SPARK_HOME/bin/spark-submit \
  --packages org.neo4j:neo4j-connector-apache-spark_2.12:5.3.1_for_spark_3 \
  --class SparkApp \
  target/spark-app-1.0.jar
