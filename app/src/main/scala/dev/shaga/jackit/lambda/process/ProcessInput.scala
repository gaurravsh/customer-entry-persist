package dev.shaga.jackit.lambda.process

import com.google.gson.Gson

import java.nio.charset.StandardCharsets
import java.util.Base64
import dev.shaga.jackit.lambda.model.CustomerDetails
import dev.shaga.jackit.lambda.persistence.{GoogleSheetPersistenceClient, MongodbPersistenceClient}

object ProcessInput {

  def processJson(eventBody: String): Unit = {
    val bytes = eventBody.getBytes(StandardCharsets.UTF_8)
    val decoded = Base64.getDecoder.decode(bytes)
    val jsonBody = new String(decoded, StandardCharsets.UTF_8)
    var customerDetails : CustomerDetails = null
    try
      customerDetails = new Gson().fromJson(jsonBody,classOf[CustomerDetails])
    catch {
      case e: RuntimeException =>
        throw new RuntimeException(e)
    }
    persistOnMongodb(jsonBody)
    persistOnGoogleSheets(customerDetails)



  }

   private def persistOnGoogleSheets(customerDetails: CustomerDetails): Unit = {
    val googleSheetId = sys.env ("SHEET_ID") // like BgU13fCC9IStpS8hjdUXUpHX3qSFb8dwXQ8FHwDkAIhc
    val sheetName = sys.env ("SHEET_NAME")
    val googleSheetClient = GoogleSheetPersistenceClient.apply()
    val dataToInsert = customerDetails.fetchAsListOfObject
    googleSheetClient.appendData (googleSheetId, s"$sheetName!A:Z", dataToInsert)
  }

  private def persistOnMongodb(customerDetails: String) : Unit = {
    val mongodbClient = MongodbPersistenceClient.apply()
    mongodbClient.persistRecord(customerDetails)
  }

}

