package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::IsInTailPosition` extends Algo {
  val head = NormalHead("IsInTailPosition", List(Param("call", Normal)))
  val ids = List(
    "sec-isintailposition",
    "sec-tail-position-calls",
    "sec-ecmascript-language-functions-and-classes",
  )
  val rawBody = parseInst("""{
  |  1:if false return false else 0:{}
  |  2:??? "If id:{call} is not contained within a nt:{FunctionBody} , nt:{ConciseBody} , or nt:{AsyncConciseBody} , return value:{false} ."
  |  3:??? "Let id:{body} be the nt:{FunctionBody} , nt:{ConciseBody} , or nt:{AsyncConciseBody} that most closely contains id:{call} ."
  |  4:??? "If id:{body} is the nt:{FunctionBody} of a nt:{GeneratorBody} , return value:{false} ."
  |  5:??? "If id:{body} is the nt:{FunctionBody} of an nt:{AsyncFunctionBody} , return value:{false} ."
  |  6:??? "If id:{body} is the nt:{FunctionBody} of an nt:{AsyncGeneratorBody} , return value:{false} ."
  |  7:if (is-instance-of body AsyncConciseBody) return false else 0:{}
  |  8:access __x0__ = (body "HasCallInTailPosition" call)
  |  8:return __x0__
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _call_ is a Parse Node.""",
    """        1. If the source code matching _call_ is non-strict code, return *false*.""",
    """        1. If _call_ is not contained within a |FunctionBody|, |ConciseBody|, or |AsyncConciseBody|, return *false*.""",
    """        1. Let _body_ be the |FunctionBody|, |ConciseBody|, or |AsyncConciseBody| that most closely contains _call_.""",
    """        1. If _body_ is the |FunctionBody| of a |GeneratorBody|, return *false*.""",
    """        1. If _body_ is the |FunctionBody| of an |AsyncFunctionBody|, return *false*.""",
    """        1. If _body_ is the |FunctionBody| of an |AsyncGeneratorBody|, return *false*.""",
    """        1. If _body_ is an |AsyncConciseBody|, return *false*.""",
    """        1. Return the result of HasCallInTailPosition of _body_ with argument _call_.""",
  )
}
