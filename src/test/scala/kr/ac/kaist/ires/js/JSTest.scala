package kr.ac.kaist.ires.js

import kr.ac.kaist.ires.IRESTest
import kr.ac.kaist.ires.ast._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.phase._
import kr.ac.kaist.ires.model.{ Parser => JSParser, Script }
import kr.ac.kaist.ires.util.Useful._
import spray.json._

trait JSTest extends IRTest {
  override def category: String = "js"

  // parse JS codes
  def parse(str: String): Script =
    JSParser.parse(JSParser.Script(Nil), str).get
  def parseFile(filename: String): Script =
    JSParser.parse(JSParser.Script(Nil), fileReader(filename)).get
  def esparseFile(filename: String): Script =
    Script(executeCmd(s"bin/esparse $filename").parseJson)

  // eval JS codes
  def eval(str: String): State = Interp(Load(parse(str)))
  def evalFile(filename: String): State =
    Interp(Load(parseFile(filename), filename), filename)

  // tests for JS parser
  def parseTest(ast: AST): Unit = {
    val newAST = parse(ast.toString)
    assert(ast == newAST)
  }

  // tests for JS interpreter
  def evalTest(st: State): State = st.retVal match {
    case addr: Addr => st(addr, Str("Type")) match {
      case (addr: Addr) =>
        assert(addr == st(Id("CONST_normal")))
        st
      case v => fail(s"invalid completion type: ${v.beautified}")
    }
    case Absent => fail("no return value")
    case v => fail(s"return not an address: ${v.beautified}")
  }
  def evalTest(str: String): Unit = evalTest(eval(str))
  def evalTestFile(filename: String): Unit = evalTest(evalFile(filename))

  // conversion extension from .js to .ir
  val js2ir = changeExt("js", "ir")
}
