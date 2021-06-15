package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::Proxy.revocable` extends Algo {
  val head = BuiltinHead(parseRef("""Proxy.revocable"""), List(Param("target", Normal), Param("handler", Normal)))
  val ids = List(
    "sec-proxy.revocable",
    "sec-properties-of-the-proxy-constructor",
    "sec-proxy-objects",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (ProxyCreate target handler)
  |  0:let p = [? __x0__]
  |  1:let steps = ProxyRevocationFunctions
  |  2:??? "Let id:{length} be the number of non - optional parameters of the function definition in ProxyRevocationFunctions ."
  |  3:app __x1__ = (CreateBuiltinFunction steps length "" (new ["RevocableProxy"]))
  |  3:let revoker = [! __x1__]
  |  4:revoker.RevocableProxy = p
  |  5:app __x2__ = (OrdinaryObjectCreate INTRINSIC_Object_prototype)
  |  5:let result = [! __x2__]
  |  6:app __x3__ = (CreateDataPropertyOrThrow result "proxy" p)
  |  6:[! __x3__]
  |  7:app __x4__ = (CreateDataPropertyOrThrow result "revoke" revoker)
  |  7:[! __x4__]
  |  8:return result
  |}""".stripMargin)
  val code = scala.Array[String](
    """          1. Let _p_ be ? ProxyCreate(_target_, _handler_).""",
    """          1. Let _steps_ be the algorithm steps defined in <emu-xref href="#sec-proxy-revocation-functions" title></emu-xref>.""",
    """          1. Let _length_ be the number of non-optional parameters of the function definition in <emu-xref href="#sec-proxy-revocation-functions" title></emu-xref>.""",
    """          1. Let _revoker_ be ! CreateBuiltinFunction(_steps_, _length_, *""*, « [[RevocableProxy]] »).""",
    """          1. Set _revoker_.[[RevocableProxy]] to _p_.""",
    """          1. Let _result_ be ! OrdinaryObjectCreate(%Object.prototype%).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_result_, *"proxy"*, _p_).""",
    """          1. Perform ! CreateDataPropertyOrThrow(_result_, *"revoke"*, _revoker_).""",
    """          1. Return _result_.""",
  )
}
