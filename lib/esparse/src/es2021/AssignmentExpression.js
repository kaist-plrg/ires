const Node = require('../Node');

// AssignmentExpression[In, Yield, Await] :
//    ConditionalExpression[?In, ?Yield, ?Await]
//    [+Yield]YieldExpression[?In, ?Await]
//    ArrowFunction[?In, ?Yield, ?Await]
//    AsyncArrowFunction[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] = AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] AssignmentOperator AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] &&= AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] ||= AssignmentExpression[?In, ?Yield, ?Await]
//    LeftHandSideExpression[?Yield, ?Await] ??= AssignmentExpression[?In, ?Yield, ?Await]
let AssignmentExpression = (In, Yield, Await) => (given) => {
  let ConditionalExpression = require('./ConditionalExpression');

  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'BinaryExpression':
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let cond = ConditionalExpression(In, Yield, Await)(given);
      return new Node('AssignmentExpression', given, 0, [cond], params);
    }
    default:
      Node.TODO(`${type} @ AssignmentExpression`);
  }
}

module.exports = AssignmentExpression;
