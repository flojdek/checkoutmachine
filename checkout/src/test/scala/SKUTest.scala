import com.typesane.checkout._
import org.scalatest.FunSuite

class SKUTest extends FunSuite {

  test("Parsing from string") {
    assert(SKU.fromString("A").contains(A))
    assert(SKU.fromString("B").contains(B))
    assert(SKU.fromString("C").contains(C))
    assert(SKU.fromString("D").contains(D))
    assert(SKU.fromString("666").isEmpty)
    assert(SKU.fromString("E").isEmpty)
  }

}

