# customer-entry-persist-lambda
Lambda that reads customer data and persists it.

------------

The process is an `AWS Lambda Handler`. It will read the customer details and persist it to `Google Sheets` as well on `Mongodb` in format of Json document.

The proposed enhancements/improvements/suggestions are added in the `Issues` tab of the project.

---------

## Learnings so far

* ### Parameters accepted by Lambda handler - 
    The `handleRequest` method of Lambda Handler was provided the input parameter of type `java.util.Map<String,String>` which it did not like. It threw an error as described on [this SO page](https://stackoverflow.com/questions/37155595/aws-can-not-deserialize-instance-of-java-lang-string-out-of-start-object). With the help of [this AWS documentation](https://docs.aws.amazon.com/lambda/latest/dg/java-handler.html), I consumed the input as `InputStream`, and converted it to `String` to learn how the input looks like. It made me realize that having `java.util.Map<String,Object>` would be better idea.

* ### Scala Map cannot be used in place of Java Map AFA Lambdas are concerned -
  Tried consuming input in form of `scala.collection.immutable.Map` but it failed saying
  >Can not construct instance of scala.collection.immutable.Map, problem: abstract types either need to be mapped to concrete types, have custom deserializer, or be instantiated with additional type information

  Using `java.util.Map` now, with `String,Object` rather than `String,Any`