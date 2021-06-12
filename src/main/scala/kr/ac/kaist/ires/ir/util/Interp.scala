package kr.ac.kaist.ires.ir

import kr.ac.kaist.ires.{ DEBUG, TIMEOUT }
import kr.ac.kaist.ires.error.NotSupported
import kr.ac.kaist.ires.util.Useful._
import kr.ac.kaist.ires.model.{ Parser => ESParser, Model, Lexical }
import kr.ac.kaist.ires.parser.ESValueParser

// IR Interpreter
case class Interp(
  st: State,
  filename: String = "unknown",
  timeLimit: Option[Long] = Some(TIMEOUT)
) {
  // set start time of interpreter
  val startTime: Long = System.currentTimeMillis

  // the number of instructions
  def getInstCount: Int = instCount
  private var instCount: Int = 0

  // iteration period for check
  private val CHECK_PERIOD = 10000

  // perform transition until instructions are empty
  // @tailrec
  final def fixpoint: Unit = st.context.insts match {
    case Nil => st.ctxtStack match {
      case Nil =>
      case ctxt :: rest => {
        st.context.locals += ctxt.retId -> Absent
        st.ctxtStack = rest
        fixpoint
      }
    }
    case inst :: rest => {
      st.context.insts = rest
      interp(inst)
      fixpoint
    }
  }

  // transition for instructions
  def interp(inst: Inst): Unit = {
    instCount += 1
    if (instCount % CHECK_PERIOD == 0) timeLimit.map(limit => {
      val duration = (System.currentTimeMillis - startTime) / 1000
      if (duration > limit) error("TIMEOUT")
    })
    if (DEBUG) inst match {
      case ISeq(_) =>
      case _ => println(s"${st.context.name}: ${inst.beautified}")
    }
    inst match {
      case IIf(cond, thenInst, elseInst) => interp(cond).escaped(st) match {
        case Bool(true) => st.context.insts ::= thenInst
        case Bool(false) => st.context.insts ::= elseInst
        case v => error(s"not a boolean: ${v.beautified}")
      }
      case IWhile(cond, body) => interp(cond).escaped(st) match {
        case Bool(true) => st.context.insts = body :: inst :: st.context.insts
        case Bool(false) =>
        case v => error(s"not a boolean: ${v.beautified}")
      }
      case IApp(id, fexpr, args) => ???
      case IAccess(id, bexpr, expr, args) => ???
      case IExpr(expr) => interp(expr)
      case ILet(id, expr) => st.context.locals += id -> interp(expr)
      case IAssign(ref, expr) => st.update(interp(ref), interp(expr))
      case IDelete(ref) => st.delete(interp(ref))
      case IAppend(expr, list) => interp(list).escaped(st) match {
        case (addr: Addr) => st.append(addr, interp(expr).escaped(st))
        case v => error(s"not an address: ${v.beautified}")
      }
      case IPrepend(expr, list) => interp(list).escaped(st) match {
        case (addr: Addr) => st.prepend(addr, interp(expr).escaped(st))
        case v => error(s"not an address: ${v.beautified}")
      }
      case IReturn(expr) => st.ctxtStack match {
        case Nil => error(s"no remaining calling contexts")
        case ctxt :: rest =>
          ctxt.locals += ctxt.retId -> interp(expr)
          st.context = ctxt
          st.ctxtStack = rest
      }
      case IThrow(name) => ???
      case IAssert(expr) => interp(expr).escaped(st) match {
        case Bool(true) =>
        case v => error(s"assertion failure: ${v.toString}")
      }
      case IPrint(expr) => interp(expr) match {
        case (addr: Addr) => println(st.getString(addr))
        case v => println(v.beautified)
      }
      case IWithCont(id, params, bodyInst) => ???
      case ISeq(insts) => st.context.insts = insts ++ st.context.insts
    }
  }
  //   if (DEBUG) inst match {
  //     case ISeq(_) =>
  //     case _ => println(s"${st.context.name}: ${inst.beautified}")
  //   }
  //   val res = inst match {
  //     case IExpr(expr) =>
  //       val (_, s0) = interp(expr)(st)
  //       s0
  //     case ILet(id, expr) =>
  //       val (value, s0) = interp(expr)(st)
  //       s0.define(id, value)
  //     case IAssign(ref, expr) =>
  //       val (refV, s0) = interp(ref)(st)
  //       val (value, s1) = interp(expr)(s0)
  //       s1.updated(refV, value)
  //     case IApp(id, fexpr, args) =>
  //       val (fv, s0) = interp(fexpr)(st)
  //       fv match {
  //         case Func(fname, params, varparam, body) =>
  //           val (locals0, s1, restArg) = params.foldLeft(Map[Id, Value](), s0, args) {
  //             case ((map, st, arg :: rest), param) =>
  //               val (av, s0) = interp(arg)(st)
  //               (map + (param -> av), s0, rest)
  //             case (triple, _) => triple
  //           }
  //           val (locals1, s2) = varparam.map((param) => {
  //             val (av, s0) = interp(EList(restArg))(s1)
  //             (locals0 + (param -> av), s0)
  //           }).getOrElse((locals0, s1))

  //           val updatedCtxt = s2.context.copy(retId = id)
  //           val newCtxt = Context(name = fname, insts = List(body), locals = locals1)
  //           s2.copy(context = newCtxt, ctxtStack = updatedCtxt :: s2.ctxtStack)
  //         case ASTMethod(Func(fname, params, _, body), baseLocals) =>
  //           val (locals, s1, _) = params.foldLeft(baseLocals, s0, args) {
  //             case ((map, st, arg :: rest), param) =>
  //               val (av, s0) = interp(arg)(st)
  //               (map + (param -> av), s0, rest)
  //             case (triple, _) => triple
  //           }

  //           val updatedCtxt = s1.context.copy(retId = id)
  //           val newCtxt = Context(name = fname, insts = List(body), locals = locals)
  //           s1.copy(context = newCtxt, ctxtStack = updatedCtxt :: s1.ctxtStack)
  //         case Cont(params, body, context, ctxtStack) =>
  //           val (locals0, s1, restArg) = params.foldLeft(Map[Id, Value](), s0, args) {
  //             case ((map, st, arg :: rest), param) =>
  //               val (av, s0) = interp(arg)(st)
  //               (map + (param -> av), s0, rest)
  //             case (triple, _) => triple
  //           }

  //           val updatedCtxt = context.copy(insts = List(body), locals = context.locals ++ locals0)
  //           s1.copy(context = updatedCtxt, ctxtStack = ctxtStack)

  //         case v => error(s"not a function: $v")
  //       }
  //     case IAccess(id, bexpr, expr) =>
  //       val (base, s1) = interp(bexpr)(st)
  //       val (p, s2) = escapeCompletion(interp(expr)(s1))
  //       (base, p) match {
  //         case (addr: Addr, p) => s2.get(addr) match {
  //           case Some(IRMap(Ty("Completion"), m, _)) if !m.contains(p) => m(Str("Value"))._1 match {
  //             case a: Addr => s2.define(id, s2.heap(a, p))
  //             case Str(s) => p match {
  //               case Str("length") => s2.define(id, INum(s.length))
  //               case INum(k) => s2.define(id, Str(s(k.toInt).toString))
  //               case Num(k) => s2.define(id, Str(s(k.toInt).toString))
  //               case v => error(s"wrong access of string reference: $s.$p")
  //             }
  //             case _ => error(s"Completion does not have value: $bexpr[$expr]")
  //           }
  //           case _ => s2.define(id, s2.heap(addr, p))
  //         }
  //         case (ASTVal(Lexical(kind, str)), Str(name)) => s2.define(id, (kind, name) match {
  //           case ("(IdentifierName \\ (ReservedWord))" | "IdentifierName", "StringValue") => Str(ESValueParser.parseIdentifier(str))
  //           case ("NumericLiteral", "MV") => Num(ESValueParser.parseNumber(str))
  //           case ("StringLiteral", "SV" | "StringValue") => Str(ESValueParser.parseString(str))
  //           case ("NoSubstitutionTemplate", "TV") => Str(ESValueParser.parseTVNoSubstitutionTemplate(str))
  //           case ("TemplateHead", "TV") => Str(ESValueParser.parseTVTemplateHead(str))
  //           case ("TemplateMiddle", "TV") => Str(ESValueParser.parseTVTemplateMiddle(str))
  //           case ("TemplateTail", "TV") => Str(ESValueParser.parseTVTemplateTail(str))
  //           case ("NoSubstitutionTemplate", "TRV") => Str(ESValueParser.parseTRVNoSubstitutionTemplate(str))
  //           case ("TemplateHead", "TRV") => Str(ESValueParser.parseTRVTemplateHead(str))
  //           case ("TemplateMiddle", "TRV") => Str(ESValueParser.parseTRVTemplateMiddle(str))
  //           case ("TemplateTail", "TRV") => Str(ESValueParser.parseTRVTemplateTail(str))
  //           case (_, "Contains") => Func("", Nil, None, IReturn(EBool(false)))
  //           case _ => throw new Error(s"$kind, $str, $name")
  //         })
  //         case (astV: ASTVal, Str(name)) =>
  //           val ASTVal(ast) = astV
  //           name match {
  //             case "parent" => s2.define(id, ast.parent.map(ASTVal(_)).getOrElse(Absent))
  //             case name =>
  //               ast.semantics(name) match {
  //                 case Some((Func(fname, params, varparam, body), lst)) =>
  //                   val (locals, rest) = lst.foldLeft(Map[Id, Value](), params) {
  //                     case ((map, param :: rest), arg) =>
  //                       (map + (param -> arg), rest)
  //                     case (pair, _) => pair
  //                   }
  //                   rest match {
  //                     case Nil =>
  //                       val updatedCtxt = s2.context.copy(retId = id)
  //                       val newCtxt = Context(name = fname, insts = List(body), locals = locals)
  //                       s2.copy(context = newCtxt, ctxtStack = updatedCtxt :: s2.ctxtStack)
  //                     case _ =>
  //                       s2.define(id, ASTMethod(Func(fname, rest, varparam, body), locals))
  //                   }
  //                 case None => ast.subs(name) match {
  //                   case Some(v) => s2.define(id, v)
  //                   case None => error(s"Unexpected semantics: ${ast.name}.$name")
  //                 }
  //               }
  //           }
  //         case (Str(str), p) => p match {
  //           case Str("length") => s2.define(id, INum(str.length))
  //           case INum(k) => s2.define(id, Str(str(k.toInt).toString))
  //           case Num(k) => s2.define(id, Str(str(k.toInt).toString))
  //           case v => error(s"wrong access of string reference: $str.$p")
  //         }
  //         case v => error(s"not an address: $v")
  //       }
  //     case IWithCont(id, params, body) => {
  //       val s0 = st.define(id, Cont(params, ISeq(st.context.insts), st.context, st.ctxtStack))
  //       s0.copy(context = s0.context.copy(insts = List(body)))
  //     }
  //   }
  //   if (instCount % 100000 == 0) GC.gc(res)
  //   else res
  // }

  // expresssions
  def interp(expr: Expr): Value = expr match {
    case ENum(n) => Num(n)
    case EINum(n) => INum(n)
    case EBigINum(b) => BigINum(b)
    case EStr(str) => Str(str)
    case EBool(b) => Bool(b)
    case EUndef => Undef
    case ENull => Null
    case EAbsent => Absent
    case EMap(ty, props) => {
      val addr = st.allocMap(ty)
      for ((kexpr, vexpr) <- props) {
        val k = interp(kexpr).escaped(st)
        val v = interp(vexpr)
        st.update(addr, k, v)
      }
      addr
    }
    case EList(exprs) => st.allocList(exprs.map(interp))
    case ESymbol(desc) => interp(desc) match {
      case (str: Str) => st.allocSymbol(str)
      case v => error(s"not a string: ${v.beautified}")
    }
    case EPop(list, idx) => interp(list).escaped(st) match {
      case (addr: Addr) => st.pop(addr, interp(idx).escaped(st))
      case v => error(s"not an address: ${v.beautified}")
    }
    case ERef(ref) => st(interp(ref))
    case ECont(params, body) => ???
    case EUOp(uop, expr) => {
      val x = interp(expr).escaped(st)
      interp(uop, x)
    }
    case EBOp(OAnd, left, right) => shortCircuit(OAnd, left, right)
    case EBOp(OOr, left, right) => shortCircuit(OOr, left, right)
    case EBOp(bop, left, right) => {
      val l = interp(left).escaped(st)
      val r = interp(right).escaped(st)
      interp(bop, l, r)
    }
    case ETypeOf(expr) => Str(interp(expr).escaped(st) match {
      case (addr: Addr) => st(addr) match {
        case IRNotSupported(tyname, desc) => tyname
        case obj => {
          val name = obj.ty.name
          if (name endsWith "Object") "Object" else name
        }
      }
      case Num(_) | INum(_) => "Number"
      case BigINum(_) => "BigInt"
      case Str(_) => "String"
      case Bool(_) => "Boolean"
      case Undef => "Undefined"
      case Null => "Null"
      case Absent => "Absent"
      case Func(_, _, _, _) => "Function"
      case Cont(_, _, _, _) => "Continuation"
      case ASTVal(_) => "AST"
      case ASTMethod(_, _) => "ASTMethod"
    })
    case EIsCompletion(expr) => Bool(interp(expr) match {
      case addr: Addr => st(addr) match {
        case IRMap(Ty("Completion"), _, _) => true
        case _ => false
      }
      case _ => false
    })
    case EIsInstanceOf(base, name) => ???
    case EGetElems(base, name) => ???
    case EGetSyntax(base) => ???
    case EParseSyntax(code, rule, flags) => ???
    case EConvert(source, target, flags) => ???
    case EContains(list, elem) => ???
    case EReturnIfAbrupt(expr, check) => ???
    case ECopy(obj) => ???
    case EKeys(mobj) => ???
    case ENotSupported(msg) => ???
  }
  // st => expr match {
  //   case ECont(params, body) =>
  //     (Cont(params, body, st.context, st.ctxtStack), st)
  //   case EIsInstanceOf(base, kind) => escapeCompletion(interp(base)(st)) match {
  //     case (ASTVal(ast), s0) => (Bool(ast.name == kind || ast.getKinds.contains(kind)), s0)
  //     case (Str(str), s0) => (Bool(str == kind), s0)
  //     case (v, _) => error(s"not an AST value: $v")
  //   }
  //   case EGetElems(base, kind) => escapeCompletion(interp(base)(st)) match {
  //     case (ASTVal(ast), s0) => s0.allocList(ast.getElems(kind).map(ASTVal(_)))
  //     case (v, _) => error(s"not an AST value: $v")
  //   }
  //   case EGetSyntax(base) => escapeCompletion(interp(base)(st)) match {
  //     case (ASTVal(ast), s0) => (Str(ast.toString), s0)
  //     case (v, s0) => error(s"not an AST value: $v")
  //   }
  //   case EParseSyntax(code, rule, flags) =>
  //     val (v, s0) = escapeCompletion(interp(code)(st))
  //     val (p, s1) = escapeCompletion(interp(rule)(st)) match {
  //       case (Str(str), st) => (ESParser.rules.getOrElse(str, error(s"not exist parse rule: $rule")), st)
  //       case (v, _) => error(s"not a string: $v")
  //     }
  //     v match {
  //       case ASTVal(ast) =>
  //         val newVal = try {
  //           ASTVal(Await.result(Future(
  //             ESParser.parse(p(ast.parserParams), ast.toString).get
  //           ), timeLimit.map(_.seconds).getOrElse(Duration.Inf)))
  //         } catch {
  //           case e: TimeoutException => error("parser timeout")
  //           case e: Throwable => Absent
  //         }
  //         newVal match {
  //           case ASTVal(s) => Model.checkSupported(s)
  //           case _ => ()
  //         }
  //         (newVal, s1)
  //       case Str(str) =>
  //         val (s2, parserParams) = flags.foldLeft(s1, List[Boolean]()) {
  //           case ((st, ps), param) =>
  //             val (av, s1) = interp(param)(st)
  //             av match {
  //               case Bool(v) => (s1, ps :+ v)
  //               case _ => error(s"parserParams should be boolean")
  //             }
  //         }
  //         val newVal = try {
  //           ASTVal(Await.result(Future(
  //             ESParser.parse(p(parserParams), str).get
  //           ), timeLimit.map(_.seconds).getOrElse(Duration.Inf)))
  //         } catch {
  //           case e: TimeoutException => error("parser timeout")
  //           case e: Throwable => Absent
  //         }
  //         newVal match {
  //           case ASTVal(s) => Model.checkSupported(s)
  //           case _ => ()
  //         }
  //         (newVal, s2)
  //       case v => error(s"not an AST value or a string: $v")
  //     }
  //   case EConvert(expr, cop, l) => escapeCompletion(interp(expr)(st)) match {
  //     case (Str(s), s0) => {
  //       (cop match {
  //         case CStrToNum => Num(ESValueParser.str2num(s))
  //         case _ => error(s"not convertable option: Str to $cop")
  //       }, s0)
  //     }
  //     case (INum(n), s0) => {
  //       val (radix, s1) = l match {
  //         case e :: rest => escapeCompletion(interp(e)(s0)) match {
  //           case (INum(n), s1) => (n.toInt, s1)
  //           case (Num(n), s1) => (n.toInt, s1)
  //           case _ => error("radix is not int")
  //         }
  //         case _ => (10, s0)
  //       }
  //       (cop match {
  //         case CNumToStr => Str(Helper.toStringHelper(n, radix))
  //         case CNumToInt => INum(n)
  //         case _ => error(s"not convertable option: Num to $cop")
  //       }, s1)
  //     }
  //     case (Num(n), s0) => {
  //       val (radix, s1) = l match {
  //         case e :: rest => escapeCompletion(interp(e)(s0)) match {
  //           case (INum(n), s1) => (n.toInt, s1)
  //           case (Num(n), s1) => (n.toInt, s1)
  //           case _ => error("radix is not int")
  //         }
  //         case _ => (10, s0)
  //       }
  //       (cop match {
  //         case CNumToStr => Str(Helper.toStringHelper(n, radix))
  //         case CNumToInt => INum((math.signum(n) * math.floor(math.abs(n))).toLong)
  //         case _ => error(s"not convertable option: Num to $cop")
  //       }, s1)
  //     }
  //     case (v, s0) => error(s"not an convertable value: $v")
  //   }
  //   case EContains(list, elem) =>
  //     val (l, s0) = escapeCompletion(interp(list)(st))
  //     l match {
  //       case (addr: Addr) => s0.heap(addr) match {
  //         case IRList(vs) =>
  //           val (v, s1) = escapeCompletion(interp(elem)(st))
  //           (Bool(vs contains v), s1)
  //         case obj => error(s"not a list: $obj")
  //       }
  //       case v => error(s"not an address: $v")
  //     }
  //   case ECopy(expr) =>
  //     val (v, s0) = escapeCompletion(interp(expr)(st))
  //     v match {
  //       case (addr: Addr) => s0.copyObj(addr)
  //       case v => error(s"not an address: $v")
  //     }
  //   case EKeys(expr) =>
  //     val (v, s0) = escapeCompletion(interp(expr)(st))
  //     v match {
  //       case (addr: Addr) => s0.keys(addr)
  //       case v => error(s"not an address: $v")
  //     }
  //   case ENotSupported(msg) => throw NotSupported(msg)
  // }

  // references
  def interp(ref: Ref): RefValue = ref match {
    case RefId(id) => RefValueId(id)
    case RefProp(ref, expr) => {
      var base = st(interp(ref))
      val p = interp(expr).escaped(st)
      p match {
        case Str("Type" | "Value" | "Target") => st(interp(ref))
        case _ => base = base.escaped(st)
      }
      base match {
        case (addr: Addr) => RefValueProp(addr, p)
        case Str(str) => RefValueString(str, p)
        case v => error(s"not an address: ${v.beautified}")
      }
    }
  }

  // unary operators
  def interp(uop: UOp, operand: Value): Value = (uop, operand) match {
    case (ONeg, Num(n)) => Num(-n)
    case (ONeg, INum(n)) => INum(-n)
    case (ONot, Bool(b)) => Bool(!b)
    case (OBNot, Num(n)) => INum(~(n.toInt))
    case (OBNot, INum(n)) => INum(~n)
    case (_, value) => error(s"wrong type of value for the operator ${uop.beautified}: ${value.beautified}")
  }

  // binary operators
  def interp(bop: BOp, left: Value, right: Value): Value = (bop, left, right) match {
    // double operations
    case (OPlus, Num(l), Num(r)) => Num(l + r)
    case (OSub, Num(l), Num(r)) => Num(l - r)
    case (OMul, Num(l), Num(r)) => Num(l * r)
    case (OPow, Num(l), Num(r)) => Num(math.pow(l, r))
    case (ODiv, Num(l), Num(r)) => Num(l / r)
    case (OMod, Num(l), Num(r)) => Num(modulo(l, r))
    case (OUMod, Num(l), Num(r)) => Num(unsigned_modulo(l, r))
    case (OLt, Num(l), Num(r)) => Bool(l < r)

    // double with long operations
    case (OPlus, INum(l), Num(r)) => Num(l + r)
    case (OSub, INum(l), Num(r)) => Num(l - r)
    case (OMul, INum(l), Num(r)) => Num(l * r)
    case (ODiv, INum(l), Num(r)) => Num(l / r)
    case (OMod, INum(l), Num(r)) => Num(modulo(l, r))
    case (OPow, INum(l), Num(r)) => Num(scala.math.pow(l, r))
    case (OUMod, INum(l), Num(r)) => Num(unsigned_modulo(l, r))
    case (OLt, INum(l), Num(r)) => Bool(l < r)
    case (OPlus, Num(l), INum(r)) => Num(l + r)
    case (OSub, Num(l), INum(r)) => Num(l - r)
    case (OMul, Num(l), INum(r)) => Num(l * r)
    case (ODiv, Num(l), INum(r)) => Num(l / r)
    case (OMod, Num(l), INum(r)) => Num(modulo(l, r))
    case (OPow, Num(l), INum(r)) => Num(math.pow(l, r))
    case (OUMod, Num(l), INum(r)) => Num(unsigned_modulo(l, r))
    case (OLt, Num(l), INum(r)) => Bool(l < r)

    // string operations
    case (OPlus, Str(l), Str(r)) => Str(l + r)
    case (OSub, Str(l), INum(r)) => Str(l.dropRight(r.toInt))
    case (OLt, Str(l), Str(r)) => Bool(l < r)

    // long operations
    case (OPlus, INum(l), INum(r)) => INum(l + r)
    case (OSub, INum(l), INum(r)) => INum(l - r)
    case (OMul, INum(l), INum(r)) => INum(l * r)
    case (ODiv, INum(l), INum(r)) => INum(l / r)
    case (OUMod, INum(l), INum(r)) => INum(unsigned_modulo(l, r).toLong)
    case (OMod, INum(l), INum(r)) => INum(modulo(l, r).toLong)
    case (OLt, INum(l), INum(r)) => Bool(l < r)
    case (OBAnd, INum(l), INum(r)) => INum(l & r)
    case (OBOr, INum(l), INum(r)) => INum(l | r)
    case (OBXOr, INum(l), INum(r)) => INum(l ^ r)
    case (OLShift, INum(l), INum(r)) => INum((l.toInt << r.toInt).toLong)
    case (OSRShift, INum(l), INum(r)) => INum((l.toInt >> r.toInt).toLong)
    case (OURShift, INum(l), INum(r)) => INum((l.toLong >>> r.toInt).toLong & 0xffffffffL)

    // logical operations
    case (OAnd, Bool(l), Bool(r)) => Bool(l && r)
    case (OOr, Bool(l), Bool(r)) => Bool(l || r)
    case (OXor, Bool(l), Bool(r)) => Bool(l ^ r)

    // equality operations
    case (OEq, INum(l), Num(r)) => Bool(!(r equals -0.0) && l == r)
    case (OEq, Num(l), INum(r)) => Bool(!(l equals -0.0) && l == r)
    case (OEq, Num(l), Num(r)) => Bool(l equals r)
    case (OEq, l, r) => Bool(l == r)

    // double equality operations
    case (OEqual, INum(l), Num(r)) => Bool(l == r)
    case (OEqual, Num(l), INum(r)) => Bool(l == r)
    case (OEqual, Num(l), Num(r)) => Bool(l == r)
    case (OEqual, l, r) => Bool(l == r)

    case (_, lval, rval) => error(s"wrong type: ${lval.beautified} ${bop.beautified} ${rval.beautified}")
  }

  // short circuit evaluation
  def shortCircuit(bop: BOp, left: Expr, right: Expr): Value = {
    val l = interp(left).escaped(st)
    (bop, l) match {
      case (OAnd, Bool(false)) => Bool(false)
      case (OOr, Bool(true)) => Bool(true)
      case _ => {
        val r = interp(right).escaped(st)
        interp(bop, l, r)
      }
    }
  }
}
