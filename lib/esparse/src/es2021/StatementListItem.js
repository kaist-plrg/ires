const Node = require('../Node');

const Statement = require('./Statement');
const Declaration = require('./Declaration');

// StatementListItem[Yield, Await, Return] :
//    Statement[?Yield, ?Await, ?Return]
//    Declaration[?Yield, ?Await]
let StatementListItem = (Yield, Await, Return) => (given) => {
  let { type, kind } = given
  if (type.endsWith('Statement') || (
    type === 'VariableDeclaration' &&
    kind === 'var'
  )) {
    let stmt = Statement(Yield, Await, Return)(given);
    let params = [Yield, Await, Return];
    return new Node('StatementListItem', given, 0, [stmt], params);
  } else {
    let decl = Declaration(Yield, Await)(given);
    let params = [Yield, Await];
    return new Node('StatementListItem', given, 1, [decl], params);
  }
}

module.exports = StatementListItem;
