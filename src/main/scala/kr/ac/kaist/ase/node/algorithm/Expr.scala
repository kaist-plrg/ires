package kr.ac.kaist.ase.node.algorithm

sealed trait Expr
case class Expr0(e0: A0Expr) extends Expr // ? $A1Expr
case class Expr1(e0: A0Expr) extends Expr // $A1Expr

sealed trait A0Expr
case class A0Expr0(e0: A1Expr, e1: List[Argument]) extends A0Expr // $AExpr ( $Arguments )
case class A0Expr1(e0: A1Expr) extends A0Expr // opt("the" | "a") $AExpr

sealed trait A1Expr
case class A1Expr0(e0: Value) extends A1Expr // opt("value") $Value
case class A1Expr1(e0: Value) extends A1Expr // $Code
case class A1Expr2(e0: Value) extends A1Expr // $Const
case class A1Expr3(e0: RecordV) extends A1Expr // $RecordV
case class A1Expr4(e0: EnvironmentV) extends A1Expr // $EnvironmentV
case class A1Expr5(e0: ExecContextV) extends A1Expr // $ExecContextV
case class A1Expr6(e0: ListV) extends A1Expr // $ListV
case class A1Expr7(e0: ObjectV) extends A1Expr // $ObjectV
case class A1Expr8(e0: SymbolV) extends A1Expr // $SymbolV
case class A1Expr9() extends A1Expr // the String value of the property name
case class A1Expr10(e0: Id, e1: AMERName) extends A1Expr // $Id . $AMERName
case class A1Expr11(e0: Id, e1: Id) extends A1Expr // value currently bound to $Id in $Id
case class A1Expr12(e0: Binding) extends A1Expr // $Binding
case class A1Expr13(e0: A0Settable) extends A1Expr // $Settable
case class A1Expr14(e0: TypeV, e1: List[InitExpr]) extends A1Expr // value of type $Type $InitializeType
case class A1Expr15(e0: AMERName) extends A1Expr // $AMERName

sealed trait Argument
case class Argument0(e0: Expr) extends Argument
case class Argument1(e0: Flag) extends Argument

case class Flag(e0: Field, e1: Expr)

sealed trait RecordV

case class RecordV0() extends RecordV // declarative Environment Record for which the method was invoked
case class RecordV1() extends RecordV // object Environment Record for which the method was invoked
case class RecordV2() extends RecordV // global Environment Record for which the method was invoked
case class RecordV3() extends RecordV // function Environment Record for which the method was invoked
case class RecordV4() extends RecordV // module Environment Record for which the method was invoked
case class RecordV5() extends RecordV // Agent Record of the surrounding agent
case class RecordV6() extends RecordV // new Record
case class RecordV7() extends RecordV // new global Environment Record
case class RecordV8() extends RecordV // new declarative Environment Record containing no bindings
case class RecordV9() extends RecordV // new module Environment Record containing no bindings
case class RecordV10(e0: Id) extends RecordV // new object Environment Record containing $Id as the binding object
case class RecordV11() extends RecordV // new function Environment Record containing no bindings
case class RecordV12() extends RecordV // new Realm Record
case class RecordV13(e0: RecordDescriptor, e1: List[Flag]) extends RecordV // $RecordDescriptor { $FlagArgs }

case class RecordDescriptor(s: String)

sealed trait EnvironmentV
case class EnvironmentV0() extends EnvironmentV // new Lexical Environment
case class EnvironmentV1(e0: ExecContextV) extends EnvironmentV // $ExecContextV ' s LexicalEnvironment

sealed trait ExecContextV
case class ExecContextV0() extends ExecContextV // new execution context
case class ExecContextV1() extends ExecContextV // running execution context

sealed trait ListV
case class ListV0() extends ListV // new empty List, « »
case class ListV1(e0: List[Argument]) extends ListV // « $Arguments »

sealed trait ObjectV
case class ObjectV0(e0: Id) extends ObjectV // binding object for $Id

sealed trait SymbolV
case class SymbolV0(e0: SymbolName) extends SymbolV // @ @ $SymbolName

case class SymbolName(s: String)

case class AMERName(s: String)

trait Binding
case class Binding0(e0: Id, e1: Id) extends Binding // the binding for $Id in $Id
case class Binding1(e0: Id) extends Binding // the binding for $Id

sealed trait Settable
case class Settable0(e0: A0Settable) extends Settable // opt(the) $Settable1

