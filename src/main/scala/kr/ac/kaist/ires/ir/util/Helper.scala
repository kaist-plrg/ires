package kr.ac.kaist.ires.ir

object Helper {
  def toStringHelper(m: Double, radix: Int): String = {
    if (m.isNaN) "NaN"
    else if (m == 0) "0"
    else if (m < 0) "-" + toStringHelper(-m, radix)
    else if (m.isPosInfinity) "Infinity"
    else {
      var s = BigDecimal(m)
      var n = 0
      while (s % radix == BigDecimal(0) || s % 1 != BigDecimal(0)) {
        if (s % radix == BigDecimal(0)) { s /= radix; n += 1 }
        else { s *= radix; n -= 1 }
      }
      while ((((s - (s % radix)) / radix) * BigDecimal(radix).pow(n + 1)).toDouble == m) {
        s = (s - (s % radix)) / radix
        n = n + 1
      }
      var sLong = s.toLong
      var k = 0
      while (s >= BigDecimal(1)) { s /= radix; k += 1 }
      n += k
      if (k <= n && n <= 21) {
        getStr(sLong, radix) + ("0" * (n - k))
      } else if (0 < n && n <= 21) {
        val str = getStr(sLong, radix)
        str.substring(0, n) + '.' + str.substring(n)
      } else if (-6 < n && n <= 0) {
        "0." + ("0" * (-n)) + getStr(sLong, radix)
      } else if (k == 1) {
        getStr(sLong, radix) + "e" + getSign(n) + math.abs(n - 1).toString
      } else {
        val str = getStr(sLong, radix)
        str.substring(0, 1) + '.' + str.substring(1) + 'e' + getSign(n) + math.abs(n - 1).toString
      }
    }
  }

  // get sign
  def getSign(n: Int): Char = if (n - 1 > 0) '+' else '-'

  // get string of number
  def getStr(number: Long, radix: Int): String = {
    var str = ""
    var sLong = number
    while (sLong > 0) { str += getRadixString(sLong % radix); sLong /= radix }
    str.reverse
  }

  // get radix string of number
  def getRadixString(d: Long): String = {
    if (d < 10) d.toString else ('a' + (d - 10)).toChar.toString
  }

  // normalize strings
  def norm(str: String): String =
    str.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n")
}
