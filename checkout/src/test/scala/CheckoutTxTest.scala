import com.typesane.checkout._
import org.scalatest.FunSuite

class CheckoutTxTest extends FunSuite {

  val priceMap = Map[SKU, Double](A -> 1, B -> 2)
  val pricingRules = List(Simple(A, priceMap(A)), Simple(B, priceMap(B)), TwoForOne(A, -priceMap(A)))

  test("Empty pricing rules quick test") {
    assert(CheckoutTx.newCheckoutTx(List()).total == 0)
    assert(CheckoutTx.newCheckoutTx(List()).scan(A).scan(B).total == 0)
  }

  test("Non empty pricing rules initial total 0") {
    assert(CheckoutTx.newCheckoutTx(pricingRules).total == 0)
  }

  test("Non empty pricing rules scan one item total") {
    val ct = CheckoutTx.newCheckoutTx(pricingRules)
    assert(ct.scan(A).total == 1)
    assert(ct.scan(B).total == 2)
  }

  test("Non empty pricing rules scan two items total") {
    assert(CheckoutTx.newCheckoutTx(pricingRules).scan(A).scan(B).total == 3)
  }

  test("Non empty pricing rules order of scan doesn't matter") {
    val ct = CheckoutTx.newCheckoutTx(pricingRules)
    assert(ct.scan(A).scan(B).total == ct.scan(B).scan(A).total)
    assert(ct.scan(A).scan(B).scan(A).total == ct.scan(B).scan(A).scan(A).total)
  }

  test("Non empty pricing rules two for one applies") {
    val ct = CheckoutTx.newCheckoutTx(pricingRules)
    assert(ct.scan(A).scan(A).total == 1)
    assert(ct.scan(A).scan(B).scan(A).total == 3)
  }

  test("Non empty pricing rules to string") {
    assert(CheckoutTx.newCheckoutTx(pricingRules).scan(A).scan(B).toString == "total = 3.0, basket = List(A, B)")
  }

}

