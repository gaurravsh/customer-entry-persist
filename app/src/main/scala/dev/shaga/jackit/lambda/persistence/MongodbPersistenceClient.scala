package dev.shaga.jackit.lambda.persistence

import com.mongodb.MongoClientSettings
import com.mongodb.client.{MongoClients, MongoDatabase}
import org.bson.Document
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.slf4j.{Logger, LoggerFactory}

class MongodbPersistenceClient private (private val db: MongoDatabase, private val collectionName : String){
  private val logger: Logger = LoggerFactory.getLogger(classOf[MongodbPersistenceClient])
  def persistRecord(recordAsJson: String): Unit = {
    val pojoDocument = Document.parse(recordAsJson)
    logger.info("Inserting the Customer Service Details' Record now . . .")
    db.getCollection(collectionName).insertOne(pojoDocument)
    logger.info("Customer Service Details' Record inserted successfully !!")
  }
}

object MongodbPersistenceClient {
  private val logger: Logger = LoggerFactory.getLogger(MongodbPersistenceClient.getClass)
  def apply(): MongodbPersistenceClient = {
    logger.info("Creating DB Client now . . .")
    val mongodbUserName = sys.env("MONGO_USER_NAME")
    val mongodbPwd = sys.env("MONGO_PWD")
    val mongodbHostName = sys.env("MONGO_CLUSTER")
    val mongodbDatabaseName = sys.env("MONGO_DB")
    val customerDetailsCollectionName = sys.env("MONGO_COLLECTION")

    val mongoUrl = s"mongodb+srv://$mongodbUserName:$mongodbPwd@$mongodbHostName"
    val mongoClient = MongoClients.create(mongoUrl)

    val pojoCodecRegistry = CodecRegistries
      .fromRegistries(MongoClientSettings.getDefaultCodecRegistry,
        CodecRegistries.fromProviders(PojoCodecProvider.builder.automatic(true).build)
      )
    val db: MongoDatabase = mongoClient.getDatabase(mongodbDatabaseName).withCodecRegistry(pojoCodecRegistry)
    val dbClient = new MongodbPersistenceClient(db,customerDetailsCollectionName)
    logger.info("DB Client created successfully !")
    dbClient
  }
}
