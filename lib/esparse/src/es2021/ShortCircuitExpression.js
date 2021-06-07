const Node = require('../Node');

let LogicalORExpression = require('./LogicalORExpression');

// ShortCircuitExpression[In, Yield, Await] :
//    LogicalORExpression[?In, ?Yield, ?Await]
//    CoalesceExpression[?In, ?Yield, ?Await]
let ShortCircuitExpression = (In, Yield, Await) => (given) => {
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'BinaryExpression':
    case 'ObjectExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = LogicalORExpression(In, Yield, Await)(given);
      return new Node('ShortCircuitExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ ShortCircuitExpression`);
  }
  Node.TODO('ShortCircuitExpression');
}

module.exports = ShortCircuitExpression;
