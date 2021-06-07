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
  In, Yield, Await, given;
  Node.TODO('AssignmentExpression');
}

module.exports = AssignmentExpression;
