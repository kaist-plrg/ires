package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::BlockDeclarationInstantiation` extends Algo {
  val head = NormalHead("BlockDeclarationInstantiation", List(Param("code", Normal), Param("env", Normal)))
  val ids = List(
    "sec-blockdeclarationinstantiation",
    "sec-block",
    "sec-ecmascript-language-statements-and-declarations",
  )
  val rawBody = parseInst("""{
  |  0:assert (is-instance-of env DeclarativeEnvironmentRecord)
  |  1:access __x0__ = (code "LexicallyScopedDeclarations")
  |  1:let declarations = __x0__
  |  2:let __x1__ = declarations
  |  2:let __x2__ = 0i
  |  2:while (< __x2__ __x1__.length) {
  |    let d = __x1__[__x2__]
  |    3:access __x3__ = (d "BoundNames")
  |    3:let __x4__ = __x3__
  |    3:let __x5__ = 0i
  |    3:while (< __x5__ __x4__.length) {
  |      let dn = __x4__[__x5__]
  |      6:access __x6__ = (d "IsConstantDeclaration")
  |      6:if (= __x6__ true) {
  |        5:app __x7__ = (env.CreateImmutableBinding env dn true)
  |        5:[! __x7__]
  |      } else {
  |        7:app __x8__ = (env.CreateMutableBinding env dn false)
  |        7:[! __x8__]
  |      }
  |      __x5__ = (+ __x5__ 1i)
  |    }
  |    8:if (|| (|| (|| (is-instance-of d FunctionDeclaration) (is-instance-of d GeneratorDeclaration)) (is-instance-of d AsyncFunctionDeclaration)) (is-instance-of d AsyncGeneratorDeclaration)) {
  |      9:access __x9__ = (d "BoundNames")
  |      9:let fn = __x9__[0i]
  |      10:access __x10__ = (d "InstantiateFunctionObject" env)
  |      10:let fo = __x10__
  |      11:app __x11__ = (env.InitializeBinding env fn fo)
  |      11:__x11__
  |    } else 10:{}
  |    __x2__ = (+ __x2__ 1i)
  |  }
  |}""".stripMargin)
  val code = scala.Array[String](
    """        1. Assert: _env_ is a declarative Environment Record.""",
    """        1. Let _declarations_ be the LexicallyScopedDeclarations of _code_.""",
    """        1. For each element _d_ of _declarations_, do""",
    """          1. For each element _dn_ of the BoundNames of _d_, do""",
    """            1. If IsConstantDeclaration of _d_ is *true*, then""",
    """              1. Perform ! _env_.CreateImmutableBinding(_dn_, *true*).""",
    """            1. Else,""",
    """              1. [id="step-blockdeclarationinstantiation-createmutablebinding"] Perform ! _env_.CreateMutableBinding(_dn_, *false*). NOTE: This step is replaced in section <emu-xref href="#sec-web-compat-blockdeclarationinstantiation"></emu-xref>.""",
    """          1. If _d_ is a |FunctionDeclaration|, a |GeneratorDeclaration|, an |AsyncFunctionDeclaration|, or an |AsyncGeneratorDeclaration|, then""",
    """            1. Let _fn_ be the sole element of the BoundNames of _d_.""",
    """            1. Let _fo_ be InstantiateFunctionObject of _d_ with argument _env_.""",
    """            1. [id="step-blockdeclarationinstantiation-initializebinding"] Perform _env_.InitializeBinding(_fn_, _fo_). NOTE: This step is replaced in section <emu-xref href="#sec-web-compat-blockdeclarationinstantiation"></emu-xref>.""",
  )
}
