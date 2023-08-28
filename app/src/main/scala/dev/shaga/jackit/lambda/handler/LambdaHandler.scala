package dev.shaga.jackit.lambda.handler

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import dev.shaga.jackit.lambda.process.ProcessInput
import org.slf4j.{Logger, LoggerFactory}


object LambdaHandler extends RequestHandler[java.util.Map[String,Object], Unit]{
  private val logger: Logger = LoggerFactory.getLogger(LambdaHandler.getClass)
  override def handleRequest(inputEvent: java.util.Map[String,Object], context: Context): Unit = {

    val body = inputEvent.get("body")
    logger.info("body from the input : {}",body)

    ProcessInput.processJson(body.toString)
  }
}
