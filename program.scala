import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.{OutputMode, Trigger}
import org.apache.spark.sql.types.{StructType, StructField, StringType, IntegerType}

    // ====== Setar nosso diretório que será tratado com uma Stream de Dados
    val DIR = new java.io.File(".").getCanonicalPath + "/dataset/stream_in"

        // ==== Definindo o cluster do Spark
        val conf = new SparkConf().setMaster("local[*]").setAppName("Spark Streaming Job")


        // ==== definindo a sessão Spark
        val spark = SparkSession.builder.config(conf).getOrCreate()

        object SchemaDefinition {
        
         // Csv data schema
         def csvSchema = StructType {StructType(Array(
             StructField("id", StringType, true),
             StructField("pais", StringType, true),
             StructField("cidade", StringType, true),
             StructField("telefone", StringType, true),
             StructField("idade", IntegerType, true),
             StructField("operadora", StringType, true),
             StructField("estado_civil", StringType, true)
           ))
         }
        }

