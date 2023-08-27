package dev.shaga.jackit.lambda.handler

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import dev.shaga.jackit.lambda.process.ProcessInput


object LambdaHandler extends RequestHandler[java.util.Map[String, String], Unit]{
  override def handleRequest(input: java.util.Map[String, String], context: Context): Unit = {
    val body = input.get("body")
    ProcessInput.processJson(body)
  }
}