sealed trait A0Settable
case class A0Settable0(e0: Id, e1: Field) extends A0Settable // $Id. [ [ $Field ] ]
case class A0Settable1(e0: Id, e1: Id) extends A0Settable // $Id flag of $Id
case class A0Settable2(e0: Id, e1: Field) extends A0Settable // $Id ' s $Field opt(component)
case class A0Settable3(e0: Id) extends A0Settable
case class A0Settable4(e0: Id, e1: Field) extends A0Settable // $Field of $Id
case class A0Settable5(e0: Id) extends A0Settable // outer lexical environment reference of $Id
case class A0Settable6(e0: Id) extends A0Settable // value of $Id ' s outer environment reference
case class A0Settable7(e0: Id, e1: Id) extends A0Settable // bound value for $Id in $Id

case class TypeV(s: String)

sealed trait InitExpr
case class InitExpr0(e0: ComponentName, e1: Expr) extends InitExpr
case class InitExpr1(e0: FlagName, e1: Expr) extends InitExpr
case class ComponentName(s: String)
case class FlagName(s: String)
case class Field(s: String)

trait ExprParsers { this: AlgorithmParsers =>
  lazy val expr: Parser[Expr] = expr0 | expr1
  def expr0: Parser[Expr0] =
    "?" ~ a0expr ^^ { case _ ~ e0 => Expr0(e0) }
  def expr1: Parser[Expr1] =
    a0expr ^^ { case e0 => Expr1(e0) }
  def a0expr0: Parser[A0Expr0] =
    a1expr ~ "(" ~ argumentList ~ ")" ^^ {
      case e0 ~ _ ~ alist ~ _ => A0Expr0(e0, alist)
    }
  def a0expr1: Parser[A0Expr1] =
    opt("the" | "a") ~ a1expr ^^ {
      case _ ~ e0 => A0Expr1(e0)
    }
  lazy val a0expr: Parser[A0Expr] = a0expr0 | a0expr1

  def a1expr0: Parser[A1Expr0] =
    opt("value") ~ value ^^ {
      case _ ~ e0 => A1Expr0(e0)
    }
  def a1expr3: Parser[A1Expr3] =
    recordv ^^ {
      case e0 => A1Expr3(e0)
    }
  def a1expr4: Parser[A1Expr4] =
    environmentv ^^ {
      case e0 => A1Expr4(e0)
    }
  def a1expr5: Parser[A1Expr5] =
    execcontextv ^^ {
      case e0 => A1Expr5(e0)
    }
  def a1expr6: Parser[A1Expr6] =
    listv ^^ {
      case e0 => A1Expr6(e0)
    }
  def a1expr7: Parser[A1Expr7] =
    objectv ^^ {
      case e0 => A1Expr7(e0)
    }
  def a1expr8: Parser[A1Expr8] =
    symbolv ^^ {
      case e0 => A1Expr8(e0)
    }
  def a1expr9: Parser[A1Expr9] =
    "the" ~ "String" ~ "value" ~ "of" ~ "the" ~ "property" ~ "name" ^^ {
      case _ => A1Expr9()
    }
  def a1expr10: Parser[A1Expr10] =
    id ~ "." ~ amername ^^ {
      case e0 ~ _ ~ e1 => A1Expr10(e0, e1)
    }
  def a1expr11: Parser[A1Expr11] =
    "value" ~ "currently" ~ "bound" ~ "to" ~ id ~ "in" ~ id ^^ {
      case _ ~ _ ~ _ ~ _ ~ e0 ~ _ ~ e1 => A1Expr11(e0, e1)
    }
  def a1expr12: Parser[A1Expr12] =
    binding ^^ {
      case e0 => A1Expr12(e0)
    }
  def a1expr13: Parser[A1Expr13] =
    a0settable ^^ {
      case e0 => A1Expr13(e0)
    }
  def a1expr14: Parser[A1Expr14] =
    "value" ~ "of" ~ "type" ~ typev ~ initializeList ^^ {
      case _ ~ _ ~ _ ~ e0 ~ e1 => A1Expr14(e0, e1)
    }
  def a1expr15: Parser[A1Expr15] =
    amername ^^ {
      case e0 => A1Expr15(e0)
    }
  lazy val a1expr: Parser[A1Expr] = a1expr0 |
    a1expr3 | a1expr4 |
    a1expr5 | a1expr6 | a1expr7 | a1expr8 |
    a1expr9 | a1expr10 | a1expr11 | a1expr12 |
    a1expr13 | a1expr14 | a1expr15

