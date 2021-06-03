package kr.ac.kaist.ires.phase

import kr.ac.kaist.ires._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.util._

// IREval phase
case object IREval extends PhaseObj[State, IREvalConfig, State] {
  val name: String = "eval-ir"
  val help: String = "evaluates JavaScript source files to IR."

  def apply(
    initialSt: State,
    iresConfig: IRESConfig,
    config: IREvalConfig
  ): State = (new Interp(config.timeout))(initialSt)

  def defaultConfig: IREvalConfig = IREvalConfig()
  val options: List[PhaseOption[IREvalConfig]] = List(
    ("timeout", NumOption((c, i) => c.timeout = if (i == 0) None else Some(i)),
      "set timeout of interpreter(second), 0 for unlimited.")
  )
}

// IREval phase config
case class IREvalConfig(
  var timeout: Option[Long] = Some(TIMEOUT)
) extends Config
