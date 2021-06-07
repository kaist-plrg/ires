const Node = require('../Node');

let ConditionalExpression = require('./ConditionalExpression');

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
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'BinaryExpression':
    case 'ObjectExpression':
    case 'Identifier':
    case 'Literal': {
      let cond = ConditionalExpression(In, Yield, Await)(given);
      return new Node('AssignmentExpression', given, 0, [cond], params);
    }
    case 'ObjectExpression':
      console.log(given);
    default:
      Node.TODO(`${type} @ AssignmentExpression`);
  }
}

module.exports = AssignmentExpression;
