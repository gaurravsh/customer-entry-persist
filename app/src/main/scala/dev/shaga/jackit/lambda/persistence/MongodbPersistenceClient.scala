package dev.shaga.jackit.lambda.persistence

import com.mongodb.MongoClientSettings
import com.mongodb.client.{MongoClients, MongoDatabase}
import dev.shaga.jackit.lambda.model.CustomerDetails
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider

class MongodbPersistenceClient private (private val db: MongoDatabase, private val collectionName : String){
  def persistRecord(record: CustomerDetails): Unit = {
    val collection = db.getCollection(collectionName, classOf[CustomerDetails])
    val insertionResult = collection.insertOne(record)

  }
}

object MongodbPersistenceClient {
  def apply(): MongodbPersistenceClient = {
    val mongodbUserName = sys.env("MONGO_USER_NAME")
    val mongodbPwd = sys.env("MONGO_PWD")
    val mongodbHostName = sys.env("MONGO_CLUSTER")
    val mongodbDatabaseName = sys.env("MONGO_DB")
    val customerDetailsCollectionName = sys.env("MONGO_COLLECTION")

    val mongoUrl = s"mongodb+srv://$mongodbUserName:$mongodbPwd@$mongodbHostName"
    val mongoClient = MongoClients.create(mongoUrl)
    val pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry, CodecRegistries.fromProviders(PojoCodecProvider.builder.automatic(true).build))
    val db: MongoDatabase = mongoClient.getDatabase(mongodbDatabaseName).withCodecRegistry(pojoCodecRegistry)
    new MongodbPersistenceClient(db,customerDetailsCollectionName)
  }
}
