const Node = require('../Node');

let BitwiseXORExpression = require('./BitwiseXORExpression');

// BitwiseORExpression[In, Yield, Await] :
//    BitwiseXORExpression[?In, ?Yield, ?Await]
//    BitwiseORExpression[?In, ?Yield, ?Await] | BitwiseXORExpression[?In, ?Yield, ?Await]
let BitwiseORExpression = (In, Yield, Await) => (given) => {
  let params = [In, Yield, Await];
  let { type } = given;
  switch (type) {
    case 'UnaryExpression':
    case 'ObjectExpression':
    case 'Identifier':
    case 'Literal': {
      let expr = BitwiseXORExpression(In, Yield, Await)(given);
      return new Node('BitwiseORExpression', given, 0, [expr], params);
    }
    case 'BinaryExpression': {
      let { index, children } = Node.getBinary(given, ['|'],
        BitwiseORExpression(In, Yield, Await),
        BitwiseXORExpression(In, Yield, Await),
      );
      return new Node('BitwiseORExpression', given, index, children, params);
    }
    default:
      Node.TODO(`${type} @ BitwiseORExpression`);
  }
  Node.TODO('BitwiseORExpression');
}

module.exports = BitwiseORExpression;
