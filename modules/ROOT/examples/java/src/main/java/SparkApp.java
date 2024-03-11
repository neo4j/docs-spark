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

        // Replace with the actual connection URI and credentials
        String url = "neo4j://localhost:7687";
        String username = "neo4j";
        String password = "password";

        Dataset<Row> ds = spark.read().format("org.neo4j.spark.DataSource")
                .option("url", url)
                .option("authentication.basic.username", username)
                .option("authentication.basic.password", password)
                .option("labels", "Person")
                .load();

        ds.show();
    }
}
