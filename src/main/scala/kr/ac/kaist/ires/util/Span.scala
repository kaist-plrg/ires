package kr.ac.kaist.ires.util

case class Span(var start: Pos = Pos(), var end: Pos = Pos())
case class Pos(var line: Int = -1, var column: Int = -1)
