package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.model.Algorithm

// Walker that fixes the unique id of instructions
object FixUIdWalker {
  def apply(func: Func, algorithm: Algorithm): Func = func
}
