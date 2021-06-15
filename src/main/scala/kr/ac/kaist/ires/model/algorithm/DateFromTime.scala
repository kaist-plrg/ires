package kr.ac.kaist.ires.model

import kr.ac.kaist.ires.algorithm._
import kr.ac.kaist.ires.grammar._
import kr.ac.kaist.ires.ir._
import kr.ac.kaist.ires.ir.Parser._
import Param.Kind._

object `AL::DateFromTime` extends Algo {
  val head = NormalHead("DateFromTime", List(Param("t", Normal)))
  val ids = List(
    "sec-date-number",
    "sec-overview-of-date-objects-and-definitions-of-abstract-operations",
    "sec-date-objects",
    "sec-numbers-and-dates",
  )
  val rawBody = parseInst("""{
  |  0:app __x0__ = (MonthFromTime t)
  |  0:if (== __x0__ 0i) {
  |    app __x1__ = (DayWithinYear t)
  |    return (+ __x1__ 1i)
  |  } else 8:{}
  |  1:app __x2__ = (MonthFromTime t)
  |  1:if (== __x2__ 1i) {
  |    app __x3__ = (DayWithinYear t)
  |    return (- __x3__ 30i)
  |  } else 8:{}
  |  2:app __x4__ = (MonthFromTime t)
  |  2:if (== __x4__ 2i) {
  |    app __x5__ = (DayWithinYear t)
  |    app __x6__ = (InLeapYear t)
  |    return (- (- __x5__ 58i) __x6__)
  |  } else 8:{}
  |  3:app __x7__ = (MonthFromTime t)
  |  3:if (== __x7__ 3i) {
  |    app __x8__ = (DayWithinYear t)
  |    app __x9__ = (InLeapYear t)
  |    return (- (- __x8__ 89i) __x9__)
  |  } else 8:{}
  |  4:app __x10__ = (MonthFromTime t)
  |  4:if (== __x10__ 4i) {
  |    app __x11__ = (DayWithinYear t)
  |    app __x12__ = (InLeapYear t)
  |    return (- (- __x11__ 119i) __x12__)
  |  } else 8:{}
  |  5:app __x13__ = (MonthFromTime t)
  |  5:if (== __x13__ 5i) {
  |    app __x14__ = (DayWithinYear t)
  |    app __x15__ = (InLeapYear t)
  |    return (- (- __x14__ 150i) __x15__)
  |  } else 8:{}
  |  6:app __x16__ = (MonthFromTime t)
  |  6:if (== __x16__ 6i) {
  |    app __x17__ = (DayWithinYear t)
  |    app __x18__ = (InLeapYear t)
  |    return (- (- __x17__ 180i) __x18__)
  |  } else 8:{}
  |  7:app __x19__ = (MonthFromTime t)
  |  7:if (== __x19__ 7i) {
  |    app __x20__ = (DayWithinYear t)
  |    app __x21__ = (InLeapYear t)
  |    return (- (- __x20__ 211i) __x21__)
  |  } else 8:{}
  |  8:app __x22__ = (MonthFromTime t)
  |  8:if (== __x22__ 8i) {
  |    app __x23__ = (DayWithinYear t)
  |    app __x24__ = (InLeapYear t)
  |    return (- (- __x23__ 242i) __x24__)
  |  } else 8:{}
  |  9:app __x25__ = (MonthFromTime t)
  |  9:if (== __x25__ 9i) {
  |    app __x26__ = (DayWithinYear t)
  |    app __x27__ = (InLeapYear t)
  |    return (- (- __x26__ 272i) __x27__)
  |  } else 8:{}
  |  10:app __x28__ = (MonthFromTime t)
  |  10:if (== __x28__ 10i) {
  |    app __x29__ = (DayWithinYear t)
  |    app __x30__ = (InLeapYear t)
  |    return (- (- __x29__ 303i) __x30__)
  |  } else 8:{}
  |  11:app __x31__ = (MonthFromTime t)
  |  11:if (== __x31__ 11i) {
  |    app __x32__ = (DayWithinYear t)
  |    app __x33__ = (InLeapYear t)
  |    return (- (- __x32__ 333i) __x33__)
  |  } else 8:{}
  |}""".stripMargin)
  val code = scala.Array[String](
    """          = DayWithinYear(_t_) + *1*<sub>𝔽</sub> if MonthFromTime(_t_) = *+0*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *30*<sub>𝔽</sub> if MonthFromTime(_t_) = *1*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *58*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *2*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *89*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *3*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *119*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *4*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *150*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *5*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *180*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *6*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *211*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *7*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *242*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *8*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *272*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *9*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *303*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *10*<sub>𝔽</sub>""",
    """          = DayWithinYear(_t_) - *333*<sub>𝔽</sub> - InLeapYear(_t_) if MonthFromTime(_t_) = *11*<sub>𝔽</sub>""",
  )
}
