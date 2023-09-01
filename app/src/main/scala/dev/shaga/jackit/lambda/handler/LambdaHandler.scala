package dev.shaga.jackit.lambda.handler

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import dev.shaga.jackit.lambda.process.ProcessInput
import org.slf4j.{Logger, LoggerFactory}

import java.io.{BufferedReader, InputStream, InputStreamReader}
import java.nio.charset.StandardCharsets
import java.util.stream.Collectors


object LambdaHandler extends RequestHandler[InputStream, Unit]{
  private val logger: Logger = LoggerFactory.getLogger(LambdaHandler.getClass)
  override def handleRequest(inputStream: InputStream, context: Context): Unit = {

    val inputBody = new BufferedReader(
      new InputStreamReader(inputStream, StandardCharsets.UTF_8))
      .lines()
      .collect(Collectors.joining("\n"))

    logger.info("body from the input : {}",inputBody)

    ProcessInput.processJson(inputBody)
  }
}
