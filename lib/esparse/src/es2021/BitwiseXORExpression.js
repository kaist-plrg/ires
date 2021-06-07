const Node = require('../Node');

let BitwiseANDExpression = require('./BitwiseANDExpression');

// BitwiseXORExpression[In, Yield, Await] :
//    BitwiseANDExpression[?In, ?Yield, ?Await]
//    BitwiseXORExpression[?In, ?Yield, ?Await] ^ BitwiseANDExpression[?In, ?Yield, ?Await]
let BitwiseXORExpression = (In, Yield, Await) => (given) => {
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = BitwiseANDExpression(In, Yield, Await)(given);
      return new Node('BitwiseXORExpression', given, 0, [expr], params);
    }
    default:
      Node.TODO(`${type} @ BitwiseXORExpression`);
  }
  Node.TODO('BitwiseXORExpression');
}

module.exports = BitwiseXORExpression;
