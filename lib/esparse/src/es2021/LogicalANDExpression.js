const Node = require('../Node');

// LogicalANDExpression[In, Yield, Await] :
//    BitwiseORExpression[?In, ?Yield, ?Await]
//    LogicalANDExpression[?In, ?Yield, ?Await] && BitwiseORExpression[?In, ?Yield, ?Await]
let LogicalANDExpression = (In, Yield, Await) => (given) => {
  let BitwiseORExpression = require('./BitwiseORExpression');

  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'BinaryExpression':
    case 'MemberExpression':
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = BitwiseORExpression(In, Yield, Await)(given);
      return new Node('LogicalANDExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ LogicalANDExpression`);
  }
  Node.TODO('LogicalANDExpression');
}

module.exports = LogicalANDExpression;
