package com.typesane.coniface

import com.typesane.checkout._

// Console interface.
object Coniface {

  val priceMap = Map[SKU, Double](A -> 1, B -> 2, C -> 3, D -> 4)

  val pricingRules = List(
    Simple(A, priceMap(A)),
    Simple(B, priceMap(B)),
    Simple(C, priceMap(C)),
    Simple(D, priceMap(D)),
    TwoForOne(A, -priceMap(A)),
    TwoForOne(B, -priceMap(B))
  )

  // Functional style input/output.
  def input(sc: java.util.Scanner, currentCheckoutTx: CheckoutTx): CheckoutTx = {
    print("""Enter command ("scan" (A|B|C|D) | "total" | "rules" | "quit"): """)
    val newCheckoutTx = sc.next() match {
      case "scan" =>
        SKU.fromString(sc.next()) match {
          case None => println("error: unknown item"); Some(currentCheckoutTx)
          case Some(item) => Some(currentCheckoutTx.scan(item))
        }
      case "total" =>
        println(currentCheckoutTx); Some(currentCheckoutTx)
      case "rules" =>
        println(priceMap); println(pricingRules); Some(currentCheckoutTx)
      case "quit" =>
        None
      case _ =>
        println("error: unknown command"); Some(currentCheckoutTx)
    }
    if (newCheckoutTx.isEmpty) currentCheckoutTx
    else input(sc, newCheckoutTx.get) // Tail recursive hopefully!
  }

  def main(args: Array[String]) =
    println(input(new java.util.Scanner(System.in), CheckoutTx.newCheckoutTx(pricingRules)))

}
