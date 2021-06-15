package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::JSON.stringify` extends Algo {
  val head = BuiltinHead(parseRef("""JSON.stringify"""), List(Param("value", Normal), Param("replacer", Optional), Param("space", Optional)))
  val ids = List(
    "sec-json.stringify",
    "sec-json-object",
    "sec-structured-data",
  )
  val rawBody = parseInst("""{
  |  0:let stack = (new [])
  |  1:let indent = ""
  |  2:let PropertyList = undefined
  |  2:let ReplacerFunction = undefined
  |  3:if (= (typeof replacer) Object) {
  |    6:app __x0__ = (IsCallable replacer)
  |    6:if (= __x0__ true) ReplacerFunction = replacer else {
  |      7:app __x1__ = (IsArray replacer)
  |      7:let isArray = [? __x1__]
  |      8:if (= isArray true) {
  |        9:PropertyList = (new [])
  |        10:app __x2__ = (LengthOfArrayLike replacer)
  |        10:let len = [? __x2__]
  |        11:let k = 0i
  |        12:while (< k len) {
  |          13:app __x3__ = (ToString k)
  |          13:let prop = [! __x3__]
  |          14:app __x4__ = (Get replacer prop)
  |          14:let v = [? __x4__]
  |          15:let item = undefined
  |          18:if (= (typeof v) String) item = v else if (= (typeof v) Number) {
  |            app __x5__ = (ToString v)
  |            item = [! __x5__]
  |          } else if (= (typeof v) Object) ??? "If id:{v} has a [ [ StringData ] ] or [ [ NumberData ] ] internal slot , set id:{item} to ? ToString ( id:{v} ) ." else 7:{}
  |          20:??? "If id:{item} is not value:{undefined} and id:{item} is not currently an element of id:{PropertyList} , then in:{} out:{}"
  |          22:k = (+ k 1i)
  |        }
  |      } else 7:{}
  |    }
  |  } else 7:{}
  |  23:if (= (typeof space) Object) if (! (= space.NumberData absent)) {
  |    25:app __x6__ = (ToNumber space)
  |    25:space = [? __x6__]
  |  } else if (! (= space.StringData absent)) {
  |    27:app __x7__ = (ToString space)
  |    27:space = [? __x7__]
  |  } else 7:{} else 7:{}
  |  34:if (= (typeof space) Number) {
  |    29:app __x8__ = (ToIntegerOrInfinity space)
  |    29:let spaceMV = [! __x8__]
  |    30:app __x9__ = (min 10i spaceMV)
  |    30:spaceMV = __x9__
  |    31:??? "If id:{spaceMV} < 1 , let id:{gap} be the empty String ; otherwise let id:{gap} be the String value containing id:{spaceMV} occurrences of the code unit 0x0020 ( SPACE ) ."
  |  } else if (= (typeof space) String) ??? "If the length of id:{space} is 10 or less , let id:{gap} be id:{space} ; otherwise let id:{gap} be the substring of id:{space} from 0 to 10 ." else let gap = ""
  |  36:app __x10__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  36:let wrapper = [! __x10__]
  |  37:app __x11__ = (CreateDataPropertyOrThrow wrapper "" value)
  |  37:[! __x11__]
  |  38:let state = (new Record("ReplacerFunction" -> ReplacerFunction, "Stack" -> stack, "Indent" -> indent, "Gap" -> gap, "PropertyList" -> PropertyList))
  |  39:app __x12__ = (SerializeJSONProperty state "" wrapper)
  |  39:return [? __x12__]
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Let _stack_ be a new empty List.""",
    """        1. Let _indent_ be the empty String.""",
    """        1. Let _PropertyList_ and _ReplacerFunction_ be *undefined*.""",
    """        1. If Type(_replacer_) is Object, then""",
    """          1. If IsCallable(_replacer_) is *true*, then""",
    """            1. Set _ReplacerFunction_ to _replacer_.""",
    """          1. Else,""",
    """            1. Let _isArray_ be ? IsArray(_replacer_).""",
    """            1. If _isArray_ is *true*, then""",
    """              1. Set _PropertyList_ to a new empty List.""",
    """              1. Let _len_ be ? LengthOfArrayLike(_replacer_).""",
    """              1. Let _k_ be 0.""",
    """              1. Repeat, while _k_ < _len_,""",
    """                1. Let _prop_ be ! ToString(𝔽(_k_)).""",
    """                1. Let _v_ be ? Get(_replacer_, _prop_).""",
    """                1. Let _item_ be *undefined*.""",
    """                1. If Type(_v_) is String, set _item_ to _v_.""",
    """                1. Else if Type(_v_) is Number, set _item_ to ! ToString(_v_).""",
    """                1. Else if Type(_v_) is Object, then""",
    """                  1. If _v_ has a [[StringData]] or [[NumberData]] internal slot, set _item_ to ? ToString(_v_).""",
    """                1. If _item_ is not *undefined* and _item_ is not currently an element of _PropertyList_, then""",
    """                  1. Append _item_ to the end of _PropertyList_.""",
    """                1. Set _k_ to _k_ + 1.""",
    """        1. If Type(_space_) is Object, then""",
    """          1. If _space_ has a [[NumberData]] internal slot, then""",
    """            1. Set _space_ to ? ToNumber(_space_).""",
    """          1. Else if _space_ has a [[StringData]] internal slot, then""",
    """            1. Set _space_ to ? ToString(_space_).""",
    """        1. If Type(_space_) is Number, then""",
    """          1. Let _spaceMV_ be ! ToIntegerOrInfinity(_space_).""",
    """          1. Set _spaceMV_ to min(10, _spaceMV_).""",
    """          1. If _spaceMV_ < 1, let _gap_ be the empty String; otherwise let _gap_ be the String value containing _spaceMV_ occurrences of the code unit 0x0020 (SPACE).""",
    """        1. Else if Type(_space_) is String, then""",
    """          1. If the length of _space_ is 10 or less, let _gap_ be _space_; otherwise let _gap_ be the substring of _space_ from 0 to 10.""",
    """        1. Else,""",
    """          1. Let _gap_ be the empty String.""",
    """        1. Let _wrapper_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """        1. Perform ! CreateDataPropertyOrThrow(_wrapper_, the empty String, _value_).""",
    """        1. Let _state_ be the Record { [[ReplacerFunction]]: _ReplacerFunction_, [[Stack]]: _stack_, [[Indent]]: _indent_, [[Gap]]: _gap_, [[PropertyList]]: _PropertyList_ }.""",
    """        1. Return ? SerializeJSONProperty(_state_, the empty String, _wrapper_).""",
  )
}
