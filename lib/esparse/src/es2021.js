const assert = require('assert');
const acorn = require('acorn');
const fs = require('fs');

class Node {
  constructor(type, index, from, ...children) {
    this.type = type;
    this.index = index;
    this.start = from.start;
    this.end = from.end;
    if (children.length > 0) this.children = children;
  }
}

// Script : ScriptBody?
function Script(given) {
  assert(given.sourceType === 'script');

  let body = null;
  if (given.body.length > 0) body = ScriptBody(given);
  return new Node('Script', 0, given, body);
}

// ScriptBody : StatementList
function ScriptBody(given) {
  return new Node('ScriptBody', 0, given, StatementList(given));
}

// Block : `{` StatementList? `}`
function Block(given) {
  let list = null;
  if (given.body.length > 0) list = StatementList(given);
  return new Node('Block', 0, given, list);
}

// StatementList :
//    StatementListItem
//    StatementList StatementListItem
function StatementList(given) {
  return getList(given.body, 'StatementList', StatementListItem);
}

// StatementListItem :
//    Statement
//    Declaration
function StatementListItem(given) {
  if (given.type.endsWith('Statement')) {
    return new Node('StatementListItem', 0, given, Statement(given));
  } else {
    return new Node('StatementListItem', 1, given, Declaration(given));
  }
}

// Statement :
//    BlockStatement
//    VariableStatement
//    EmptyStatement
//    ExpressionStatement
//    IfStatement
//    BreakableStatement
//    ContinueStatement
//    BreakStatement
//    ReturnStatement
//    WithStatement
//    LabelledStatement
//    ThrowStatement
//    TryStatement
//    DebuggerStatement
let stmtRhsList = [
  BlockStatement, VariableStatement, EmptyStatement, ExpressionStatement,
  IfStatement, BreakableStatement, ContinueStatement, BreakStatement,
  ReturnStatement, WithStatement, LabelledStatement, ThrowStatement,
  TryStatement, DebuggerStatement
];
function Statement(given) {
  let { index, child } = getRhs(stmtRhsList, given);
  return new Node('Statement', index, given, child);
}

// BlockStatement : Block
function BlockStatement(given) {
  return new Node('BlockStatement', 0, given, Block(given));
}

// VariableStatement : `var` VariableDeclarationList `;`
function VariableStatement(given) {
  return new Node('VariableStatement', 0, given, VariableDeclarationList(given));
}

// VariableDeclarationList :
//    VariableDeclaration
//    VariableDeclarationList `,` VariableDeclaration
function VariableDeclarationList(given) {
  return getList(given.declarations, 'VariableDeclarationList', VariableDeclaration);
}

// VariableDeclaration :
//    BindingIdentifier Initializer?
//    BindingPattern Initializer
function VariableDeclaration(given) {
  if (given.id.type === 'Identifier') {
    let bindingId = BindingIdentifier(given.id);
    let init = null;
    if (given.init) init = Initializer(given.init);
    return new Node('VariableDeclaration', 0, given, bindingId, init);
  } else {
    // TODO
    return new Node('VariableDeclaration', 1, given);
  }
}

// BindingIdentifier :
//    Identifier
//    `yield`
//    `await`
function BindingIdentifier(given) {
  switch (given.name) {
    case 'yield':
      return new Node('BindingIdentifier', 1, given);
    case 'await':
      return new Node('BindingIdentifier', 2, given);
    default:
      return new Node('BindingIdentifier', 0, given, Identifier(given));
  }
}

// Identifier : IdentifierName
function Identifier(given) {
  return new Node('Identifier', 0, given, given.name);
}

function Initializer() {} // TODO

// EmptyStatement : `;`
function EmptyStatement(given) {
  return new Node('EmptyStatement', 0, given);
}
function ExpressionStatement() {} // TODO
function IfStatement() {} // TODO
function BreakableStatement() {} // TODO
function ContinueStatement() {} // TODO
function BreakStatement() {} // TODO
function ReturnStatement() {} // TODO
function WithStatement() {} // TODO
function LabelledStatement() {} // TODO
function ThrowStatement() {} // TODO
function TryStatement() {} // TODO
function DebuggerStatement() {} // TODO

// Declaration :
//    HoistableDeclaration
//    ClassDeclaration
//    LexicalDeclaration
function Declaration(given) {
  switch (given.type) {
    case 'FunctionDeclaration':
      return new Node('Declaration', 0, given, HoistableDeclaration(given));
    case 'ClassDeclaration':
      return new Node('Declaration', 1, given, ClassDeclaration(given));
    case 'VariableDeclaration':
      if (given.kind === 'var')
        return new Node('Statement', 1, given, VariableStatement(given));
      else
        return new Node('Declaration', 2, given, LexicalDeclaration(given));
  }
}

function HoistableDeclaration() {} // TODO
function ClassDeclaration() {} // TODO
function LexicalDeclaration() {} // TODO

////////////////////////////////////////////////////////////////////////////////
// Helpers
////////////////////////////////////////////////////////////////////////////////
function getRhs(rhsList, given) {
  let index = rhsList.map(f => f.name).indexOf(given.type);
  let child = rhsList[index](given);
  return { index, child };
}

function getList(elems, name, childGen) {
  let list;
  for (let elem of elems) {
    let child = childGen(elem);
    if (list === undefined) {
      list = new Node(name, 0, elem, child);
    } else {
      list = new Node(name, 1, list, list, child);
      list.end = child.end;
    }
  }
  return list;
}

////////////////////////////////////////////////////////////////////////////////
// Exported Functions
////////////////////////////////////////////////////////////////////////////////
function acornParse(code) {
  try {
    return acorn.parse(code, { ecmaVersion: 2021 });
  } catch (e) {
    console.error(`[SyntaxError] ${e}`);
    process.exit(1);
  }
}

function trans(acornAst) {
  return Script(acornAst);
}

function parse(code) {
  const acornAst = acornParse(code);
  return trans(acornAst);
}

function parseFile(filename) {
  const code = fs.readFileSync(filename, 'utf8');
  return parse(code);
}

module.exports = {
  trans: trans,
  acornParse: acornParse,
  parse: parse,
  parseFile: parseFile
}
