package kr.ac.kaist.ires.phase

import kr.ac.kaist.ires.{ LINE_SEP, IRESConfig }
import kr.ac.kaist.ires.model.{ Parser => JSParser, _ }
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.util.Useful._
import scala.io.Source

// Load phase
case object Load extends PhaseObj[Script, LoadConfig, State] {
  val name = "load"
  val help = "read script object and using global, convert to State object."

  def apply(
    script: Script,
    iresConfig: IRESConfig,
    config: LoadConfig
  ): State = Model.getInitState(
    program = Parser.parseProgram("""{
      app __x0__ = (RunJobs)
      return __x0__
    }"""),
    globals = Map(
      Id("script") -> ASTVal(script),
      Id("__filename__") -> Str(getFirstFilename(iresConfig, "load")),
    )
  )

  def defaultConfig: LoadConfig = LoadConfig()
  val options: List[PhaseOption[LoadConfig]] = List()
}

// Parse phase config
case class LoadConfig() extends Config
