package dev.shaga.jackit.lambda.persistence

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.sheets.v4.model.ValueRange
import com.google.api.services.sheets.v4.{Sheets, SheetsScopes}
import dev.shaga.jackit.lambda.model.ServiceAccountConfig
import org.slf4j.LoggerFactory

import java.io.{ByteArrayInputStream, IOException}
import java.nio.charset.StandardCharsets
import java.security.GeneralSecurityException
import java.util.Collections

class GoogleSheetPersistenceClient private(private val sheets: Sheets){
  private val logger = LoggerFactory.getLogger(GoogleSheetPersistenceClient.getClass)

  def appendData(sheetId: String, range: String, dataList: java.util.List[java.util.List[AnyRef]]): Unit = {
    logger.info("Going to update Price in Excel")
    try sheets
      .spreadsheets
      .values
      .append(sheetId, range, new ValueRange().setValues(dataList))
      .setValueInputOption("USER_ENTERED")
      .setIncludeValuesInResponse(true)
      .execute
    catch {
      case e: IOException =>
        logger.error("Error while trying to update excel !!")
        throw new RuntimeException(e)
    }
  }



}

object GoogleSheetPersistenceClient {

  private val logger = LoggerFactory.getLogger(GoogleSheetPersistenceClient.getClass)

  private def authorize(serviceAccountConfig: ServiceAccountConfig): GoogleCredential = {
    val scopes = Collections.singletonList(SheetsScopes.SPREADSHEETS)
    val serviceAccountConfigJson = serviceAccountConfig.toString
    try GoogleCredential.fromStream(new ByteArrayInputStream(serviceAccountConfigJson.getBytes(StandardCharsets.UTF_8)),
      GoogleNetHttpTransport.newTrustedTransport,
      JacksonFactory.getDefaultInstance).createScoped(scopes)
    catch {
      case e: IOException =>
        logger.error("Error while reading client secret json")
        e.printStackTrace()
        throw new RuntimeException(e)
      case e: GeneralSecurityException =>
        logger.error("Error while creating google credentials ")
        e.printStackTrace()
        throw new RuntimeException(e)
    }
  }

  def apply(): GoogleSheetPersistenceClient = {
    val sheets = new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport, JacksonFactory.getDefaultInstance, authorize(ServiceAccountConfig.apply())).setApplicationName("Customer Data").build
    new GoogleSheetPersistenceClient(sheets)
  }



}
