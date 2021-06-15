package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ToNumber` extends Algo {
  val head = NormalHead("ToNumber", List(Param("argument", Normal)))
  val ids = List()
  val rawBody = parseInst("""{
  |  if (= (typeof argument) Undefined) return NaN else if (= (typeof argument) Null) return 0.0 else if (= (typeof argument) Boolean) if argument return 1.0 else return 0.0 else if (= (typeof argument) Number) return argument else if (= (typeof argument) String) return (convert argument str2num ) else if (= (typeof argument) Symbol) throw TypeError else if (= (typeof argument) BigInt) throw TypeError else {
  |    app __x0__ = (ToPrimitive argument CONST_number)
  |    let primValue = [? __x0__]
  |    app __x1__ = (ToNumber primValue)
  |    return [? __x1__]
  |  }
  |}""".stripMargin)
  val code = scala.Array[String]()
}
