/*
 * This Scala source file was generated by the Gradle 'init' task.
 */
package dev.shaga.jackit.lambda

import dev.shaga.jackit.lambda.model.CustomerDetails
import dev.shaga.jackit.lambda.process.ProcessInput

object App {
  def main(args: Array[String]): Unit = {
    val stringInBase64 = "eyJhbWMiOiJZZXMiLCJhcmVhIjoiQSIsImJpa2VJbnN1cmFuY2VFeHBpcmVkWWVzT3JObyI6IlllcyIsImJpa2VOdW1iZXIiOiJCTiIsImNvbnRhY3ROdW1iZXIiOiIiLCJjdXN0b21lclR5cGUiOiJDdCIsImRhdGVPZlNlcnZpY2UiOiIyMDIzLTgtMjUiLCJkZWxpdmVyeURhdGUiOiIyMDIzLTgtMjYiLCJkaXNjb3VudCI6IjIwIiwiZW1haWxJZCI6ImUiLCJmaW5hbFBheW1lbnQiOiIyMDAiLCJpbnN1cmFuY2VDb21wYW55TmFtZSI6IklucyBDIiwiaW5zdXJhbmNlRXhwaXJ5RGF0ZSI6IjIwMjMtOC05IiwibWVjaGFuaWMiOiJTYXJvaiIsIm5hbWUiOiJSIiwicGFydERldGFpbHMiOlt7ImFtb3VudCI6IjEwMCIsImNvc3QiOiI1MCIsInBhcnRUeXBlIjoiUG4ifSx7ImFtb3VudCI6IjEyMCIsImNvc3QiOiI1MCIsInBhcnRUeXBlIjoiUG4yIn1dLCJwYXltZW50TW9kZSI6IiIsInBheW1lbnRTdGF0dXMiOiJQZW5kaW5nIiwicGhvbmVOdW1iZXIiOiI5NyIsInBpbmNvZGUiOiIzIiwicHJvZml0TWFyZ2luIjoiNTQuNSIsInJldmVudWUiOiIxMjAiLCJ0b3RhbEFtb3VudCI6IjIyMCIsInRvdGFsQ29zdCI6IjEwMCIsInR5cGVPZlNlcnZpY2UiOiJSZXBhaXIiLCJ2ZWhpY2xlQ29sb3IiOiJWY29sb3IiLCJ2ZWhpY2xlQ29tcGFueSI6IlZjIiwidmVoaWNsZU1vZGVsIjoiVm0iLCJ2aXNpdFR5cGUiOiJXYWxrIGluIn0="
    ProcessInput.processJson(stringInBase64)

  }

}
