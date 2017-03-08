package com.typesane.checkout

// Stock Keeping Unit unique objects per product.
trait SKU
case object A extends SKU
case object B extends SKU
case object C extends SKU
case object D extends SKU

object SKU {

  // This can get out of sync, but I guess in reality this would be constructed from some DB automatically
  // rather than be maintained manually. In case it would need to be maintained manually we could create a
  // Scala macro to generate a list of case objects from all SKU child objects.
  def SKUs = List(A, B, C, D)

  def fromString(value: String): Option[SKU] = SKUs.find(_.toString == value)

}