  def recordv0: Parser[RecordV0] =
    "declarative" ~ "Environment" ~ "Record" ~ "for" ~ "which" ~ "the" ~ "method" ~ "was" ~ "invoked" ^^ {
      case _ => RecordV0()
    }
  def recordv1: Parser[RecordV1] =
    "object" ~ "Environment" ~ "Record" ~ "for" ~ "which" ~ "the" ~ "method" ~ "was" ~ "invoked" ^^ {
      case _ => RecordV1()
    }
  def recordv2: Parser[RecordV2] =
    "global" ~ "Environment" ~ "Record" ~ "for" ~ "which" ~ "the" ~ "method" ~ "was" ~ "invoked" ^^ {
      case _ => RecordV2()
    }
  def recordv3: Parser[RecordV3] =
    "function" ~ "Environment" ~ "Record" ~ "for" ~ "which" ~ "the" ~ "method" ~ "was" ~ "invoked" ^^ {
      case _ => RecordV3()
    }
  def recordv4: Parser[RecordV4] =
    "module" ~ "Environment" ~ "Record" ~ "for" ~ "which" ~ "the" ~ "method" ~ "was" ~ "invoked" ^^ {
      case _ => RecordV4()
    }
  def recordv5: Parser[RecordV5] =
    "Agent" ~ "Record" ~ "of" ~ "the" ~ "surrounding" ~ "agent" ^^ {
      case _ => RecordV5()
    }
  def recordv6: Parser[RecordV6] =
    "new" ~ "Record" ^^ {
      case _ => RecordV6()
    }
  def recordv7: Parser[RecordV7] =
    "new" ~ "global" ~ "Environment" ~ "Record" ^^ {
      case _ => RecordV7()
    }
  def recordv8: Parser[RecordV8] =
    "new" ~ "declarative" ~ "Environment" ~ "Record" ~ "containing" ~ "no" ~ "bindings" ^^ {
      case _ => RecordV8()
    }
  def recordv9: Parser[RecordV9] =
    "new" ~ "module" ~ "Environment" ~ "Record" ~ "containing" ~ "no" ~ "bindings" ^^ {
      case _ => RecordV9()
    }
  def recordv10: Parser[RecordV10] =
    "new" ~ "object" ~ "Environment" ~ "Record" ~ "containing" ~ id ~ "as" ~ "the" ~ "binding" ~ "object" ^^ {
      case _ ~ _ ~ _ ~ _ ~ _ ~ e0 ~ _ ~ _ ~ _ ~ _ => RecordV10(e0)
    }
  def recordv11: Parser[RecordV11] =
    "new" ~ "function" ~ "Environment" ~ "Record" ~ "containing" ~ "no" ~ "bindings" ^^ {
      case _ => RecordV11()
    }
  def recordv12: Parser[RecordV12] =
    "new" ~ "Realm" ~ "Record" ^^ {
      case _ => RecordV12()
    }
  def recordv13: Parser[RecordV13] =
    recorddescriptor ~ "{" ~ flagList ~ "}" ^^ {
      case e0 ~ _ ~ e1 ~ _ => RecordV13(e0, e1)
    }
  lazy val recordv: Parser[RecordV] = recordv0 | recordv1 | recordv2 |
    recordv3 | recordv4 | recordv5 | recordv6 | recordv7 | recordv8 |
    recordv9 | recordv10 | recordv11 | recordv12 | recordv13
  def environmentv0: Parser[EnvironmentV0] =
    "new" ~ "Lexical" ~ "Environment" ^^ {
      case _ => EnvironmentV0()
    }
  def environmentv1: Parser[EnvironmentV1] =
    execcontextv ~ "'" ~ "s" ~ "LexicalEnvironment" ^^ {
      case e0 ~ _ ~ _ ~ _ => EnvironmentV1(e0)
    }
  lazy val environmentv: Parser[EnvironmentV] = environmentv0 | environmentv1
  def execcontextv0: Parser[ExecContextV0] =
    "new" ~ "execution" ~ "context" ^^ {
      case _ => ExecContextV0()
    }
  def execcontextv1: Parser[ExecContextV1] =
    "running" ~ "execution" ~ "context" ^^ {
      case _ => ExecContextV1()
    }
  lazy val execcontextv: Parser[ExecContextV] = execcontextv0 | execcontextv1
  def listv0: Parser[ListV0] =
    (("new" ~ "empty" ~ "List") | ("«" ~ "»")) ^^ {
      case _ => ListV0()
    }
  def listv1: Parser[ListV1] =
    "«" ~ argumentList ~ "»" ^^ {
      case _ ~ alist ~ _ => ListV1(alist)
    }
  lazy val listv: Parser[ListV] = listv0 | listv1
  lazy val objectv: Parser[ObjectV] = {
    "binding" ~ "object" ~ "for" ~ id ^^ {
      case _ ~ _ ~ _ ~ e0 => ObjectV0(e0)
    }
  }
  lazy val symbolname: Parser[String] = "unscopables"
  lazy val symbolv: Parser[SymbolV] = {
    "@" ~ "@" ~ symbolname ^^ {
      case _ ~ _ ~ e0 => SymbolV0(SymbolName(e0))
    }
  }
  lazy val typev: Parser[TypeV] = {
    "a" ~ "Lexical" ~ "Environment" ^^ {
      case _ => TypeV("a Lexical Environment")
    } |
      "a" ~ "Module" ~ "Record" ^^ { case _ => TypeV("a Module Record") } |
      "an" ~ "ECMAScript" ~ "function" ^^ {
        case _ => TypeV("an ECMAScript function")
      } |
      "Object" ^^ { case _ => TypeV("Object") } |
      "Undefined" ^^ { case _ => TypeV("Undefined") } |
      "Reference" ^^ { case _ => TypeV("Reference") }
  }
  lazy val argumentList: Parser[List[Argument]] = {
    opt(argument ~ rep("," ~ argument)) ^^ {
      case Some(e0 ~ elist) =>
        e0 +: elist.map {
          case _ ~ e1 => e1
        }
      case None => List()
    }
  }
  lazy val flagList: Parser[List[Flag]] = {
    opt(flag ~ rep("," ~ flag)) ^^ {
      case Some(e0 ~ elist) =>
        e0 +: elist.map {
          case _ ~ e1 => e1
        }
      case None => List()
    }
  }
  def argument0: Parser[Argument0] = {
    expr ^^ { case e0 => Argument0(e0) }
  }
  def argument1: Parser[Argument1] = {
    flag ^^ { case e0 => Argument1(e0) }
  }
  lazy val argument: Parser[Argument] = argument0 | argument1
  lazy val flag: Parser[Flag] = {
    "[" ~ "[" ~ field ~ "]" ~ "]" ~ ":" ~ expr ^^ {
      case _ ~ _ ~ e0 ~ _ ~ _ ~ _ ~ e1 => Flag(e0, e1)
    }
  }
  lazy val field: Parser[Field] = {
    val fieldname: Parser[String] = ("CanBlock" |
      "Signifier" |
      "IdNames" |
      "EnvironmentRecord" |
      "FunctionObject" |
      "Function" |
      "Realm" |
      "ScriptOrModule" |
      "ThisBindingStatus" |
      "ThisValue" |
      "HomeObject" |
      "NewTarget" |
      "ObjectRecord" |
      "GlobalThisValue" |
      "DeclarativeRecord" |
      "IdNames" |
      "GlobalObject" |
      "GlobalEnv" |
      "TemplateMap" |
      "Intrinsics" |
      "Value" |
      "%" ~ "ObjectPrototype" ~ "%" ^^ { case _ => "% ObjectPrototype %" } |
      "%" ~ "ThrowTypeError" ~ "%" ^^ { case _ => "% ThrowTypeError %" } |
      "%" ~ "FunctionPrototype" ~ "%" ^^ { case _ => "% FunctionPrototype %" } |
      "SetPrototypeOf" |
      "GetPrototypeOf" |
      "Environment" |
      "GetOwnProperty" |
      "Configurable" |
      "ThisBindingStatus" |
      "ThisMode" |
      "Delete" |
      "Writable" |
      "Enumerable" |
      "Job" |
      "Arguments" |
      "HostDefined")
    fieldname ^^ { case s => Field(s) }
  }
  lazy val initializeList: Parser[List[InitExpr]] = {
    opt(initexpr ~ rep(("," | ("," ~ "and")) ~ initexpr)) ^^ {
      case Some(e0 ~ elist) =>
        e0 +: elist.map {
          case _ ~ e1 => e1
        }
      case None => List()
    }
  }
  def initexpr0: Parser[InitExpr0] = {
    "whose" ~ componentname ~ "component" ~ "is" ~ expr ^^ {
      case _ ~ e0 ~ _ ~ _ ~ e1 => InitExpr0(e0, e1)
    }
  }
  def initexpr1: Parser[InitExpr1] = {
    "whose" ~ flagname ~ "flag" ~ "is" ~ expr ^^ {
      case _ ~ e0 ~ _ ~ _ ~ e1 => InitExpr1(e0, e1)
    }
  }
  lazy val initexpr: Parser[InitExpr] = initexpr0 | initexpr1
  lazy val componentname: Parser[ComponentName] = {
    ("base" ~ "value" ^^ { case _ => "base value" } |
      "referenced" ~ "name" ^^ { case _ => "referenced name" }) ^^ {
        case s => ComponentName(s)
      }
  }
  lazy val flagname: Parser[FlagName] = {
    ("strict" ~ "reference" ^^ { case _ => "strict reference" }) ^^ {
      case s => FlagName(s)
    }
  }
  lazy val amername: Parser[AMERName] = {
    val amerstring = "GetIdentifierReference" |
      "TopLevelModuleEvaluationJob" |
      "ScriptEvaluationJob" |
      "HostReportErrors" |
      "EnqueueJob" |
      "GetThisEnvironment" |
      "CreateMutableBinding" |
      "InitializeBinding" |
      "HasThisBinding" |
      "GetThisBinding" |
      "AddRestrictedFunctionProperties" |
      "SetRealmGlobalObject" |
      "CreateIntrinsics" |
      "CreateRealm" |
      "ObjectCreate" |
      "NewGlobalEnvironment" |
      "GetBindingValue" |
      "IsExtensible" |
      "SetMutableBinding" |
      "HasBinding" |
      "DeleteBinding" |
      "DefinePropertyOrThrow" |
      "SetDefaultGlobalBindings" |
      "Get" |
      "Set" |
      "Type" |
      "InitializeHostDefinedRealm" |
      "HasOwnProperty" |
      "HasProperty" |
      "CreateImmutableBinding" |
      "CreateBuiltinFunction" |
      "NormalCompletion" |
      "ToBoolean"
    amerstring ^^ { case s => AMERName(s) }
  }
  lazy val recorddescriptor: Parser[RecordDescriptor] = {
    ("PropertyDescriptor" | "PendingJob") ^^ {
      case s => RecordDescriptor(s)
    }
  }
  lazy val binding: Parser[Binding] = {
    "the" ~ "binding" ~ "for" ~ id ~ opt("in" ~ id) ^^ {
      case _ ~ _ ~ _ ~ e0 ~ e1op =>
        e1op match {
          case Some(_ ~ e1) => Binding0(e0, e1)
          case None => Binding1(e0)
        }
    }
  }
  lazy val settable: Parser[Settable] = {
    opt("the") ~ a0settable ^^ { case _ ~ e0 => Settable0(e0) }
  }
  def a0settable0: Parser[A0Settable0] = {
    id ~ "." ~ "[" ~ "[" ~ field ~ "]" ~ "]" ^^ {
      case e0 ~ _ ~ _ ~ _ ~ e1 ~ _ ~ _ => A0Settable0(e0, e1)
    }
  }
  def a0settable1: Parser[A0Settable1] = {
    id ~ "flag" ~ "of" ~ id ^^ {
      case e0 ~ _ ~ _ ~ e1 => A0Settable1(e0, e1)
    }
  }
  def a0settable2: Parser[A0Settable2] = {
    id ~ "'" ~ "s" ~ field ~ opt("component") ^^ {
      case e0 ~ _ ~ _ ~ e1 ~ _ => A0Settable2(e0, e1)
    }
  }
  def a0settable3: Parser[A0Settable3] = {
    id ^^ { case e0 => A0Settable3(e0) }
  }
  def a0settable4: Parser[A0Settable4] = {
    field ~ "of" ~ id ^^ {
      case e1 ~ _ ~ e0 => A0Settable4(e0, e1)
    }
  }
  def a0settable5: Parser[A0Settable5] = {
    "outer" ~ "lexical" ~ "environment" ~ "reference" ~ "of" ~ id ^^ {
      case _ ~ _ ~ _ ~ _ ~ _ ~ e0 => A0Settable5(e0)
    }
  }
  def a0settable6: Parser[A0Settable6] = {
    "value" ~ "of" ~ id ~ "'" ~ "s" ~ "outer" ~ "environment" ~ "reference" ^^ {
      case _ ~ _ ~ e0 ~ _ ~ _ ~ _ ~ _ ~ _ => A0Settable6(e0)
    }
  }
  def a0settable7: Parser[A0Settable7] = {
    "bound" ~ "value" ~ "for" ~ id ~ "in" ~ id ^^ {
      case _ ~ _ ~ _ ~ e0 ~ _ ~ e1 => A0Settable7(e0, e1)
    }
  }
  lazy val a0settable: Parser[A0Settable] = a0settable0 | a0settable1 |
    a0settable2 | a0settable3 | a0settable4 | a0settable5 |
    a0settable6 | a0settable7
}