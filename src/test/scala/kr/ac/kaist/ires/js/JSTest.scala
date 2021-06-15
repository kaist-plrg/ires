package kr.ac.kaist.ires.js

import kr.ac.kaist.ires.IRESTest
import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.phase._
import kr.ac.kaist.ires.model.{ Parser => JSParser, Script }
import kr.ac.kaist.ires.util.Useful._
import spray.json._

trait JSTest extends IRESTest {
  val category: String = "js"

  // parse js codes
  def parse(str: String): Script =
    JSParser.parse(JSParser.Script(Nil), str).get
  def parseFile(filename: String): Script =
    JSParser.parse(JSParser.Script(Nil), fileReader(filename)).get
  def esparseFile(filename: String): Script =
    Script(executeCmd(s"bin/esparse $filename").parseJson)

  // eval js codes
  def eval(str: String): State = Interp(Load(parse(str)))
  def evalFile(filename: String): State =
    Interp(Load(parseFile(filename), filename), filename)

  // tests for js parser
  def parseTest(ast: AST): Unit = {
    val newAST = parse(ast.toString)
    assert(ast == newAST)
  }

  // tests for js interpreter
  def evalTest(st: State): Unit = st.retVal match {
    case addr: Addr => st(addr, Str("Type")) match {
      case (addr: Addr) => assert(addr == st(Id("CONST_normal")))
      case v => fail(s"invalid completion type: ${v.beautified}")
    }
    case Absent => fail("no return value")
    case v => fail(s"return not an address: ${v.beautified}")
  }

  // conversion extension from .js to .ir
  val js2ir = changeExt("js", "ir")
}
