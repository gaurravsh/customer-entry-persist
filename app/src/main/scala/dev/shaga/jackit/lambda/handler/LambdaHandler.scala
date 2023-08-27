package dev.shaga.jackit.lambda.handler

import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}


object LambdaHandler extends RequestHandler[java.util.Map[String, String], String]{
  override def handleRequest(input: java.util.Map[String, String], context: Context): String = {
    val body = input.get("body")

    null
  }
}
