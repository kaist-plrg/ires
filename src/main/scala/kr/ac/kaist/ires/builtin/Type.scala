package kr.ac.kaist.ires.builtin

import kr.ac.kaist.ires.model._
import kr.ac.kaist.ires.ir._

object Type {
  val ArgumentsExoticObject = Map[Value, Value](
    Str("Set") -> Func(`AL::ArgumentsExoticObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::ArgumentsExoticObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Delete") -> Func(`AL::ArgumentsExoticObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::ArgumentsExoticObject.GetOwnProperty`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::ArgumentsExoticObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::OrdinaryObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val ArrayExoticObject = Map[Value, Value](
    Str("Set") -> Func(`AL::OrdinaryObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::OrdinaryObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::OrdinaryObject.GetOwnProperty`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::ArrayExoticObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::OrdinaryObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val BoundFunctionExoticObject = Map[Value, Value](
    Str("Set") -> Func(`AL::OrdinaryObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::OrdinaryObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Construct") -> Func(`AL::BoundFunctionExoticObject.Construct`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::OrdinaryObject.GetOwnProperty`),
    Str("Call") -> Func(`AL::BoundFunctionExoticObject.Call`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::OrdinaryObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::OrdinaryObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val BuiltinFunctionObject = Map[Value, Value](
    Str("Set") -> Func(`AL::OrdinaryObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::OrdinaryObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Construct") -> Func(`AL::BuiltinFunctionObject.Construct`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::OrdinaryObject.GetOwnProperty`),
    Str("Call") -> Func(`AL::BuiltinFunctionObject.Call`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::OrdinaryObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::OrdinaryObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val DeclarativeEnvironmentRecord = Map[Value, Value](
    Str("WithBaseObject") -> Func(`AL::DeclarativeEnvironmentRecord.WithBaseObject`),
    Str("HasSuperBinding") -> Func(`AL::DeclarativeEnvironmentRecord.HasSuperBinding`),
    Str("DeleteBinding") -> Func(`AL::DeclarativeEnvironmentRecord.DeleteBinding`),
    Str("InitializeBinding") -> Func(`AL::DeclarativeEnvironmentRecord.InitializeBinding`),
    Str("GetBindingValue") -> Func(`AL::DeclarativeEnvironmentRecord.GetBindingValue`),
    Str("CreateMutableBinding") -> Func(`AL::DeclarativeEnvironmentRecord.CreateMutableBinding`),
    Str("CreateImmutableBinding") -> Func(`AL::DeclarativeEnvironmentRecord.CreateImmutableBinding`),
    Str("SetMutableBinding") -> Func(`AL::DeclarativeEnvironmentRecord.SetMutableBinding`),
    Str("HasThisBinding") -> Func(`AL::DeclarativeEnvironmentRecord.HasThisBinding`),
    Str("HasBinding") -> Func(`AL::DeclarativeEnvironmentRecord.HasBinding`),
  )

  val ECMAScriptFunctionObject = Map[Value, Value](
    Str("Set") -> Func(`AL::OrdinaryObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::OrdinaryObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Construct") -> Func(`AL::ECMAScriptFunctionObject.Construct`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::OrdinaryObject.GetOwnProperty`),
    Str("Call") -> Func(`AL::ECMAScriptFunctionObject.Call`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::OrdinaryObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::OrdinaryObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val FunctionEnvironmentRecord = Map[Value, Value](
    Str("WithBaseObject") -> Func(`AL::DeclarativeEnvironmentRecord.WithBaseObject`),
    Str("HasSuperBinding") -> Func(`AL::FunctionEnvironmentRecord.HasSuperBinding`),
    Str("DeleteBinding") -> Func(`AL::DeclarativeEnvironmentRecord.DeleteBinding`),
    Str("InitializeBinding") -> Func(`AL::DeclarativeEnvironmentRecord.InitializeBinding`),
    Str("GetBindingValue") -> Func(`AL::DeclarativeEnvironmentRecord.GetBindingValue`),
    Str("CreateMutableBinding") -> Func(`AL::DeclarativeEnvironmentRecord.CreateMutableBinding`),
    Str("BindThisValue") -> Func(`AL::FunctionEnvironmentRecord.BindThisValue`),
    Str("GetThisBinding") -> Func(`AL::FunctionEnvironmentRecord.GetThisBinding`),
    Str("GetSuperBase") -> Func(`AL::FunctionEnvironmentRecord.GetSuperBase`),
    Str("CreateImmutableBinding") -> Func(`AL::DeclarativeEnvironmentRecord.CreateImmutableBinding`),
    Str("SetMutableBinding") -> Func(`AL::DeclarativeEnvironmentRecord.SetMutableBinding`),
    Str("HasThisBinding") -> Func(`AL::FunctionEnvironmentRecord.HasThisBinding`),
    Str("HasBinding") -> Func(`AL::DeclarativeEnvironmentRecord.HasBinding`),
  )

  val GlobalEnvironmentRecord = Map[Value, Value](
    Str("CreateGlobalVarBinding") -> Func(`AL::GlobalEnvironmentRecord.CreateGlobalVarBinding`),
    Str("WithBaseObject") -> Func(`AL::GlobalEnvironmentRecord.WithBaseObject`),
    Str("HasRestrictedGlobalProperty") -> Func(`AL::GlobalEnvironmentRecord.HasRestrictedGlobalProperty`),
    Str("HasSuperBinding") -> Func(`AL::GlobalEnvironmentRecord.HasSuperBinding`),
    Str("HasLexicalDeclaration") -> Func(`AL::GlobalEnvironmentRecord.HasLexicalDeclaration`),
    Str("CanDeclareGlobalVar") -> Func(`AL::GlobalEnvironmentRecord.CanDeclareGlobalVar`),
    Str("DeleteBinding") -> Func(`AL::GlobalEnvironmentRecord.DeleteBinding`),
    Str("InitializeBinding") -> Func(`AL::GlobalEnvironmentRecord.InitializeBinding`),
    Str("GetBindingValue") -> Func(`AL::GlobalEnvironmentRecord.GetBindingValue`),
    Str("GetThisBinding") -> Func(`AL::GlobalEnvironmentRecord.GetThisBinding`),
    Str("CanDeclareGlobalFunction") -> Func(`AL::GlobalEnvironmentRecord.CanDeclareGlobalFunction`),
    Str("HasVarDeclaration") -> Func(`AL::GlobalEnvironmentRecord.HasVarDeclaration`),
    Str("CreateImmutableBinding") -> Func(`AL::GlobalEnvironmentRecord.CreateImmutableBinding`),
    Str("CreateGlobalFunctionBinding") -> Func(`AL::GlobalEnvironmentRecord.CreateGlobalFunctionBinding`),
    Str("CreateMutableBinding") -> Func(`AL::GlobalEnvironmentRecord.CreateMutableBinding`),
    Str("SetMutableBinding") -> Func(`AL::GlobalEnvironmentRecord.SetMutableBinding`),
    Str("HasThisBinding") -> Func(`AL::GlobalEnvironmentRecord.HasThisBinding`),
    Str("HasBinding") -> Func(`AL::GlobalEnvironmentRecord.HasBinding`),
  )

  val ImmutablePrototypeExoticObject = Map[Value, Value](
    Str("Set") -> Func(`AL::OrdinaryObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::ImmutablePrototypeExoticObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::OrdinaryObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::OrdinaryObject.GetOwnProperty`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::OrdinaryObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::OrdinaryObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val IntegerIndexedExoticObject = Map[Value, Value](
    Str("Set") -> Func(`AL::IntegerIndexedExoticObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::IntegerIndexedExoticObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::IntegerIndexedExoticObject.GetOwnProperty`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::IntegerIndexedExoticObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::IntegerIndexedExoticObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::IntegerIndexedExoticObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val ObjectEnvironmentRecord = Map[Value, Value](
    Str("WithBaseObject") -> Func(`AL::ObjectEnvironmentRecord.WithBaseObject`),
    Str("HasSuperBinding") -> Func(`AL::ObjectEnvironmentRecord.HasSuperBinding`),
    Str("DeleteBinding") -> Func(`AL::ObjectEnvironmentRecord.DeleteBinding`),
    Str("InitializeBinding") -> Func(`AL::ObjectEnvironmentRecord.InitializeBinding`),
    Str("GetBindingValue") -> Func(`AL::ObjectEnvironmentRecord.GetBindingValue`),
    Str("CreateMutableBinding") -> Func(`AL::ObjectEnvironmentRecord.CreateMutableBinding`),
    Str("SetMutableBinding") -> Func(`AL::ObjectEnvironmentRecord.SetMutableBinding`),
    Str("HasThisBinding") -> Func(`AL::ObjectEnvironmentRecord.HasThisBinding`),
    Str("HasBinding") -> Func(`AL::ObjectEnvironmentRecord.HasBinding`),
  )

  val OrdinaryObject = Map[Value, Value](
    Str("Set") -> Func(`AL::OrdinaryObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::OrdinaryObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::OrdinaryObject.GetOwnProperty`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::OrdinaryObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::OrdinaryObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )

  val ProxyObject = Map[Value, Value](
    Str("Set") -> Func(`AL::ProxyObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::ProxyObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::ProxyObject.Get`),
    Str("PreventExtensions") -> Func(`AL::ProxyObject.PreventExtensions`),
    Str("Construct") -> Func(`AL::ProxyObject.Construct`),
    Str("Delete") -> Func(`AL::ProxyObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::ProxyObject.GetOwnProperty`),
    Str("Call") -> Func(`AL::ProxyObject.Call`),
    Str("IsExtensible") -> Func(`AL::ProxyObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::ProxyObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::ProxyObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::ProxyObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::ProxyObject.GetPrototypeOf`),
  )

  val StringExoticObject = Map[Value, Value](
    Str("Set") -> Func(`AL::OrdinaryObject.Set`),
    Str("SetPrototypeOf") -> Func(`AL::OrdinaryObject.SetPrototypeOf`),
    Str("Get") -> Func(`AL::OrdinaryObject.Get`),
    Str("PreventExtensions") -> Func(`AL::OrdinaryObject.PreventExtensions`),
    Str("Delete") -> Func(`AL::OrdinaryObject.Delete`),
    Str("GetOwnProperty") -> Func(`AL::StringExoticObject.GetOwnProperty`),
    Str("IsExtensible") -> Func(`AL::OrdinaryObject.IsExtensible`),
    Str("HasProperty") -> Func(`AL::OrdinaryObject.HasProperty`),
    Str("DefineOwnProperty") -> Func(`AL::StringExoticObject.DefineOwnProperty`),
    Str("OwnPropertyKeys") -> Func(`AL::StringExoticObject.OwnPropertyKeys`),
    Str("GetPrototypeOf") -> Func(`AL::OrdinaryObject.GetPrototypeOf`),
  )
}
