package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.WeakMap` extends Algo {
  val head = BuiltinHead(parseRef("""WeakMap"""), List(Param("iterable", Optional)))
  val ids = List(
    "sec-weakmap-iterable",
    "sec-weakmap-constructor",
    "sec-weakmap-objects",
    "sec-keyed-collections",
  )
  val rawBody = parseInst("""{
  |  0:if (= NewTarget undefined) throw TypeError else 15:{}
  |  1:app __x0__ = (OrdinaryCreateFromConstructor NewTarget "%WeakMap.prototype%" (new ["WeakMapData"]))
  |  1:let map = [? __x0__]
  |  2:map.WeakMapData = (new [])
  |  3:if (|| (= iterable undefined) (= iterable null)) return map else 15:{}
  |  4:app __x1__ = (Get map "set")
  |  4:let adder = [? __x1__]
  |  5:app __x2__ = (AddEntriesFromIterable map iterable adder)
  |  5:return [? __x2__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. If NewTarget is *undefined*, throw a *TypeError* exception.""",
    """          1. Let _map_ be ? OrdinaryCreateFromConstructor(NewTarget, *"%WeakMap.prototype%"*, « [[WeakMapData]] »).""",
    """          1. Set _map_.[[WeakMapData]] to a new empty List.""",
    """          1. If _iterable_ is either *undefined* or *null*, return _map_.""",
    """          1. Let _adder_ be ? Get(_map_, *"set"*).""",
    """          1. Return ? AddEntriesFromIterable(_map_, _iterable_, _adder_).""",
  )
}
