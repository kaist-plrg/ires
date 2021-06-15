package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::MonthFromTime` extends Algo {
  val head = NormalHead("MonthFromTime", List(Param("t", Normal)))
  val ids = List(
    "eqn-MonthFromTime",
    "sec-month-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (DayWithinYear t)
  |  0:if (&& (! (< __x0__ 0i)) (< __x0__ 31i)) return 0i else 8:{}
  |  1:app __x1__ = (DayWithinYear t)
  |  1:app __x2__ = (InLeapYear t)
  |  1:if (&& (! (< __x1__ 31i)) (< __x1__ (+ 59i __x2__))) return 1i else 8:{}
  |  2:app __x3__ = (InLeapYear t)
  |  2:app __x4__ = (DayWithinYear t)
  |  2:app __x5__ = (InLeapYear t)
  |  2:if (&& (! (< __x4__ (+ 59i __x3__))) (< __x4__ (+ 90i __x5__))) return 2i else 8:{}
  |  3:app __x6__ = (InLeapYear t)
  |  3:app __x7__ = (DayWithinYear t)
  |  3:app __x8__ = (InLeapYear t)
  |  3:if (&& (! (< __x7__ (+ 90i __x6__))) (< __x7__ (+ 120i __x8__))) return 3i else 8:{}
  |  4:app __x9__ = (InLeapYear t)
  |  4:app __x10__ = (DayWithinYear t)
  |  4:app __x11__ = (InLeapYear t)
  |  4:if (&& (! (< __x10__ (+ 120i __x9__))) (< __x10__ (+ 151i __x11__))) return 4i else 8:{}
  |  5:app __x12__ = (InLeapYear t)
  |  5:app __x13__ = (DayWithinYear t)
  |  5:app __x14__ = (InLeapYear t)
  |  5:if (&& (! (< __x13__ (+ 151i __x12__))) (< __x13__ (+ 181i __x14__))) return 5i else 8:{}
  |  6:app __x15__ = (InLeapYear t)
  |  6:app __x16__ = (DayWithinYear t)
  |  6:app __x17__ = (InLeapYear t)
  |  6:if (&& (! (< __x16__ (+ 181i __x15__))) (< __x16__ (+ 212i __x17__))) return 6i else 8:{}
  |  7:app __x18__ = (InLeapYear t)
  |  7:app __x19__ = (DayWithinYear t)
  |  7:app __x20__ = (InLeapYear t)
  |  7:if (&& (! (< __x19__ (+ 212i __x18__))) (< __x19__ (+ 243i __x20__))) return 7i else 8:{}
  |  8:app __x21__ = (InLeapYear t)
  |  8:app __x22__ = (DayWithinYear t)
  |  8:app __x23__ = (InLeapYear t)
  |  8:if (&& (! (< __x22__ (+ 243i __x21__))) (< __x22__ (+ 273i __x23__))) return 8i else 8:{}
  |  9:app __x24__ = (InLeapYear t)
  |  9:app __x25__ = (DayWithinYear t)
  |  9:app __x26__ = (InLeapYear t)
  |  9:if (&& (! (< __x25__ (+ 273i __x24__))) (< __x25__ (+ 304i __x26__))) return 9i else 8:{}
  |  10:app __x27__ = (InLeapYear t)
  |  10:app __x28__ = (DayWithinYear t)
  |  10:app __x29__ = (InLeapYear t)
  |  10:if (&& (! (< __x28__ (+ 304i __x27__))) (< __x28__ (+ 334i __x29__))) return 10i else 8:{}
  |  11:app __x30__ = (InLeapYear t)
  |  11:app __x31__ = (DayWithinYear t)
  |  11:app __x32__ = (InLeapYear t)
  |  11:if (&& (! (< __x31__ (+ 334i __x30__))) (< __x31__ (+ 365i __x32__))) return 11i else 8:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          = *+0*<sub>𝔽</sub> if *+0*<sub>𝔽</sub> ≤ DayWithinYear(_t_) < *31*<sub>𝔽</sub>""",
    """          = *1*<sub>𝔽</sub> if *31*<sub>𝔽</sub> ≤ DayWithinYear(_t_) < *59*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *2*<sub>𝔽</sub> if *59*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *90*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *3*<sub>𝔽</sub> if *90*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *120*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *4*<sub>𝔽</sub> if *120*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *151*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *5*<sub>𝔽</sub> if *151*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *181*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *6*<sub>𝔽</sub> if *181*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *212*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *7*<sub>𝔽</sub> if *212*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *243*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *8*<sub>𝔽</sub> if *243*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *273*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *9*<sub>𝔽</sub> if *273*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *304*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *10*<sub>𝔽</sub> if *304*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *334*<sub>𝔽</sub> + InLeapYear(_t_)""",
    """          = *11*<sub>𝔽</sub> if *334*<sub>𝔽</sub> + InLeapYear(_t_) ≤ DayWithinYear(_t_) < *365*<sub>𝔽</sub> + InLeapYear(_t_)""",
  )
}
