const Node = require('../Node');

// LogicalORExpression[In, Yield, Await] :
//    LogicalANDExpression[?In, ?Yield, ?Await]
//    LogicalORExpression[?In, ?Yield, ?Await] || LogicalANDExpression[?In, ?Yield, ?Await]
let LogicalORExpression = (In, Yield, Await) => (given) => {
  let LogicalANDExpression = require('./LogicalANDExpression');

  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'BinaryExpression':
    case 'CallExpression':
    case 'MemberExpression':
    case 'ObjectExpression':
    case 'ArrayExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = LogicalANDExpression(In, Yield, Await)(given);
      return new Node('LogicalORExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ LogicalORExpression`);
  }
  Node.TODO('LogicalORExpression');
}

module.exports = LogicalORExpression;
