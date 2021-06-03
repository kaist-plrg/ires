package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToBigInt` extends Algo {
  val head = NormalHead("ToBigInt", List(Param("argument", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  app __x0__ = (ToPrimitive argument CONST_number)
  |  let prim = [? __x0__]
  |  if (= (typeof prim) Undefined) throw TypeError else if (= (typeof prim) Null) throw TypeError else if (= (typeof prim) Boolean) if prim return 1n else return 0n else if (= (typeof prim) BigInt) return prim else if (= (typeof prim) Number) throw TypeError else if (= (typeof prim) String) {
  |    app __x1__ = (StringToBigInt prim)
  |    let n = [! __x1__]
  |    if (= n NaN) throw SyntaxError else {}
  |    return n
  |  } else if (= (typeof prim) Symbol) throw TypeError else {}
  |}""".stripMargin)
  val code = scala.Array[String]()
}
