package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.ir.Inst

trait Algo {
  val head: Head
  val ids: List[String]
  val rawBody: Inst
  val code: Iterable[String]
}
