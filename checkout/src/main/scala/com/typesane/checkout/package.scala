package com.typesane

package object checkout {

  case class BasketPartition(relevantItems: List[SKU], otherItems: List[SKU])

  def partitionBasket(basket: List[SKU], item: SKU) = BasketPartition.tupled(basket.partition(_ == item))

}
