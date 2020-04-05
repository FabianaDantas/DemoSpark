        // == DATA INPUT ==
        // Lendo os dados que temos, que neste caso estão em um diretório
        // Em caso de APIs, aqui você seta a entrada das API
        val reader = spark.readStream.format("csv").option("header", true).option("delimiter", ";").option("latestFirst", "true").schema(SchemaDefinition.csvSchema).load(DIR + "/*")


        // == DATA PROCESSING ==
        reader.createOrReplaceTempView("user_records")

        val transformation = spark.sql(
          """
            SELECT operadora, estado_civil, COUNT(1) as num_usuarios
            FROM user_records
            GROUP BY operadora, estado_civil
          """)

        //3. == DATA OUTPUT ==
        val consoleStream = transformation.writeStream.option("truncate", false).outputMode(OutputMode.Complete).trigger(Trigger.ProcessingTime("90 seconds")).format("console").start() // momento que começa a rodar

        consoleStream.awaitTermination()
