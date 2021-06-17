package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.Map` extends Algo {
  val head = BuiltinHead(parseRef("""Map"""), List(Param("iterable", Optional)))
  val ids = List(
    "sec-map-iterable",
    "sec-map-constructor",
    "sec-map-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 0:{}
  |  1:app __x0__ = (OrdinaryCreateFromConstructor NewTarget "%Map.prototype%" (new ["MapData"]))
  |  1:let map = [? __x0__]
  |  2:map.MapData = (new [])
  |  3:if (|| (= iterable undefined) (= iterable null)) return map else 0:{}
  |  4:app __x1__ = (Get map "set")
  |  4:let adder = [? __x1__]
  |  5:app __x2__ = (AddEntriesFromIterable map iterable adder)
  |  5:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. Let _map_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%Map.prototype%"*, « [[MapData]] »).""",
    """          1. Set _map_.[[MapData]] to a new empty List.""",
    """          1. If _iterable_ is either *undefined* or *null*, return _map_.""",
    """          1. Let _adder_ be ? Get(_map_, *"set"*).""",
    """          1. Return ? AddEntriesFromIterable(_map_, _iterable_, _adder_).""",
  )
}
