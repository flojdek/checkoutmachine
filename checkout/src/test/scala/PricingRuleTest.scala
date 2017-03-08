import com.typesane.checkout._
import org.scalatest.FunSuite

class PricingRuleTest extends FunSuite {

  test("Simple pricing rule") {
    val r = Simple(A, 10)
    assert(r.item == A)
    assert(r.total == 10)
    assert(r.matches(List()) == 0)
    assert(r.matches(List(A)) == 1)
    assert(r.matches(List(A, B)) == 1)
    assert(r.matches(List(A, B, A)) == 2)
  }

  test("Two for one pricing rule") {
    val r = TwoForOne(B, -5)
    assert(r.item == B)
    assert(r.total == -5)
    assert(r.matches(List()) == 0)
    assert(r.matches(List(A)) == 0)
    assert(r.matches(List(A, A)) == 0)
    assert(r.matches(List(B, A)) == 0)
    assert(r.matches(List(B, A, B)) == 1)
    assert(r.matches(List(B, A, B, B)) == 1)
    assert(r.matches(List(B, A, B, B, C, B)) == 2)
  }

}

