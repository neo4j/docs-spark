import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

public class SparkApp {
    public static void main(String[] args) {
        SparkSession spark = SparkSession
                .builder()
                .appName("Spark App")
                .config("spark.master", "local")
                .getOrCreate();

        Dataset<Row> ds = spark.read().format("org.neo4j.spark.DataSource")
                .option("url", "neo4j://localhost:7687")
                .option("authentication.basic.username", "neo4j")
                .option("authentication.basic.password", "password")
                .option("labels", "Customer")
                .load();

        ds.show();
    }
}
