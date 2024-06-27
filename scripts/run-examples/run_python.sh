mkdir -p python-example
cd python-example

cp ../../../modules/ROOT/examples/example.jsonl .
cp ../../../modules/ROOT/examples/python/spark_app.py .

$SPARK_HOME/bin/spark-submit \
  --packages org.neo4j:neo4j-connector-apache-spark_2.12:5.3.1_for_spark_3 \
  spark_app.py
