import com.typesane.checkout._
import org.scalatest.FunSuite

class UtilTest extends FunSuite {

  test("Partition basket - no item") {
    assert(partitionBasket(List(), A) == BasketPartition(Nil, Nil))
  }

  test("Partition basket - more than one item") {
    assert(partitionBasket(List(A), A) == BasketPartition(List(A), Nil))
    assert(partitionBasket(List(A), B) == BasketPartition(Nil, List(A)))
    assert(partitionBasket(List(A, A), A) == BasketPartition(List(A, A), Nil))
  }

}

