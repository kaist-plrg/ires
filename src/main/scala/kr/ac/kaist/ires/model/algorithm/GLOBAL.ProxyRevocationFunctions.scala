package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::GLOBAL.ProxyRevocationFunctions` extends Algo {
  val head = BuiltinHead(parseRef("""ProxyRevocationFunctions"""), List())
  val ids = List(
    "sec-proxy-revocation-functions",
    "sec-proxy.revocable",
    "sec-properties-of-the-proxy-constructor",
    "sec-proxy-objects",
    "sec-reflection",
  )
  val rawBody = parseInst("""{
  |  0:let F = CONTEXT.Function
  |  1:let p = F.RevocableProxy
  |  2:if (= p null) return undefined else 4:{}
  |  3:F.RevocableProxy = null
  |  5:p.ProxyTarget = null
  |  6:p.ProxyHandler = null
  |  7:return undefined
  |}""".stripMargin)
  val code = scala.Array[String](
    """            1. Let _F_ be the active function object.""",
    """            1. Let _p_ be _F_.[[RevocableProxy]].""",
    """            1. If _p_ is *null*, return *undefined*.""",
    """            1. Set _F_.[[RevocableProxy]] to *null*.""",
    """            1. Assert: _p_ is a Proxy object.""",
    """            1. Set _p_.[[ProxyTarget]] to *null*.""",
    """            1. Set _p_.[[ProxyHandler]] to *null*.""",
    """            1. Return *undefined*.""",
  )
}
