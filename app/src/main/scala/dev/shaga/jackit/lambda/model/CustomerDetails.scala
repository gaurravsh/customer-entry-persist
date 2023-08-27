package dev.shaga.jackit.lambda.model

class CustomerDetails(var visitType: String = "",
                      var area: String = "",
                      var pincode: String = "",
                      var name: String = "",
                      var contactNumber: String = "",
                      var emailId: String = "",
                      var phoneNumber: String = "",
                      var customerType: String = "",
                      var amc: String = "",
                      var vehicleCompany: String = "",
                      var vehicleModel: String = "",
                      var vehicleColor: String = "",
                      var bikeNumber: String = "",
                      var bikeInsuranceExpiredYesOrNo: String = "",
                      var insuranceExpiryDate: String = "",
                      var insuranceCompanyName: String = "",
                      var typeOfService: String = "",
                      var partDetails: java.util.List[PartDetails] = new java.util.LinkedList[PartDetails] {},
                      var totalAmount: String = "",
                      var totalCost: String = "",
                      var revenue: String = "",
                      var profitMargin: String = "",
                      var discount: String = "",
                      var finalPayment: String ="",
                      var dateOfService: String = "",
                      var deliveryDate: String = "",
                      var paymentStatus: String = "",
                      var paymentMode: String = "",
                      var mechanic: String = ""
                          ) {

  def this()  = this("")

  def fetchAsListOfObject: java.util.List[java.util.List[Object]] = {
    val result: java.util.List[java.util.List[Object]] = new java.util.LinkedList[java.util.List[Object]]()
    val firstRecord: java.util.List[Object] = new java.util.ArrayList[Object]()
    val fields = this.getClass.getDeclaredFields
    fields.foreach(field => {
      if (!field.getName.equalsIgnoreCase("partDetails"))
        firstRecord.add(field.get(this).toString)
      else {
        val firstPartDetail = field.get(this).asInstanceOf[java.util.List[PartDetails]].get(0)
        firstRecord.add(firstPartDetail.partType)
        firstRecord.add(firstPartDetail.amount)
        firstRecord.add(firstPartDetail.cost)
      }

    })
    result.add(firstRecord)
    val totalFieldCount = fields.length + 2
    1 until partDetails.size map (index => {
      val record: java.util.List[Object] = new java.util.ArrayList[Object](totalFieldCount)
      0 until totalFieldCount foreach (loc => record.add(loc, "-"))
      record.set(17, this.partDetails.get(index).partType)
      record.set(18, this.partDetails.get(index).amount)
      record.set(19, this.partDetails.get(index).cost)
      result.add(record)
      }
    )
    result
  }
}

case class PartDetails(val partType: String = "",
                       val amount:String = "",
                       val cost: String= "")



