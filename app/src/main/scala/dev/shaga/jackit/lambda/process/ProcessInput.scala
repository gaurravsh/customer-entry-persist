package dev.shaga.jackit.lambda.process

import com.google.gson.Gson

import dev.shaga.jackit.lambda.model.CustomerDetails
import dev.shaga.jackit.lambda.persistence.{GoogleSheetPersistenceClient, MongodbPersistenceClient}
import org.slf4j.{Logger, LoggerFactory}

import java.util.concurrent.{ ExecutorService, Executors,  TimeUnit}

object ProcessInput {

  private val logger: Logger = LoggerFactory.getLogger(ProcessInput.getClass)

  def processJson(jsonBody: String): Unit = {
    val t0 = System.currentTimeMillis()
    var customerDetails : CustomerDetails = null
    try
      customerDetails = new Gson().fromJson(jsonBody,classOf[CustomerDetails])
    catch {
      case e: RuntimeException =>
        throw new RuntimeException(e)
    }

    val service: ExecutorService = Executors.newFixedThreadPool(2)
    val taskList: java.util.List[Runnable] = new java.util.LinkedList()
    taskList.add(() => persistOnGoogleSheets(customerDetails))
    taskList.add(() => persistOnMongodb(jsonBody))

    service.submit(taskList.get(0))
    service.submit(taskList.get(1))
    TimeUnit.SECONDS.sleep(2)
    service.shutdown()
    service.awaitTermination(Long.MaxValue,TimeUnit.NANOSECONDS)
    val t1 = System.currentTimeMillis()

    logger.info(s"Total execution time = ${(t1-t0)/1000.0} seconds")


  }

   private def persistOnGoogleSheets(customerDetails: CustomerDetails): Unit = {
    val googleSheetId = sys.env ("SHEET_ID") // like BgU13fCC9IStpS8hjdUXUpHX3qSFb8dwXQ8FHwDkAIhc
    val sheetName = sys.env ("SHEET_NAME")
    val googleSheetClient = GoogleSheetPersistenceClient.apply()
    val dataToInsert = customerDetails.fetchAsListOfObject
    googleSheetClient.appendData (googleSheetId, s"$sheetName!A:A", dataToInsert)
  }

  private def persistOnMongodb(customerDetails: String) : Unit = {
    val mongodbClient = MongodbPersistenceClient.apply()
    logger.info("Calling the Mongodb Client now . . .")
    mongodbClient.persistRecord(customerDetails)
  }

}

