package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InternalizeJSONProperty` extends Algo {
  val head = BuiltinHead(parseRef("""InternalizeJSONProperty"""), List(Param("holder", Normal), Param("name", Normal), Param("reviver", Normal)))
  val ids = List(
    "sec-internalizejsonproperty",
    "sec-json.parse",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (Get holder name)
  |  0:let val = [? __x0__]
  |  1:if (= (typeof val) Object) {
  |    2:app __x1__ = (IsArray val)
  |    2:let isArray = [? __x1__]
  |    14:if (= isArray true) {
  |      4:let I = 0i
  |      5:app __x2__ = (LengthOfArrayLike val)
  |      5:let len = [? __x2__]
  |      6:while (< I len) {
  |        7:app __x3__ = (ToString I)
  |        7:let prop = [! __x3__]
  |        8:app __x4__ = (InternalizeJSONProperty val prop reviver)
  |        8:let newElement = [? __x4__]
  |        11:if (= newElement undefined) {
  |          10:app __x5__ = (val.Delete val prop)
  |          10:[? __x5__]
  |        } else {
  |          12:app __x6__ = (CreateDataProperty val prop newElement)
  |          12:[? __x6__]
  |        }
  |        13:I = (+ I 1i)
  |      }
  |    } else {
  |      15:app __x7__ = (EnumerableOwnPropertyNames val CONST_key)
  |      15:let keys = [? __x7__]
  |      16:let __x8__ = keys
  |      16:let __x9__ = 0i
  |      16:while (< __x9__ __x8__.length) {
  |        let P = __x8__[__x9__]
  |        17:app __x10__ = (InternalizeJSONProperty val P reviver)
  |        17:let newElement = [? __x10__]
  |        20:if (= newElement undefined) {
  |          19:app __x11__ = (val.Delete val P)
  |          19:[? __x11__]
  |        } else {
  |          21:app __x12__ = (CreateDataProperty val P newElement)
  |          21:[? __x12__]
  |        }
  |        __x9__ = (+ __x9__ 1i)
  |      }
  |    }
  |  } else 7:{}
  |  22:app __x13__ = (Call reviver holder (new [name, val]))
  |  22:return [? __x13__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _val_ be ? Get(_holder_, _name_).""",
    """          1. If Type(_val_) is Object, then""",
    """            1. Let _isArray_ be ? IsArray(_val_).""",
    """            1. If _isArray_ is *true*, then""",
    """              1. Let _I_ be 0.""",
    """              1. Let _len_ be ? LengthOfArrayLike(_val_).""",
    """              1. Repeat, while _I_ < _len_,""",
    """                1. Let _prop_ be ! ToString(𝔽(_I_)).""",
    """                1. Let _newElement_ be ? InternalizeJSONProperty(_val_, _prop_, _reviver_).""",
    """                1. If _newElement_ is *undefined*, then""",
    """                  1. Perform ? _val_.[[Delete]](_prop_).""",
    """                1. Else,""",
    """                  1. Perform ? CreateDataProperty(_val_, _prop_, _newElement_).""",
    """                1. Set _I_ to _I_ + 1.""",
    """            1. Else,""",
    """              1. Let _keys_ be ? EnumerableOwnPropertyNames(_val_, ~key~).""",
    """              1. For each String _P_ of _keys_, do""",
    """                1. Let _newElement_ be ? InternalizeJSONProperty(_val_, _P_, _reviver_).""",
    """                1. If _newElement_ is *undefined*, then""",
    """                  1. Perform ? _val_.[[Delete]](_P_).""",
    """                1. Else,""",
    """                  1. Perform ? CreateDataProperty(_val_, _P_, _newElement_).""",
    """          1. Return ? Call(_reviver_, _holder_, « _name_, _val_ »).""",
  )
}
