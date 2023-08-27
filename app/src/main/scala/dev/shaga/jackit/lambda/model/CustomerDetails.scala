package dev.shaga.jackit.lambda.model

class CustomerDetails(visitType: String = "",
                      area: String = "",
                      pincode: String = "",
                      name: String = "",
                      contactNumber: String = "",
                      emailId: String = "",
                      phoneNumber: String = "",
                      customerType: String = "",
                      amc: String = "",
                      vehicleCompany: String = "",
                      vehicleModel: String = "",
                      vehicleColor: String = "",
                      bikeNumber: String = "",
                      bikeInsuranceExpiredYesOrNo: String = "",
                      insuranceExpiryDate: String = "",
                      insuranceCompanyName: String = "",
                      typeOfService: String = "",
//                      partDetails: Array[PartDetails] = Array(),
                      partDetails: java.util.List[PartDetails] = new java.util.LinkedList[PartDetails] {},
                      totalAmount: String = "",
                      totalCost: String = "",
                      revenue: String = "",
                      profitMargin: String = "",
                      discount: String = "",
                      finalPayment: String ="",
                      dateOfService: String = "",
                      deliveryDate: String = "",
                      paymentStatus: String = "",
                      paymentMode: String = "",
                      mechanic: String = ""
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


