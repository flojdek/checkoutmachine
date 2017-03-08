package com.typesane.checkout

case class CheckoutTx private (pricingRules: List[PricingRule], basket: List[SKU]) {

  // @param item Item to scan.
  // @return New checkout transaction representing `basket` with `item` added.
  def scan(item: SKU): CheckoutTx = CheckoutTx(pricingRules, basket ++ List(item))

  // @return Total amount to pay for the `basket` according to `pricingRules`.
  def total: Double = {
    def totalAux(pricingRules: List[PricingRule]): Double =
      pricingRules match {
        case Nil => 0.0
        case pricingRule :: restOfPricingRules =>
          val times = List.fill(pricingRule.matches(basket))(pricingRule)
          // if (times.nonEmpty) println(times) - Uncomment to see for clarity WRT what rule was applied.
          times.foldLeft(0.0)(_ + _.total) + totalAux(restOfPricingRules)
      }

    totalAux(pricingRules)
  }

  override def toString: String = s"total = ${total}, basket = ${basket}"

}

object CheckoutTx {

  def emptyBasket = List.empty[SKU]

  def newCheckoutTx(pricingRules: List[PricingRule]) = CheckoutTx(pricingRules, emptyBasket)

}
