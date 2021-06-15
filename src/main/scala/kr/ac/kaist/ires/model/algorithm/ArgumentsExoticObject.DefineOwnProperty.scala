package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::ArgumentsExoticObject.DefineOwnProperty` extends Algo {
  val head = MethodHead("ArgumentsExoticObject", "DefineOwnProperty", Param("args", Normal), List(Param("P", Normal), Param("Desc", Normal)))
  val ids = List(
    "sec-arguments-exotic-objects-defineownproperty-p-desc",
    "sec-arguments-exotic-objects",
    "sec-built-in-exotic-object-internal-methods-and-slots",
    "sec-ordinary-and-exotic-objects-behaviours",
  )
  val rawBody = parseInst("""{
  |  0:let map = args.ParameterMap
  |  1:app __x0__ = (HasOwnProperty map P)
  |  1:let isMapped = __x0__
  |  2:let newArgDesc = Desc
  |  3:let __x1__ = true
  |  3:__x1__ = (= isMapped true)
  |  3:if __x1__ {
  |    app __x2__ = (IsDataDescriptor Desc)
  |    __x1__ = (= __x2__ true)
  |  } else 15:{}
  |  3:if __x1__ if (&& (= Desc.Value absent) (= Desc.Writable false)) {
  |    5:newArgDesc = (copy-obj Desc)
  |    6:app __x3__ = (Get map P)
  |    6:newArgDesc.Value = __x3__
  |  } else 15:{} else 15:{}
  |  7:app __x4__ = (OrdinaryDefineOwnProperty args P newArgDesc)
  |  7:let allowed = [? __x4__]
  |  8:if (= allowed false) return false else 15:{}
  |  9:if (= isMapped true) {
  |    12:app __x5__ = (IsAccessorDescriptor Desc)
  |    12:if (= __x5__ true) {
  |      11:app __x6__ = (map.Delete map P)
  |      11:__x6__
  |    } else {
  |      13:if (! (= Desc.Value absent)) {
  |        14:app __x7__ = (Set map P Desc.Value false)
  |        14:let setStatus = __x7__
  |      } else 15:{}
  |      16:if (= Desc.Writable false) {
  |        17:app __x8__ = (map.Delete map P)
  |        17:__x8__
  |      } else 15:{}
  |    }
  |  } else 15:{}
  |  18:return true
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _map_ be _args_.[[ParameterMap]].""",
    """          1. Let _isMapped_ be HasOwnProperty(_map_, _P_).""",
    """          1. Let _newArgDesc_ be _Desc_.""",
    """          1. If _isMapped_ is *true* and IsDataDescriptor(_Desc_) is *true*, then""",
    """            1. If _Desc_.[[Value]] is not present and _Desc_.[[Writable]] is present and its value is *false*, then""",
    """              1. Set _newArgDesc_ to a copy of _Desc_.""",
    """              1. Set _newArgDesc_.[[Value]] to Get(_map_, _P_).""",
    """          1. Let _allowed_ be ? OrdinaryDefineOwnProperty(_args_, _P_, _newArgDesc_).""",
    """          1. If _allowed_ is *false*, return *false*.""",
    """          1. If _isMapped_ is *true*, then""",
    """            1. If IsAccessorDescriptor(_Desc_) is *true*, then""",
    """              1. Call _map_.[[Delete]](_P_).""",
    """            1. Else,""",
    """              1. If _Desc_.[[Value]] is present, then""",
    """                1. Let _setStatus_ be Set(_map_, _P_, _Desc_.[[Value]], *false*).""",
    """                1. Assert: _setStatus_ is *true* because formal parameters mapped by argument objects are always writable.""",
    """              1. If _Desc_.[[Writable]] is present and its value is *false*, then""",
    """                1. Call _map_.[[Delete]](_P_).""",
    """          1. Return *true*.""",
  )
}
