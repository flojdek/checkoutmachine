package com.typesane.checkout

trait PricingRule {
  // @param basket Items in the checkout basket.
  // @return Number of times this pricing rule applies to the items in the basket.
  def matches(basket: List[SKU]): Int

  // @return Total cost for this pricing rule.
  def total: Double
}

case class Simple(item: SKU, total: Double) extends PricingRule {
  override def matches(basket: List[SKU]) = partitionBasket(basket, item).relevantItems.length
}

case class TwoForOne(item: SKU, total: Double) extends PricingRule {
  override def matches(basket: List[SKU]) = partitionBasket(basket, item).relevantItems.length / 2
}
