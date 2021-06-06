const assert = require('assert');
const acorn = require('acorn');
const fs = require('fs');

class Node {
  constructor(kind, index, ...children) {
    this.kind = kind;
    this.index = index;
    this.children = children;
  }
}

// Script : ScriptBody?
function Script(given) {
  assert(given.sourceType === 'script');

  let body = null;
  if (given.body.length > 0) body = ScriptBody(given);
  return new Node('Script', 0, body);
}

// ScriptBody : StatementList
function ScriptBody(given) {
  return new Node('ScriptBody', 0, StatementList(given));
}

// Block : `{` StatementList? `}`
function Block(given) {
  let list = null;
  if (given.body.length > 0) list = StatementList(given);
  return new Node('Block', 0, list);
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
  let { type, kind } = given
  if (type.endsWith('Statement')) {
    return new Node('StatementListItem', 0, Statement(given));
  } else if (
    type === 'VariableDeclaration' &&
    kind === 'var'
  ) {
    return new Node('StatementListItem', 0, Statement(given));
  } else {
    return new Node('StatementListItem', 1, Declaration(given));
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
  if (given.type === 'VariableDeclaration') {
    return new Node('Statement', 1, VariableStatement(given));
  }
  let { index, child } = getRhs(stmtRhsList, given);
  return new Node('Statement', index, child);
}

// BlockStatement : Block
function BlockStatement(given) {
  return new Node('BlockStatement', 0, Block(given));
}

// VariableStatement : `var` VariableDeclarationList `;`
function VariableStatement(given) {
  return new Node('VariableStatement', 0, VariableDeclarationList(given));
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
    return new Node('VariableDeclaration', 0, bindingId, init);
  } else {
    // TODO
    return new Node('VariableDeclaration', 1);
  }
}

// BindingIdentifier :
//    Identifier
//    `yield`
//    `await`
function BindingIdentifier(given) {
  switch (given.name) {
    case 'yield':
      return new Node('BindingIdentifier', 1);
    case 'await':
      return new Node('BindingIdentifier', 2);
    default:
      return new Node('BindingIdentifier', 0, Identifier(given));
  }
}

// Identifier : IdentifierName
function Identifier(given) {
  return new Node('Identifier', 0, given.name);
}

function Initializer() {} // TODO

// EmptyStatement : `;`
function EmptyStatement() {
  return new Node('EmptyStatement', 0);
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
      return new Node('Declaration', 0, HoistableDeclaration(given));
    case 'ClassDeclaration':
      return new Node('Declaration', 1, ClassDeclaration(given));
    case 'VariableDeclaration':
      if (given.kind === 'var')
        return new Node('Statement', 1, VariableStatement(given));
      else
        return new Node('Declaration', 2, LexicalDeclaration(given));
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
      list = new Node(name, 0, child);
    } else {
      list = new Node(name, 1, list, child);
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
