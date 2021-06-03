package kr.ac.kaist.ires

import kr.ac.kaist.ires.IRESTest
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.model.{ AST, Parser => JSParser }
import kr.ac.kaist.ires.util.Useful._

trait JSTest extends IRESTest {
  val category: String = "js"

  // tests for js-parser
  def parseJSTest(ast: AST): Unit = {
    val newAST = JSParser.parse(JSParser.Script(Nil), ast.toString).get
    assert(ast == newAST)
  }

  // tests for js-interpreter
  def evalJSTest(st: State): Unit = st.context.locals.get(st.context.retId) match {
    case Some(addr: Addr) => st.heap(addr, Str("Type")) match {
      case (addr: Addr) =>
        assert(addr == st.globals.getOrElse(Id("CONST_normal"), Absent))
      case v => fail(s"invalid completion type: $v")
    }
    case Some(v) => fail(s"return not an address: $v")
    case None => fail("no return value")
  }

  // conversion extension from .js to .ir
  val js2ir = changeExt("js", "ir")
}
