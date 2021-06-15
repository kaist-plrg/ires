package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::InitializeHostDefinedRealm` extends Algo {
  val head = NormalHead("InitializeHostDefinedRealm", List())
  val ids = List(
    "sec-initializehostdefinedrealm",
    "sec-executable-code-and-execution-contexts",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (CreateRealm)
  |  0:let realm = __x0__
  |  1:let newContext = (new ExecutionContext("SubMap" -> (new SubMap())))
  |  2:newContext.Function = null
  |  3:newContext.Realm = realm
  |  4:newContext.ScriptOrModule = null
  |  5:append newContext -> EXECUTION_STACK
  |  5:CONTEXT = EXECUTION_STACK[(- EXECUTION_STACK.length 1i)]
  |  6:??? "If the host requires use of an exotic object to serve as id:{realm} ' s global object , let id:{global} be such an object created in a host - defined manner . Otherwise , let id:{global} be value:{undefined} , indicating that an ordinary object should be created as the global object ."
  |  7:??? "If the host requires that the code:{this} binding in id:{realm} ' s global scope return an object other than the global object , let id:{thisValue} be such an object created in a host - defined manner . Otherwise , let id:{thisValue} be value:{undefined} , indicating that id:{realm} ' s global code:{this} binding should be the global object ."
  |  8:app __x1__ = (SetRealmGlobalObject realm global thisValue)
  |  8:__x1__
  |  9:app __x2__ = (SetDefaultGlobalBindings realm)
  |  9:let globalObj = [? __x2__]
  |  10:??? "Create any host - defined global object properties on id:{globalObj} ."
  |  11:return CONST_empty
  |}""".stripMargin)
  val code = scala.Array[String](
    """      1. Let _realm_ be CreateRealm().""",
    """      1. Let _newContext_ be a new execution context.""",
    """      1. Set the Function of _newContext_ to *null*.""",
    """      1. Set the Realm of _newContext_ to _realm_.""",
    """      1. Set the ScriptOrModule of _newContext_ to *null*.""",
    """      1. Push _newContext_ onto the execution context stack; _newContext_ is now the running execution context.""",
    """      1. If the host requires use of an exotic object to serve as _realm_'s global object, let _global_ be such an object created in a host-defined manner. Otherwise, let _global_ be *undefined*, indicating that an ordinary object should be created as the global object.""",
    """      1. If the host requires that the `this` binding in _realm_'s global scope return an object other than the global object, let _thisValue_ be such an object created in a host-defined manner. Otherwise, let _thisValue_ be *undefined*, indicating that _realm_'s global `this` binding should be the global object.""",
    """      1. Perform SetRealmGlobalObject(_realm_, _global_, _thisValue_).""",
    """      1. Let _globalObj_ be ? SetDefaultGlobalBindings(_realm_).""",
    """      1. Create any host-defined global object properties on _globalObj_.""",
    """      1. Return NormalCompletion(~empty~).""",
  )
}
